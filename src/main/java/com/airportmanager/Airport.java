package com.airportmanager;

import java.util.HashMap;
import java.util.Optional;




public class Airport{

    // config left e right qd o prog comeca
    // popular a config

     
    

    protected HashMap<Spot, Optional<Plane>> spotList;

    public Airport() {
    }



    public void addPlane(Spot s, Optional<Plane> p){
        spotList.put(s, p);
    }



    
        
    
}

