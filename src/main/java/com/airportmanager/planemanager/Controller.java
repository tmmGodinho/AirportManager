package com.airportmanager.planemanager;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.image.Image;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

import java.util.HashSet;

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
        if(!currentOperation.isVisible()) currentOperation.setVisible(true); //boot condition
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
    //TODO: implement SelectAirplane
    //TODO: implement moveAirplane
    //TODO: properly sided plane.pngs
    //TODO: bling it up with Facing Color + Lines on Select + selected plane circle.png

    //phase 2

    @FXML
    public void createPlaneButtonPressed(javafx.event.ActionEvent actionEvent){
        if(opCode == OPCode.CREATE) setOpCode(OPCode.NONE);
        else setOpCode(OPCode.CREATE);
        unselectToggles();
    }

    @FXML
    public void deletePlaneButtonPressed(javafx.event.ActionEvent actionEvent){
        if(opCode == OPCode.DELETE) setOpCode(OPCode.NONE);
        else setOpCode(OPCode.DELETE);
        unselectToggles();
    }
    @FXML
    public void movePlaneButtonPressed(javafx.event.ActionEvent actionEvent){
        if(opCode == OPCode.SELECT) setOpCode(OPCode.NONE);
        else setOpCode(OPCode.SELECT);
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
        updatePlaneFacingButtons();
    }

    @FXML
    public void buttonPressed(javafx.event.ActionEvent actionEvent){
        //save spotId when isOccupied
        String buttonId = this.getButtonLocation(actionEvent);
        switch (opCode){
            case NONE:
                break;
            case CREATE:
                spawnAirplane(buttonId);
                break;
            case DELETE:
                deleteAirplane(buttonId);
                break;
            case SELECT:
                selectAirplane(buttonId);
                break;
            case MOVE:
                moveAirplane(buttonId);
                break;
        }
    }



    private void unselectToggles(){
        if (opCode != OPCode.CREATE) createPlaneButton.setSelected(false);
        if (opCode != OPCode.SELECT) movePlaneButton.setSelected(false);
        if (opCode != OPCode.DELETE) deletePlaneButton.setSelected(false);
    }

    public String getButtonLocation(javafx.event.ActionEvent actionEvent){
        Button button = (Button)actionEvent.getSource();
        return button.getId();
    }


    public void spawnAirplane(String buttonId){
        //check airport spotlist for same id as button to see if there is a plane there
        if (!airport.isSpotOccupied(buttonId)) {
            //spawn plane in buttonId Spot
            airport.populateNewPlane(buttonId);
            showPlaneImage(buttonId);
        }
    }

    public void deleteAirplane(String buttonId){
        //check if spot has a plane
        if(airport.isSpotOccupied(buttonId)) { //check if delete is legal
            //delete from planeList and wherePlaneAt
            airport.removePlane(buttonId);
            hidePlaneImage(buttonId);
        }
    }

    private void selectAirplane(String clickedSpotId) {
        //check spot for plane,
        if (airport.isSpotOccupied(clickedSpotId)) {
            // if plane -> highlight it and change OPCODE to move
            //TODO: code for highlight
            //check for connected spots and show lines
            HashSet<String> connectedSpotIds = airport.getConnectedSpotIds(clickedSpotId);
            for (String sId : connectedSpotIds){
                //TODO:code for line show or create
            }
            setOpCode(OPCode.MOVE);
            selectedSpotId = clickedSpotId;
        }
    }




    //TODO: make airplane move
    // every click checks if lastSelection, if not, set to true
    // set operation Label
    // make movement paths visible, crosses for disrupted connectedSpots
    //click 2
    // TODO: if move is legal: set fromSpot isOccupied to False, set toSpot isOccupied to True
    //                  call airport.movePlane for airport guts rearrange
    //                  make fromSpotPlane invis, set toSpotPlane to visible

    public void moveAirplane(String clickedSpotId){
        //check airport spotlist for same id as button to see if there is a plane there
        if (!airport.isSpotOccupied(clickedSpotId)) {
            HashSet<String> connectedSpotIds = airport.getConnectedSpotIds(selectedSpotId);
            if (connectedSpotIds.contains(clickedSpotId)){
                //move plane on airport
                airport.movePlane(selectedSpotId, clickedSpotId);
                //hide previousspot plane image
                hidePlaneImage(selectedSpotId);
                //show newspot plane image
                showPlaneImage(clickedSpotId);
            }

        }
        setOpCode(OPCode.SELECT);
    }
//check if spot is a legal move (connectedlists)
    //check if spot isOccupied
    //if all good call airport.moveAirplane

    //make fromPicture invis and toPicture visible

    public void updatePlaneFacingButtons(){
        for(String planeId : airport.getPlaneList().keySet()){
                if(airport.getSpotList().get(airport.planeToSpot(planeId)).getClass() == Parking.class) {
                    updatePlaneFacingButton(planeId);
                }
        }
    }


    public void showPlaneImage(String buttonId){
        //make plane.png appear
        myVBox.lookup("#" + buttonId + "Plane").setVisible(true);
        //update plane facing button
        if (airport.isSpotParking(buttonId)){
            ToggleButton planeFacingButton = (ToggleButton) myVBox.lookup("#" + buttonId + "Facing");
            planeFacingButton.setText(shortenFacing(airport.getFacing()));
        }
    }
    public void hidePlaneImage(String buttonId){
        //make picture invis
        myVBox.lookup("#" + buttonId + "Plane").setVisible(false);
        //update plane facing button
        if (airport.isSpotParking(buttonId)) {
            ToggleButton planeFacingButton = (ToggleButton) myVBox.lookup("#" + buttonId + "Facing");
            planeFacingButton.setText("");
        }
    }
    public void updatePlaneFacingButton(String planeId){
        ToggleButton planeFacingButton = (ToggleButton) myVBox.lookup("#" + airport.planeToSpot(planeId) + "Facing");
        planeFacingButton.setText(shortenFacing(airport.getPlaneList().get(planeId).getFacing()));
    }

    public String shortenFacing(Facing facing){
        if (facing == Facing.EAST) return "E";
        else return "W";
    }



}