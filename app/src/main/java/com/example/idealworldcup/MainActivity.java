package com.example.idealworldcup;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

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

                ChoiceFragment choiceFragment = new ChoiceFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, choiceFragment);  // R.id.fragment_container는 activity_main.xml에 있는 FrameLayout의 ID여야 합니다.
                transaction.addToBackStack(null); // 이를 통해 뒤로 가기 버튼을 눌렀을 때 이전 상태로 돌아갈 수 있습니다.
                transaction.commit();
            }
        });
    }
}
