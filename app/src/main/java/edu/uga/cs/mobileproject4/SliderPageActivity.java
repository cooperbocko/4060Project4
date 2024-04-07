package edu.uga.cs.mobileproject4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SliderPageActivity extends FragmentActivity {
    //Debug
    private static final String DEBUG = "SliderPageActivity";
    //Pager Variables
    private static final int NUM_PAGES_QUIZ = 7;
    private static final int NUM_PAGES_RESULT = 1;
    //Views
    private TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_page);

        //views
        header = findViewById(R.id.textView4);
        ViewPager2 vp = (ViewPager2) findViewById(R.id.viewpager);
        vp.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        //sets up fragment adapter
        ScreenSlidePagerAdapter pagerAdapter =
                new ScreenSlidePagerAdapter(getSupportFragmentManager(), getLifecycle());
        vp.setAdapter(pagerAdapter);


        //gets button name from main activity
        Intent intent = getIntent();
        String btnPressed = intent.getStringExtra(MainActivity.BTN_TYPE);
        //sets text for main activity header
        header.setText(btnPressed);
    }


    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        //gets button name from main activity
        Intent intent = getIntent();
        String btnPressed = intent.getStringExtra(MainActivity.BTN_TYPE);

        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        public ScreenSlidePagerAdapter(FragmentManager supportFragmentManager, Lifecycle lifecycle) {
            super(supportFragmentManager, lifecycle);
        }


        @Override
        public Fragment createFragment(int position) {
            if (Objects.equals(btnPressed, "Results")) {
                return new ResultsFragment();
            } else {
                if (position == 6){
                    return EndOfQuizFragment.newInstance(position);
                }else {
                    return QuizFragment.newInstance(position);
                }
            }

        }

        @Override
        public int getItemCount() {
            if (Objects.equals(btnPressed, "Results")) {
                return NUM_PAGES_RESULT;
            } else {
                return NUM_PAGES_QUIZ;
            }

        }
    }

}







