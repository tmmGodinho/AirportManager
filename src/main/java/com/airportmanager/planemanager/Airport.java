package com.airportmanager.planemanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Airport {

//TODO: revert wherePlaneAt + for loop changeParkedFacings (we brute force those)

    private Facing facing;
    private int planeCounter;
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

    public void populateNewPlane(String spotId){  // mark spot as occupied on spotList + put the plane on planeList
            String planeId = "Plane" + planeCounter++;
            Plane newPlane = new Plane(planeId, this.facing);
            wherePlaneAt.put(spotId,newPlane.getId());
            planeList.put(planeId, newPlane);
    }

    public void removePlane(String spotId){
        //delete from planeList and wherePlaneAt and set spot isOccupied to false
        String planeId = wherePlaneAt.get(spotId);
        wherePlaneAt.remove(spotId);
        planeList.remove(planeId);
    }

    public void movePlane(String fromSpotId, String toSpotId){
        //update spotId in wherePlaneAt
        String planeId = wherePlaneAt.remove(fromSpotId);
        wherePlaneAt.put(toSpotId, planeId);
        if(isSpotParking(toSpotId)) planeList.get(planeId).setFacing(facing);
    }

//    ArrayList<String> parkedPlaneIds = airport.lookUpParkedPlaneIds();

    public void changeParkedPlaneFacings(){  //this switches the Facing on every parked Plane
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

    public boolean isSpotOccupied(String spotId){ //TODO:refactor isSpotOccupieds
        return wherePlaneAt.containsKey(spotId);
    }
    public boolean isSpotParking(String spotId){
        Spot planeSpot = spotList.get(spotId);
        return planeSpot.getClass() == Parking.class;
    }
    public boolean isSpotLane(String spotId){
        Spot planeSpot = spotList.get(spotId);
        return planeSpot.getClass() == Lane.class;
    }
    public HashSet<String> getConnectedSpotIds(String spotId){     //returns connected spot ids according to occupying plane facing
        HashSet<String> connectedSpotIds = new HashSet<>();
        String planeId = wherePlaneAt.get(spotId);
        Facing planeFacing = planeList.get(planeId).getFacing();
        for(Spot s : spotList.get(spotId).getConnectedSpots(planeFacing)){
            connectedSpotIds.add(s.getId());
        }
        return connectedSpotIds;
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
