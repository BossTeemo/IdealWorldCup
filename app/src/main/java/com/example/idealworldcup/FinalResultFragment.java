package com.example.idealworldcup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

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

        if (getArguments() != null) {
            textViewFinalJobName.setText(getArguments().getString("selectedJobName"));
            textViewFinalJobDescription.setText(getArguments().getString("selectedJobDescription"));

            ArrayList<String> jobSubjects = getArguments().getStringArrayList("selectedJobSubjects");
            if (jobSubjects != null) {
                textViewFinalSubjects.setText(String.join(",", jobSubjects));
            }
        }


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
