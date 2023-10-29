package com.example.idealworldcup;


import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class RoundNotationActivity extends AppCompatActivity {

    private TextView roundTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_notation);

        roundTextView = findViewById(R.id.roundTextView);

        // 인텐트에서 라운드 정보 가져오기
        String roundInfo = getIntent().getStringExtra("ROUND_INFO");
        roundTextView.setText(roundInfo);


        //애니메이션 적용
        Animation slideIn = AnimationUtils.loadAnimation(this,R.anim.slide_in_right_to_left);
        Animation slideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out_left_to_right);

        roundTextView.startAnimation(slideIn);

        // 일정 시간 후 액티비티 종료
        new Handler().postDelayed(() ->{
            roundTextView.startAnimation(slideOut);
            finish();
        }, 2000); // 2초 후 자동 종료
    }
}
