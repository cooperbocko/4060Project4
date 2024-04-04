package edu.uga.cs.mobileproject4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

public class SliderPageActivity extends FragmentActivity {
    //Debug
    private static final String DEBUG = "SliderPageActivity";
    //DB
    private Data data = null;
    private List<CountryModel> countryModelList;
    //Countries and Continents variables
    private CountryModel[] countryModels = new CountryModel[6];
    //Quiz result
    private int result = 0;
    private String date;
    private QuizModel quizResult;
    //Pager Variables
    private static final int NUM_PAGES_QUIZ = 6;
    private static final int NUM_PAGES_RESULT = 1;
    //Views
    private TextView header;


    //Getting the countries async and adding them randomly to the countryModelList
    private class CountryDB extends AsyncTask<Void, List<CountryModel>> {

        @Override
        protected List<CountryModel> doInBackground(Void... arguments) {
            List<CountryModel> countryList = data.getCountries();
            if (countryList.size() == 0) {
                data.populateCountries(SliderPageActivity.this);
                data.getCountries();
            }
            Log.d(DEBUG, "CountryDB: Countries: " + countryList.size());

            return countryList;
        }

        @Override
        protected void onPostExecute(List<CountryModel> countryModels) {
            Log.d(DEBUG, "CountryDB: countryModels.size(): " + countryModels.size());
            //get random countries
            while (countryModelList.size() < 6) {
                int rand = (int)(Math.random() * countryModels.size());
                if (!countryModelList.contains(countryModels.get(rand))){
                    countryModelList.add(countryModels.get(rand));
                }
            }
            Log.d(DEBUG, "Random Countries added: " + countryModelList.size());
        }
    }

    //Adding the quiz result to the DB
    private class QuizDB extends AsyncTask<QuizModel, QuizModel> {

        @Override
        protected QuizModel doInBackground(QuizModel... quizModels) {
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
        setContentView(R.layout.activity_slider_page);


        header = findViewById(R.id.textView4);
        ViewPager2 pageslide = (ViewPager2) findViewById(R.id.viewpager);
        FragmentStateAdapter pagerAdapter = new ScreenSlidePagerAdapter(this);
        pageslide.setAdapter(pagerAdapter);


        //gets button name from main activity
        Intent intent = getIntent();
        String btnPressed = intent.getStringExtra(MainActivity.BTN_TYPE);



        header.setText(btnPressed);

    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {


        //gets button name from main activity
        Intent intent = getIntent();
        String btnPressed = intent.getStringExtra(MainActivity.BTN_TYPE);
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {


            if (Objects.equals(btnPressed, "Results")) {
                return new ResultsFragment();
            }else{
                //add quizModel to quiz fragment
                return new QuizFragment(null);
            }

        }


        @Override
        public int getItemCount() {


            if (Objects.equals(btnPressed, "Results")) {
                return NUM_PAGES_RESULT;
            }else{
                return NUM_PAGES_QUIZ;
            }

        }
        }
    }





