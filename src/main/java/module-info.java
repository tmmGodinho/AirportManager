module com.airportmanager.planemanager {
    requires javafx.controls;
    requires javafx.fxml;
        requires javafx.web;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
            requires net.synedra.validatorfx;
            requires org.kordamp.ikonli.javafx;
            requires org.kordamp.bootstrapfx.core;
//            requires eu.hansolo.tilesfx;
            requires com.almasb.fxgl.all;
    requires com.google.gson;
    requires java.desktop;

    opens com.airportmanager.planemanager to javafx.fxml;
//    exports com.airportmanager;
    exports com.airportmanager.planemanager;
    exports com.airportmanager.planemanager.deserializer;
    opens com.airportmanager.planemanager.deserializer to javafx.fxml;
//    opens com.airportmanager.planemanager to javafx.fxml;
}