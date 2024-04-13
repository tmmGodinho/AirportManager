package com.airportmanager;

import java.util.HashMap;




public class Airport{

    // config left e right qd o prog comeca
    // popular a config


    

    protected HashMap<Spot, Boolean> spotList;
    protected HashMap<Plane, Spot> planeList;

    public Airport() {
        this.spotList = new HashMap<>();
        this.planeList = new HashMap<>();
    }



    public void addPlane(Spot s, Plane p){
        if (!spotList.get(s)) {
            spotList.put(s, true);
            planeList.put(p, s);
        }
    }

    public void addSpot(Spot s){
        spotList.putIfAbsent(s, true);
    }



    
        
    
}

