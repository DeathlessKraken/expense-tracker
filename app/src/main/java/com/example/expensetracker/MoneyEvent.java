package com.example.expensetracker;

public class MoneyEvent {

    private String id;         //UID for each event
    private int type;       //0 for income, 1 for expense
    private String cat;
    private String date;    //change type to actual date object
    private double amount;
    private String notes;

    public MoneyEvent(String id, int type, String cat, String date, double amount, String notes) {
        this.id = id;
        this.type = type;
        this.cat = cat;
        this.date = date;
        this.amount = amount;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
