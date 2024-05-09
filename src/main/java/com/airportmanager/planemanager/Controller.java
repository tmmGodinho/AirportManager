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

    private OPCode opCode;
    private String selectedSpotId;
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

    //phase 2

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
        //save spotId when isOccupied
        String buttonId = this.getLaneButtonLocation(actionEvent);
        Spot planeSpot = airport.getSpotList().get(buttonId);

        if(createPlaneButton.isSelected()) {
            spawnAirplane(actionEvent);
        }
        if(deletePlaneButton.isSelected()) {
            deleteAirplane(actionEvent);
        }
        if(selectedSpotId!=null) moveAirplane(actionEvent);
        else if (planeSpot.isOccupied){
            selectedSpotId = planeSpot.getId();
        }
        selectedSpotId = null;
    }


    public String getLaneButtonLocation(javafx.event.ActionEvent actionEvent){
        Button button = (Button)actionEvent.getSource();
        return button.getId();
    }

    public void spawnAirplane(javafx.event.ActionEvent actionEvent){
        Button button = (Button) actionEvent.getSource();
        String planeID = "#" + button.getText() + "Plane"; //myVBox.lookup(planeID); planeID has to start with #
        String buttonId = this.getLaneButtonLocation(actionEvent);
        //check airport spotlist for same id as button to see if there is a plane there
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
        //check if spot has a plane TODO:proper delete conditions (parking or PB edge according to Facing)
        if(planeSpot.getIsOccupied()) { //check if delete is legal
            Plane planeToRemove = airport.getPlaneList().get(planeIdToRemove);
            //delete from planeList and wherePlaneAt
            airport.removePlane(planeToRemove, planeSpot);
            //make picture invis
            myVBox.lookup("#" + buttonId + "Plane").setVisible(false);
        }
    }

    //TODO: make airplane move
    //TODO:every click checks if lastSelection, if not, set to true
    //TODO:set operation Label
    //TODO:make movement paths visible, crosses for disrupted connectedSpots
    //click 2
    //TODO:if move is legal: set fromSpot isOccupied to False, set toSpot isOccupied to True
    //                  call airport.movePlane for airport guts rearrange
    //                  make fromSpotPlane invis, set toSpotPlane to visible

    public void moveAirplane(javafx.event.ActionEvent actionEvent){

    }
//check if spot is a legal move (connectedlists)
    //check if spot isOccupied
    //if all good call airport.moveAirplane
    //make fromPicture invis and toPicture visible

}