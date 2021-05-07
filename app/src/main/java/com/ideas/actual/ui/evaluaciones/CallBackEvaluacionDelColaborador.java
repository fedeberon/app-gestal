package com.ideas.actual.ui.evaluaciones;

import android.arch.lifecycle.MutableLiveData;

import com.ideas.actual.model.EvaluacionDelColaborador;

import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallBackEvaluacionDelColaborador implements Callback<List<EvaluacionDelColaborador>>{

    private MutableLiveData<List<EvaluacionDelColaborador>> dataEvaluaciones;

    public CallBackEvaluacionDelColaborador(MutableLiveData<List<EvaluacionDelColaborador>> dataEvaluaciones) {
        this.dataEvaluaciones = dataEvaluaciones;
    }

    @Override
    public void onResponse(Call<List<EvaluacionDelColaborador>> call, Response<List<EvaluacionDelColaborador>> response) {
        if(response.isSuccessful()){
            dataEvaluaciones.setValue(response.body());
        }
    }

    @Override
    public void onFailure(Call<List<EvaluacionDelColaborador>> call, Throwable t) {
        dataEvaluaciones.setValue(Arrays.asList(new EvaluacionDelColaborador()));
    }

}
