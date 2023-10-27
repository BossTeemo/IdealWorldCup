package com.example.idealworldcup;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.content.Intent;// Intent를 사용하기 위해 추가함

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
                //새로운 액티비티 (ChoiceActivity)로 전환
                Intent intent = new Intent(MainActivity.this, ChoiceActivity.class);
                startActivity(intent);

                //choice_screen.xml로 넘어가는 동작으로 수정하기위해 이 다음부분을 주석처리함.
//                ChoiceFragment choiceFragment = new ChoiceFragment();
//
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container, choiceFragment);  // R.id.fragment_container는 activity_main.xml에 있는 FrameLayout의 ID여야 합니다.
//                transaction.addToBackStack(null); // 이를 통해 뒤로 가기 버튼을 눌렀을 때 이전 상태로 돌아갈 수 있습니다.
//                transaction.commit();
            }
        });
    }
}
