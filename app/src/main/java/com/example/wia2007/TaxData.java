package com.example.wia2007;

import java.io.Serializable;

public class TaxData implements Serializable {
    private double totalIncome;
    private double taxRelief;
    private double chargeableIncome;
    private double incomeTax;
    private double taxRebate;
    private double balanceIncomeTax;
    private double monthlyTaxDeduction;
    private double takafulZakat;
    private double incomeTaxPayable;
    private double roadTax;
    private double propertyTax;
    private double quitRent;
    private double otherTax;
    private double totalAdditionalTax;

    public TaxData(double totalIncome, double taxRelief) {
        this.totalIncome = totalIncome;
        this.taxRelief = taxRelief;
        this.chargeableIncome = totalIncome - taxRelief;
        calculateIncomeTax();
        calculateTaxRebate();
        calculateBalanceIncomeTax();
    }

    public void setMonthlyTaxDeduction(double monthlyTaxDeduction) {
        this.monthlyTaxDeduction = monthlyTaxDeduction;
        calculateIncomeTaxPayable();
    }

    public void setTakafulZakat(double takafulZakat) {
        this.takafulZakat = takafulZakat;
        calculateIncomeTaxPayable();
    }

    public void setRoadTax(double roadTax) {
        this.roadTax = roadTax;
        calculateTotalAdditionalTax();
    }

    public void setPropertyTax(double propertyTax) {
        this.propertyTax = propertyTax;
        calculateTotalAdditionalTax();
    }

    public void setQuitRent(double quitRent) {
        this.quitRent = quitRent;
        calculateTotalAdditionalTax();
    }

    public void setOtherTax(double otherTax) {
        this.otherTax = otherTax;
        calculateTotalAdditionalTax();
    }

    private void calculateIncomeTax() {
        double income = chargeableIncome;
        double tax = 0;

        if (income > 0) {
            if (income <= 5000) {
                tax = 0;
            } else if (income <= 20000) {
                tax = (income - 5000) * 0.08;
            } else if (income <= 35000) {
                tax = 150 + (income - 20000) * 0.14;
            } else if (income <= 50000) {
                tax = 600 + (income - 35000) * 0.21;
            } else if (income <= 70000) {
                tax = 1500 + (income - 50000) * 0.24;
            } else if (income <= 100000) {
                tax = 3700 + (income - 70000) * 0.245;
            } else if (income <= 400000) {
                tax = 9400 + (income - 100000) * 0.25;
            } else if (income <= 600000) {
                tax = 84400 + (income - 400000) * 0.26;
            } else if (income <= 2000000) {
                tax = 158400 + (income - 600000) * 0.28;
            } else {
                tax = 578400 + (income - 2000000) * 0.3;
            }
        }

        this.incomeTax = tax;
    }

    private void calculateTaxRebate() {
        this.taxRebate = (chargeableIncome <= 35000) ? 400 : 0;
    }

    private void calculateBalanceIncomeTax() {
        this.balanceIncomeTax = incomeTax - taxRebate;
    }

    private void calculateIncomeTaxPayable() {
        this.incomeTaxPayable = balanceIncomeTax - (monthlyTaxDeduction * 12) - takafulZakat;
    }

    private void calculateTotalAdditionalTax() {
        this.totalAdditionalTax = roadTax + propertyTax + quitRent + otherTax;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTaxRelief() {
        return taxRelief;
    }

    public double getChargeableIncome() {
        return chargeableIncome;
    }

    public double getIncomeTax() {
        return incomeTax;
    }

    public double getTaxRebate() {
        return taxRebate;
    }

    public double getBalanceIncomeTax() {
        return balanceIncomeTax;
    }

    public double getMonthlyTaxDeduction() {
        return monthlyTaxDeduction;
    }

    public double getTakafulZakat() {
        return takafulZakat;
    }

    public double getIncomeTaxPayable() {
        return incomeTaxPayable;
    }

    public double getRoadTax() {
        return roadTax;
    }

    public double getPropertyTax() {
        return propertyTax;
    }

    public double getQuitRent() {
        return quitRent;
    }

    public double getOtherTax() {
        return otherTax;
    }

    public double getTotalAdditionalTax() {
        return totalAdditionalTax;
    }

    public double getTotalTaxPayable() {
        return incomeTax - taxRebate - (monthlyTaxDeduction * 12) - takafulZakat - roadTax - propertyTax - quitRent - otherTax;
    }
}