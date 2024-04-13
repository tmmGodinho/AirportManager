package com.airportmanager;

// import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

/**
 * Unit test for simple App.
 */
public class AirportDeserializerTest 
{
    @Test
    public void givenJsonArrayOfFoos_whenDeserializingToArray_thenCorrect() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        // gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.registerTypeAdapter(Airport.class, new AirportDeserializer());
        Gson gson = gsonBuilder.create();
        try (Reader reader = new FileReader("./test_config.json")) {

            // Convert JSON File to Java Object
            Airport airport = gson.fromJson(reader, Airport.class);
                    
            // print airport 
            System.out.println(airport);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
