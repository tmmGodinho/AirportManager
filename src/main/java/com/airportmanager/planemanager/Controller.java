package com.airportmanager.planemanager;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.image.Image;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class Controller {
    @FXML
    private Label welcomeText;
    @FXML
    private Airport airport;
    @FXML
    private VBox myVBox;
    @FXML
    private ToggleButton createPlaneButton;
    @FXML
    private ToggleButton deletePlaneButton;
    @FXML
    private ToggleButton airportFacingButton;

    private Parent root;
    private int planeCounter;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    public void thisWorks(javafx.event.ActionEvent actionEvent) {
        System.out.println("Button Pressed! "+ actionEvent.getSource() );
    }

    public void setAirport(Airport airport){
        this.airport = airport;
    }

    public void setRoot(Parent root){
        this.root = root;
    }

    public void printAirport(){
        this.airport.print();
    }

    // make procedure
    // do behind scenes Airport alteration
    // do UI update   getChildrenUnmodifiable   .isVisible();


    //phase 1
    //TODO: finish deserializer + .json forever(tm)
    //TODO: spawn airplane (1/2 done, missing discriminator condition [which bp according to facing + all parks])
    //TODO: make airplane move
    //TODO: delete airplane
    //phase 2
    //TODO: cluster parkings

    @FXML
    public void createPlaneButtonPressed(javafx.event.ActionEvent actionEvent){
        if(deletePlaneButton.isSelected()) deletePlaneButton.setSelected(false);
    }

    @FXML
    public void deletePlaneButtonPressed(javafx.event.ActionEvent actionEvent){
        if(createPlaneButton.isSelected()) createPlaneButton.setSelected(false);
    }

    @FXML
    public void laneButtonPressed(javafx.event.ActionEvent actionEvent){
        if(createPlaneButton.isSelected()) spawnAirplane(actionEvent);
        if(deletePlaneButton.isSelected()) deleteAirplane(actionEvent);
    }


    public String getLaneButtonLocation(javafx.event.ActionEvent actionEvent){
        Button button = (Button)actionEvent.getSource();
        return button.getId();
    }

    public void spawnAirplane(javafx.event.ActionEvent actionEvent){
        Button button = (Button) actionEvent.getSource();
        String planeID = "#" + button.getText() + "Plane";
        //myVBox.lookup(planeID); planeID has to start with #
        String buttonId = this.getLaneButtonLocation(actionEvent);
        //TODO: change whichConfig
        //TODO: spawn plane
        //check airport config spotlist for same id as button to see if there is a plane there
        Spot planeSpot = airport.getSpotList().get(buttonId);
        if (!planeSpot.equals(null)) {
            //spawn plane in buttonId Spot
            Plane newPlane = new Plane("Plane" + planeCounter, planeSpot, airport.getFacing());
            planeCounter++;
            airport.populateNewPlane(newPlane, planeSpot);
            //make plane.png appear
            myVBox.lookup("#" + buttonId + "Plane").setVisible(true);
        }
    }

    public void deleteAirplane(javafx.event.ActionEvent actionEvent){
        String buttonId = this.getLaneButtonLocation(actionEvent);
        Spot planeSpot = airport.getSpotList().get(buttonId);
        String planeIdToRemove = airport.getWherePlaneAt().get(planeSpot.id);
        //check if spot has a plane TODO:proper delete conditions
        if(planeSpot.isOccupied) { //check if delete is legal
            Plane planeToRemove = airport.getPlaneList().get(planeIdToRemove);
            //delete from planeList and wherePlaneAt
            airport.removePlane(planeToRemove, planeSpot);
            //TODO:make picture invisible
            //make picture invis
            myVBox.lookup("#" + buttonId + "Plane").setVisible(false);
        }
    }


}