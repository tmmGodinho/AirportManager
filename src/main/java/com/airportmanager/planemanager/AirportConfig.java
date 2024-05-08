package com.airportmanager.planemanager;

import java.util.HashMap;




public class AirportConfig {


    final protected Facing facing;
    //this is dumb
    protected HashMap<Spot, Boolean> spotList;
    protected HashMap<Plane, Spot> planeList;

    public AirportConfig(Facing f) {
        this.spotList = new HashMap<>();
        this.planeList = new HashMap<>();
        this.facing = f;
    }


    public void addPlane(Spot s, Plane p){
        // if spawn spot is a Lane
        if (s.getClass() == Lane.class) {
            // if spot is marked as empty on spotList
            if (!s.getIsOccupied()) {
                // mark spot as occupied on spotList + put the plane on planeList
                spotList.put(s, true); //TODO: remove old yucky line
                s.setIsOccupied(true);
                planeList.put(p, s);
            }
        }
    }



    public void removePlane(Plane p){
        //check if in a lane
        if(p.getCurrentSpot().getClass() == Lane.class) {
            //remove from planeList
            this.planeList.remove(p);
            //normalize spot on spotList
            this.spotList.put(p.getCurrentSpot(), false);
        }
    }

    public void addSpot(Spot s){
        spotList.putIfAbsent(s, false);
    }




    
        
    
}

