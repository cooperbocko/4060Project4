package edu.uga.cs.mobileproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String BTN_TYPE = "button type";
    private  Button results, startQuiz;
    private static final String DEBUG = "Main Activity";
    //
    public List<CountryModel> countryModelList;
    private Data data;
    public Question question;
    //


    //getting countries
    private class CountryDB extends AsyncTask<Void, List<CountryModel>> {

        @Override
        protected List<CountryModel> doInBackground(Void... arguments) {
            //get and or populate countries
            List<CountryModel> countries = data.getCountries();
            if (countries.size() == 0) {
                data.populateCountries(MainActivity.this);
                data.getCountries();
            }
            Log.d(DEBUG, "CountryDB: Countries: " + countries.size());

            return countries;
        }

        @Override
        protected void onPostExecute(List<CountryModel> countryModels) {
            //give countries to the activity
            countryModelList = countryModels;
            question = new Question(countryModels);
            Question.makeQuestions();
        }
    }

    //Adding the quiz result to the DB
    private class QuizDB extends AsyncTask<QuizModel, QuizModel> {

        @Override
        protected QuizModel doInBackground(QuizModel... quizModels) {
            //adding quiz to db
            data.addQuiz(quizModels[0]);
            Log.d(DEBUG, "Quiz added: " + quizModels[0].toString());
            return quizModels[0];
        }

        @Override
        protected void onPostExecute(QuizModel quizModel) {

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //call to db to get countries
        countryModelList = new ArrayList<>();
        data = new Data(MainActivity.this);
        data.open();
        new CountryDB().execute();
        Log.d(DEBUG, "Added countries to main activity: " + countryModelList.size());

        //views
        results = findViewById(R.id.button2);
        startQuiz = findViewById(R.id.button);

        //set views
        results.setOnClickListener((new ButtonClickListenerResults()));
        startQuiz.setOnClickListener((new ButtonClickListenerQuiz()));
    }

    private class ButtonClickListenerResults implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(view.getContext(), SliderPageActivity.class);
            intent.putExtra(BTN_TYPE, "Results");
            startActivity(intent);
        }
    }

    private class ButtonClickListenerQuiz implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), SliderPageActivity.class);
            intent.putExtra(BTN_TYPE, "Quiz");
            startActivity(intent);
        }

    }
}