package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.metrics.Event;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<MoneyEvent> moneyEventArrayList = new ArrayList<MoneyEvent>();

    private ListView listView;

    private Button creditButton;
    private Button debitButton;
    private Button sortButton;
    private Button filterButton;
    private LinearLayout filterView;
    private LinearLayout sortView1;
    private LinearLayout sortView2;

    boolean sortHidden = true;
    boolean filterHidden = true;


    private Boolean ascendingSort = true;
    private String selectedFilter = "all";
    private String currentSearchText = "";
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupSearchWidget();
        setupWidgets();

        setupData();
        setupList();
        setupOnClickListener();

        hideFilter();
        hideSort();

    }

    private void setupWidgets()
    {
        sortButton = (Button) findViewById(R.id.sortButton);
        filterButton = (Button) findViewById(R.id.filterButton);
        filterView = (LinearLayout) findViewById(R.id.filterTabsLayout);
        sortView1 = (LinearLayout) findViewById(R.id.sortTabsLayout);
        sortView2 = (LinearLayout) findViewById(R.id.sortTabsLayout2);
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

    public void showFilterTapped(View view)
    {
        if(filterHidden)
        {
            filterHidden = false;
            showFilter();
        }
        else
        {
            filterHidden = true;
            hideFilter();
        }
    }

    public void showSortTapped(View view)
    {
        if(sortHidden)
        {
            sortHidden = false;
            showSort();
        }
        else
        {
            sortHidden = true;
            hideSort();
        }
    }

    public void amountTapped(View view)
    {

    }

    public void categoryTapped(View view)
    {
    }

    public void dateTapped(View view)
    {
    }

    public void alphabeticallyTapped(View view)
    {
    }

    public void typeTapped(View view)
    {
        if(ascendingSort)
        {
            Collections.sort(moneyEventArrayList, MoneyEvent.typeAscending);
            Collections.reverse(moneyEventArrayList);
            EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), 0, moneyEventArrayList);
            listView.setAdapter(eventAdapter);

            ascendingSort = false;
        }
        else
        {
            Collections.sort(moneyEventArrayList, MoneyEvent.typeAscending);
            EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), 0, moneyEventArrayList);
            listView.setAdapter(eventAdapter);

            ascendingSort = true;
        }
    }

    private void hideFilter()
    {
        filterView.setVisibility(View.GONE);
        searchView.setVisibility(View.GONE);
        filterButton.setText("FILTER");
    }

    private void hideSort()
    {
        sortView1.setVisibility(View.GONE);
        sortView2.setVisibility(View.GONE);
        sortButton.setText("SORT");
    }

    private void showFilter()
    {
        filterView.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.VISIBLE);
        filterButton.setText("HIDE");
    }

    private void showSort()
    {
        sortView1.setVisibility(View.VISIBLE);
        sortView2.setVisibility(View.VISIBLE);
        sortButton.setText("HIDE");
    }
}