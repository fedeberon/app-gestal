package com.ideas.actual.services;

import com.ideas.actual.model.ConsideracionItemEvaluado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ItemEvaluadoService {

    @GET("itemEvaluado/consideracionesEvaluadas/{id}")
    Call<List<ConsideracionItemEvaluado>> getByItemEvaluado(@Path("id") Long id);
}
