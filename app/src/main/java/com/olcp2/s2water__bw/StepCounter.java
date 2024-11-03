package com.olcp2.s2water__bw;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class StepCounter extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    private ProgressBar stepProgressBar;
    private TextView stepCountText, textViewGoalStep;
    private Button startButton;
    private int stepGoal = 10000;
    private int stepCount = 0;
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_step_counter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sensorManager = (SensorManager) requireActivity().getSystemService(getContext().SENSOR_SERVICE);
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (stepCounterSensor == null) {
            stepCountText.setText("이 기기는 만보기 센서를 지원하지 않습니다.");
            return;
        }

        stepProgressBar = view.findViewById(R.id.StepCounter);
        stepCountText = view.findViewById(R.id.stepCountText);
        textViewGoalStep = view.findViewById(R.id.textView_goal_step);
        startButton = view.findViewById(R.id.StartButton);

        stepProgressBar.setMax(stepGoal);

        startButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.BODY_SENSORS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.BODY_SENSORS}, PERMISSION_REQUEST_CODE);
            } else {
                startCountingSteps();
            }
        });
    }

    private void startCountingSteps() {
        stepCount = 0;
        sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_UI);
        updateStepCountDisplay();
    }

    private void updateStepCountDisplay() {
        stepCountText.setText(String.valueOf(stepCount));
        stepProgressBar.setProgress(stepCount);
        textViewGoalStep.setText((stepGoal - stepCount) + " 걸음");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            if (stepCount == 0) {
                stepCount = (int) event.values[0];
            }
            int currentStepCount = (int) event.values[0];
            int stepsTaken = currentStepCount - stepCount;
            stepCount += stepsTaken;
            updateStepCountDisplay();
            if (stepCount >= stepGoal) {
                sensorManager.unregisterListener(this);
                Toast.makeText(getContext(), "목표 걸음 수에 도달했습니다!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCountingSteps();
            } else {
                Toast.makeText(getContext(), "권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
