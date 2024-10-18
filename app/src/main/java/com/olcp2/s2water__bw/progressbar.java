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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_progressbar); // XML 레이아웃 파일

        seekBar = findViewById(R.id.seekBar);
        textViewTotalAmount = findViewById(R.id.textView_totalAmount);
        buttonAdd = findViewById(R.id.button_add);

        // SeekBar의 값이 변경될 때마다 호출되는 리스너
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // SeekBar에서 선택한 값을 텍스트로 업데이트
                textViewTotalAmount.setText(progress + " ml");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 터치 시작할 때
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 터치 끝났을 때
            }
        });

        buttonAdd.setOnClickListener(v -> {
            int selectedAmount = seekBar.getProgress();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("addedWater", selectedAmount); // 추가할 양 반환
            setResult(RESULT_OK, resultIntent);
            finish(); // 액티비티 종료
        });
    }
}
