package edu.uga.cs.mobileproject4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import  androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class QuizFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_QUESTION_NUMBER = "question_number";

    // TODO: Rename and change types of parameters

    private int questionNumber;
    private TextView question;

    private Spinner continents;

    private Button submit;

    public QuizFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
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


        View rootView =  inflater.inflate(R.layout.fragment_quiz, container, false);

        question = (TextView) rootView.findViewById(R.id.textView3);
        continents = (Spinner) rootView.findViewById(R.id.spinner);
        submit = (Button) rootView.findViewById(R.id.button3);

        return rootView;

    }
}