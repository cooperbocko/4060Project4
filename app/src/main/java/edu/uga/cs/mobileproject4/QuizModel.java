package edu.uga.cs.mobileproject4;

public class QuizModel {
    //variables
    private int id;
    private int score;
    private String date;

    //constructors
    public QuizModel(int id, int score, String date) {
        this.id = id;
        this.score = score;
        this.date = date;
    }

    public QuizModel(int score, String date) {
        this.id = -1;
        this.score = score;
        this.date = date;
    }

    QuizModel(){}

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    //tostring
    @Override
    public String toString() {
        return "Score: " + score + " Date: " + date;
    }
}
