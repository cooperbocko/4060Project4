package edu.uga.cs.mobileproject4;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question {
    private static final String DEBUG = "Question";
    public static List<String> continents = Arrays.asList("Asia", "Africa" , "North America", "South America", "Antarctica", "Europe", "Australia");
    public static List<CountryModel> countryModels;
    public static List<List<String>> questions; //country, -> continents
    public static Boolean[] results = {false, false, false, false, false, false};
    public static Boolean[] soFar = {false, false, false, false, false, false}; //may not need this one due to page number
    public static List<String> questionAnswer = new ArrayList<>();



    //contructor
    Question(List<CountryModel> x){
        countryModels = x;
    }
    //makes the questions
    public static void makeQuestions() {
        //arrrays
        questions = new ArrayList<>();
        questionAnswer = new ArrayList<>();

        //make sure countryModels are supplied
        if (countryModels == null) {
            Log.d(DEBUG, "countryModels null");
            return;
        }

        //getting random countrymodels
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

            //insert name first and add to answers
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

    //resets every variable that changes and calls make question again to have a new quiz prepared
    //probably do not even need the null calls but I added them just in case
    //don't know if the boolean lists will be reset like this
    public static void reset() {
        countryModels = null;
        questionAnswer = null;
        questions = null;
        results = Arrays.asList(false, false, false, false, false, false).toArray(new Boolean[0]);
        Log.d(DEBUG, "reset results array: " + results.toString());
        soFar = Arrays.asList(false, false, false, false, false, false).toArray(new Boolean[0]);
        Log.d(DEBUG, "reset soFar array: " + soFar.toString());

        makeQuestions();
    }


}
