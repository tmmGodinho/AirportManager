package com.airportmanager;

import java.util.LinkedList;

public abstract class Spot implements AirportInterface{

    protected int location;
    protected LinkedList<Spot> connectedSpots;

    // given a spot where i can go, connections structure

    public abstract String getAirportType();
    
    public abstract LinkedList<Spot> getConnectedSpots();






    
}