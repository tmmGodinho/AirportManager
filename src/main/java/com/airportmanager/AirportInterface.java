package com.airportmanager;

import java.util.HashSet;

public interface AirportInterface {

    String getAirportType();

    
    HashSet<Spot> getConnectedSpots();




}