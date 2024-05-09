package com.airportmanager.planemanager;


import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


public class AirportDeserializer implements JsonDeserializer<Airport> {
    @Override
    public Airport deserialize(JsonElement jsonElement, Type type,
                                     JsonDeserializationContext context)
                             throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray parkingJsonArray = (JsonArray) jsonObject.get("parking");
        JsonArray laneJsonArray = (JsonArray) jsonObject.get("lanes");


        HashMap<String, Spot> spotMap = new HashMap<>();
        parseSpotLists(spotMap, parkingJsonArray, Parking::new);
        parseSpotLists(spotMap, laneJsonArray, Lane::new);
        JsonObject eastObject = (JsonObject) jsonObject.get("east_connections");
        JsonObject westObject = (JsonObject) jsonObject.get("west_connections");

        genConnections(spotMap, eastObject, westObject);
        if(jsonObject.has("constrained_parking")){
            JsonObject constrainedParkingObject = (JsonObject) jsonObject.get("constrained_parking");
            genConstraints(spotMap, constrainedParkingObject);
        }
        return new Airport(spotMap);
    }

    private void parseSpotLists(Map<String, Spot> spotMap, JsonArray spotList, Supplier<Spot> spotConstructor) {
        Iterator<JsonElement> it = spotList.iterator();
        while (it.hasNext()) {
            String spotString = it.next().getAsString();
            Spot spot = spotConstructor.get();
            spot.setId(spotString);
            spotMap.put(spotString, spot);
        }
    }

    private void genConnections(Map<String, Spot> spotMap, JsonObject westObject, JsonObject eastObject){
        for (String laneId : westObject.keySet()) {
            Spot spotFrom = spotMap.get(laneId);
            JsonArray laneConnections = (JsonArray) westObject.get(laneId);
            Iterator<JsonElement> it = laneConnections.iterator();
            while (it.hasNext()){
                String toKey = it.next().getAsString();
                Spot spotTo = spotMap.get(toKey);
                spotFrom.addToConnectedSpots(spotTo, Facing.WEST);
                if (!(spotFrom instanceof Lane && spotTo instanceof Lane)) {
                    spotTo.addToConnectedSpots(spotFrom, Facing.WEST);
                }
            }

        }
        for (String laneId : eastObject.keySet()) {
            Spot spotFrom = spotMap.get(laneId);
            JsonArray laneConnections = (JsonArray) eastObject.get(laneId);
            Iterator<JsonElement> it = laneConnections.iterator();
            while (it.hasNext()){
                String toKey = it.next().getAsString();
                Spot spotTo = spotMap.get(toKey);
                spotFrom.addToConnectedSpots(spotTo, Facing.EAST);
                if (!(spotFrom instanceof Lane && spotTo instanceof Lane)) {
                    spotTo.addToConnectedSpots(spotFrom, Facing.EAST);
                }
            }
        }

    }

    private void genConstraints(Map<String, Spot> spotMap, JsonObject constrainedParkingObject){
        for (String fromKey : constrainedParkingObject.keySet()) {
            Parking parkingFrom = (Parking)spotMap.get(fromKey);
            JsonArray parkingConstraints = (JsonArray) constrainedParkingObject.get(fromKey);
            Iterator<JsonElement> it = parkingConstraints.iterator();
            while (it.hasNext()){
                String toKey = it.next().getAsString();
                Parking parkingTo = (Parking) spotMap.get(toKey);
                parkingFrom.addToConstrainedParking(toKey);
                parkingTo.addToConstrainedParking(fromKey);
            }
        }
    }

}