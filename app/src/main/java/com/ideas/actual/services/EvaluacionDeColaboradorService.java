package com.ideas.actual.services;

import com.ideas.actual.model.EvaluacionDelColaborador;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EvaluacionDeColaboradorService {

    @GET("evaluaciones/list/{page}/{textToSeach}")
    Call<List<EvaluacionDelColaborador>> findAll(@Path("page") Integer page, @Path("textToSeach") String textToSeach);

    @GET("evaluaciones/list/{page}")
    Call<List<EvaluacionDelColaborador>> findAll(@Path("page") Integer page);

}
