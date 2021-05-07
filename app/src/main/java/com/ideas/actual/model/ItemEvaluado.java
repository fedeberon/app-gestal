package com.ideas.actual.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by federicoberon on 04/02/2020.
 */
public class ItemEvaluado implements Serializable{

    private Long id;

    private Item item;

    private Float rating;

    private EvaluacionDelColaborador evaluacionDelColaborador;

    private List<ConsideracionItemEvaluado> consideracionItemEvaluados;

    private Integer valorConsideracionItemEvaluados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public EvaluacionDelColaborador getEvaluacionDelColaborador() {
        return evaluacionDelColaborador;
    }

    public void setEvaluacionDelColaborador(EvaluacionDelColaborador evaluacionDelColaborador) {
        this.evaluacionDelColaborador = evaluacionDelColaborador;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public List<ConsideracionItemEvaluado> getConsideracionItemEvaluados() {
        return consideracionItemEvaluados;
    }

    public void setConsideracionItemEvaluados(List<ConsideracionItemEvaluado> consideracionItemEvaluados) {
        this.consideracionItemEvaluados = consideracionItemEvaluados;
    }

    public Integer getValorConsideracionItemEvaluados() {
        return valorConsideracionItemEvaluados;
    }

    public void setValorConsideracionItemEvaluados(Integer valorConsideracionItemEvaluados) {
        this.valorConsideracionItemEvaluados = valorConsideracionItemEvaluados;
    }

    public Integer calculateRating() {
        if (Objects.isNull(this.consideracionItemEvaluados)) return 0;

        Integer ratingTotal = 5;
        Integer totalConsideraciones = this.consideracionItemEvaluados.size();
        Long consideracionesChequeadas = this.consideracionItemEvaluados.stream().filter(line -> line.isCheckeado()).count();

        Long porcentajeDeConsideracionesChequeadas = (consideracionesChequeadas * 100) / totalConsideraciones;
        Integer porcentajeDeConsideracionesChequeadasConValorRedondeado = Math.round(porcentajeDeConsideracionesChequeadas);


        Integer estrellasCalculadas = (porcentajeDeConsideracionesChequeadasConValorRedondeado * ratingTotal) / 100;

        return Math.round(estrellasCalculadas);
    }

}
