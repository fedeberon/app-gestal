package com.ideas.actual.ui.evaluacion;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ideaas.services.domain.Evaluacion;
import com.ideaas.services.domain.Rol;
import com.ideas.actual.configuration.RetrofitServiceFactory;
import com.ideas.actual.services.EvaluacionService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvaluacionViewModel extends ViewModel {

    private MutableLiveData<Evaluacion> dataEvaluacion;
    private EvaluacionService evaluacionService = RetrofitServiceFactory.createService(EvaluacionService.class);

    public void init(Rol rol){
        dataEvaluacion = new MutableLiveData<>();
        Call<Evaluacion> call = evaluacionService.getByRol(rol);
        call.enqueue(new Callback<Evaluacion>() {
            @Override
            public void onResponse(Call<Evaluacion> call, Response<Evaluacion> response) {
                dataEvaluacion.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Evaluacion> call, Throwable t) {
                System.out.println(t.toString());
                dataEvaluacion.setValue(new Evaluacion());
            }
        });
    }

    public LiveData<Evaluacion> getData() {
        return this.dataEvaluacion;
    }
}
