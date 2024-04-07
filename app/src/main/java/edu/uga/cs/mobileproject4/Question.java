package edu.uga.cs.mobileproject4;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question {
    private static final String DEBUG = "Question";
    public static List<String> continents = Arrays.asList("Asia", "Africa" , "North America", "South America", "Antarctica", "Europe", "Australia");
    public static List<CountryModel> countryModels;
    public static List<String> questionAnswer = new ArrayList<>();
    public static List<List<String>> questions;



    //contructor
    Question(List<CountryModel> x){
        countryModels = x;
    }
    //makes the questions
    public static void makeQuestions() {
        //arrrays
        questions = new ArrayList<>();

        //make sure countryModels are supplied
        if (countryModels == null) {
            Log.d(DEBUG, "countryModels null");
            return;
        }

        List<CountryModel> randomcountries = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int rand = (int) (Math.random() * countryModels.size());
            if (!randomcountries.contains(countryModels.get(rand))) {
                randomcountries.add(countryModels.get(rand));
            } else {
                i--;
                continue;
            }
        }
        Log.d(DEBUG, "Random Countries: " + randomcountries.toString());

        //make six questions
        for (int i = 0; i < 6; i++) {
            List<String> question = new ArrayList<>();

            //insert name first
            question.add(randomcountries.get(i).getName());
            questionAnswer.add(randomcountries.get(i).getContinent());
            //insert the continents randomly
            List<String> randomcontinents = new ArrayList<>();
            randomcontinents.add(randomcountries.get(i).getContinent());
            for (int j = 0; j < 3; j++) {
                int rand = (int) (Math.random() * continents.size());
                if (!randomcontinents.contains(continents.get(rand))){
                    randomcontinents.add(continents.get(rand));
                } else {
                    j--;
                    continue;
                }
            }
            Log.d(DEBUG, "random continents: " + randomcontinents.toString());
            for (int j = 0; j < 4; j++) {
                int rand = (int) (Math.random() * randomcontinents.size());
                question.add(randomcontinents.get(rand));
                randomcontinents.remove(rand);
            }
            Log.d(DEBUG, "question: " + question.toString());
            Log.d(DEBUG, "questionsAnswers: " + questionAnswer.toString());
            questions.add(question);
        }
        Log.d(DEBUG, "questions: " + questions.toString());
    }


}
