package com.airportmanager.planemanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Airport {

//TODO: revert wherePlaneAt + for loop changeParkedFacings (we brute force those)

    private Facing facing;
    private Map<String, Spot> spotList;     //spotId, Spot

    private Map<String, String> wherePlaneAt;  //  spotId, PlaneId

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

    public void populateNewPlane(Plane plane, Spot spot){  // mark spot as occupied on spotList + put the plane on planeList

            spot.setIsOccupied(true);
            wherePlaneAt.put(spot.getId(),plane.getId());
            planeList.put(plane.getId(), plane);
    }

    public void removePlane(Plane plane,Spot spot){
        //delete from planeList and wherePlaneAt and set spot isOccupied to false
        spot.setIsOccupied(false);
        wherePlaneAt.remove(plane.getId());
        planeList.remove(plane.getId());
    }

    public void movePlane(Plane plane, Spot spot){
//    fromSpot isOccupied to false
//    toSpot isOccupied to true
//    update spotId in wherePlaneAt


    }

//    ArrayList<String> parkedPlaneIds = airport.lookUpParkedPlaneIds();

    public void changeParkedPlaneFacings(){  //this switches the Facing on every parked Plane  TODO:brute (for)ce here + update planeFacing buttons
        for (String planeID : planeList.keySet()){
           if(spotList.get(planeToSpot(planeID)).getClass() == Parking.class){
               planeList.get(planeID).switchFacing();
           }
        }
    }

    public String planeToSpot(String planeId){ //returns which spotID the plane is at
        String thisOne = "";
        for(String spotId : wherePlaneAt.keySet()){
            if(wherePlaneAt.get(spotId).equals(planeId)) thisOne = spotId;
        }
        return thisOne;
    }


    public void print(){
        for (Map.Entry<String, Spot> spotEntry : this.spotList.entrySet()) {
            Spot spot = spotEntry.getValue();
            System.out.println(spot.getId());
            if(spot.getClass() == Parking.class){
                Parking parking = (Parking) spot;
                System.out.println("Parking constraint IDs: " + parking.getConstrainedParkingIds());
            }
            System.out.println(spot.getId() + " Connected WEST: " + spot.getConnectedSpots(Facing.WEST));
            System.out.println(spot.getId() + " Connected EAST: " + spot.getConnectedSpots(Facing.EAST));
        }
    }
}
