package com.ideas.actual.model;

import java.io.Serializable;

/**
 * Created by enzo on 13/4/2020.
 */
public class ConsideracionItemEvaluado implements Serializable {

    private Long id;

    private Consideracion consideracion;

    private ItemEvaluado itemEvaluado;

    public ConsideracionItemEvaluado() {
    }

    public ConsideracionItemEvaluado(Consideracion consideracion, Boolean checkeado ) {
        this.consideracion = consideracion;
        this.checkeado = checkeado;
    }

    private boolean checkeado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Consideracion getConsideracion() {
        return consideracion;
    }

    public void setConsideracion(Consideracion consideracion) {
        this.consideracion = consideracion;
    }

    public ItemEvaluado getItemEvaluado() {
        return itemEvaluado;
    }

    public void setItemEvaluado(ItemEvaluado itemEvaluado) {
        this.itemEvaluado = itemEvaluado;
    }

    public boolean isCheckeado() {
        return checkeado;
    }

    public void setCheckeado(boolean checkeado) {
        this.checkeado = checkeado;
    }

}
