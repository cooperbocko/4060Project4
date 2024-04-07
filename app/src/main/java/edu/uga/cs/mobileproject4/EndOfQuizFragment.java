package edu.uga.cs.mobileproject4;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EndOfQuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EndOfQuizFragment extends Fragment {
    private static final String DEBUG = "EndOfQuizFragment";
    private static final String ARG_QUESTION_NUMBER = "question_number";
    private int questionNumber;
    private TextView tv_result;
    private Button btn_result;
    private int score;
    private String date;
    private QuizModel quizResult;
    private Data data;
    public EndOfQuizFragment() {
        // Required empty public constructor
    }
    public static EndOfQuizFragment newInstance(int questionNum) {
        EndOfQuizFragment fragment = new EndOfQuizFragment();
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
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_end_of_quiz, container, false);

        //get views
        tv_result = (TextView) rootView.findViewById(R.id.tv_result);
        btn_result = rootView.findViewById(R.id.button3);

        btn_result.setOnClickListener((new ButtonClickListenerHome()));

        //return root
        return rootView;
    }

    private class ButtonClickListenerHome implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(view.getContext(), MainActivity.class);
            startActivity(intent);

        }
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        //public void onActivityCreated(Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //get date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        date = sdf.format(new Date());
        Log.d(DEBUG, "get date: " + date);

        //get results and send quiz to db
        score = 0;
        for (int i = 0; i < 6; i++) {
            if (Question.results[i] == true) {
                score++;
            }
        }
        Log.d(DEBUG, "get score: " + score);

        //make quiz model
        quizResult = new QuizModel(score, date);
        Log.d(DEBUG, "make quiz model: " + quizResult.toString());


        //update textview
        tv_result.setText("Score: " + score +" Date: " + date);

        //send result to db
        data = new Data(getActivity());
        data.open();
        new QuizDB().execute();

    }

    //Adding the quiz result to the DB
    private class QuizDB extends AsyncTask<Void, QuizModel> {

        @Override
        protected QuizModel doInBackground(Void... arguments) {
            //adding quiz to db
            data.addQuiz(quizResult);
            Log.d(DEBUG, "Quiz added: " + quizResult.toString());
            return quizResult;
        }

        @Override
        protected void onPostExecute(QuizModel quizModel) {

        }
    }
}