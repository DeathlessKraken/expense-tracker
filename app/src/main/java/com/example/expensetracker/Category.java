package com.example.expensetracker;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Category
{
    private static ArrayList<Category> categoryList= new ArrayList<>();

    private String id;
    private String text;

    public Category(String id, String text) {
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

    public static ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public static void clearCategoryList()
    {
        categoryList.clear();
    }

    public static void initCategoryList()
    {
        Category home = new Category("home", "Home");
        categoryList.add(home);

        Category food = new Category("food", "Food");
        categoryList.add(food);

        Category family = new Category("family", "Family");
        categoryList.add(family);

        Category debt = new Category("debt", "Debt");
        categoryList.add(debt);

        Category health = new Category("health", "Health");
        categoryList.add(health);

        Category personal = new Category("personal", "Personal");
        categoryList.add(personal);

        Category transportation = new Category("transportation", "Transportation");
        categoryList.add(transportation);

        Category entertainment = new Category("entertainment", "Entertainment");
        categoryList.add(entertainment);

        Category misc = new Category("misc", "Miscellaneous");
        categoryList.add(misc);
    }

    public int getImage()
    {
        switch (getId())
        {
            case "home":
                return R.drawable.ic_home;
            case "food":
                return R.drawable.ic_food;
            case "family":
                return R.drawable.ic_family;
            case "debt":
                return R.drawable.ic_debt;
            case "health":
                return R.drawable.ic_health;
            case "personal":
                return R.drawable.ic_personal;
            case "transportation":
                return R.drawable.ic_transportation;
            case "entertainment":
                return R.drawable.ic_entertainment;
            case "misc":
                return R.drawable.ic_misc;
        }

        return R.drawable.ic_bunny;
    }
}
