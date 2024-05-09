package com.airportmanager.planemanager;

import java.util.HashSet;

public class Parking extends Spot{

    //TODO:make tostring to make ricky happy :) format "Lane[315]"



    private HashSet<String> constrainedParkingIds;

    public Parking() {
        this.eastConnectedSpots = new HashSet<>();
        this.westConnectedSpots = new HashSet<>();
        this.constrainedParkingIds = new HashSet<>();
        this.isOccupied = false;
    }


//    protected String id;
//    protected HashSet<Spot> eastConnectedSpots;
//    protected HashSet<Spot> westConnectedSpots;
//
//
//
//    protected boolean isOccupied;
//



    @Override
    public String toString(){
        return "Parking[" + this.id + "]";
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

    public HashSet<String> getConstrainedParkingIds() {
        return constrainedParkingIds;
    }

    public void addToConstrainedParking(String parkId){
        this.constrainedParkingIds.add(parkId);
    }





}


