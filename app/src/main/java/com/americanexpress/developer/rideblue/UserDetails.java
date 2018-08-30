package com.americanexpress.developer.rideblue;

public class UserDetails {
    String tokenID;
    String houseAddress;
    String officeAddress;
    String carDetails;
    public UserDetails(String tokenID,String houseAddress, String officeAddress, String carDetails) {
        this.houseAddress = houseAddress;
        this.officeAddress = officeAddress;
        this.carDetails = carDetails;
        this.tokenID = tokenID;
    }
    public String getHouseAddress() {
        return houseAddress;
    }
    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }
    public String getOfficeAddress() {
        return officeAddress;
    }
    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }
    public String getCarDetails() {
        return carDetails;
    }
    public void setCarDetails(String carDetails) {
        this.carDetails = carDetails;
    }
    public String getTokenID() {
        return tokenID;
    }
    public void setTokenID(String tokenID) {
        this.tokenID = tokenID;
    }
}