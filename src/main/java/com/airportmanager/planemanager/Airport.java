package com.airportmanager.planemanager;

import java.util.HashMap;
import java.util.Map;

public class Airport {

    private AirportConfig eastConfig;

    private AirportConfig westConfig;

    private Facing facing;
    private Map<String, Spot> spotList;
    private Map<String, String> wherePlaneAt;
    private Map<String, Plane> planeList;




    public Airport(Map<String, Spot> spotList){
        this.eastConfig = null;
        this.westConfig = null;
        this.spotList = spotList;
        this.wherePlaneAt = new HashMap<>();
        this.planeList = new HashMap<>();
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
        for (Spot entry: this.eastConfig.spotList.keySet()){
            if (entry.getId().equals(spot.id)){
                this.eastConfig.spotList.put(entry, true);
                this.eastConfig.planeList.put(plane, entry);
            }
        }
        for (Spot entry: this.westConfig.spotList.keySet()){
            if (entry.getId().equals(spot.id)){
                this.westConfig.spotList.put(entry, true);
                this.westConfig.planeList.put(plane, entry);
            }
        }
    }

    public void changeConfig(){
        //TODO: this should transfer active planes from active config to new config
    }


    public void print(){

        for (Map.Entry<String, Spot> spotEntry : this.spotList.entrySet()) {
            Spot spot = spotEntry.getValue();
            System.out.println(spot.getId());
            System.out.println(spot.getId() + " Connected WEST: " + spot.getConnectedSpots(Facing.WEST));
            System.out.println(spot.getId() + " Connected EAST: " + spot.getConnectedSpots(Facing.EAST));
        }

    }
}
