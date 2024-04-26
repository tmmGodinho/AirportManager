package com.airportmanager.planemanager;

import java.util.Map;

public class Airport {

    private AirportConfig eastConfig;
    private AirportConfig westConfig;

    private Facing facing;

    public Airport(AirportConfig eastConfig, AirportConfig westConfig){
        this.eastConfig = eastConfig;
        this.westConfig = westConfig;
    }

    public void setFacing(Facing facing) {
        this.facing = facing;
    }

    public void changeConfig(){
        //TODO: this should transfer active planes from active config to new config
    }


    public void print(){
        System.out.println("Printing West Config...");
        for (Map.Entry<Spot, Boolean> spotEntry : this.westConfig.spotList.entrySet()) {
            Spot spot = spotEntry.getKey();
            System.out.println(spot.getAirportType());
            System.out.println(spot + " Connected: " + spot.getConnectedSpots());
        }
        System.out.println("Printing East Config...");
        for (Map.Entry<Spot, Boolean> spotEntry : this.eastConfig.spotList.entrySet()) {
            Spot spot = spotEntry.getKey();
            System.out.println(spot.getAirportType());
            System.out.println(spot + " Connected: " + spot.getConnectedSpots());
        }

    }
}
