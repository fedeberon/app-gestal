package com.ideas.actual.ui.evaluacion;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ideaas.services.domain.Colaborador;
import com.ideaas.services.domain.EvaluacionDelColaborador;
import com.ideaas.services.domain.Item;
import com.ideaas.services.domain.ItemEvaluado;
import com.ideas.actual.R;
import com.ideas.actual.Utils;
import com.ideas.actual.adapter.ItemArrayAdapter;
import com.ideas.actual.configuration.RetrofitServiceFactory;
import com.ideas.actual.services.EvaluacionService;
import com.ideas.actual.ui.colaborador.ColaboradorActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvaluacionActivity extends AppCompatActivity {

    private EvaluacionViewModel evaluacionViewModel;
    private ListView listViewEvaluacion;
    private Button btnSave, btnBack;
    private TextView txtIdEvaluacion;
    private Colaborador colaborador;
    private EvaluacionService evaluacionService = RetrofitServiceFactory.createService(EvaluacionService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion);

        listViewEvaluacion = (ListView) findViewById(R.id.evaluacion);
        txtIdEvaluacion = findViewById(R.id.txtIdEvaluacion);

        evaluacionViewModel = ViewModelProviders.of(this).get(EvaluacionViewModel.class);
        colaborador = getColaboradorEvaluado();

        btnSave = new Button(this);
        btnSave.setBackgroundColor(Color.parseColor("#28a744"));
        btnSave.setText("Guardar");
        listViewEvaluacion.addFooterView(btnSave);

        btnSave.setOnClickListener(view -> save());
        evaluacionViewModel.init(colaborador.getRol());
        evaluacionViewModel.getData().observe(this, response -> {
            ItemArrayAdapter adapter = new ItemArrayAdapter(this, response.getItems());
            txtIdEvaluacion.setText(response.getId().toString());
            this.setTitle(Utils.getDataColaborador(colaborador));
            listViewEvaluacion.setAdapter(adapter);
        });

    }

    private void save(){
        ArrayList<ItemEvaluado> itemsEvaluados = this.getItemsEvaluados();
        EvaluacionDelColaborador evaluacionDelColaborador = new EvaluacionDelColaborador();
        evaluacionDelColaborador.setColaborador(colaborador);
        evaluacionDelColaborador.setRolEvaluado(colaborador.getRol());
        evaluacionDelColaborador.setItemEvaluados(itemsEvaluados);
        Toast.makeText(EvaluacionActivity.this,"Guardando...",Toast.LENGTH_LONG).show();
        Call<EvaluacionDelColaborador> call = evaluacionService.save(evaluacionDelColaborador);
        call.enqueue(new Callback<EvaluacionDelColaborador>() {
            @Override
            public void onResponse(Call<EvaluacionDelColaborador> call, Response<EvaluacionDelColaborador> response) {
                Intent intent = new Intent(EvaluacionActivity.this, ColaboradorActivity.class);
                intent.putExtra("evaluacionDelColaborador", response.body());
                finish();
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<EvaluacionDelColaborador> call, Throwable t) {
                Toast.makeText(EvaluacionActivity.this,"Hubo un error al intentar guardar la Evaluacion.",Toast.LENGTH_LONG).show();
            }
        });
    }

    private Colaborador getColaboradorEvaluado() {
        return (Colaborador) getIntent().getSerializableExtra("colaborador");
    }

    private ArrayList<ItemEvaluado> getItemsEvaluados() {
        ArrayList<ItemEvaluado> itemsEvaluados = new ArrayList<>();
        for (int i = 0; i < listViewEvaluacion.getAdapter().getCount(); i++) {
            View v = getViewByPosition(i, listViewEvaluacion);
            if(v instanceof Button) continue;

            TextView idItem = v.findViewById(R.id.id_item_to_evaluate);
            TextView valueItem = v.findViewById(R.id.item_to_evaluate);
            RatingBar rating = v.findViewById(R.id.ratingBar);
            Item item = new Item();
            item.setId(Long.parseLong(idItem.getText().toString()));
            item.setValue(valueItem.getText().toString());

            ItemEvaluado itemEvaluado = new ItemEvaluado();
            itemEvaluado.setItem(item);
            itemEvaluado.setRating(rating.getRating());

            itemsEvaluados.add(itemEvaluado);
        }

        return itemsEvaluados;
    }

    public View getViewByPosition(int position, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (position < firstListItemPosition || position > lastListItemPosition ) {
            return listView.getAdapter().getView(position, listView.getChildAt(position), listView);
        } else {
            final int childIndex = position - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

}