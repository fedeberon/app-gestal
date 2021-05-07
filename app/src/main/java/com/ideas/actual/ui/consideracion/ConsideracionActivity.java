package com.ideas.actual.ui.consideracion;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.ideas.actual.R;
import com.ideas.actual.adapter.ConsideracionEvaluadaAdapter;
import com.ideas.actual.model.ItemEvaluado;

public class ConsideracionActivity extends AppCompatActivity {

    private ConsideracionEvaluadaViewModel consideracionEvaluadaViewModel;
    private ListView listViewEvaluacion;
    private Dialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.progress);
        dialog = builder.create();
        dialog.show();
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
            dialog.dismiss();
        });
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
