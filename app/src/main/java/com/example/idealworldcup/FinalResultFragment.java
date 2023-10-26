package com.example.idealworldcup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FinalResultFragment extends Fragment {

    private TextView textViewFinalJobName;
    private TextView textViewFinalJobDescription;
    private TextView textViewFinalSubjects;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_final_result, container, false);

        textViewFinalJobName = view.findViewById(R.id.textView_finalJobName);
        textViewFinalJobDescription = view.findViewById(R.id.textView_finalJobDescription);
        textViewFinalSubjects = view.findViewById(R.id.textView_finalSubjects);

        // 예시: 선택된 직업과 관련 과목을 표시
        // 이 부분은 실제 로직에 따라 수정되어야 합니다.
        // textViewFinalJobName.setText(selectedFinalJobName);
        // textViewFinalJobDescription.setText(selectedFinalJobDescription);
        // textViewFinalSubjects.setText(relatedSubjects);

        return view;
    }

    // 최종 결과를 설정하는 메서드
    public void setFinalResult(JobData jobData) {
        if (textViewFinalJobName != null && textViewFinalSubjects != null) {
            textViewFinalJobName.setText(jobData.getJobName());
            textViewFinalJobDescription.setText(jobData.getJobDescription());
            textViewFinalSubjects.setText(String.join(",", jobData.getSubjects()));
        }
    }
}
