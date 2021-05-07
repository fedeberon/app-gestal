package com.ideas.actual.model;

import java.io.Serializable;

/**
 * Created by Benja on 22/3/2020.
 */
public class Sucursal implements Serializable {

    private Long id;

    private String name;

    private String direction;

    private String telephone;

    private String mail;

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

    public String getDirection(){ return direction; }

    public void setDirection(String direction) { this.direction = direction; }

    public String getTelephone() { return telephone; }

    public void setTelephone (String telephone) { this.telephone = telephone; }

    public String getMail () { return mail; }

    public void setMail(String mail) { this.mail = mail; }

}
