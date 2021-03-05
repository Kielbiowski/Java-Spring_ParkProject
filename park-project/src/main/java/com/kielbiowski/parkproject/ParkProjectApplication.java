package com.kielbiowski.parkproject;

import com.structurizr.Workspace;
import com.structurizr.analysis.ComponentFinder;
import com.structurizr.analysis.StructurizrAnnotationsComponentFinderStrategy;
import com.structurizr.api.StructurizrClient;
import com.structurizr.model.*;
import com.structurizr.view.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;

@SpringBootApplication
public class ParkProjectApplication {
    private static final long WORKSPACE_ID = 64298;
    private static final String API_KEY = "9682c417-139d-4dc2-90de-5cfe8e7b4104";
    private static final String API_SECRET = "b0080bff-5826-4a43-aade-5cc70116b0cd";

    public static void main(String[] args) throws Exception {
        Structurizr structurizr = new Structurizr(64298,"9682c417-139d-4dc2-90de-5cfe8e7b4104","b0080bff-5826-4a43-aade-5cc70116b0cd");
        structurizr.createWorkspace("ParkProject system structure","Structure of ParkProject");

        //Model elements definitions
        //Person
        Person user = structurizr.model.addPerson("User", "User of ParkProject");
        Person admin = structurizr.model.addPerson("Admin", "Administrator of ParkProject");
        //Software Systems
        SoftwareSystem parkProject = structurizr.model.addSoftwareSystem("ParkProject System", "Internet system of users parking spaces sharing");
        //Containers
        Container api = parkProject.addContainer("API","ParkProject application interface","Java");
        ComponentFinder apiFinder = new ComponentFinder(api,"com.kielbiowski.parkproject", new StructurizrAnnotationsComponentFinderStrategy());
        Container webApp = parkProject.addContainer("Web Application", "ParkProject Web Application", "Spring");
        //ComponentFinder webAppFinder = new ComponentFinder(webApp,"com.kielbiowski.parkproject", new StructurizrAnnotationsComponentFinderStrategy());
        Container mobileApp = parkProject.addContainer("Mobile Application", "ParkProject Mobile Application", "Kotlin");
        ComponentFinder mobileAppFinder = new ComponentFinder(mobileApp,"com.kielbiowski.parkproject", new StructurizrAnnotationsComponentFinderStrategy());
        Container database = parkProject.addContainer("Database", "ParkProject Database", "MySQL");
        ComponentFinder databaseFinder = new ComponentFinder(database,"com.kielbiowski.parkproject", new StructurizrAnnotationsComponentFinderStrategy());

        //System context diagram connections
        user.uses(parkProject,"uses");
        admin.uses(parkProject,"uses");

        //Container diagram connections
        user.uses(webApp, "uses");
        user.uses(mobileApp, "uses");
        admin.uses(webApp,"administrates");
        database.uses(api, "provides data");
        api.uses(database, "stores data");
        api.uses(webApp, "provides data");
        api.uses(mobileApp, "provides data");

        //System context views definition
        SystemContextView parkProjectContextView = structurizr.viewSet.createSystemContextView(parkProject, "ParkProjectApplication", "ParkProject application context view");
        parkProjectContextView.add(user);
        parkProjectContextView.add(admin);

        //Container views definition
        //WebApp container view
        ContainerView webAppContainerView = structurizr.viewSet.createContainerView(parkProject, "WebApplication", "Web Application container view");
        webAppContainerView.add(user);
        webAppContainerView.add(admin);
        webAppContainerView.add(api);
        webAppContainerView.add(webApp);
        webAppContainerView.add(mobileApp);
        webAppContainerView.add(database);

        //WebApp components view
        structurizr.autoCreateComponentView(webApp,"com.kielbiowski.parkproject",new StructurizrAnnotationsComponentFinderStrategy(),"MainController","Main Controller Component view");


        //Styling
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
