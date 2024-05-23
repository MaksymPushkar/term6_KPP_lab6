package com.example.salesdb.data;

import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int id;
    private String department;
    private String surnameAndInitials;
    private String position;
    private String qualification;
    private double hoursWorkedNumber;
    private double hourPay;

    public Employee(String department, String surnameAndInitials, String position, String qualification,
                    double hoursWorkedNumber, double hourPay) {
        this.id = 0;
        this.department = department;
        this.surnameAndInitials = surnameAndInitials;
        this.position = position;
        this.qualification = qualification;
        this.hoursWorkedNumber = hoursWorkedNumber;
        this.hourPay = hourPay;
    }
    public Employee(int id, String department, String surnameAndInitials, String position, String qualification,
                    double hoursWorkedNumber, double hourPay) {
        this.id = id;
        this.department = department;
        this.surnameAndInitials = surnameAndInitials;
        this.position = position;
        this.qualification = qualification;
        this.hoursWorkedNumber = hoursWorkedNumber;
        this.hourPay = hourPay;
    }


    @Override
    public String toString() {
        return id + ") " + department + ", " + surnameAndInitials + ", " + position + ", " + qualification + ", " +
                hoursWorkedNumber + ", " + hourPay;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public String getSurnameAndInitials() {
        return surnameAndInitials;
    }

    public String getPosition() {
        return position;
    }

    public String getQualification() {
        return qualification;
    }

    public double getHoursWorkedNumber() {
        return hoursWorkedNumber;
    }

    public double getHourPay() {
        return hourPay;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public void setSurnameAndInitials(String surnameAndInitials){
        this.surnameAndInitials = surnameAndInitials;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public void setHoursWorkedNumber(double hoursWorkedNumber) {
        this.hoursWorkedNumber = hoursWorkedNumber;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setHourPay(double hourPay) {
        this.hourPay = hourPay;
    }
}
