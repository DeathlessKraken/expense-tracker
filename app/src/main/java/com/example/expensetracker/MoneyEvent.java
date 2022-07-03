package com.example.expensetracker;

import java.util.Comparator;

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

    public static Comparator<MoneyEvent> typeAscending = new Comparator<MoneyEvent>() {
        @Override
        public int compare(MoneyEvent moneyEvent1, MoneyEvent moneyEvent2)
        {
            int id1 = Integer.valueOf(moneyEvent1.getType());
            int id2 = Integer.valueOf(moneyEvent2.getType());

            return Integer.compare(id1, id2);
        }
    };

    public static Comparator<MoneyEvent> nameAscending = new Comparator<MoneyEvent>() {
        @Override
        public int compare(MoneyEvent moneyEvent1, MoneyEvent moneyEvent2)
        {
            String name1 = moneyEvent1.getNotes();
            String name2 = moneyEvent2.getNotes();
            name1.toLowerCase();
            name2.toLowerCase();

            return name1.compareTo(name2);
        }
    };

    public static Comparator<MoneyEvent> dateAscending = new Comparator<MoneyEvent>() {
        @Override
        public int compare(MoneyEvent moneyEvent1, MoneyEvent moneyEvent2)
        {
            String date1 = moneyEvent1.getDate();
            String date2 = moneyEvent2.getDate();
            date1.toLowerCase();
            date2.toLowerCase();

            return date1.compareTo(date2);
        }
    };

    public static Comparator<MoneyEvent> categoryAscending = new Comparator<MoneyEvent>() {
        @Override
        public int compare(MoneyEvent moneyEvent1, MoneyEvent moneyEvent2)
        {
            String cat1 = moneyEvent1.getCat();
            String cat2 = moneyEvent2.getCat();
            cat1.toLowerCase();
            cat2.toLowerCase();

            return cat1.compareTo(cat2);
        }
    };

    public static Comparator<MoneyEvent> amountAscending = new Comparator<MoneyEvent>() {
        @Override
        public int compare(MoneyEvent moneyEvent1, MoneyEvent moneyEvent2)
        {
            double amount1 = moneyEvent1.getAmount();
            double amount2 = moneyEvent2.getAmount();

            return (Integer) Double.compare(amount1, amount2);
        }
    };
}
