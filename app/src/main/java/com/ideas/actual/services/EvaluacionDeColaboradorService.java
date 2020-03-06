package com.ideas.actual.services;

import com.ideaas.services.domain.EvaluacionDelColaborador;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EvaluacionDeColaboradorService {

    @GET("evaluaciones/list/{page}")
    Call<List<EvaluacionDelColaborador>> findAll(@Path("page") Integer page);

}
