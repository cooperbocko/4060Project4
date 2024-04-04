package edu.uga.cs.mobileproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String BTN_TYPE = "button type";

    private  Button results, startQuiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        results = findViewById(R.id.button2);
        startQuiz = findViewById(R.id.button);

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