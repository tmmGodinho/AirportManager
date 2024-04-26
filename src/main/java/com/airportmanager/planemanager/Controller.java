package com.airportmanager.planemanager;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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

    private Parent root;

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

    //TODO: make procedure
    //TODO: do behind scenes Airport alteration
    //TODO: do UI update   getChildrenUnmodifiable   .isVisible();

    public void laneButtonPressed(javafx.event.ActionEvent actionEvent){
        Button button = (Button)actionEvent.getSource();
        String planeID = "#" + button.getText() + "Plane";
//        myVBox.lookup(planeID)
        //iterate every object in GUI
        for (Node node : myVBox.getChildren()) {
            if (node.getClass() == AnchorPane.class) {
                //iterate every object in AnchorPane
                for(Node nodeAux : ((AnchorPane) node).getChildren()){
                    if (nodeAux.getClass() == ImageView.class) {
                        if (Objects.equals(nodeAux.getId(), "B18Plane") && !nodeAux.isVisible()) {
                            nodeAux.setVisible(true);
                        }
                    }
                }
            }
        }
    }



}