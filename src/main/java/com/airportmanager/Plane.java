package com.airportmanager;

import java.util.HashSet;

public class Plane implements AirportInterface{

    protected Spot currentSpot;
    protected Facing facing;

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

    public void setCurrentSpot(Spot nextSpot) {
        this.currentSpot = nextSpot;
    }

    @Override
    public HashSet<Spot> getConnectedSpots() {
        return currentSpot.getConnectedSpots();
    }

    




}