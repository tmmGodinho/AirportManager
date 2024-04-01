package com.airportmanager;

public class Plane implements AirportInterface{

    private int location;


    @Override
    public String getAirportType() {
        return "Plane";
    }

    @Override
    public int getAirportLocation() {
        return this.location;
    }

    




}