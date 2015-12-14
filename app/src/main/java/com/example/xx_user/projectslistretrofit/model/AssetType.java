package com.example.xx_user.projectslistretrofit.model;

/**
 * Created by XX-User on 14.12.2015.
 */
public enum AssetType {

    IMAGE("image"), MOVIE("movie"), NOT_DEFINED("not_defined");

    private String asString;

    AssetType(String asString) {
        this.asString = asString;
    }

    public static AssetType fromString(String stringRepresentation) {
        for (AssetType assetType : AssetType.values()) {
            if (assetType.asString.equalsIgnoreCase(stringRepresentation)) {
                return assetType;
            }
        }
        return NOT_DEFINED;
    }
}
