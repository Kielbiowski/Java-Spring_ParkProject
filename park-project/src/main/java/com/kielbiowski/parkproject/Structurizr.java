package com.kielbiowski.parkproject;

import com.structurizr.Workspace;
import com.structurizr.analysis.ComponentFinder;
import com.structurizr.analysis.ComponentFinderStrategy;
import com.structurizr.analysis.StructurizrAnnotationsComponentFinderStrategy;
import com.structurizr.api.StructurizrClient;
import com.structurizr.api.StructurizrClientException;
import com.structurizr.model.*;
import com.structurizr.view.ComponentView;
import com.structurizr.view.ContainerView;
import com.structurizr.view.SystemContextView;
import com.structurizr.view.ViewSet;

import java.util.Set;

public class Structurizr {
    private final long WORKSPACE_ID;
    private final String API_KEY;
    private final String API_SECRET;

    Workspace workspace;
    Model model;
    ViewSet viewSet;

    public Structurizr(long WORKSPACE_ID, String API_KEY, String API_SECRET) {
        this.WORKSPACE_ID = WORKSPACE_ID;
        this.API_KEY = API_KEY;
        this.API_SECRET = API_SECRET;
    }

    public void createWorkspace(String workspaceName, String workspaceDescription) {
        workspace = new Workspace(workspaceName, workspaceDescription);
        model = workspace.getModel();
        model.setImpliedRelationshipsStrategy(new CreateImpliedRelationshipsUnlessAnyRelationshipExistsStrategy());
        viewSet = workspace.getViews();
    }


    public void autoCreateSoftwareSystemViews(SoftwareSystem softwareSystem, String key, String description, String componentPackageName) throws Exception {
        ComponentFinderStrategy componentFinderStrategy = new StructurizrAnnotationsComponentFinderStrategy();
/*

        for (Container container : softwareSystem.getContainers()) {
            String componentViewKey = container.getName();
            String componentViewDescription = componentViewKey + " Component View";
            autoCreateComponentView(container, componentPackageName,componentFinderStrategy, componentViewKey, componentViewDescription);
        }

*/
        String containerViewKey = softwareSystem.getName();
        String containerViewDescription = containerViewKey + " Container View";
        autoCreateContainerView(softwareSystem, containerViewKey, containerViewDescription);

        autoCreateSystemContextView(softwareSystem, key, description);

    }


    public void autoCreateComponentView(Container container, String packageName, ComponentFinderStrategy componentFinderStrategy, String key, String description) throws Exception {
        ComponentFinder componentFinder = new ComponentFinder(container, packageName, componentFinderStrategy);
        ComponentView componentView = viewSet.createComponentView(container, key, description);
        componentFinder.findComponents().forEach(componentView::add);
        componentView.addExternalDependencies();
    }

    public void autoCreateContainerView(SoftwareSystem softwareSystem, String key, String description) {
        ContainerView containerView = viewSet.createContainerView(softwareSystem, key, description);
        containerView.addAllContainersAndInfluencers();
    }

    public void autoCreateSystemContextView(SoftwareSystem softwareSystem, String key, String description) {
        SystemContextView systemContextView = viewSet.createSystemContextView(softwareSystem, key, description);
        systemContextView.addNearestNeighbours(softwareSystem);
    }

    public void update() throws StructurizrClientException {
        StructurizrClient structurizrClient = new StructurizrClient(API_KEY, API_SECRET);
        structurizrClient.putWorkspace(WORKSPACE_ID, workspace);
    }
}
