package com.example.wia2007;

public class FinancialData {
    public String monthID;
    public String monthName;
    public int monthExpenses;
    public int monthEFB;
    public int monthEUtilities;
    public int monthEFundTransfers;
    public int monthETransport;
    public int monthEServices;
    public int monthEOthers;
    public int monthIncome;
    public int monthIFB;
    public int monthIUtilities;
    public int monthIFundTransfers;
    public int monthITransport;
    public int monthIServices;
    public int monthIOthers;
    public int monthNetProfit;

    public FinancialData(String monthID, String monthName, int monthExpenses, int monthEFB, int monthEUtilities, int monthEFundTransfers, int monthETransport, int monthEServices, int monthEOthers,
                         int monthIncome, int monthIFB, int monthIUtilities, int monthIFundTransfers, int monthITransport, int monthIServices, int monthIOthers, int monthNetProfit) {
        this.monthID = monthID;
        this.monthName = monthName;
        this.monthExpenses = monthExpenses;
        this.monthEFB = monthEFB;
        this.monthEUtilities = monthEUtilities;
        this.monthEFundTransfers = monthEFundTransfers;
        this.monthETransport = monthETransport;
        this.monthEServices = monthEServices;
        this.monthEOthers = monthEOthers;
        this.monthIncome = monthIncome;
        this.monthIFB = monthIFB;
        this.monthIUtilities = monthIUtilities;
        this.monthIFundTransfers = monthIFundTransfers;
        this.monthITransport = monthITransport;
        this.monthIServices = monthIServices;
        this.monthIOthers = monthIOthers;
        this.monthNetProfit = monthNetProfit;
    }
}