package com.ideas.actual.model;

import java.util.Collection;

/**
 * Created by federicoberon on 04/02/2020.
 */
public class Privilege {

    private Long id;

    private String name;

    private Collection<Rol> roles;

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

    public Collection<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Rol> roles) {
        this.roles = roles;
    }
}
