package com.airportmanager.planemanager;

import java.util.HashSet;

public class Plane implements AirportInterface{

    protected String id;
    protected Spot currentSpot;



    protected Facing facing;

    Plane(String id, Spot spot, Facing facing){
        this.id = id;
        this.currentSpot = spot;
        this.facing = facing;
    }


    @Override
    public String getId() {
        return id;
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

    public Facing getFacing(){
        return facing;
    }
    public void setFacing(Facing facing) {
        this.facing = facing;
    }

    @Override
    public HashSet<Spot> getConnectedSpots() {
        return currentSpot.getConnectedSpots();
    }

    




}