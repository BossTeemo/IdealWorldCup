package com.example.idealworldcup;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
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
    private ImageView imageViewFinalJobImage;
    private Button restartButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_final_result, container, false);

        textViewFinalJobName = view.findViewById(R.id.textView_finalJobName);
        textViewFinalJobDescription = view.findViewById(R.id.textView_finalJobDescription);
        textViewFinalSubjects = view.findViewById(R.id.textView_finalSubjects);
        imageViewFinalJobImage = view.findViewById(R.id.imageView_finalJobImage);
        restartButton = view.findViewById(R.id.button_restart);

        Animation slideInAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right_to_left);

        textViewFinalJobName.startAnimation(slideInAnimation);
        textViewFinalJobDescription.startAnimation(slideInAnimation);
        textViewFinalSubjects.startAnimation(slideInAnimation);

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 다시하기 버튼을 클릭했을 때 실행되는 코드
                restartApp();
            }
        });

        if (getArguments() != null) {
            textViewFinalJobName.setText(getArguments().getString("selectedJobName"));
            textViewFinalJobDescription.setText(getArguments().getString("selectedJobDescription"));

            ArrayList<String> jobSubjects = getArguments().getStringArrayList("selectedJobSubjects");
            if (jobSubjects != null) {
                textViewFinalSubjects.setText(String.join(",", jobSubjects));
            }

            int imageResourceId = JobDataManager.getImageResourceIdForJob(getArguments().getString("selectedJobName"));
            imageViewFinalJobImage.setImageResource(imageResourceId);

        } else {
            Log.d("FinalResultFragment", "getArguments() is null");
        }

        Log.d("FinalResultFragment", "onCreateView executed");

        return view;
    }

    private void restartApp() {
        // 현재 액티비티를 종료합니다.
        getActivity().finish();

        // 루트 액티비티를 다시 시작합니다.
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    public void setFinalResult(JobData jobData) {
        if (textViewFinalJobName != null && textViewFinalSubjects != null && imageViewFinalJobImage != null) {
            textViewFinalJobName.setText(jobData.getJobName());
            textViewFinalJobDescription.setText(jobData.getJobDescription());
            textViewFinalSubjects.setText(String.join(",", jobData.getSubjects()));
            imageViewFinalJobImage.setImageResource(jobData.getImageResourceId());
        } else {
            Log.d("FinalResultFragment", "Views are null");
        }
    }
}
