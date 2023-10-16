package com.example.idealworldcup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class ChoiceFragment extends Fragment {

    private List<JobData> jobDataList = JobDataManager.getJobList();
    private TextView textViewJob1;
    private TextView textViewJob2;
    private Button buttonSelectJob1;
    private Button buttonSelectJob2;
    private int currentIndex = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choice, container, false);

        textViewJob1 = view.findViewById(R.id.textView_job1);
        textViewJob2 = view.findViewById(R.id.textView_job2);
        buttonSelectJob1 = view.findViewById(R.id.button_selectJob1);
        buttonSelectJob2 = view.findViewById(R.id.button_selectJob2);

        updateJobsDisplay();

        buttonSelectJob1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫 번째 직업 선택 처리
                proceedToNextChoice();
            }
        });

        buttonSelectJob2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 두 번째 직업 선택 처리
                proceedToNextChoice();
            }
        });

        return view;
    }

    private void updateJobsDisplay() {
        if (currentIndex < jobDataList.size()) {
            textViewJob1.setText(jobDataList.get(currentIndex).getJobName());
            currentIndex++;
        }
        if (currentIndex < jobDataList.size()) {
            textViewJob2.setText(jobDataList.get(currentIndex).getJobName());
            currentIndex++;
        }
    }

    private void proceedToNextChoice() {
        if (currentIndex < jobDataList.size()) {
            updateJobsDisplay();
        } else {
            // 모든 선택이 완료되면 결과 화면으로 이동하는 코드를 여기에 추가합니다.
        }
    }
}
