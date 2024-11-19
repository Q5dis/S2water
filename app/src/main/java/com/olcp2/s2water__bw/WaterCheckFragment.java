package com.olcp2.s2water__bw;

import static android.app.Activity.RESULT_OK;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.models.Size;
import nl.dionsegijn.konfetti.xml.KonfettiView;

public class WaterCheckFragment extends Fragment {

    private View waterFill;
    private int totalAmount = 0;
    private final int goalAmount = 2000;
    private TextView totalAmountTextView;
    private KonfettiView konfettiView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_water_check, container, false);

        waterFill = view.findViewById(R.id.waterFill);
        FrameLayout cupContainer = view.findViewById(R.id.cupContainer);
        totalAmountTextView = view.findViewById(R.id.textView_totalAmount);
        konfettiView = view.findViewById(R.id.konfettiView);

        Button sipButton = view.findViewById(R.id.sip);
        Button cupButton = view.findViewById(R.id.cup);
        Button bottleButton = view.findViewById(R.id.bottle);

        sipButton.setOnClickListener(v -> updateTotalAmount(20));
        cupButton.setOnClickListener(v -> updateTotalAmount(180));
        bottleButton.setOnClickListener(v -> updateTotalAmount(500));

        updateWaterFill();

        cupContainer.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), progressbar.class);
            startActivityForResult(intent, 1);
        });

        return view;
    }

    private void updateWaterFill() {
        int targetHeight = (int) (totalAmount * 0.4);
        int currentHeight = waterFill.getLayoutParams().height;

        ValueAnimator animator = ValueAnimator.ofInt(currentHeight, targetHeight);
        animator.setDuration(1000);
        animator.addUpdateListener(animation -> {
            int animatedHeight = (int) animation.getAnimatedValue();
            ViewGroup.LayoutParams params = waterFill.getLayoutParams();
            params.height = animatedHeight;
            waterFill.setLayoutParams(params);
        });
        animator.start();

        totalAmountTextView.setText(totalAmount + " ml");
    }

    int isGoalAchieved = 0;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                if (data.getBooleanExtra("resetValues", false)) {
                    resetValues();
                } else {
                    int addedWater = data.getIntExtra("addedWater", 0);
                    updateTotalAmount(addedWater);
                }

                int deletedWater = data.getIntExtra("deletedWater", 0);
                if (deletedWater > 0) {
                    updateTotalAmount(-deletedWater);
                }
            }
        }
    }

    private void updateTotalAmount(int amount) {
        totalAmount += amount;
        updateWaterFill();

        if (totalAmount >= goalAmount && isGoalAchieved == 0) {
            konfettiView.setVisibility(View.VISIBLE); // KonfettiView를 보이게 설정
            displayCelebration();
            isGoalAchieved = 1;

            EmitterConfig emitterConfig = new Emitter(5L, TimeUnit.SECONDS).perSecond(50);

            Party party = new PartyFactory(emitterConfig)
                    .angle(90) // 수평으로 하늘에서 내리도록 설정
                    .spread(360) // 퍼짐을 주어 벚꽃비처럼 확산되게 설정
                    .shapes(Arrays.asList(Shape.Circle.INSTANCE, Shape.Square.INSTANCE))
                    .colors(Arrays.asList(0x2643FF, 0xD3D3D3, 0xA7C8FF)) // 색상은 그대로 유지
                    .setSpeedBetween(30f, 50f) // 빠르게 떨어지도록 속도 설정
                    .timeToLive(3000L)
                    .sizes(new Size(12, 5f, 0.2f))
                    .position(new Position.Relative(0.5, 0)) // 화면 상단 중앙에서 시작
                    .build();



            konfettiView.start(party);
        } else if (totalAmount < goalAmount) {
            isGoalAchieved = 0;
            konfettiView.setVisibility(View.GONE); // 목표 미달 시 숨김
        }
    }



    private void resetValues() {
        totalAmount = 0;
        updateWaterFill();
    }

    private void displayCelebration() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("축하합니다!")
                .setMessage("오늘치 목표량을 달성했어요!")
                .setPositiveButton("확인", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
