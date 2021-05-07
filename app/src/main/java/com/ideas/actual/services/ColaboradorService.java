package com.ideas.actual.services;

import com.ideas.actual.model.Colaborador;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ColaboradorService {

    @GET("colaborador/list/")
    Call<List<Colaborador>> findAll();

}
