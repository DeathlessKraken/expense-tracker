package com.example.expensetracker;

import java.util.ArrayList;

public class Type
{
    private static ArrayList<Type> typeList= new ArrayList<>();

    private String id;
    private String text;

    public Type(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static ArrayList<Type> getTypeList() {
        return typeList;
    }

    public static void clearTypeList()
    {
        typeList.clear();
    }

    public static void initTypeList()
    {
        Type income = new Type("income","Income");
        typeList.add(income);

        Type expense = new Type("expense", "Expense");
        typeList.add(expense);
    }

    public int getImage()
    {
        switch (getId())
        {
            case "income":
                return R.drawable.ic_income;
            case "expense":
                return R.drawable.ic_expense;
        }

        return R.drawable.ic_bunny;
    }
}
