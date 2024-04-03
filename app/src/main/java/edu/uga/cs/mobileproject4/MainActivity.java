package edu.uga.cs.mobileproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String RESULTS_BTN = "Results";

    public static final String START_QUIZ_BTN = "Start";

    private  Button results;

    private Button startQuiz;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        results = findViewById(R.id.button);
        startQuiz = findViewById(R.id.button2);

        results.setOnClickListener((new ButtonClickListenerResults()));
        startQuiz.setOnClickListener((new ButtonClickListenerQuiz()));
    }

    private class ButtonClickListenerResults implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(view.getContext(), SliderPageActivity.class);
            intent.putExtra(RESULTS_BTN, "result");
            startActivity(intent);
        }
    }

    private class ButtonClickListenerQuiz implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), SliderPageActivity.class);
            intent.putExtra(START_QUIZ_BTN, "start");
            startActivity(intent);
        }
    }
}