package com.airportmanager;


import com.google.gson.JsonArray;                       
import com.google.gson.JsonElement;                       
import com.google.gson.JsonObject;                       
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Supplier;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParseException;


public class AirportDeserializer implements JsonDeserializer<Airport> {
    @Override
    public Airport deserialize(JsonElement jsonElement, Type type, 
                             JsonDeserializationContext context) 
                             throws JsonParseException {
        Airport airport = new Airport();
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray parkingJsonArray = (JsonArray) jsonObject.get("parking");
        JsonArray laneJsonArray = (JsonArray) jsonObject.get("lanes");
        HashMap<String, Spot> spotMap = new HashMap<>();
        parseSpotLists(airport, spotMap, parkingJsonArray, Parking::new);
        parseSpotLists(airport, spotMap, laneJsonArray, Lane::new);
        JsonObject connectionsJsonObject = (JsonObject) jsonObject.get("connections");
        connectionsJsonObject.keySet().forEach(fromKey ->
        {
            Spot from = spotMap.get(fromKey);
            JsonArray connectList = (JsonArray) connectionsJsonObject.get(fromKey);
            Iterator<JsonElement> it = connectList.iterator();
            while (it.hasNext()){
                String toKey = it.next().getAsString();
                Spot to = spotMap.get(toKey);
                from.addToConnectedSpots(to);
                if (!(from instanceof Lane && to instanceof Lane)) {
                    to.addToConnectedSpots(from);
                }
            }
        });
        return airport;
    }

    private void parseSpotLists(Airport airport, HashMap<String, Spot> spotMap, JsonArray spotList, Supplier<Spot> spotConstructor) {
        Iterator<JsonElement> it = spotList.iterator();
        while (it.hasNext()) {
            String spotString = it.next().getAsString();
            Spot spot = spotConstructor.get();
            spotMap.put(spotString, spot);
            airport.addSpot(spot);
        }
    }
}