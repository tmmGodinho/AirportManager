package com.airportmanager.planemanager;

import java.util.HashMap;
import java.util.Map;

public class Airport {



    private Facing facing;
    private Map<String, Spot> spotList;     //spotId, Spot

    private Map<String, String> wherePlaneAt;  //spotId , PlaneId

    private Map<String, Plane> planeList;  // planeId, Plane




    public Airport(Map<String, Spot> spotList){
        this.spotList = spotList;
        this.wherePlaneAt = new HashMap<>();
        this.planeList = new HashMap<>();
    }

    public void setFacing(Facing facing) {
        this.facing = facing;
    }

    public Facing getFacing() {
        return facing;
    }

    public Map<String, Spot> getSpotList() {
        return spotList;
    }

    public Map<String, String> getWherePlaneAt() {
        return wherePlaneAt;
    }

    public Map<String, Plane> getPlaneList() {
        return planeList;
    }

    public void populateNewPlane(Plane plane, Spot spot){
        // if spawn spot is a Lane
        // if spot is marked as empty on spotList
        // mark spot as occupied on spotList + put the plane on planeList
        if(spot.getClass() == Lane.class && !spot.isOccupied){
            spot.setIsOccupied(true);
            wherePlaneAt.put(spot.id, plane.id);
            planeList.put(plane.id, plane);
        }
    }

    public void removePlane(Plane plane, Spot spot){
        //delete from planeList and wherePlaneAt and set spot isOccupied to false
        wherePlaneAt.remove(spot.id);
        spot.setIsOccupied(false);
        planeList.remove(plane.id);
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
