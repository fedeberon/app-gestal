package com.ideas.actual.services;

import com.ideaas.services.domain.Evaluacion;
import com.ideaas.services.domain.EvaluacionDelColaborador;
import com.ideaas.services.domain.Puesto;
import com.ideaas.services.domain.Rol;

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
