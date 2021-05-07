package com.ideas.actual.services;

import com.ideas.actual.model.Evaluacion;
import com.ideas.actual.model.EvaluacionDelColaborador;
import com.ideas.actual.model.Puesto;
import com.ideas.actual.model.Rol;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EvaluacionService {

    @POST("evaluacion/byRol")
    Call<Evaluacion> getByRol(@Body Rol rol);

    @POST("evaluacion/byPuesto")
    Call<Evaluacion> getByPuesto(@Body Puesto puesto);

    @POST("evaluacion/save")
    Call<EvaluacionDelColaborador> save(@Body EvaluacionDelColaborador evaluacion);
}
