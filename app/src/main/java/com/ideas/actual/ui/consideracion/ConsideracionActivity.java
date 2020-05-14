package com.ideas.actual.ui.consideracion;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.ideaas.services.domain.ItemEvaluado;
import com.ideas.actual.R;
import com.ideas.actual.adapter.ConsideracionEvaluadaAdapter;

public class ConsideracionActivity extends AppCompatActivity {

    private ConsideracionEvaluadaViewModel consideracionEvaluadaViewModel;
    private ListView listViewEvaluacion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ItemEvaluado itemEvaluado = this.getItemEvaluado();
        this.setTitle("Item: ".concat(itemEvaluado.getItem().getValue()));
        setContentView(R.layout.fragment_consideracion_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listViewEvaluacion = findViewById(R.id.list_consideraciones);
        consideracionEvaluadaViewModel = ViewModelProviders.of(this).get(ConsideracionEvaluadaViewModel.class);
        consideracionEvaluadaViewModel.init(itemEvaluado);
        consideracionEvaluadaViewModel.getData().observe(this, consideracions -> {
            ConsideracionEvaluadaAdapter  adapter = new ConsideracionEvaluadaAdapter(this, consideracions);
            listViewEvaluacion.setAdapter(adapter);
        });
    }

    private void backToHome() {
        finish();
    }

    private ItemEvaluado getItemEvaluado() {
        return (ItemEvaluado) getIntent().getSerializableExtra("itemEvaluado");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
