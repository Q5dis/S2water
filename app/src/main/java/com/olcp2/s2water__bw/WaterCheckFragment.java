package com.olcp2.s2water__bw;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class WaterCheckFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_water_check, container, false);

        View waterProgress = view.findViewById(R.id.waterProgress);
        waterProgress.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), progressbar.class);
            startActivity(intent);
        });

        return view;
    }
}
