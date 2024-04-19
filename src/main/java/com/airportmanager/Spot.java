package com.airportmanager;

import java.util.HashSet;


public abstract class Spot implements AirportInterface{


    protected HashSet<Spot> connectedSpots;

    // given a spot where i can go, connections structure

    public abstract String getAirportType();
    
    public abstract HashSet<Spot> getConnectedSpots();

    public abstract void addToConnectedSpots(Spot s);






    
}