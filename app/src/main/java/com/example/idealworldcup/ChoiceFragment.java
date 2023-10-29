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
    private TextView textViewRoundInfo; // 라운드 정보 TextView
    private TextView textViewSelectionProgress; // 선택 진행 상황 TextView
    private int selectionCount = 1; // 선택 횟수 초기화

    private Button buttonSelectJob1;
    private Button buttonSelectJob2;
    private int stage = 8;  // 시작은 8강

    private void showRoundStartAnimation(String roundInfo, Runnable onAnimationEndCallback) {
        Intent intent = new Intent(getActivity(), RoundNotationActivity.class);
        intent.putExtra("ROUND_INFO", roundInfo);
        startActivity(intent);

        // 애니메이션이 끝난 후 콜백 실행
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (onAnimationEndCallback != null) {
                    onAnimationEndCallback.run();
                }
            }
        }, 2000); // 애니메이션 지속 시간에 맞춰서 시간을 설정해야 합니다.
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
        textViewRoundInfo = view.findViewById(R.id.textView_roundInfo);
        textViewSelectionProgress = view.findViewById(R.id.textView_selectionProgress);

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

    private void updateRoundDisplay() {
        String roundInfo = "";
        String selectionProgress = "";
        switch (stage) {
            case 8:
                roundInfo = "8강";
                selectionProgress = selectionCount + "/4";
                break;
            case 4:
                roundInfo = "4강";
                selectionProgress = selectionCount + "/2";
                break;
            case 2:
                roundInfo = "결승";
                selectionProgress = "1/1";
                break;
        }
        textViewRoundInfo.setText(roundInfo);
        textViewSelectionProgress.setText(selectionProgress);
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

        if (selectedIndex == 0) { // 왼쪽 TextView 선택됐을 때 grow_and_center_left 적용
            Animation growAndCenterAnimationLeft = AnimationUtils.loadAnimation(getActivity(), R.anim.grow_and_center_left);
            selectedTextView.startAnimation(growAndCenterAnimationLeft);

            //애니메이션 완료 후 로직
            growAndCenterAnimationLeft.setAnimationListener(new Animation.AnimationListener() {
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


        } else { // 오른쪽 TextView 선택됐을 때, 기존 애니메이션 적용
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

    }

    private void processSelection(int selectedIndex){
        JobData selectedJob = currentSelection.get(selectedIndex);
        jobDataList.add(selectedJob);

        // 라운드 변경을 먼저 검사
        if (stage == 8 && selectionCount == 4) { // 8강에서 4번째 선택 후
            showRoundStartAnimation("4강", new Runnable() {
                @Override
                public void run() {
                    stage = 4;
                    Collections.shuffle(jobDataList);
                    selectionCount = 1; // 선택 횟수를 1로 초기화
                    updateRoundDisplay();
                    updateJobsDisplay();
                }
            });
            return;
        } else if (stage == 4 && selectionCount == 2) { // 4강에서 2번째 선택 후
            showRoundStartAnimation("결승전", new Runnable() {
                @Override
                public void run() {
                    stage = 2;  // 결승
                    selectionCount = 1; // 선택 횟수를 1로 초기화
                    updateRoundDisplay();
                    updateJobsDisplay();
                }
            });
            return;
        }

        // 결승 라운드일 경우 처리
        if (stage == 2) {
            showFinalResultFragment(selectedJob);
            return;
        }

        // 그 외의 경우 선택 횟수 증가
        selectionCount++;

        // 다음 선택 화면으로 넘어가기
        updateRoundDisplay(); // 라운드 정보 업데이트
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