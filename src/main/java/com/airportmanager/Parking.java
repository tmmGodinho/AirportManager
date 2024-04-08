package com.airportmanager;

import java.util.LinkedList;

public class Parking extends Spot{


    public Parking() {
        this.connectedSpots = new LinkedList<Spot>();
    }

    @Override
    public String getAirportType() {
        return "Parking";
    }

    @Override
    public int getAirportLocation() {
        return this.location;
    }

    public LinkedList<Spot> getConnectedSpots() {
        return this.connectedSpots;
    } 
    


}