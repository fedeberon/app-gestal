package com.ideas.actual.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by federicoberon on 03/02/2020.
 */
public class Item implements Serializable{

    private Long id;

    private String value;

    private Float score;

    private Evaluacion evaluacion;

    private boolean invalidaEvaluacion = false;

    private List<Consideracion> consideraciones;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public boolean isInvalidaEvaluacion() {
        return invalidaEvaluacion;
    }

    public void setInvalidaEvaluacion(boolean invalidaEvaluacion) {
        this.invalidaEvaluacion = invalidaEvaluacion;
    }

    public List<Consideracion> getConsideraciones() {
        return consideraciones;
    }

    public void setConsideraciones(List<Consideracion> consideraciones) {
        this.consideraciones = consideraciones;
    }

    @Override
    public boolean equals(Object o) {
        Item item = (Item) o;
        return item.getId() == id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
