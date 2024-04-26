package com.airportmanager.planemanager;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
TODO: big parking - small parking
TODO: MAKE TESTCONFIG.JSON HAVE THE ACTUAL CONFIG

*/



public class AirportService extends Application {
//disgusting
    private static Airport airportToGUI;

    public static void main ( String[] args )
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Airport.class, new AirportDeserializer());
        Gson gson = gsonBuilder.create();
        try (Reader reader = new FileReader("src/main/resources/TestConfig.json")) {

            // Convert JSON File to Java Object
            Airport airport = gson.fromJson(reader, Airport.class);

            // Save airport as attribute to pass to GUi
            setAirport(airport);
                    
            // print airport
            airport.print();
/*
             for (Map.Entry<Spot, Boolean> spotEntry : airportConfig.spotList.entrySet()) {
                Spot spot = spotEntry.getKey();
                System.out.println(spot.getAirportType());
                System.out.println(spot + " Connected: " + spot.getConnectedSpots());
            }
*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO: THIS LAUNCHES GUI
        launch(args);


    }

    //TODO:

    @Override
    public void start(Stage stage) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("slice.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("slice.fxml"));
        Parent root = loader.load();





        Controller controller = loader.getController();
        controller.setAirport(airportToGUI);

        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void setAirport(Airport airport){
        airportToGUI = airport;
    }

}


