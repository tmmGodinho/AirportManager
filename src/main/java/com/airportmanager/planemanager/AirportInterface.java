package com.airportmanager.planemanager;

import java.util.HashSet;

public interface AirportInterface {

    String getId();

    String getAirportType();

    
    HashSet<Spot> getConnectedSpots();




}