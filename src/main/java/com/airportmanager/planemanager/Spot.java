package com.airportmanager.planemanager;

import java.util.HashSet;


public abstract class Spot{



    protected String id;
    protected HashSet<Spot> eastConnectedSpots;
    protected HashSet<Spot> westConnectedSpots;




    public abstract String getId();



    public abstract void setId(String id);

    public abstract String getAirportType();
    
    public abstract HashSet<Spot> getConnectedSpots(Facing facing);

    public abstract void addToConnectedSpots(Spot s, Facing facing);






    
}