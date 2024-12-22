package com.example.wia2007;
public class FinancialAid {

    int aidID, aidAmount, aidSlots;
    String aidName, aidDateline;

    public FinancialAid(int aidID, String aidName, int aidAmount, int aidSlots, String aidDateline) {
        this.aidID = aidID;
        this.aidName = aidName;
        this.aidAmount = aidAmount;
        this.aidSlots = aidSlots;
        this.aidDateline = aidDateline;
    }

    public int getAidID() {
        return aidID;
    }

    public void setAidID(int aidID) {
        this.aidID = aidID;
    }

    public String getAidName() {
        return aidName;
    }

    public void setAidName(String aidName) {
        this.aidName = aidName;
    }

    public int getAidAmount() {
        return aidAmount;
    }

    public void setAidAmount(int aidAmount) {
        this.aidAmount = aidAmount;
    }

    public int getAidSlots() {
        return aidSlots;
    }

    public void setAidSlots(int aidSlots) {
        this.aidSlots = aidSlots;
    }

    public String getAidDateline() {
        return aidDateline;
    }

    public void setAidDateline(String aidDateline) {
        this.aidDateline = aidDateline;
    }
}