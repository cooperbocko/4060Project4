package edu.uga.cs.mobileproject4;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Data {
    //Debug Tag
    public static final String DEBUG_TAG = "Data";

    //references to the database
    private SQLiteDatabase db;
    private SQLiteOpenHelper dataBaseHelper;

    //constructor
    public Data (Context context) {
        this.dataBaseHelper = DataBaseHelper.getInstance(context);
    }

    //open database
    public void open() {
        db = dataBaseHelper.getWritableDatabase();

        Log.d(DEBUG_TAG, "Opening database");
    }

    //close the database
    public void close() {
        if (dataBaseHelper != null) {
            dataBaseHelper.close();

            Log.d(DEBUG_TAG, "Closing database");
        }
    }

    //is open?
    public boolean isDBOpen(){
        return db.isOpen();
    }

    //adding a country and a quiz
    public boolean addCountry(CountryModel countryModel){
        ContentValues cv = new ContentValues();

        cv.put(DataBaseHelper.COLUMN_COUNTRY_NAME, countryModel.getName());
        cv.put(DataBaseHelper.COLUMN_COUNTRY_CONTINENT, countryModel.getContinent());

        long insert = db.insert(DataBaseHelper.COUNTRY_TABLE, null, cv);

        if (insert == -1) {
            Log.d(DEBUG_TAG, "Did NOT add country");
            return false;
        } else {
            Log.d(DEBUG_TAG, "Did add country");
            return true;
        }

    }

    public boolean addQuiz(QuizModel quizModel){
        ContentValues cv = new ContentValues();

        cv.put(DataBaseHelper.COLUMN_QUIZ_SCORE, quizModel.getScore());
        cv.put(DataBaseHelper.COLUMN_QUIZ_DATE, quizModel.getDate());

        long insert = db.insert(DataBaseHelper.QUIZ_TABLE, null, cv);

        if (insert == -1) {
            Log.d(DEBUG_TAG, "Did NOT add quiz");
            return false;
        } else {
            Log.d(DEBUG_TAG, "Did add quiz");
            return true;
        }

    }

    //getting all countries and quizzes
    public List<CountryModel> getCountries() {
        List<CountryModel> list = new ArrayList<>();
        int columnIndex;
        String query = "SELECT * FROM " + DataBaseHelper.COUNTRY_TABLE;
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    columnIndex = cursor.getColumnIndex(DataBaseHelper.ID);
                    int id = cursor.getInt(columnIndex);
                    columnIndex = cursor.getColumnIndex(DataBaseHelper.COLUMN_COUNTRY_NAME);
                    String name = cursor.getString(columnIndex);
                    columnIndex = cursor.getColumnIndex(DataBaseHelper.COLUMN_COUNTRY_CONTINENT);
                    String continent = cursor.getString(columnIndex);
                    CountryModel countryModel = new CountryModel(id, name, continent);

                    list.add(countryModel);
                    Log.d(DEBUG_TAG, "Got Country");
                } while (cursor.moveToNext());
            } else {

            }
            if (cursor != null) {
                Log.d(DEBUG_TAG, "Countries from db: " + cursor.getCount());
            } else {
                Log.d(DEBUG_TAG, "Did not get any countries");
            }
        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "Exception: " + e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }

    public List<QuizModel> getQuizzes() {
        List<QuizModel> list = new ArrayList<>();
        int columnIndex;
        String query = "SELECT * FROM " + DataBaseHelper.QUIZ_TABLE;
        Cursor cursor = null;

        try{
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    columnIndex = cursor.getColumnIndex(DataBaseHelper.ID);
                    int id = cursor.getInt(columnIndex);
                    columnIndex = cursor.getColumnIndex(DataBaseHelper.COLUMN_QUIZ_SCORE);
                    int score = cursor.getInt(columnIndex);
                    columnIndex = cursor.getColumnIndex(DataBaseHelper.COLUMN_QUIZ_DATE);
                    String date = cursor.getString(columnIndex);
                    QuizModel quizModel = new QuizModel(id, score, date);

                    list.add(quizModel);
                    Log.d(DEBUG_TAG, "Got Quiz");
                } while (cursor.moveToNext());
            } else {

            }
            if (cursor != null) {
                Log.d(DEBUG_TAG, "Quizzes from db: " + cursor.getCount());
            } else {
                Log.d(DEBUG_TAG, "Did not get any quizzes");
            }
        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "Exception: " + e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return list;
    }

    public void populateCountries(Context context){
        //
        try {
            InputStream in_s = context.getAssets().open("country_continent.csv");
            CSVReader reader = new CSVReader( new InputStreamReader(in_s));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                //country, continent

                ContentValues cv = new ContentValues();

                cv.put(DataBaseHelper.COLUMN_COUNTRY_NAME, nextLine[0]);
                cv.put(DataBaseHelper.COLUMN_COUNTRY_CONTINENT, nextLine[1]);

                long insert = db.insert(DataBaseHelper.COUNTRY_TABLE, null, cv);

                if (insert == -1) {
                    Log.d(DEBUG_TAG, "Did NOT add country");
                } else {
                    Log.d(DEBUG_TAG, "Did add country");
                }

                System.out.println(nextLine[0] + " : " + nextLine[1]);
            }
        } catch (IOException e) {
            Log.d(DEBUG_TAG, "CSV reading failure: " + e);
        }
    }
}
