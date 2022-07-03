package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    MoneyEvent selectedMoneyEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSelectedMoneyEvent();
        setValues();

    }

    private void getSelectedMoneyEvent()
    {
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        selectedMoneyEvent = getParsedMoneyEvent(parsedStringID);
    }

    private MoneyEvent getParsedMoneyEvent(String parsedID)
    {
        for(MoneyEvent moneyEvent : MainActivity.moneyEventArrayList)
        {
            if(moneyEvent.getId().equals(parsedID))
            {
                return moneyEvent;
            }

        }
        return null;
    }

    private void setValues()
    {
        TextView name = (TextView) findViewById(R.id.moneyEventName);
        TextView category = (TextView) findViewById(R.id.moneyEventCategoryTextView);
        TextView date = (TextView) findViewById(R.id.moneyEventDateTextView);
        TextView amount = (TextView) findViewById(R.id.moneyEventAmountTextView);

        name.setText(selectedMoneyEvent.getNotes());
        category.setText(selectedMoneyEvent.getCat());
        date.setText(selectedMoneyEvent.getDate());
        amount.setText(Double.toString(selectedMoneyEvent.getAmount()));
    }
}