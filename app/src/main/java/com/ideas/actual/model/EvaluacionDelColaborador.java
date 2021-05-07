package com.ideas.actual.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by federicoberon on 04/02/2020.
 */
public class EvaluacionDelColaborador implements Serializable{


    private Long id;

    private Colaborador colaborador;

    private Puesto rolEvaluado;

    private Date fechaDeCarga;

    private List<ItemEvaluado> itemEvaluados;

    private Float resultado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Puesto getRolEvaluado() {
        return rolEvaluado;
    }

    public void setRolEvaluado(Puesto rolEvaluado) {
        this.rolEvaluado = rolEvaluado;
    }

    public Date getFechaDeCarga() {
        return fechaDeCarga;
    }

    public void setFechaDeCarga(Date fechaDeCarga) {
        this.fechaDeCarga = fechaDeCarga;
    }

    public List<ItemEvaluado> getItemEvaluados() {return itemEvaluados; }

    public void setItemEvaluados(List<ItemEvaluado> itemEvaluados) {
        this.itemEvaluados = itemEvaluados;
    }

    public Float getResultado() {
        return resultado;
    }

    public void setResultado(Float resultado) {
        this.resultado = resultado;
    }
}
