package com.example.idealworldcup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class ChoiceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_screen); // choice_screen.xml 레이아웃을 사용함.

        ChoiceFragment choiceFragment = new ChoiceFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, choiceFragment);
        transaction.addToBackStack(null);// 뒤로가기 버튼 누르면 이전 상태로 돌아감.
        transaction.commit();

    }
}
