package com.airportmanager;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AirportService 
{
    public static void main( String[] args )
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Airport.class, new AirportDeserializer());
        Gson gson = gsonBuilder.create();
        try (Reader reader = new FileReader("AirportManager/src/main/resources/TestConfig.json")) {

            // Convert JSON File to Java Object
            Airport airport = gson.fromJson(reader, Airport.class);
                    
            // print airport 
             for (Map.Entry<Spot, Boolean> spotEntry : airport.spotList.entrySet()) {
                Spot spot = spotEntry.getKey();
                System.out.println(spot.getAirportType());
                System.out.println(spot + " Connected: " + spot.getConnectedSpots());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}



