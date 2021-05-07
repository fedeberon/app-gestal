package com.ideas.actual.model;

/**
 * Created by federicoberon on 04/02/2020.
 */
public enum State {

    ACTIVE("ACTIVO", "activo"),
    INACTIVE("INACTIVO", "inactivo");

    private final String displayName;
    private final String shortName;

    State(String displayName, String shortName) {
        this.displayName = displayName;
        this.shortName = shortName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getShortName() {
        return shortName;
    }
}
