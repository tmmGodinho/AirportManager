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
    private Label currentOperation;
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
    @FXML
    private ToggleButton movePlaneButton;




    private OPCode opCode;
    private String selectedSpotId;
    private Parent root;
    private int planeCounter;


    public void thisWorks(javafx.event.ActionEvent actionEvent) {
        System.out.println("Button Pressed! "+ actionEvent.getSource() );
    }

    public void setAirport(Airport airport){
        this.airport = airport;
    }

    public void setRoot(Parent root){
        this.root = root;
    }
    public void setOpCode(OPCode opCode) {
        if(!currentOperation.isVisible()) currentOperation.setVisible(true);
        this.opCode = opCode;
        currentOperation.setText(opCode.toString());
        if(opCode == OPCode.NONE) currentOperation.setVisible(false);
    }

    public OPCode getOpCode() {
        return opCode;
    }
    public ToggleButton getAirportFacingButton() {
        return airportFacingButton;
    }
    public void printAirport(){
        this.airport.print();
    }

    // make procedure
    // do behind scenes Airport alteration
    // do UI update   getChildrenUnmodifiable   .isVisible();


    //phase 1
    //TODO: implement OPCODE change on ToggleButton Presses
    //TODO: implement currentOPCODE (label)
    //TODO: properly sided plane.pngs
    //TODO: bling it up with Facing Color + Lines on Select

    //phase 2

    @FXML
    public void createPlaneButtonPressed(javafx.event.ActionEvent actionEvent){
        setOpCode(OPCode.CREATE);
        unselectToggles();
    }

    @FXML
    public void deletePlaneButtonPressed(javafx.event.ActionEvent actionEvent){
        setOpCode(OPCode.DELETE);
        unselectToggles();

    }
    @FXML
    public void movePlaneButtonPressed(javafx.event.ActionEvent actionEvent){
        setOpCode(OPCode.SELECT);
        unselectToggles();
    }
    @FXML
    public void airportFacingButtonPressed(javafx.event.ActionEvent actionEvent){  //Switch Airport Facing + change Parked Facings
        if(airport.getFacing() == Facing.WEST){
            airport.setFacing(Facing.EAST);
            airportFacingButton.setText(airport.getFacing().toString());
        }
        else{
            airport.setFacing(Facing.WEST);
            airportFacingButton.setText(airport.getFacing().toString());
        }
        airport.changeParkedPlaneFacings();
    }

    @FXML
    public void laneButtonPressed(javafx.event.ActionEvent actionEvent){
        //save spotId when isOccupied
        String buttonId = this.getLaneButtonLocation(actionEvent);
        Spot planeSpot = airport.getSpotList().get(buttonId);
        switch (opCode){
            case NONE:
                break;
            case MOVE:
                break;
            case CREATE:
                spawnAirplane(actionEvent);
                break;
            case DELETE:
                deleteAirplane(actionEvent);
                break;
            case SELECT:
                break;
        }
//        if(selectedSpotId!=null) moveAirplane(actionEvent);
//    NONE,
//    CREATE,
//    SELECT,
//    MOVE,
//    DELETE
//        else if (planeSpot.isOccupied){
//            selectedSpotId = planeSpot.getId();
//        }
//        selectedSpotId = null;
    }

    private void unselectToggles(){
        if (opCode != OPCode.CREATE) createPlaneButton.setSelected(false);
        if (opCode != OPCode.SELECT || opCode != OPCode.MOVE )movePlaneButton.setSelected(false);
        if (opCode != OPCode.DELETE) createPlaneButton.setSelected(false);
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
        if (!planeSpot.equals(null) && !planeSpot.getIsOccupied()) {
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
        //check if spot has a plane
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

    public void changeParkedPlanesFacing(){ //TODO: update Facing buttons on all parked planes
        //check airport.planelist
        //check wherePlaneAt to see if Park   ArrayList<String> parkedPlaneIds = airport.lookUpParkedPlaneIds();
        //find key in wherePlaneAt
        //check if key object is park
        //if park, change facing

//        for (String planeID : airport.getPlaneList().keySet()){
//            if(airport.getSpotList().get(airport.getWherePlaneAt().get(planeID)).getClass() == Parking.class)
//        }

    }
    //make fromPicture invis and toPicture visible



}