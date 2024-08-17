package com.airportmanager.planemanager;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.image.Image;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

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
    private LinkedList<Shape> selectedPlaneLines;


    public void thisWorks(javafx.event.ActionEvent actionEvent) {
        System.out.println("Button Pressed! "+ actionEvent.getSource() );
    }

    public void startPlaneLineList(){
        selectedPlaneLines = new LinkedList<>();
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
    public LinkedList<Shape> getSelectedPlaneLines(){
        return selectedPlaneLines;
    }

    // make procedure
    // do behind scenes Airport alteration
    // do UI update   getChildrenUnmodifiable   .isVisible();


    //phase 1
    //TODO: bling it up with
    // Facing Color +
    // selected plane circle.png

    //phase 2
    //TODO:
    // when plane leaves parking, check way for other planes
    // planes can park from any lane
    // constrained parking
    // red cross on create and move (link with constrained parking logic)

    @FXML
    public void createPlaneButtonPressed(javafx.event.ActionEvent actionEvent){
        if(opCode == OPCode.CREATE) setOpCode(OPCode.NONE);
        else setOpCode(OPCode.CREATE);
        unselectToggles();
        cleanSelectedLines();
    }

    @FXML
    public void deletePlaneButtonPressed(javafx.event.ActionEvent actionEvent){
        if(opCode == OPCode.DELETE) setOpCode(OPCode.NONE);
        else setOpCode(OPCode.DELETE);
        unselectToggles();
        cleanSelectedLines();
    }
    @FXML
    public void movePlaneButtonPressed(javafx.event.ActionEvent actionEvent){
        if(opCode == OPCode.SELECT) setOpCode(OPCode.NONE);
        else setOpCode(OPCode.SELECT);
        unselectToggles();
        cleanSelectedLines();
    }
    @FXML
    public void airportFacingButtonPressed(javafx.event.ActionEvent actionEvent){  //Switch Airport Facing + change Parked Facings TODO:if opcode = move
        if(opCode == OPCode.MOVE) setOpCode(OPCode.NONE);
        Facing newFacing = airport.getFacing() == Facing.WEST ? Facing.EAST : Facing.WEST;
        airport.setFacing(newFacing);
        airportFacingButton.setText(airport.getFacing().toString());
        airport.changeParkedPlaneFacings();
        updatePlaneFacingButtons();
        cleanSelectedLines();
        //TODO:maybe unselectToggles() here?
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
            double upperY = 0,
                    bottomY = Double.POSITIVE_INFINITY;
            // if plane -> highlight it and change OPCODE to move
            //TODO: code for highlight
            //calculate every middlegroundY to check for closest to fromButton for top & bottom
            //feed middlegroundYs to createline procedure
            HashSet<String> connectedSpotIds = airport.getConnectedSpotIds(clickedSpotId);
            Button fromButton = (Button) myVBox.lookup("#" + clickedSpotId);
            double fromY = fromButton.getLayoutY();
            for (String sId : connectedSpotIds){  //figure out midYs logic
                Button toButton = (Button) myVBox.lookup("#"+sId);
                //if both buttons are lanes do nothing
                if(!(airport.isSpotLane(clickedSpotId) && airport.isSpotLane(sId))){
                    double toY = toButton.getLayoutY();
                    if (toY < fromY) {  //to is above from
                        if (upperY < toY) upperY = toY;//if to is closest so far, replace
                    } else { //if to is below from
                        if (bottomY > toY) bottomY = toY; //if to is closest so far, replace
                    }
                }
                //check if toButton higher or lower than fromButton
            }
            double midUpperY = (fromY+upperY)/2,
                   midBottomY = (fromY+bottomY)/2;
            //check for connected spots and show lines
            for (String sId : connectedSpotIds){
                if(!airport.isSpotOccupied(sId)) {
                    Button toButton = (Button) myVBox.lookup("#" + sId);
                    if (airport.isSpotLane(clickedSpotId) && airport.isSpotLane(sId)) {
                        createLine(fromButton, toButton);
                    } else {
                        double toY = toButton.getLayoutY();
                        double midY = toY < fromY ? midUpperY : midBottomY;
                        createPolyLine(fromButton, toButton, midY);
                    }
                }
            }

            setOpCode(OPCode.MOVE);
            selectedSpotId = clickedSpotId;
        }
    }





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
        cleanSelectedLines();
        setOpCode(OPCode.SELECT);
    }


    public void updatePlaneFacingButtons(){
        for(String planeId : airport.getPlaneList().keySet()){
                if(airport.getSpotList().get(airport.planeToSpot(planeId)).getClass() == Parking.class) {
                    updatePlaneFacingButton(planeId);
                }
        }
    }


    public void showPlaneImage(String buttonId){
        ImageView planeImage =(ImageView) myVBox.lookup("#" + buttonId + "Plane");
        //make plane.png appear
        //myVBox.lookup("#" + buttonId + "Plane").setVisible(true);
        planeImage.setVisible(true);
        //update plane facing button
        if (airport.isSpotParking(buttonId)){
            ToggleButton planeFacingButton = (ToggleButton) myVBox.lookup("#" + buttonId + "Facing");
            planeFacingButton.setText(shortenFacing(airport.getFacing()));
        } else if (airport.getPlaneList().get(airport.getWherePlaneAt().get(buttonId)).getFacing() == Facing.EAST) {
            //turn jpeg 270
            planeImage.setRotate(270);
        } else { //turn jpeg 90
            planeImage.setRotate(90);
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


    public void createPolyLine(Button fromButton, Button toButton, double midY){
        AnchorPane anchorPane = (AnchorPane) myVBox.lookup("#anchorPane");
        double fromX, fromY, toX, toY;
        fromX = fromButton.getLayoutX();
        fromY = fromButton.getLayoutY();
        toX = toButton.getLayoutX();
        toY = toButton.getLayoutY();
        /*
        check which button on top
        then assign value to middleground Y
         */


        //Creating a polyline
        Polyline polyline = new Polyline();
        //Adding coordinates to the polygon
        polyline.getPoints().addAll(
                fromX, fromY,
                fromX, midY,   //225 up y & 410 down y
                toX, midY,
                toX, toY);
        polyline.setVisible(true);
        anchorPane.getChildren().add(polyline);
        selectedPlaneLines.add(polyline);
    }

    public void cleanSelectedLines(){
        AnchorPane anchorPane = (AnchorPane) myVBox.lookup("#anchorPane");
        for (Iterator<Shape> it = selectedPlaneLines.iterator(); it.hasNext(); ) {
            Shape aShape = it.next();
            it.remove();
            anchorPane.getChildren().remove(aShape);
            selectedPlaneLines.remove(aShape);
        }
    }

    public void createLine(Button fromButton, Button toButton){
        AnchorPane anchorPane = (AnchorPane) myVBox.lookup("#anchorPane");
        double fromX, fromY, toX, toY;
        fromX = fromButton.getLayoutX();
        fromY = fromButton.getLayoutY();
        toX = toButton.getLayoutX();
        toY = toButton.getLayoutY();
        Line line = new Line(fromX,fromY,toX,toY);
        line.setVisible(true);
        anchorPane.getChildren().add(line);
        selectedPlaneLines.add(line);

    }

}