package com.example.idealworldcup;

import android.os.Bundle;           //각 데이터를 다른 fragment로 넘길때 사용
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChoiceFragment extends Fragment {

    private List<JobData> jobDataList = new ArrayList<>(JobDataManager.getJobList());
    private List<JobData> currentSelection;
    private TextView textViewJob1;
    private TextView textViewJob2;
    private Button buttonSelectJob1;
    private Button buttonSelectJob2;
    private int stage = 8;  // 시작은 8강

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
                handleSelection(0);
            }
        });

        buttonSelectJob2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSelection(1);
            }
        });
            return view;
    }

    private void updateJobsDisplay() {
        if (jobDataList.isEmpty()) return;

        currentSelection.clear();

        currentSelection.add(jobDataList.remove(0));   //기존 리스트의 객체는 제거하며 리턴
        currentSelection.add(jobDataList.remove(0));    //제거하고 나머지 원소 땡겨짐

        textViewJob1.setText(currentSelection.get(0).getJobName());
        textViewJob2.setText(currentSelection.get(1).getJobName());
    }

    private void handleSelection(int selectedIndex) {
        switch (stage) {
            case 8:
                jobDataList.add(currentSelection.get(selectedIndex));  // 선택한 직업을 다음 라운드로 보냅니다.
                showConfirmationFragment(currentSelection.get(selectedIndex));
                if (jobDataList.size() == 4) {
                    stage = 4;
                    Collections.shuffle(jobDataList);
                    Toast.makeText(getActivity(), "4강 시작합니다.", Toast.LENGTH_SHORT).show();
                }
                break;
            case 4:
                jobDataList.add(currentSelection.get(selectedIndex));
                showConfirmationFragment(currentSelection.get(selectedIndex));
                if (jobDataList.size() == 2) {
                    stage = 2;  // 결승
                    Toast.makeText(getActivity(), "결승 시작합니다.", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                jobDataList.add(currentSelection.get(selectedIndex));
                showFinalResultFragment(currentSelection.get(selectedIndex));  // 선택한 직업으로 결과 화면을 표시
                break;  // 로직 종료
        }
    }


    private void showConfirmationFragment(JobData selectedJob) {
        ConfirmationFragment confirmationFragment = new ConfirmationFragment();

        Bundle args = new Bundle();
        args.putString("selectedJobName", selectedJob.getJobName());
        args.putString("selectedJobDescription", selectedJob.getJobDescription());
        confirmationFragment.setArguments(args);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, confirmationFragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
    }

}
