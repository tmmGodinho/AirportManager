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
        //AirportConfig airportConfig = new AirportConfig(Facing.);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonObject eastObject = (JsonObject) jsonObject.get("east");
        JsonObject westObject = (JsonObject) jsonObject.get("west");
        AirportConfig eastAirportConfig = genAirportConfig(eastObject, Facing.EAST);
        AirportConfig westAirportConfig = genAirportConfig(westObject, Facing.WEST);
        //TODO: THIS RETURNS AN AIRPORT
        return new Airport(eastAirportConfig, westAirportConfig);
    }

    private AirportConfig genAirportConfig(JsonObject jsonAirport, Facing facing){
        AirportConfig airportConfig = new AirportConfig(facing);
        JsonArray parkingJsonArray = (JsonArray) jsonAirport.get("parking");
        JsonArray laneJsonArray = (JsonArray) jsonAirport.get("lanes");
        HashMap<String, Spot> spotMap = new HashMap<>();
        parseSpotLists(airportConfig, spotMap, parkingJsonArray, Parking::new);
        parseSpotLists(airportConfig, spotMap, laneJsonArray, Lane::new);
        JsonObject connectionsJsonObject = (JsonObject) jsonAirport.get("connections");
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
        return airportConfig;
    }
    
    
    private void parseSpotLists(AirportConfig airportConfig, HashMap<String, Spot> spotMap, JsonArray spotList, Supplier<Spot> spotConstructor) {
        Iterator<JsonElement> it = spotList.iterator();
        while (it.hasNext()) {
            String spotString = it.next().getAsString();
            Spot spot = spotConstructor.get();
            spotMap.put(spotString, spot);
            airportConfig.addSpot(spot);
        }
    }
}