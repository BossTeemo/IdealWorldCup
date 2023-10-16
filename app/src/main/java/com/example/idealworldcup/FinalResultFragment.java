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

    private TextView textViewFinalJob;
    private TextView textViewSubjects;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_final_result, container, false);

        textViewFinalJob = view.findViewById(R.id.textView_finalJob);
        textViewSubjects = view.findViewById(R.id.textView_subjects);

        // 예시: 선택된 직업과 관련 과목을 표시
        // 이 부분은 실제 로직에 따라 수정되어야 합니다.
        // textViewFinalJob.setText(selectedFinalJob);
        // textViewSubjects.setText(relatedSubjects);

        return view;
    }

    // 최종 결과를 설정하는 메서드
    public void setFinalResult(JobData jobData) {
        if (textViewFinalJob != null && textViewSubjects != null) {
            textViewFinalJob.setText(jobData.getJobName());
            textViewSubjects.setText(String.join(", ", jobData.getSubjects()));
        }
    }
}
