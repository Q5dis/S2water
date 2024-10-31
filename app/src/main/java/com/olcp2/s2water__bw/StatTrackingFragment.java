package com.olcp2.s2water__bw;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import androidx.fragment.app.Fragment;
import java.util.Random;

public class StatTrackingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stat_tracking, container, false);

        ImageView imageViewSprout = rootView.findViewById(R.id.imageViewSprout);
        imageViewSprout.setBackgroundResource(R.drawable.sprout_anim);
        AnimationDrawable sproutAnimation = (AnimationDrawable) imageViewSprout.getBackground();
        sproutAnimation.start();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            sproutAnimation.stop();
            imageViewSprout.setBackgroundResource(R.drawable.sprout_anim); // 백그라운드 리셋
            sproutAnimation.start(); // 다시 애니메이션 시작
        }, (sproutAnimation.getNumberOfFrames() * 300) + 100);



        TableLayout myGardenTable = rootView.findViewById(R.id.MyGardenTable);
        String[] colors = {
                "#2643FF", "#6B8BF6", "#ADCAFF", "#DEE4EF",
        };

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            TableRow tableRow = new TableRow(getActivity());
            tableRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            ));

            for (int j = 0; j < 7; j++) {
                View view = new View(getActivity());
                int size = 100;
                TableRow.LayoutParams params = new TableRow.LayoutParams(size, size);
                params.setMargins(10, 10, 10, 10);
                view.setLayoutParams(params);

                String randomColor = colors[random.nextInt(colors.length)];
                view.setBackgroundColor(Color.parseColor(randomColor));

                int id = View.generateViewId();
                view.setId(id);

                tableRow.addView(view);
            }

            myGardenTable.addView(tableRow);
        }

        return rootView;
    }
}
