package com.airportmanager.planemanager;

import java.util.HashSet;


public abstract class Spot implements AirportInterface{



    protected String id;
    protected HashSet<Spot> connectedSpots;


    public abstract String getId();

    public abstract void setId(String id);

    public abstract String getAirportType();
    
    public abstract HashSet<Spot> getConnectedSpots();

    public abstract void addToConnectedSpots(Spot s);






    
}