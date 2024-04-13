package com.airportmanager;

public class AirportUtils {

    public static Airport genBasicConfig() {
        //init objects
        Airport airport = new Airport();
        Lane lane1 = new Lane();
        Lane lane2 = new Lane();
        Parking parking1 = new Parking();
        Parking parking2 = new Parking();
        Parking parking3 = new Parking();
        Parking parking4 = new Parking();
        Parking parking5 = new Parking();
        Parking parking6 = new Parking();
        Parking parking7 = new Parking();
        Parking parking8 = new Parking();
        Plane plane1 = new Plane(lane2);
        // fill connectedSpots
        parking1.addToConnectedSpots(lane1);
        parking2.addToConnectedSpots(lane1);
        parking3.addToConnectedSpots(lane1);
        parking4.addToConnectedSpots(lane1);
        parking5.addToConnectedSpots(lane2);
        parking6.addToConnectedSpots(lane2);
        parking7.addToConnectedSpots(lane2);
        parking8.addToConnectedSpots(lane2);
        lane1.addToConnectedSpots(lane2);
        // add spots to airport
        airport.addSpot(lane1);
        airport.addSpot(lane2);
        airport.addSpot(parking1);
        airport.addSpot(parking2);
        airport.addSpot(parking3);
        airport.addSpot(parking4);
        airport.addSpot(parking5);
        airport.addSpot(parking6);
        airport.addSpot(parking7);
        airport.addSpot(parking8);
        // add planes
        airport.addPlane(plane1.getCurrentSpot(), plane1);
        return airport;
    }




}
