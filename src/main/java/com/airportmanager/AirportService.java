package com.airportmanager;

import com.google.gson.Gson;

public class AirportService 
{
    public static void main( String[] args )
    {
        Gson gson = new Gson();
        System.out.println( "Hello World!" );
        Airport airport = new Airport();
        String gsonString = gson.toJson(airport);
        System.out.println(gsonString);
    }
}



