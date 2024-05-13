package com.airportmanager.planemanager;

import java.util.HashSet;

public class Parking extends Spot{


    private HashSet<String> constrainedParkingIds;

    public Parking() {
        this.eastConnectedSpots = new HashSet<>();
        this.westConnectedSpots = new HashSet<>();
        this.constrainedParkingIds = new HashSet<>();
    }

    @Override
    public String toString(){
        return "Parking[" + this.id + "]";
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

    public HashSet<String> getConstrainedParkingIds() {
        return constrainedParkingIds;
    }

    public void addToConstrainedParking(String parkId){
        this.constrainedParkingIds.add(parkId);
    }





}


