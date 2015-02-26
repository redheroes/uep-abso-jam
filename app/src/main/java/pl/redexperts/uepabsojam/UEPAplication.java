package pl.redexperts.uepabsojam;

import android.accounts.Account;
import android.app.Application;
import android.location.Location;

import java.util.ArrayList;
import java.util.Calendar;

import pl.redexperts.uepabsojam.model.Jam;

public class UEPAplication extends Application {

    private static Account             account;
    private static Application instance;
    private String accessToken = null;
    private Location userLocation;
    private Calendar lastLocationUpdateDate;
    public static ArrayList<Jam> jams;

    public UEPAplication() {
        super();
        instance = this;
    }

    public static Application getInstance() {
        return instance;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Location getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(Location userLocation) {

        Calendar currentDate = Calendar.getInstance();
        lastLocationUpdateDate = currentDate;

        this.userLocation = userLocation;
    }

    public Calendar getLastLocationUpdateDate() {
        return lastLocationUpdateDate;
    }
}
