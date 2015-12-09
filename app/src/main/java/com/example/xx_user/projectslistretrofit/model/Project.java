package com.example.xx_user.projectslistretrofit.model;

public class Project {
    private int projectId;
    private int clientId;
    private String projectName;
    private String projectImageUrl;
    private String projectDescription;
    private String projectTechnologies;
    private String projectSupportedScreens;
    private String projectSolutionTypes;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectImageUrl() {
        return projectImageUrl;
    }

    public void setProjectImageUrl(String projectImageUrl) {
        this.projectImageUrl = projectImageUrl;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectTechnologies() {
        return projectTechnologies;
    }

    public void setProjectTechnologies(String projectTechnologies) {
        this.projectTechnologies = projectTechnologies;
    }

    public String getProjectSupportedScreens() {
        return projectSupportedScreens;
    }

    public void setProjectSupportedScreens(String projectSupportedScreens) {
        this.projectSupportedScreens = projectSupportedScreens;
    }

    public String getProjectSolutionTypes() {
        return projectSolutionTypes;
    }

    public void setProjectSolutionTypes(String projectSolutionTypes) {
        this.projectSolutionTypes = projectSolutionTypes;
    }
}