package com.ideas.actual.ui.evaluacion;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ideas.actual.model.ItemEvaluado;

import java.util.List;

public class EvaluacionDeColaboradorViewModel extends ViewModel {

    private MutableLiveData<List<ItemEvaluado>> dataItemsEvaluados;

    public EvaluacionDeColaboradorViewModel(List<ItemEvaluado> itemEvaluados) {
        dataItemsEvaluados = new MutableLiveData<>();
        this.dataItemsEvaluados.setValue(itemEvaluados);
    }


    public LiveData<List<ItemEvaluado>> getData() {
        return this.dataItemsEvaluados;
    }

}
