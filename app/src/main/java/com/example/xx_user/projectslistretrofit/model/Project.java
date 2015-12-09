package com.example.xx_user.projectslistretrofit.model;



import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Project {

    @SerializedName("id")
    private int projectId;

    @SerializedName("name")
    private String projectName;

    @SerializedName("description")
    private String projectDescription;

    @SerializedName("clientId")
    private String clientId;

    @SerializedName("image")
    private Image projectImage;

    public Project() {
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getClientId() {
        return clientId;
    }

    public Image getProjectImage() {
        return projectImage;
    }
}

/*
String[] projectName,
String[] projectImageUrl,
String[] projectDescription,
String[] projectTechnologies,
String[] projectSupportedScreens,
String[] projectSolutionTypes,
String[] clientName,
String[] projectAssetsUrl,
*/
