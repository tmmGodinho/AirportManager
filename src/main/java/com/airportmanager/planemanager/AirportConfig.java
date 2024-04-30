package com.airportmanager.planemanager;

import java.util.HashMap;




public class AirportConfig {


    final protected Facing facing;
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
            if (!spotList.get(s)) {
                // mark spot as occupied on spotList + put the plane on planeList
                spotList.put(s, true);
                planeList.put(p, s);
            }
        }
    }

    public void movePlane(Plane p, Spot s){
        // if new spot is connected to current plane spot + if spot is empty on spotList
        if(p.currentSpot.getConnectedSpots().contains(s) && !this.spotList.get(s)) {
            // clear current spot on spotList
            this.spotList.put(p.currentSpot, false);
            // set current spot to new spot
            p.setCurrentSpot(s);
            planeList.put(p, s);
            // set new spot to occupied on spotList
            this.spotList.put(s, true);
            //change spot attribute on plane
            p.setCurrentSpot(s);
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

