package com.ideas.actual.ui.consideracion;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ideaas.services.domain.Consideracion;
import com.ideas.actual.ui.colaborador.ColaboradorViewModel;

import java.util.List;

public class ConsideracionViewModel extends ViewModel {

    private String TAG = ColaboradorViewModel.class.getSimpleName();

    private MutableLiveData<List<Consideracion>> dataConsideraciones;

    public void init(List<Consideracion> consideraciones) {
        dataConsideraciones = new MutableLiveData<>();
        dataConsideraciones.setValue(consideraciones);
    }

    public LiveData<List<Consideracion>> getData() {
        return this.dataConsideraciones;
    }
}
