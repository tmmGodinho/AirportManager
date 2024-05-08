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

    public AirportConfig getEastConfig() {
        return eastConfig;
    }

    public AirportConfig getWestConfig() {
        return westConfig;
    }

    public Facing getFacing() {
        return facing;
    }

    public Spot getSpotFromId(String id){
        Spot Aux = null;
        for( Spot s : this.eastConfig.spotList.keySet()){
            if( s.getId().equals(id)) {
                Aux = s;
                return s;
            }            
        }
        return Aux;
    }

    public void populateNewPlane(Plane plane, Spot spot){
        this.eastConfig.planeList.put(plane, spot);
        this.westConfig.planeList.put(plane, spot);
        for (Spot entry: this.eastConfig.spotList.keySet()) {
            if (entry.getId().equals(spot.id)) this.eastConfig.spotList.put(entry, true);
        }
        for (Spot entry: this.westConfig.spotList.keySet()) {
            if (entry.getId().equals(spot.id)) this.westConfig.spotList.put(entry, true);
        }
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
