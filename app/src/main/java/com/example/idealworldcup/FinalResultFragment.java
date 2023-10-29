package com.example.idealworldcup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import android.util.Log;

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

        // 애니메이션 리소스를 로드합니다.
        Animation slideInAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right_to_left);

        // 애니메이션을 시작합니다.
        textViewFinalJobName.startAnimation(slideInAnimation);
        textViewFinalJobDescription.startAnimation(slideInAnimation);
        textViewFinalSubjects.startAnimation(slideInAnimation);

        if (getArguments() != null) {
            textViewFinalJobName.setText(getArguments().getString("selectedJobName"));
            textViewFinalJobDescription.setText(getArguments().getString("selectedJobDescription"));

            ArrayList<String> jobSubjects = getArguments().getStringArrayList("selectedJobSubjects");
            if (jobSubjects != null) {
                textViewFinalSubjects.setText(String.join(",", jobSubjects));
            }
        } else {
            Log.d("FinalResultFragment", "getArguments() is null"); // 로그 추가
        }

        Log.d("FinalResultFragment", "onCreateView executed"); // 로그 추가

        return view;
    }

    // 최종 결과를 설정하는 메서드
    public void setFinalResult(JobData jobData) {
        if (textViewFinalJobName != null && textViewFinalSubjects != null) {
            textViewFinalJobName.setText(jobData.getJobName());
            textViewFinalJobDescription.setText(jobData.getJobDescription());
            textViewFinalSubjects.setText(String.join(",", jobData.getSubjects()));
        } else {
            Log.d("FinalResultFragment", "Text views are null"); // 로그 추가
        }
    }
}