package com.airportmanager;

import java.util.LinkedList;

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

    @Override
    public LinkedList<Spot> getConnectedSpots() {
        return this.getConnectedSpots();
    }

    




}