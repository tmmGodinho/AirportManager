package com.airportmanager.planemanager;

import java.util.HashSet;

public class Lane extends Spot{


    public Lane(){
        this.connectedSpots = new HashSet<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getAirportType() {
        return "Lane";
    }

    @Override
    public HashSet<Spot> getConnectedSpots() {
        return this.connectedSpots;
    }

    @Override
    public void addToConnectedSpots(Spot s) {
        this.connectedSpots.add(s);
    }


}