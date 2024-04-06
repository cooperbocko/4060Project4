package edu.uga.cs.mobileproject4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import  androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends Fragment {
    private static final String DEBUG = "Quiz Fragment";
    private static final String ARG_QUESTION_NUMBER = "question_number";
    private int questionNumber;
    private TextView question;
    private RadioGroup continentsGroup;
    private RadioButton ans1, ans2, ans3, ans4;


    public QuizFragment() {
        // Required empty public constructor
    }


    public static QuizFragment newInstance(int questionNum) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_QUESTION_NUMBER, questionNum);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questionNumber = getArguments().getInt(ARG_QUESTION_NUMBER);
        }else {
            questionNumber = -1;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //views
        View rootView =  inflater.inflate(R.layout.fragment_quiz, container, false);
        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        //public void onActivityCreated(Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );

        //views
        question = (TextView) view.findViewById(R.id.textView3);
        continentsGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        ans1 = (RadioButton) view.findViewById(R.id.radioButton1);
        ans2 = (RadioButton) view.findViewById(R.id.radioButton2);
        ans3 = (RadioButton) view.findViewById(R.id.radioButton3);
        ans4 = (RadioButton) view.findViewById(R.id.radioButton4);

        //setting the text
        question.setText(Question.questions.get(0).get(0));

    }


}