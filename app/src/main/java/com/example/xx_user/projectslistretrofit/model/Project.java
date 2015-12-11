package com.example.xx_user.projectslistretrofit.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Project implements Parcelable {

    @SerializedName("id")
    private int projectId;

    @SerializedName("name")
    private String projectName;

    @SerializedName("description")
    private String projectDescription;

    @SerializedName("clientId")
    private int clientId;

    @SerializedName("image")
    private Image projectImage;

    public Project() {
    }

    private Project(Parcel in) {
        projectId = in.readInt();
        projectName = in.readString();
        projectDescription = in.readString();
        clientId = in.readInt();
        projectImage = in.readParcelable(null);
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

    public int getClientId() {
        return clientId;
    }

    public Image getProjectImage() {
        return projectImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(projectId);
        dest.writeString(projectName);
        dest.writeString(projectDescription);
        dest.writeInt(clientId);
        dest.writeParcelable(projectImage, flags);
    }

    public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>() {

        @Override
        public Project createFromParcel(Parcel source) {
            return new Project(source);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };



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
