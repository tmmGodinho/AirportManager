package com.airportmanager.planemanager;

import java.util.HashSet;

public class Plane {

    protected String id;



    protected Facing facing;

    Plane(String id, Spot spot, Facing facing){
        this.id = id;
        this.facing = facing;
    }


    public String getId() {
        return id;
    }

    public String getAirportType() {
        return "Plane";
    }

    public Facing getFacing(){
        return facing;
    }
    public void setFacing(Facing facing) {
        this.facing = facing;
    }
    public void switchFacing(){
        if(facing == Facing.WEST){
            facing = Facing.EAST;
        }
        else{
            facing = Facing.WEST;
        }
    }


    




}