package com.olcp2.s2water__bw;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class StepCounter extends Fragment {

    private ProgressBar stepProgressBar;
    private TextView stepCountTextView, goalStepTextView;  // 목표 걸음 수를 위한 TextView 추가
    private Button startButton;
    private int targetSteps = 10000;  // 목표 걸음 수 (10,000걸음으로 설정)
    private int currentSteps = 0;    // 현재 걸음 수

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_step_counter, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 초기화
        stepProgressBar = view.findViewById(R.id.StepCounter);  // ProgressBar
        stepCountTextView = view.findViewById(R.id.stepCountText);  // 현재 걸음 수를 표시하는 TextView
        goalStepTextView = view.findViewById(R.id.textView_goal_step);  // 목표 걸음 수를 표시하는 TextView
        startButton = view.findViewById(R.id.StartButton);  // 시작 버튼

        // 목표 걸음 수를 텍스트로 설정
        goalStepTextView.setText(targetSteps + " 걸음");

        // 시작 버튼 클릭 리스너
        startButton.setOnClickListener(v -> animateStepsToGoal());
    }

    private void animateStepsToGoal() {
        // ValueAnimator를 사용하여 걸음 수와 프로그레스바 애니메이션
        ValueAnimator animator = ValueAnimator.ofInt(currentSteps, targetSteps);
        animator.setDuration(2000);  // 애니메이션 시간 (2000ms = 2초)
        animator.addUpdateListener(animation -> {
            currentSteps = (int) animation.getAnimatedValue();  // 애니메이션의 현재 값
            stepProgressBar.setProgress(currentSteps);  // 프로그레스바의 진행 상태 업데이트
            stepCountTextView.setText(currentSteps + " 걸음");  // 숫자 텍스트 업데이트
        });
        animator.start();  // 애니메이션 시작
    }
}
