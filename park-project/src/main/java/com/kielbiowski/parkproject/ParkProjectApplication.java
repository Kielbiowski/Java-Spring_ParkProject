package com.kielbiowski.parkproject;

import com.structurizr.analysis.StructurizrAnnotationsComponentFinderStrategy;
import com.structurizr.model.Container;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.model.Tags;
import com.structurizr.view.Shape;
import com.structurizr.view.Styles;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkProjectApplication {

    public static void main(String[] args) throws Exception {
        Structurizr structurizr = new Structurizr(64298, "9682c417-139d-4dc2-90de-5cfe8e7b4104", "b0080bff-5826-4a43-aade-5cc70116b0cd");
        structurizr.createWorkspace("ParkProject system structure", "Structure of ParkProject");

        //***** Model elements definitions *****

        //Person
        Person user = structurizr.model.addPerson("User", "User of ParkProject");
        Person admin = structurizr.model.addPerson("Admin", "Administrator of ParkProject");

        //Software Systems
        SoftwareSystem parkProject = structurizr.model.addSoftwareSystem("ParkProject System", "Internet system of users parking spaces sharing");

        //Containers
        Container api = parkProject.addContainer("API", "ParkProject application interface", "Java");
        Container webApp = parkProject.addContainer("Web Application", "ParkProject Web Application", "Spring");
        Container mobileApp = parkProject.addContainer("Mobile Application", "ParkProject Mobile Application", "Kotlin");
        Container database = parkProject.addContainer("Database", "ParkProject Database", "MySQL");

        //***** Connections *****

        //***** Views *****

        //Component view
        structurizr.autoCreateComponentView(webApp, "com.kielbiowski.parkproject", new StructurizrAnnotationsComponentFinderStrategy(), "MainController", "Main Controller Component view");

        structurizr.autoCreateSoftwareSystemViews(parkProject,"ParkProjectApplication", "ParkProject application context view","com.kielbiowski.parkproject");

        //***** Styling *****
        Styles styles = structurizr.viewSet.getConfiguration().getStyles();
        webApp.addTags("WebApp");
        mobileApp.addTags("MobileApp");
        database.addTags("Database");
        styles.addElementStyle("Database").shape(Shape.Cylinder).background("#1168bd");
        styles.addElementStyle("WebApp").shape(Shape.WebBrowser).background("#ff8000");
        styles.addElementStyle("MobileApp").shape(Shape.MobileDeviceLandscape).background("#008000");
        styles.addElementStyle(Tags.SOFTWARE_SYSTEM).background("#1168bd").color("#ffffff");
        styles.addElementStyle(Tags.PERSON).background("#08427b").color("#ffffff").shape(Shape.Person);

        structurizr.update();

        //Spring Application run
        SpringApplication.run(ParkProjectApplication.class, args);
    }
}
