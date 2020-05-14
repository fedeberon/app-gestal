package com.ideas.actual.ui.colaborador;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ideaas.services.domain.EvaluacionDelColaborador;
import com.ideas.actual.R;
import com.ideas.actual.adapter.ItemEvaluadoAdapter;
import com.ideas.actual.ui.evaluacion.EvaluacionDeColaboradorViewModel;

public class ColaboradorActivity extends AppCompatActivity {

    private ListView listViewEvaluacion;
    private EvaluacionDeColaboradorViewModel evaluacionDeColaboradorViewModel;
    private TextView txtNombreHeader;
    private TextView txtApellidoHeader;
    private TextView txtRolHeader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Resultado de Evaluacion");
        setContentView(R.layout.activity_colaborador);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listViewEvaluacion = findViewById(R.id.evaluacionDeColaborador);

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.header, listViewEvaluacion,false);
        listViewEvaluacion.addHeaderView(header);
        txtNombreHeader = findViewById(R.id.txtNombreHeader);
        txtApellidoHeader = findViewById(R.id.txtApellidoHeader);
        txtRolHeader = findViewById(R.id.txtRolHeader);

        EvaluacionDelColaborador evaluacionDelColaborador = getColaboradorEvaluado();
        evaluacionDeColaboradorViewModel = new EvaluacionDeColaboradorViewModel(evaluacionDelColaborador.getItemEvaluados());
        evaluacionDeColaboradorViewModel.getData().observe(this, itemEvaluados -> {
            ItemEvaluadoAdapter adapter = new ItemEvaluadoAdapter(this, itemEvaluados);
            txtNombreHeader.setText(evaluacionDelColaborador.getColaborador().getName());
            txtApellidoHeader.setText(evaluacionDelColaborador.getColaborador().getLastName());
            txtRolHeader.setText(evaluacionDelColaborador.getColaborador().getRol().getName());
            listViewEvaluacion.setAdapter(adapter);
        });

    }

    private EvaluacionDelColaborador getColaboradorEvaluado() {
        return (EvaluacionDelColaborador) getIntent().getSerializableExtra("evaluacionDelColaborador");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
