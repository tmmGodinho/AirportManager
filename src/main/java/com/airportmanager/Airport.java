package com.airportmanager;

import java.util.HashMap;
import java.util.Optional;




public class Airport{

    // config left e right qd o prog comeca
    // popular a config

     
    

    protected HashMap<Spot, Boolean> spotList;
    protected HashMap<Plane, Spot> planeList;

    public Airport() {
    }



    public void addPlane(Spot s, Plane p){
        spotList.put(s, true);
    }



    
        
    
}

