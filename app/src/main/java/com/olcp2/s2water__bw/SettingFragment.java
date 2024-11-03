package com.olcp2.s2water__bw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SettingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        Button button = view.findViewById(R.id.dev_setting);
        button.setOnClickListener(v -> navigateToDeveloperSetting());

        return view;
    }

    private void navigateToDeveloperSetting() {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.setting_fragment, new DeveloperSetting());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
