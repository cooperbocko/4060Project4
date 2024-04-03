package edu.uga.cs.mobileproject4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    //SQL Strings
    public static final String COUNTRY_TABLE = "COUNTRY_TABLE";
    public static final String COLUMN_COUNTRY_NAME = "COUNTRY_NAME";
    public static final String COLUMN_COUNTRY_CONTINENT = "COUNTRY_CONTINENT";
    //Quiz Strings
    public static final String QUIZ_TABLE = "QUIZ_TABLE";
    public static final String COLUMN_QUIZ_SCORE = "QUIZ_SCORE";
    public static final String COLUMN_QUIZ_DATE = "QUIZ_DATE";
    public static final String ID = "ID";

    //Debug Tag
    public static final String DEBUG_TAG = "DBHelper";

    //Static Reference to helper
    private static DataBaseHelper helperInstance;

    //first time constructor
    public DataBaseHelper(@Nullable Context context) {

        super(context, "project4.db", null, 1);
    }

    //returning the single source
    public static synchronized DataBaseHelper getInstance(Context context) {
        if (helperInstance == null) {
            helperInstance = new DataBaseHelper(context.getApplicationContext());
        }
        return helperInstance;
    }


    //creating the tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCountryTableStatement = "CREATE TABLE " + COUNTRY_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COUNTRY_NAME + " TEXT, " + COLUMN_COUNTRY_CONTINENT + " TEXT)";
        String createQuizTableStatement = "CREATE TABLE " + QUIZ_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_QUIZ_SCORE + " INT, " + COLUMN_QUIZ_DATE + " TEXT)";
        db.execSQL(createCountryTableStatement);
        db.execSQL(createQuizTableStatement);

        Log.d(DEBUG_TAG, "Tables created");

        //
        try {
            CSVReader reader = new CSVReader(new FileReader("country_continent.csv"));
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

    //version update
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + COUNTRY_TABLE);
        db.execSQL("drop table if exists " + QUIZ_TABLE);
        onCreate(db);

        Log.d(DEBUG_TAG, "Tables upgraded");
    }

    //keeping this for later reference
    /*
    //adding a country and a quiz
    public boolean addCountry(CountryModel countryModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_COUNTRY_NAME, countryModel.getName());
        cv.put(COLUMN_COUNTRY_CONTINENT, countryModel.getContinent());

        long insert = db.insert(COUNTRY_TABLE, null, cv);

        if (insert == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean addQuiz(QuizModel quizModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_QUIZ_SCORE, quizModel.getScore());
        cv.put(COLUMN_QUIZ_DATE, quizModel.getDate());

        long insert = db.insert(QUIZ_TABLE, null, cv);

        if (insert == -1) {
            return false;
        } else {
            return true;
        }

    }

    //getting all countries and quizzes
    public List<CountryModel> getCountries() {
        List<CountryModel> list = new ArrayList<>();

        String query = "SELECT * FROM " + COUNTRY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String continent = cursor.getString(2);
                CountryModel countryModel = new CountryModel(id, name, continent);

                list.add(countryModel);
            } while (cursor.moveToNext());
        } else {

        }

        return list;
    }

    public List<QuizModel> getQuizies() {
        List<QuizModel> list = new ArrayList<>();

        String query = "SELECT * FROM " + QUIZ_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int score = cursor.getInt(1);
                String date = cursor.getString(2);
                QuizModel quizModel = new QuizModel(id, score, date);

                list.add(quizModel);
            } while (cursor.moveToNext());
        } else {

        }

        return list;
    }
    */


}
