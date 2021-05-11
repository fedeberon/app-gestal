package com.ideas.actual.ui.evaluacion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
