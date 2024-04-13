package com.airportmanager;

import java.util.HashSet;

public class Plane implements AirportInterface{

    private int location;
    protected Spot currentSpot;



    @Override
    public String getAirportType() {
        return "Plane";
    }


    @Override
    public HashSet<Spot> getConnectedSpots() {
        return currentSpot.getConnectedSpots();
    }

    




}