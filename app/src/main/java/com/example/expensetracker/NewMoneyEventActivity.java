package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class NewMoneyEventActivity extends AppCompatActivity {

    private Spinner categorySpinner;
    private Spinner typeSpinner;

    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_money_event);
        
        Category.initCategoryList();
        Type.initTypeList();

        initDatePicker();

        setupWidgets();
        setupListeners();
        setupData();
    }

    private void setupWidgets()
    {
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        dateButton = findViewById(R.id.datePickerButton);

        //Default date is current day on date picker dialog
        dateButton.setText(getTodaysDate());
    }

    private void setupListeners()
    {

    }

    private void setupData()
    {
        //Populates category list via custom cell adapter
        CategorySpinnerAdapter categorySpinnerAdapter = new CategorySpinnerAdapter(this, R.layout.spinner_cell, Category.getCategoryList());
        categorySpinner.setAdapter(categorySpinnerAdapter);


        //Populates type list spinner with custom xml file
        TypeSpinnerAdapter typeSpinnerAdapter = new TypeSpinnerAdapter(this, R.layout.spinner_cell, Type.getTypeList());
        typeSpinner.setAdapter(typeSpinnerAdapter);

        //Type Spinner Default Settings
        typeSpinner.setSelection(1);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return makeDateString(day, month + 1, year);
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        return "BUN";
    }

    public void saveTapped(View view)
    {
        //Save to main file
        CharSequence saveText = "Saved! (Not Really)";
        Toast msg = Toast.makeText(this, saveText, Toast.LENGTH_SHORT);
        msg.show();

        finish();
    }

    public void cancelTapped(View view)
    {
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Category.clearCategoryList();
        Type.clearTypeList();
    }

}