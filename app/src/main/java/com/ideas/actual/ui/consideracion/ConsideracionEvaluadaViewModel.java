package com.ideas.actual.ui.consideracion;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ideaas.services.domain.ConsideracionItemEvaluado;
import com.ideaas.services.domain.ItemEvaluado;
import com.ideas.actual.configuration.RetrofitServiceFactory;
import com.ideas.actual.services.ItemEvaluadoService;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsideracionEvaluadaViewModel extends ViewModel {

    private MutableLiveData<List<ConsideracionItemEvaluado>> dataConsideraciones;
    private ItemEvaluadoService itemEvaluadoService = RetrofitServiceFactory.createService(ItemEvaluadoService.class);


    public void init(ItemEvaluado itemEvaluado) {
        dataConsideraciones = new MutableLiveData<>();
        Call<List<ConsideracionItemEvaluado>> call = itemEvaluadoService.getByItemEvaluado(itemEvaluado.getId());
        call.enqueue(new Callback<List<ConsideracionItemEvaluado>>() {
            @Override
            public void onResponse(Call<List<ConsideracionItemEvaluado>> call, Response<List<ConsideracionItemEvaluado>> response) {
                dataConsideraciones.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ConsideracionItemEvaluado>> call, Throwable t) {
                System.out.println(t.toString());
                dataConsideraciones.setValue(Arrays.asList(new ConsideracionItemEvaluado()));
            }
        });
    }

    public LiveData<List<ConsideracionItemEvaluado>> getData() {
        return this.dataConsideraciones;
    }

}
