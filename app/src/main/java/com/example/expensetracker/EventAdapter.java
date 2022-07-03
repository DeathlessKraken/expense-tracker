package com.example.expensetracker;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class EventAdapter extends ArrayAdapter<MoneyEvent>
{

    public EventAdapter(Context context, int resource, List<MoneyEvent> moneyEventList)
    {
        super(context, resource, moneyEventList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        MoneyEvent moneyEvent = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);
        }

        TextView notes = (TextView) convertView.findViewById(R.id.eventName);
        TextView amount = (TextView) convertView.findViewById(R.id.amount);
        notes.setText(moneyEvent.getNotes());
        amount.setText(Double.toString(moneyEvent.getAmount()));

        //Sets background color based on type of transaction
        RelativeLayout layout = (RelativeLayout) convertView.findViewById(R.id.cellLayout);
        if(moneyEvent.getType() == 0)
        {
            layout.setBackgroundColor(Color.parseColor("#7FFF94"));
        }
        else
        {
            layout.setBackgroundColor(Color.parseColor("#E45C4D"));
        }

        return convertView;
    }
}
