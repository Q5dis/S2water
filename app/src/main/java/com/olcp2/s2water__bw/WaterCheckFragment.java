package com.olcp2.s2water__bw;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WaterCheckFragment extends Fragment {

    private TextView textViewTotalAmount;
    private TextView textViewGoalLeft;
    private int totalAmount = 0; // 초기 물 소비량
    private final int goalAmount = 1000; // 목표 물 소비량

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_water_check, container, false);

        textViewTotalAmount = view.findViewById(R.id.textView_totalAmount);
        textViewGoalLeft = view.findViewById(R.id.textView_goalLeft);

        textViewTotalAmount.setText("순수한 물을\n총 " + totalAmount + "ml\n마셨어요!");
        updateGoalLeft();

        View waterProgress = view.findViewById(R.id.waterProgress);
        waterProgress.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), progressbar.class);
            startActivityForResult(intent, 1);
        });

        return view;
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
        textViewTotalAmount.setText("순수한 물을\n총 " + totalAmount + "ml\n마셨어요!");
        updateGoalLeft();
    }

    private void updateGoalLeft() {
        int goalLeft = goalAmount - totalAmount;
        textViewGoalLeft.setText("목표 음수량 도달까지 " + goalLeft + "ml 남았어요!");
    }

    private void resetValues() {
        totalAmount = 0;
        textViewTotalAmount.setText("순수한 물을\n총 " + totalAmount + "ml\n마셨어요!");
        updateGoalLeft();
    }
}
