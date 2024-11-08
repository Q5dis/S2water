package com.olcp2.s2water__bw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

public class StepCounterManager extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // fragment_step_counter_manager 레이아웃을 Inflate하여 view로 설정
        View view = inflater.inflate(R.layout.fragment_step_counter_manager, container, false);

        // 버튼을 view에서 찾아서 클릭 리스너를 설정
        Button createStepCounterButton = view.findViewById(R.id.createStepCounter);
        createStepCounterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity의 navigateToStepCount 메서드를 호출하여 StepCounter 프래그먼트로 이동
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).navigateToStepCount();
                }
            }
        });

        return view; // 생성된 뷰 반환
    }
}
