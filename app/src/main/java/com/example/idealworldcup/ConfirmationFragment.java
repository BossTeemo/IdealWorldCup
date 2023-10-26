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

public class ConfirmationFragment extends Fragment {

    private TextView textViewSelectedJobName;
    private TextView textView_selectedJobDescription;
    private Button buttonNext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirmation, container, false);

        textViewSelectedJobName = view.findViewById(R.id.textView_selectedJobName);
        textView_selectedJobDescription = view.findViewById(R.id.textView_selectedJobDescription);
        buttonNext = view.findViewById(R.id.button_next);

        // 여기서 선택한 직업을 표시할 수 있습니다.
        // 예: textViewSelectedJob.setText(selectedJobName);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사용자가 선택을 확인했을 때의 처리를 여기에 작성합니다.
                // 예: 다음 선택 화면으로 이동하거나 결과 화면을 표시합니다.
            }
        });

        return view;
    }

    // 선택한 직업을 설정하는 메서드
    public void setSelectedJob(String jobName, String jobDescription) {
        if (textView_selectedJobDescription != null) {
            textViewSelectedJobName.setText(jobName);
        }
        if (textView_selectedJobDescription != null){
            textView_selectedJobDescription.setText(jobDescription);
        }
    }
}
