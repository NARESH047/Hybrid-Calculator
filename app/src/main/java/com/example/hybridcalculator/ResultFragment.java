package com.example.hybridcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class
ResultFragment extends Fragment {
    TextView result;
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_fragment, container, false);
        result = view.findViewById(R.id.resultTextView);
        return view;
    }

    public void updateResult(CharSequence newResult) {
        result.setText(newResult);
    }

}
