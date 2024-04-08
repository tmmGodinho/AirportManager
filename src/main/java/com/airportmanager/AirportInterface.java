package com.airportmanager;

import java.util.LinkedList;

public interface AirportInterface {

    public String getAirportType();

    public int getAirportLocation();
    
    public LinkedList<Spot> getConnectedSpots();




}