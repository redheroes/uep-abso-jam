package pl.redexperts.uepabsojam.model;


import java.io.Serializable;
import java.util.Date;

public class Jam implements Serializable{
    long id;
    String name;
    String street;
    String houseNumber;
    String city;
    Integer neededPeopleNumber;
    Integer currentPeopleNumber = 0;
    Date date;
    String description;

    public Jam() {
    }

    public Jam(long id, String name, Integer neededPeopleNumber, Integer currentPeopleNumber, Date date) {
        this.id = id;
        this.name = name;
        this.neededPeopleNumber = neededPeopleNumber;
        this.currentPeopleNumber = currentPeopleNumber;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getNeededPeopleNumber() {
        return neededPeopleNumber;
    }

    public void setNeededPeopleNumber(Integer neededPeopleNumber) {
        this.neededPeopleNumber = neededPeopleNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCurrentPeopleNumber() {
        return currentPeopleNumber;
    }

    public void setCurrentPeopleNumber(Integer currentPeopleNumber) {
        this.currentPeopleNumber = currentPeopleNumber;
    }
}
