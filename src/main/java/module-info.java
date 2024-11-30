module com.adalove.api {
    requires javafx.fxml;
    requires bcrypt;
    requires ollama4j;
    requires java.sql;
    requires java.net.http;
    requires com.fasterxml.jackson.annotation;
//    requires junit;
//    requires org.testng;
    requires de.jensd.fx.glyphs.fontawesome;
    requires javafx.controls;
    requires java.desktop;
    requires static lombok;


    opens com.adalove.api to javafx.fxml;
    opens com.adalove.api.controller to javafx.fxml;
    opens com.adalove.api.model.entities to javafx.base;
    exports com.adalove.api;
}