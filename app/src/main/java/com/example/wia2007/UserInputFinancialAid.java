package com.example.wia2007;

public class UserInputFinancialAid {
    private int amountFinancialAid;
    private int totalFinancialAid;

    public UserInputFinancialAid(int amountFinancialAid, int totalFinancialAid) {
        this.amountFinancialAid = amountFinancialAid;
        this.totalFinancialAid = totalFinancialAid;
    }

    public int getAmountFinancialAid() {
        return amountFinancialAid;
    }

    public void setAmountFinancialAid(int amountFinancialAid) {
        this.amountFinancialAid = amountFinancialAid;
    }

    public int getTotalFinancialAid() {
        return totalFinancialAid;
    }

    public void setTotalFinancialAid(int totalFinancialAid) {
        this.totalFinancialAid = totalFinancialAid;
    }
}