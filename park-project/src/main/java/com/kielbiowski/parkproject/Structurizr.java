package com.kielbiowski.parkproject;

import com.structurizr.Workspace;
import com.structurizr.analysis.ComponentFinder;
import com.structurizr.analysis.ComponentFinderStrategy;
import com.structurizr.api.StructurizrClient;
import com.structurizr.api.StructurizrClientException;
import com.structurizr.model.Container;
import com.structurizr.model.Model;
import com.structurizr.view.ComponentView;
import com.structurizr.view.ViewSet;

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
        viewSet = workspace.getViews();
    }

    public void autoCreateComponentView(Container container, String packageName, ComponentFinderStrategy compontntFinderStrategy, String key, String description) throws Exception {
        ComponentFinder componentFinder = new ComponentFinder(container,packageName,compontntFinderStrategy);
        ComponentView componentView = viewSet.createComponentView(container, key, description);
        componentFinder.findComponents().forEach(componentView::add);
        componentView.addExternalDependencies();
    }

    public void update() throws StructurizrClientException {
        StructurizrClient structurizrClient = new StructurizrClient(API_KEY, API_SECRET);
        structurizrClient.putWorkspace(WORKSPACE_ID, workspace);
    }
}
