package edu.uga.cs.mobileproject4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ResultsFragment extends Fragment {
    //Debug
    private static final String DEBUG = "Results Fragment";
    //DB
    List<QuizModel> quizModels;
    private Data data = null;

    private ListView resultsList;



    //Getting quiz results from DB
    private class QuizDB extends AsyncTask<Void, List<QuizModel>>{

        @Override
        protected List<QuizModel> doInBackground(Void... arguments) {
            List<QuizModel> quizList = data.getQuizzes();
            Log.d(DEBUG, "QuizDB: Quizzes: " + quizList.size());

            return quizList;
        }

        @Override
        protected void onPostExecute(List<QuizModel> quizModels) {

        }
    }

    public ResultsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ResultsFragment newInstance() {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_results, container, false);

        resultsList = (ListView) rootView.findViewById(R.id.listView);



        return rootView;
    }
}