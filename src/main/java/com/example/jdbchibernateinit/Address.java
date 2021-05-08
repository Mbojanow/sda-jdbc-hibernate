package com.example.jdbchibernateinit;

// encja
public class Address {
    private Integer id;
    private String streetName;
    private Integer buildingNumber;
    private Integer houseNumber;
    private Boolean isPrimary;
    private String zipCode;
    private String country;
    private String city;
    private Integer userId;

    public Address(Integer id, String streetName, Integer buildingNumber, Integer houseNumber, Boolean isPrimary, String zipCode, String country, String city, Integer userId) {
        this.id = id;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.houseNumber = houseNumber;
        this.isPrimary = isPrimary;
        this.zipCode = zipCode;
        this.country = country;
        this.city = city;
        this.userId = userId;
    }

    public Address(String streetName, Integer buildingNumber, Integer houseNumber, Boolean isPrimary, String zipCode, String country, String city, Integer userId) {
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.houseNumber = houseNumber;
        this.isPrimary = isPrimary;
        this.zipCode = zipCode;
        this.country = country;
        this.city = city;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
