package com.airportmanager;

import java.util.HashSet;

public class Plane implements AirportInterface{

    protected Spot currentSpot;

    Plane(Spot s){
        this.currentSpot = s;
    }


    @Override
    public String getAirportType() {
        return "Plane";
    }

    public Spot getCurrentSpot(){
        return currentSpot;
    }



    @Override
    public HashSet<Spot> getConnectedSpots() {
        return currentSpot.getConnectedSpots();
    }

    




}