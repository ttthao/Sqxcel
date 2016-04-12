package com.example.ttruong.sqxel;

/**
 * Created by TTruong on 3/22/16.
 */
public class item {

    private String localCode,
            date,
            unitNum,
            vinNum,
            licenseNum,
            code,
            make,
            model,
            color,
            workDone;

    private int mileage,
            year,
            price;

    /*** Ctor ***/
    public item() {
    }

    public item(String localCode, String date, String unitNum, String vinNum, String licenseNum,
                String code, String make, String model, String color, String workDone,
                int mileage, int year, int price) {

        this.localCode = localCode;
        this.date = date;
        this.unitNum = unitNum;
        this.vinNum = vinNum;
        this.licenseNum = licenseNum;
        this.code = code;
        this.make = make;
        this.model = model;
        this.color = color;
        this.workDone = workDone;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }

    /*** GETTERS ***/

    public String getLocalCode() {
        return localCode;
    }

    public String getDate() {
        return date;
    }

    public String getUnitNum() {
        return unitNum;
    }

    public String getVinNum() {
        return vinNum;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public String getCode() {
        return code;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getWorkDone() {
        return workDone;
    }

    public int getMileage() {
        return mileage;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }

    /*** SETTERS */

    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
    }

    public void setVinNum(String vinNum) {
        this.vinNum = vinNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setWorkDone(String workDone) {
        this.workDone = workDone;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}