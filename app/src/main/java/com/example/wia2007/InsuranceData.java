package com.example.wia2007;

public class InsuranceData {
    public double lifeInsuranceDeductibles;
    public double motorInsuranceDeductibles;
    public double personalInsuranceDeductibles;
    public double medicalInsuranceDeductibles;
    public double travelInsuranceDeductibles;
    public double otherInsuranceDeductibles;
    public double totalDeductibles;
    public double lifeInsurance;
    public double motorInsurance;
    public double personalInsurance;
    public double medicalInsurance;
    public double travelInsurance;
    public double otherInsurance;
    public double totalInsurance;

    public InsuranceData(double lifeInsuranceDeductibles, double motorInsuranceDeductibles, double personalInsuranceDeductibles, double medicalInsuranceDeductibles, double travelInsuranceDeductibles, double otherInsuranceDeductibles, double totalDeductibles, double lifeInsurance, double motorInsurance, double personalInsurance, double medicalInsurance, double travelInsurance, double otherInsurance, double totalInsurance) {
        this.lifeInsuranceDeductibles = lifeInsuranceDeductibles;
        this.motorInsuranceDeductibles = motorInsuranceDeductibles;
        this.personalInsuranceDeductibles = personalInsuranceDeductibles;
        this.medicalInsuranceDeductibles = medicalInsuranceDeductibles;
        this.travelInsuranceDeductibles = travelInsuranceDeductibles;
        this.otherInsuranceDeductibles = otherInsuranceDeductibles;
        this.totalDeductibles = totalDeductibles;
        this.lifeInsurance = lifeInsurance;
        this.motorInsurance = motorInsurance;
        this.personalInsurance = personalInsurance;
        this.medicalInsurance = medicalInsurance;
        this.travelInsurance = travelInsurance;
        this.otherInsurance = otherInsurance;
        this.totalInsurance = totalInsurance;
    }

    public double getlifeInsuranceDeductibles() {
        return lifeInsuranceDeductibles;
    }

    public void setlifeInsuranceDeductibles(double lifeInsuranceDeductibles) {
        this.lifeInsuranceDeductibles = lifeInsuranceDeductibles;
    }

    public double getmotorInsuranceDeductibles() {
        return motorInsuranceDeductibles;
    }

    public void setmotorInsuranceDeductibles(double motorInsuranceDeductibles) {
        this.motorInsuranceDeductibles = motorInsuranceDeductibles;
    }

    public double getpersonalInsuranceDeductibles() {
        return personalInsuranceDeductibles;
    }

    public void setpersonalInsuranceDeductibles(double personalInsuranceDeductibles) {
        this.personalInsuranceDeductibles = personalInsuranceDeductibles;
    }

    public double getmedicalInsuranceDeductibles() {
        return medicalInsuranceDeductibles;
    }

    public void setmedicalInsuranceDeductibles(double medicalInsuranceDeductibles) {
        this.medicalInsuranceDeductibles = medicalInsuranceDeductibles;
    }

    public double gettravelInsuranceDeductibles() {
        return travelInsuranceDeductibles;
    }

    public void settravelInsuranceDeductibles(double travelInsuranceDeductibles) {
        this.travelInsuranceDeductibles = travelInsuranceDeductibles;
    }

    public double getotherInsuranceDeductibles() {
        return otherInsuranceDeductibles;
    }

    public void setotherInsuranceDeductibles(double otherInsuranceDeductibles) {
        this.otherInsuranceDeductibles = otherInsuranceDeductibles;
    }

    public double getTotalDeductibles() {
        return totalDeductibles;
    }

    public void setTotalDeductibles(double totalDeductibles) {
        this.totalDeductibles = lifeInsurance + motorInsurance + personalInsurance + medicalInsurance + travelInsurance + otherInsurance;
    }

    public double getLifeInsuranceDeductibles() {
        return lifeInsuranceDeductibles;
    }

    public void setLifeInsuranceDeductibles(double lifeInsuranceDeductibles) {
        this.lifeInsuranceDeductibles = lifeInsuranceDeductibles;
    }

    public double getMotorInsuranceDeductibles() {
        return motorInsuranceDeductibles;
    }

    public void setMotorInsuranceDeductibles(double motorInsuranceDeductibles) {
        this.motorInsuranceDeductibles = motorInsuranceDeductibles;
    }

    public double getPersonalInsuranceDeductibles() {
        return personalInsuranceDeductibles;
    }

    public void setPersonalInsuranceDeductibles(double personalInsuranceDeductibles) {
        this.personalInsuranceDeductibles = personalInsuranceDeductibles;
    }

    public double getMedicalInsuranceDeductibles() {
        return medicalInsuranceDeductibles;
    }

    public void setMedicalInsuranceDeductibles(double medicalInsuranceDeductibles) {
        this.medicalInsuranceDeductibles = medicalInsuranceDeductibles;
    }

    public double getTravelInsuranceDeductibles() {
        return travelInsuranceDeductibles;
    }

    public void setTravelInsuranceDeductibles(double travelInsuranceDeductibles) {
        this.travelInsuranceDeductibles = travelInsuranceDeductibles;
    }

    public double getOtherInsuranceDeductibles() {
        return otherInsuranceDeductibles;
    }

    public void setOtherInsuranceDeductibles(double otherInsuranceDeductibles) {
        this.otherInsuranceDeductibles = otherInsuranceDeductibles;
    }

    public double getLifeInsurance() {
        return lifeInsurance;
    }

    public void setLifeInsurance(double lifeInsurance) {
        this.lifeInsurance = lifeInsurance;
    }

    public double getMotorInsurance() {
        return motorInsurance;
    }

    public void setMotorInsurance(double motorInsurance) {
        this.motorInsurance = motorInsurance;
    }

    public double getPersonalInsurance() {
        return personalInsurance;
    }

    public void setPersonalInsurance(double personalInsurance) {
        this.personalInsurance = personalInsurance;
    }

    public double getMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(double medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    public double getTravelInsurance() {
        return travelInsurance;
    }

    public void setTravelInsurance(double travelInsurance) {
        this.travelInsurance = travelInsurance;
    }

    public double getOtherInsurance() {
        return otherInsurance;
    }

    public void setOtherInsurance(double otherInsurance) {
        this.otherInsurance = otherInsurance;
    }

    public double getTotalInsurance() {
        return totalInsurance;
    }

    public void setTotalInsurance(double totalInsurance) {
        this.totalInsurance = totalInsurance;
    }
}
