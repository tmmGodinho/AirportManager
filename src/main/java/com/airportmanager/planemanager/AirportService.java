package com.airportmanager.planemanager;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.airportmanager.planemanager.deserializer.AirportDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




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
            airport.setFacing(Facing.WEST);
            setAirport(airport);
                    
            // print airport
            airport.print();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //THIS LAUNCHES GUI
        launch(args);


    }


    @Override
    public void start(Stage stage) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("slice.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("slice.fxml"));
        Parent root = loader.load();


        Controller controller = loader.getController();
        controller.setAirport(airportToGUI);
        controller.setOpCode(OPCode.NONE);
        controller.getAirportFacingButton().setText(getAirportToGUI().getFacing().toString());
        controller.startPlaneLineList();

        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void setAirport(Airport airport){
        airportToGUI = airport;
    }
    public static Airport getAirportToGUI() {
        return airportToGUI;
    }
}



