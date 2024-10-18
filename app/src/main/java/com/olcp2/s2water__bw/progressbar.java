package com.olcp2.s2water__bw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class progressbar extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView textViewTotalAmount;
    private Button buttonAdd;
    private Button buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_progressbar);

        seekBar = findViewById(R.id.seekBar);
        textViewTotalAmount = findViewById(R.id.textView_totalAmount);
        buttonAdd = findViewById(R.id.button_add);
        buttonReset = findViewById(R.id.button_reset);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewTotalAmount.setText(progress + " ml");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        buttonAdd.setOnClickListener(v -> {
            int selectedAmount = seekBar.getProgress();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("addedWater", selectedAmount);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        buttonReset.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("resetValues", true);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
