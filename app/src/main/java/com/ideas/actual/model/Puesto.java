package com.ideas.actual.model;

import java.io.Serializable;

/**
 * Created by federicoberon on 04/02/2020.
 */
public class Puesto implements Serializable{

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
