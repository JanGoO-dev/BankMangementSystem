package com.ourbank.bankmanagementsystem;

import javafx.beans.property.SimpleStringProperty;

public class user {
    private SimpleStringProperty count;
    private SimpleStringProperty Name;
    private SimpleStringProperty accountNumber;
    private SimpleStringProperty balance;

    user(String count,String name,String account,String balance){
        this.count = new SimpleStringProperty(count);
        this.Name = new SimpleStringProperty(name);
        this.accountNumber = new SimpleStringProperty(account);
        this.balance = new SimpleStringProperty(balance);

    }

    public void setName(String n){
        this.Name.set(n);
    }
    public String getName(){
        return Name.get();
    }

    public void setAccountNumber(String acc){
        this.accountNumber.set(acc);
    }
    public String getAccountNumber(){
        return accountNumber.get();
    }

    public void setBalance(String bal){
        this.balance.set(bal);
    }
    public String getBalance(){
        return balance.get();
    }

    public void setCount(String val){
        this.count.set(val);
    }
    public String getCount(){
        return count.get();
    }




}
