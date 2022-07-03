package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class NewMoneyEventActivity extends AppCompatActivity {

    private Spinner categorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_money_event);
        Category.initCategoryList();

        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);

        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, R.layout.spinner_cell, Category.getCategoryList());
        categorySpinner.setAdapter(spinnerAdapter);
    }
}