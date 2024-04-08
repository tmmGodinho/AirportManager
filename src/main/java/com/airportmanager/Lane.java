package com.airportmanager;

import java.util.LinkedList;

public class Lane extends Spot{


    public Lane(){
        this.connectedSpots = new LinkedList<Spot>();
    }


    @Override
    public String getAirportType() {
        return "Lane";
    }

    @Override
    public int getAirportLocation() {
        return this.location;
    }

    @Override
    public LinkedList<Spot> getConnectedSpots() {
        return this.getConnectedSpots();
    }

    

}