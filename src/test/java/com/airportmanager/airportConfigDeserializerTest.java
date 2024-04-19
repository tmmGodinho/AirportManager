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
public class airportConfigDeserializerTest
{
    @Test
    public void givenJsonArrayOfFoos_whenDeserializingToArray_thenCorrect() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        // gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.registerTypeAdapter(AirportConfig.class, new AirportDeserializer());
        Gson gson = gsonBuilder.create();
        try (Reader reader = new FileReader("./test_config.json")) {

            // Convert JSON File to Java Object
            AirportConfig airportConfig = gson.fromJson(reader, AirportConfig.class);
                    
            // print airport 
            System.out.println(airportConfig);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
