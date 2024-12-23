package com.example.wia2007;

import android.os.Parcel;
import android.os.Parcelable;

public class FinancialAid implements Parcelable {

    int aidID, aidAmount, aidSlots, imageID;
    String aidName, aidDateline;

    public FinancialAid(int aidID, String aidName, int aidAmount, int aidSlots, String aidDateline, int imageID) {
        this.aidID = aidID;
        this.aidName = aidName;
        this.aidAmount = aidAmount;
        this.aidSlots = aidSlots;
        this.aidDateline = aidDateline;
        this.imageID = imageID;
    }

    // Parcelable implementation
    protected FinancialAid(Parcel in) {
        aidID = in.readInt();
        aidAmount = in.readInt();
        aidSlots = in.readInt();
        imageID = in.readInt();
        aidName = in.readString();
        aidDateline = in.readString();
    }

    public static final Creator<FinancialAid> CREATOR = new Creator<FinancialAid>() {
        @Override
        public FinancialAid createFromParcel(Parcel in) {
            return new FinancialAid(in);
        }

        @Override
        public FinancialAid[] newArray(int size) {
            return new FinancialAid[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(aidID);
        dest.writeInt(aidAmount);
        dest.writeInt(aidSlots);
        dest.writeInt(imageID);
        dest.writeString(aidName);
        dest.writeString(aidDateline);
    }

    // Getters and Setters
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

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}