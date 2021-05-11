package com.ideas.actual.ui.evaluacion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ideas.actual.configuration.RetrofitServiceFactory;
import com.ideas.actual.model.Evaluacion;
import com.ideas.actual.model.Puesto;
import com.ideas.actual.services.EvaluacionService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvaluacionViewModel extends ViewModel {

    private MutableLiveData<Evaluacion> dataEvaluacion;
    private EvaluacionService evaluacionService = RetrofitServiceFactory.createService(EvaluacionService.class);

    public void init(Puesto puesto){
        dataEvaluacion = new MutableLiveData<>();
        Call<Evaluacion> call = evaluacionService.getByPuesto(puesto);
        call.enqueue(new Callback<Evaluacion>() {
            @Override
            public void onResponse(Call<Evaluacion> call, Response<Evaluacion> response) {
                if(response.isSuccessful()){
                    dataEvaluacion.setValue(response.body());
                } else {
                    dataEvaluacion.setValue(null);
                }


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
