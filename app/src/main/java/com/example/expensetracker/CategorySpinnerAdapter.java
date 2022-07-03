package com.example.expensetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CategorySpinnerAdapter extends ArrayAdapter<Category>
{

    LayoutInflater layoutInflater;

    public CategorySpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Category> categories)
    {
        super(context, resource, categories);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View rowView = layoutInflater.inflate(R.layout.spinner_cell,null,true);
        Category category = getItem(position);

        TextView tv = (TextView) rowView.findViewById(R.id.cellTextView );
        ImageView iv = (ImageView) rowView.findViewById(R.id.cellImageIcon );

        tv.setText(category.getText());
        iv.setImageResource(category.getImage());

        return rowView;

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        if(convertView == null)
            convertView = layoutInflater.inflate(R.layout.spinner_cell,parent,false);

        Category category = getItem(position);

        TextView tv = (TextView) convertView.findViewById(R.id.cellTextView );
        ImageView iv = (ImageView) convertView.findViewById(R.id.cellImageIcon );

        tv.setText(category.getText());
        iv.setImageResource(category.getImage());

        return convertView;
    }
}
