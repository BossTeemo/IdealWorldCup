package com.example.idealworldcup;

import android.os.Bundle;           //각 데이터를 다른 fragment로 넘길때 사용
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.util.Log;


public class ChoiceFragment extends Fragment {

    private List<JobData> jobDataList = new ArrayList<>(JobDataManager.getJobList());
    private List<JobData> currentSelection;
    private TextView textViewJob1;
    private TextView textViewJob2;
    private Button buttonSelectJob1;
    private Button buttonSelectJob2;
    private int stage = 8;  // 시작은 8강

    private void showRoundStartAnimation(String roundInfo) {
        Intent intent = new Intent(getActivity(), RoundNotationActivity.class);
        intent.putExtra("ROUND_INFO", roundInfo);
        startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choice, container, false);


        currentSelection = new ArrayList<>();       //2개의 리스트 저장

        textViewJob1 = view.findViewById(R.id.textView_job1);
        textViewJob2 = view.findViewById(R.id.textView_job2);
        buttonSelectJob1 = view.findViewById(R.id.button_selectJob1);
        buttonSelectJob2 = view.findViewById(R.id.button_selectJob2);

        updateJobsDisplay();

        buttonSelectJob1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handleSelection(0, textViewJob1);
            }
        });

        buttonSelectJob2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handleSelection(1, textViewJob2);
            }
        });
            return view;
    }

    private void updateJobsDisplay() {
        if (jobDataList.size() < 2) return;  // 리스트에 최소 2개의 요소가 필요

        currentSelection.clear();

        currentSelection.add(jobDataList.remove(0));
        currentSelection.add(jobDataList.remove(0));

        textViewJob1.setText(currentSelection.get(0).getJobName());
        textViewJob2.setText(currentSelection.get(1).getJobName());
    }


    private void handleSelection(int selectedIndex, TextView selectedTextView) {
        //선택된 TextView에 애니메이션 적용
        Animation growAndCenterAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.grow_and_center);
        selectedTextView.startAnimation(growAndCenterAnimation);

        //애니메이션 완료 후 로직
        growAndCenterAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //  애니메이션 시작 시 필요한 로직
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 가운데에서 1초간 유지하는 애니메이션 실행
                Animation holdAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.hold);
                selectedTextView.startAnimation(holdAnimation);

                // 1초 후 로직 실행
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // TextView의 속성을 원래대로 되돌림
                        selectedTextView.setScaleX(1.0f);
                        selectedTextView.setScaleY(1.0f);
                        selectedTextView.setTranslationX(0.0f);
                        selectedTextView.setTranslationY(0.0f);

                        // 선택 처리 및 다음 선택 화면으로 넘어가기
                        processSelection(selectedIndex);
                    }
                }, 1000); // 1초 대기
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //  애니메이션 반복 시 필요한 로직
            }
        });
    }

    private void processSelection(int selectedIndex){
        JobData selectedJob = currentSelection.get(selectedIndex);
        jobDataList.add(selectedJob);

        switch (stage) {
            case 8:
                if (jobDataList.size() == 4) {
                    stage = 4;
                    Collections.shuffle(jobDataList);
                    //4라운드 시작 알림 애니메이션
                    showRoundStartAnimation("4라운드");
                }
                break;
            case 4:
                if (jobDataList.size() == 2) {
                    stage = 2;  // 결승
                    //결승 시작 알림 애니메이션
                    showRoundStartAnimation("결승전");
                }
                break;
            case 2:
                //결승 선택 처리
                showFinalResultFragment(selectedJob);
                break;  // 로직 종료
        }
        //다음 선택 화면으로 넘어가기
        updateJobsDisplay();

    }

    private void showFinalResultFragment(JobData selectedJob) {
        FinalResultFragment finalResultFragment = new FinalResultFragment();

        Bundle args = new Bundle();
        args.putString("selectedJobName", selectedJob.getJobName());
        args.putString("selectedJobDescription", selectedJob.getJobDescription());
        args.putStringArrayList("selectedJobSubjects", new ArrayList<>(selectedJob.getSubjects()));  // 과목은 리스트
        finalResultFragment.setArguments(args);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, finalResultFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        Log.d("ChoiceFragment", "FinalResultFragment has been added.");
    }

}
