package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

public class NewMoneyEventActivity extends AppCompatActivity {

    private Spinner categorySpinner;
    private Spinner typeSpinner;

    private EditText amountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_money_event);

        Category.initCategoryList();
        Type.initTypeList();

        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        amountText = (EditText) findViewById(R.id.amountEditText);

        CategorySpinnerAdapter categorySpinnerAdapter = new CategorySpinnerAdapter(this, R.layout.spinner_cell, Category.getCategoryList());
        categorySpinner.setAdapter(categorySpinnerAdapter);

        //Type Spinner Default Settings
        TypeSpinnerAdapter typeSpinnerAdapter = new TypeSpinnerAdapter(this, R.layout.spinner_cell, Type.getTypeList());
        typeSpinner.setAdapter(typeSpinnerAdapter);
        typeSpinner.setSelection(1);
    }

    @Override
    protected void onStop() {
        super.onStop();

        Category.clearCategoryList();
        Type.clearTypeList();
    }
}