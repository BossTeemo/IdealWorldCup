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

        if (getArguments() != null) {
            textViewSelectedJobName.setText(getArguments().getString("selectedJobName"));
            textView_selectedJobDescription.setText(getArguments().getString("selectedJobDescription"));
        }



        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getActivity() != null && getActivity().getSupportFragmentManager() != null) {
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });

        return view;
    }

}
