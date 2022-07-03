package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.metrics.Event;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<MoneyEvent> moneyEventArrayList = new ArrayList<MoneyEvent>();

    private ListView listView;

    private Button creditButton;
    private Button debitButton;

    private String selectedFilter = "all";
    private String currentSearchText = "";
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupSearchWidget();

        setupData();
        setupList();
        setupOnClickListener();

    }

    private void setupSearchWidget()
    {
        searchView = (SearchView) findViewById(R.id.moneyEventListSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                currentSearchText = s;
                ArrayList<MoneyEvent> filteredMoneyEvents = new ArrayList<MoneyEvent>();

                for (MoneyEvent moneyEvent : moneyEventArrayList)
                {
                    if(moneyEvent.getNotes().toLowerCase().contains(s.toLowerCase()))
                    {
                        if(selectedFilter.equals("all"))
                        {
                            filteredMoneyEvents.add(moneyEvent);
                        }
                        else
                        {
                            if((moneyEvent.getType() == 0 ) && (selectedFilter.equals("credits")))
                            {
                                filteredMoneyEvents.add(moneyEvent);
                            }
                            if((moneyEvent.getType() == 1 ) && (selectedFilter.equals("debits")))
                            {
                                filteredMoneyEvents.add(moneyEvent);
                            }
                        }
                    }
                }

                EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), 0, filteredMoneyEvents);
                listView.setAdapter(eventAdapter);

                return false;
            }
        });
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

    private void filterList(String status)
    {
        selectedFilter = status;

        ArrayList<MoneyEvent> filteredMoneyEvents = new ArrayList<MoneyEvent>();

        for(MoneyEvent moneyEvent : moneyEventArrayList)
        {
            if((moneyEvent.getType() == 0 ) && (status.equals("credits")))
            {
                if(currentSearchText == "")
                {
                    filteredMoneyEvents.add(moneyEvent);
                }
                else
                {
                    if(moneyEvent.getNotes().toLowerCase().contains(currentSearchText.toLowerCase()))
                    {
                        filteredMoneyEvents.add(moneyEvent);
                    }
                }
            }
            if((moneyEvent.getType() == 1 ) && (status.equals("debits")))
            {
                filteredMoneyEvents.add(moneyEvent);
            }
        }

        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), 0, filteredMoneyEvents);
        listView.setAdapter(eventAdapter);
    }

    public void creditFilterTapped(View view)
    {
        filterList("credits");
    }

    public void debitFilterTapped(View view)
    {
        filterList("debits");
    }

    public void allFilterTapped(View view)
    {
        selectedFilter = "all";
        searchView.setQuery("", false);
        searchView.clearFocus();

        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), 0, moneyEventArrayList);
        listView.setAdapter(eventAdapter);
    }
}