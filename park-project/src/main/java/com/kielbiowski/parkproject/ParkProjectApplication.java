package com.kielbiowski.parkproject;

import com.structurizr.Workspace;
import com.structurizr.api.StructurizrClient;
import com.structurizr.model.*;
import com.structurizr.view.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkProjectApplication {
	private static final long WORKSPACE_ID = 64298;
	private static final String API_KEY = "9682c417-139d-4dc2-90de-5cfe8e7b4104";
	private static final String API_SECRET = "b0080bff-5826-4a43-aade-5cc70116b0cd";

	public static void main(String[] args) throws Exception {
		//Initial Structurizr definitions
		Workspace workspace = new Workspace("ParkProject system structure", "Structure of ParkProject");
		Model model = workspace.getModel();
		model.setImpliedRelationshipsStrategy(new CreateImpliedRelationshipsUnlessAnyRelationshipExistsStrategy());
		ViewSet views = workspace.getViews();

		//Model elements definitions
		SoftwareSystem application = model.addSoftwareSystem("Application","ParkProject Application");
		SoftwareSystem database = model.addSoftwareSystem("Database","ParkProject Database");
		Person user = model.addPerson("User","User of ParkProject");
		Container container1 = application.addContainer("Container 1");
		Container container2 = database.addContainer("Container 2");

		//Application view connections
		user.uses(application,"uses");
		container1.uses(container2,"stores data");
		container2.uses(container1,"provides data");

		//System context views definition
		SystemContextView applicationContextView = views.createSystemContextView(application,"ParkProjectApplication","ParkProject application context view");
		applicationContextView.add(user);
		applicationContextView.add(application);
		applicationContextView.add(database);

		//Styling
		Styles styles = views.getConfiguration().getStyles();
		styles.addElementStyle(Tags.SOFTWARE_SYSTEM).background("#1168bd").color("#ffffff");
		styles.addElementStyle(Tags.PERSON).background("#08427b").color("#ffffff").shape(Shape.Person);

		//Server update
		StructurizrClient structurizrClient = new StructurizrClient(API_KEY, API_SECRET);
		structurizrClient.putWorkspace(WORKSPACE_ID, workspace);

		//Spring Application run
		SpringApplication.run(ParkProjectApplication.class, args);
	}
}
