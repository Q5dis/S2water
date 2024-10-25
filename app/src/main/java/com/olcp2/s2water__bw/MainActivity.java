package com.olcp2.s2water__bw;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.olcp2.s2water__bw.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set the default fragment
        if (savedInstanceState == null) {
            replaceFragment(new WaterCheckFragment());
        }

        // Set up BottomNavigationView listener
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new WaterCheckFragment());
                    return true;
                case R.id.walk:
                    replaceFragment(new StepCounterManager());
                    return true;
                case R.id.stats:
                    replaceFragment(new StatTrackingFragment());
                    return true;
                case R.id.setting:
                    replaceFragment(new SettingFragment());
                    return true;
                default:
                    return false;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


    public void navigateToStepCount() {
        replaceFragment(new StepCounter());
    }



}
