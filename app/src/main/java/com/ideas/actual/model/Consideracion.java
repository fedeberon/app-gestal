package com.ideas.actual.model;

import java.io.Serializable;

public class Consideracion implements Serializable {

    private Long id;

    private String value;

    private boolean rating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRating() {
        return rating;
    }

    public void setRating(boolean rating) {
        this.rating = rating;
    }

}
