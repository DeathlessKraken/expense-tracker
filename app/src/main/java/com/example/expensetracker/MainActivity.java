package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.metrics.Event;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<MoneyEvent> moneyEventArrayList = new ArrayList<MoneyEvent>();

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupData();
        setupList();
        setupOnClickListener();

    }

    private void setupData()
    {
        MoneyEvent gas = new MoneyEvent("0",1,"Gas","07022022",30.00,"Chevron");
        moneyEventArrayList.add(gas);

        MoneyEvent groceries = new MoneyEvent("1", 1, "Groceries", "07022022",38.56, "Fred Meyer");
        moneyEventArrayList.add(groceries);

        MoneyEvent rent = new MoneyEvent("2", 1, "Rent", "07012022",584.33, "Rent");
        moneyEventArrayList.add(rent);

        MoneyEvent fastfood = new MoneyEvent("3", 1, "Fast Food", "06232022",22.83, "McStonks");
        moneyEventArrayList.add(fastfood);

        MoneyEvent dt = new MoneyEvent("4", 0, "Job", "06302022",356.56, "Discount Tire Job");
        moneyEventArrayList.add(dt);

        MoneyEvent brakejob = new MoneyEvent("5", 0, "Side Income", "06252022",600.00, "Izzy's Brakes");
        moneyEventArrayList.add(brakejob);

        MoneyEvent beer = new MoneyEvent("6", 1, "Alcohol", "06292022",22.99, "30 pk Miller");
        moneyEventArrayList.add(beer);

        MoneyEvent payback = new MoneyEvent("7", 0, "Side Income", "06252022",39.00, "Returned Money");
        moneyEventArrayList.add(payback);
    }

    private void setupList()
    {
        listView = (ListView) findViewById(R.id.eventsListView);

        EventAdapter adapter = new EventAdapter(getApplicationContext(), 0, moneyEventArrayList);
        listView.setAdapter(adapter);

    }

    private void setupOnClickListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                MoneyEvent selectMoneyEvent = (MoneyEvent) (listView.getItemAtPosition(position));
                Intent showDetail = new Intent(getApplicationContext(), DetailActivity.class);
                showDetail.putExtra("id", selectMoneyEvent.getId());
                startActivity(showDetail);
            }
        });
    }
}