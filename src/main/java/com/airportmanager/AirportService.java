package com.airportmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AirportService 
{
    public static void main( String[] args )
    {
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println( "Hello World!" );

        // start some spots (lane+parking)
        Airport airport = AirportUtils.genBasicConfig();

        // start some planes phase 2
        // String gsonString = gson.toJson(airport);

        //read testconfig.json


        //make airport object out of testconfig.json

    }


}



