package edu.uga.cs.mobileproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv_list;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lv_list = findViewById(R.id.lv_countries);
        data = new Data(MainActivity.this);
        data.open();
        List<CountryModel> countries = data.getCountries();
        ArrayAdapter<CountryModel> customerArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, countries);
        lv_list.setAdapter(customerArrayAdapter);

    }
}