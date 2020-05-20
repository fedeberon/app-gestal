package com.ideas.actual.ui.evaluaciones;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ideaas.services.domain.EvaluacionDelColaborador;
import com.ideas.actual.configuration.RetrofitServiceFactory;
import com.ideas.actual.services.EvaluacionDeColaboradorService;
import com.ideas.actual.ui.colaborador.ColaboradorViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvaluacionDeColaboradorViewModel extends ViewModel {

    private String TAG = ColaboradorViewModel.class.getSimpleName();

    private MutableLiveData<List<EvaluacionDelColaborador>> dataEvaluaciones;
    EvaluacionDeColaboradorService evaluacionesService = RetrofitServiceFactory.createService(EvaluacionDeColaboradorService.class);


    public void init(Integer page){
        dataEvaluaciones = new MutableLiveData<>();
        Call<List<EvaluacionDelColaborador>> call = evaluacionesService.findAll(page);
        call.enqueue(new Callback<List<EvaluacionDelColaborador>>() {
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
        });
    }

    public void init(Integer page, String textToSearch){
        dataEvaluaciones = new MutableLiveData<>();
        Call<List<EvaluacionDelColaborador>> call;
        if(Objects.isNull(textToSearch) || textToSearch.isEmpty()){
            call = evaluacionesService.findAll(page);
        }
        else {
            call = evaluacionesService.findAll(page, textToSearch);
        }

        call.enqueue(new Callback<List<EvaluacionDelColaborador>>() {
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
        });
    }

    public LiveData<List<EvaluacionDelColaborador>> getData() {
        return this.dataEvaluaciones;
    }

}
