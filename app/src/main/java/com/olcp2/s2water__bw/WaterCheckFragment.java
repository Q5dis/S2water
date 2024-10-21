package com.olcp2.s2water__bw;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.widget.TextView;

public class WaterCheckFragment extends Fragment {

    private View waterFill;
    private int totalAmount = 0; // 초기 물 소비량
    private final int goalAmount = 2000; // 목표 물 소비량
    private final int cupHeight = 310; // 컵 높이
    private TextView totalAmountTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_water_check, container, false);

        waterFill = view.findViewById(R.id.waterFill);
        FrameLayout cupContainer = view.findViewById(R.id.cupContainer);
        totalAmountTextView = view.findViewById(R.id.textView_totalAmount); // 텍스트뷰 초기화

        updateWaterFill();

        cupContainer.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), progressbar.class);
            startActivityForResult(intent, 1);
        });

        return view;
    }

    private void updateWaterFill() {
        int waterHeight = (int) (totalAmount * 0.4);
        ViewGroup.LayoutParams params = waterFill.getLayoutParams();
        params.height = waterHeight;
        waterFill.setLayoutParams(params);
        totalAmountTextView.setText(totalAmount + " ml");
    }


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
            }
        }
    }

    private void updateTotalAmount(int amount) {
        totalAmount += amount;
        updateWaterFill(); // 물 양 업데이트
    }

    private void resetValues() {
        totalAmount = 0;
        updateWaterFill(); // 물 양 초기화
    }
}
