package com.olcp2.s2water__bw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import android.widget.Button;

public class StepCountFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step_count, container, false);

        // Use the inflated view to find the button
        Button button = view.findViewById(R.id.createStepCount);
        button.setOnClickListener(v -> {
            // Call the method in the hosting activity to navigate to StepCount
            ((MainActivity) getActivity()).navigateToStepCount();
        });

        return view; // Return the inflated view
    }
}
