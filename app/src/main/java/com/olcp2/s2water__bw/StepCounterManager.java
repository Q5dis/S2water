package com.olcp2.s2water__bw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class StepCounterManager extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_counter_manager, container, false);

        // 버튼을 찾아서 클릭 리스너 추가
        Button button = view.findViewById(R.id.createStepCounter);
        button.setOnClickListener(v -> {
            // 현재 프래그먼트가 이미 화면에 표시 중이라면 교체하지 않도록 처리
            if (getFragmentManager() != null) {
                Fragment stepCounterFragment = new StepCounter();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                // 기존 프래그먼트를 교체하고 백 스택에 추가
                transaction.replace(R.id.StepcountManager, stepCounterFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}
