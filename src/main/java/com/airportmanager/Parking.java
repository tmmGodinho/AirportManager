package com.airportmanager;

import java.util.HashSet;

public class Parking extends Spot{



    public Parking() {
        this.connectedSpots = new HashSet<>();
    }

    @Override
    public String getAirportType() {
        return "Parking";
    }


    public HashSet<Spot> getConnectedSpots() {
        return this.connectedSpots;
    }

    @Override
    public void addToConnectedSpots(Spot s) {
        this.connectedSpots.add(s);
    }


}