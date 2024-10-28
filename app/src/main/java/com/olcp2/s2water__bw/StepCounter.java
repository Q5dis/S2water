package com.olcp2.s2water__bw;

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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StepCounter extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    private ProgressBar stepProgressBar;
    private TextView stepCountText, textViewGoalStep;
    private Button startButton;
    private int stepGoal = 10000;
    private int stepCount = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_step_counter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sensorManager = (SensorManager) requireActivity().getSystemService(getContext().SENSOR_SERVICE);
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        stepProgressBar = view.findViewById(R.id.StepCounter);
        stepCountText = view.findViewById(R.id.stepCountText);
        textViewGoalStep = view.findViewById(R.id.textView_goal_step);
        startButton = view.findViewById(R.id.StartButton);

        stepProgressBar.setMax(stepGoal);

        startButton.setOnClickListener(v -> startCountingSteps());
    }

    private void startCountingSteps() {
        stepCount = 0;
        updateStepCountDisplay();
        sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_UI);
    }

    private void updateStepCountDisplay() {
        stepCountText.setText(String.valueOf(stepCount));
        stepProgressBar.setProgress(stepCount);
        textViewGoalStep.setText((stepGoal - stepCount) + " 걸음");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            stepCount = (int) event.values[0];
            updateStepCountDisplay();
            if (stepCount >= stepGoal) {
                sensorManager.unregisterListener(this);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No need to handle accuracy changes for step counter
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
