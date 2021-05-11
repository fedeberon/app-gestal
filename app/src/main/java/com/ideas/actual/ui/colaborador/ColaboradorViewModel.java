package com.ideas.actual.ui.colaborador;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ideas.actual.configuration.RetrofitServiceFactory;
import com.ideas.actual.model.Colaborador;
import com.ideas.actual.services.ColaboradorService;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ColaboradorViewModel extends ViewModel {

    private String TAG = ColaboradorViewModel.class.getSimpleName();

    private MutableLiveData<List<Colaborador>> dataColaboradores;
    ColaboradorService colaboradorService = RetrofitServiceFactory.createService(ColaboradorService.class);


    public void init(){
        dataColaboradores = new MutableLiveData<>();
        Call<List<Colaborador>> call = colaboradorService.findAll();
        call.enqueue(new Callback<List<Colaborador>>() {
            @Override
            public void onResponse(Call<List<Colaborador>> call, Response<List<Colaborador>> response) {
                System.out.println(call);
                dataColaboradores.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Colaborador>> call, Throwable t) {
                System.out.println(t.toString());
                dataColaboradores.setValue(Arrays.asList(new Colaborador()));
            }
        });
    }

    public LiveData<List<Colaborador>> getData() {
        return this.dataColaboradores;
    }
}
