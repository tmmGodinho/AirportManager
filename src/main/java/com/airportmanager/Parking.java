package com.airportmanager;

public class Parking extends Spot{

    @Override
    public String getAirportType() {
        return "Parking";
    }

    @Override
    public int getAirportLocation() {
        return this.location;
    }

    


}