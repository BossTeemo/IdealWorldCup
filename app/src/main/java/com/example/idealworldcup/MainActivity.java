package com.example.idealworldcup;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // activity_main.xml 레이아웃을 설정합니다.

        // 시작 버튼에 클릭 리스너 설정 (이상형월드컵 시작 로직을 여기에 추가할 수 있습니다.)
        Button startButton = findViewById(R.id.button_start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 이상형월드컵 시작 로직
                // 예: 8강 선택 프래그먼트를 표시하도록 합니다.
            }
        });
    }
}
