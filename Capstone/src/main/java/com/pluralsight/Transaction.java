package com.pluralsight;

public class Transaction {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(String date, String time, String description, String vendor, double amount){
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }
    public String getMonth(){
        return date.substring(0,7);
    }
    public String getTime(){
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getDescription(){
        return description;
    }
    public String getVendor(){
        return vendor;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount){
            this.amount = amount;
    }
    public String getYear() {
        return date.substring(0,4);
    }
    @Override
    public String toString(){
        return date + " | " + time + " | " + description + " | " + vendor + " | $" + String.format("%.2f",amount);
    }
}
