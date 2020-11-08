package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText weight;
    private EditText hight;
    private EditText BMI;
    private Spinner gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadDate();


    }

    private void loadDate() {
        name = (EditText) findViewById(R.id.name);
        weight = (EditText) findViewById(R.id.weight);
        hight = (EditText) findViewById(R.id.hight);
        BMI = (EditText) findViewById(R.id.bmi);
        gender = (Spinner) findViewById(R.id.gender);
        BMI.setEnabled(false);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String getName = sharedPref.getString("name", "");
        long getWeight = sharedPref.getLong("weight", 0);
        long getBmi = sharedPref.getLong("bmi", 0);
        long getHight = sharedPref.getLong("hight", 0);
        int getGender = sharedPref.getInt("gender", 0);
        name.setText(getName);
        weight.setText(getWeight + "");
        hight.setText(getHight + "");
        BMI.setText(getBmi + "");
        gender.setSelection(getGender);


    }

    public void onclickNext(View view) {
        Intent intent   = new Intent(this, TimerActitvity.class);
        startActivity(intent);
    }

    public void onClickSave(View view) {
        String names = name.getText().toString();
        double hights = Double.parseDouble(hight.getText().toString());
        double weights = Double.parseDouble(weight.getText().toString());
        double bmis = weights / Math.sqrt(hights);
        int genders = gender.getSelectedItemPosition();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong("hight", (long) hights);
        editor.putLong("weight", (long) weights);
        editor.putLong("bmi", (long) bmis);
        editor.putString("name", names);
        editor.putInt("gender", genders);
        BMI.setText(bmis + "");
        editor.commit();
    }
}
