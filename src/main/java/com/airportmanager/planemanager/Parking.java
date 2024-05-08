package com.airportmanager.planemanager;

import java.util.HashSet;

public class Parking extends Spot{

    //TODO:cluster the parking spots

    public Parking() {
        this.eastConnectedSpots = new HashSet<>();
        this.westConnectedSpots = new HashSet<>();
        this.isOccupied = false;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setIsOccupied(boolean b) {
        this.isOccupied = b;
    }

    @Override
    public boolean getIsOccupied() {
        return this.isOccupied;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getAirportType() {
        return "Parking";
    }


    @Override
    public HashSet<Spot> getConnectedSpots(Facing facing) {
        if(facing==Facing.WEST) return this.westConnectedSpots;
        else return this.eastConnectedSpots;
    }

    @Override
    public void addToConnectedSpots(Spot s, Facing facing) {
        if(facing==Facing.WEST) this.westConnectedSpots.add(s);
        if(facing==Facing.EAST) this.eastConnectedSpots.add(s);
    }


}


