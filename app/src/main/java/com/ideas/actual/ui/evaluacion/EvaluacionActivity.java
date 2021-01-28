package com.ideas.actual.ui.evaluacion;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ideaas.services.domain.Colaborador;
import com.ideaas.services.domain.ConsideracionItemEvaluado;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvaluacionActivity extends AppCompatActivity {

    private EvaluacionViewModel evaluacionViewModel;
    private ListView listViewEvaluacion;
    private Button btnSave;
    private TextView txtIdEvaluacion;
    private TextView textNotFound;
    private Colaborador colaborador;
    private EvaluacionService evaluacionService = RetrofitServiceFactory.createService(EvaluacionService.class);
    private List<ConsideracionItemEvaluado> consideraciones;
    public static final int PICK_CONTACT_REQUEST = 1;
    private Map<Long, List<ConsideracionItemEvaluado>> mapItems = new HashMap<>();
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.progress);
        dialog = builder.create();
        dialog.show();
        listViewEvaluacion = (ListView) findViewById(R.id.evaluacion);
        txtIdEvaluacion = findViewById(R.id.txtIdEvaluacion);
        textNotFound = findViewById(R.id.text_not_foud);

        evaluacionViewModel = ViewModelProviders.of(this).get(EvaluacionViewModel.class);
        colaborador = getColaboradorEvaluado();

        btnSave = new Button(this);
        btnSave.setBackgroundColor(Color.parseColor("#28a744"));
        btnSave.setTextColor(Color.WHITE);
        btnSave.setText("Guardar");
        btnSave.setTop(15);
        listViewEvaluacion.addFooterView(btnSave);

        btnSave.setOnClickListener(view -> save());
        evaluacionViewModel.init(colaborador.getPuesto());
        evaluacionViewModel.getData().observe(this, response -> {
            if(Objects.nonNull(response)){
                ItemArrayAdapter adapter = new ItemArrayAdapter(this, response.getItems(), this);
                txtIdEvaluacion.setText(response.getId().toString());
                this.setTitle(Utils.getDataColaborador(colaborador));
                listViewEvaluacion.setAdapter(adapter);
            } else {
                textNotFound.setVisibility(View.VISIBLE);
            }
            setDialog(false);
        });

    }

    private void save(){
        setDialog(true);
        ArrayList<ItemEvaluado> itemsEvaluados = this.getItemsEvaluados();
        EvaluacionDelColaborador evaluacionDelColaborador = new EvaluacionDelColaborador();
        evaluacionDelColaborador.setColaborador(colaborador);
        evaluacionDelColaborador.setRolEvaluado(colaborador.getPuesto());
        evaluacionDelColaborador.setItemEvaluados(itemsEvaluados);
        Toast.makeText(EvaluacionActivity.this,"Guardando...",Toast.LENGTH_LONG).show();
        Call<EvaluacionDelColaborador> call = evaluacionService.save(evaluacionDelColaborador);
        call.enqueue(new Callback<EvaluacionDelColaborador>() {
            @Override
            public void onResponse(Call<EvaluacionDelColaborador> call, Response<EvaluacionDelColaborador> response) {
                Intent intent = new Intent(EvaluacionActivity.this, ColaboradorActivity.class);
                intent.putExtra("evaluacionDelColaborador", response.body());
                finish();
                setDialog(false);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<EvaluacionDelColaborador> call, Throwable t) {
                setDialog(false);
                Toast.makeText(EvaluacionActivity.this,"Hubo un error al intentar guardar la Evaluacion.", Toast.LENGTH_LONG).show();
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
            itemEvaluado.setConsideracionItemEvaluados(mapItems.get(item.getId()));
            Integer stars = itemEvaluado.calculateRating();

            List<ConsideracionItemEvaluado> consideracionesEvaluadas = mapItems.get(item.getId());
            itemEvaluado.setConsideracionItemEvaluados(consideracionesEvaluadas);

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CONTACT_REQUEST  && resultCode  == RESULT_OK) {
                consideraciones = (ArrayList<ConsideracionItemEvaluado>) data.getSerializableExtra("consideraciones");
                Item item = (Item) data.getSerializableExtra("item");
                this.setRating(item, consideraciones);
                mapItems.putIfAbsent(item.getId(), consideraciones);
        }
    }


    private void setDialog(boolean show){
        if (show) dialog.show();
        else dialog.dismiss();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void setRating(Item item, List<ConsideracionItemEvaluado> consideracionItemEvaluados) {
        for (int i = 0; i < listViewEvaluacion.getAdapter().getCount(); i++) {
            View v = getViewByPosition(i, listViewEvaluacion);
            if(v instanceof Button) continue;

            TextView idItem = v.findViewById(R.id.id_item_to_evaluate);
            Long id = Long.parseLong(idItem.getText().toString());
            if(!item.getId().equals(id)) continue;

            RatingBar rating = v.findViewById(R.id.ratingBar);
            ItemEvaluado itemEvaluado = new ItemEvaluado();
            itemEvaluado.setItem(item);
            itemEvaluado.setConsideracionItemEvaluados(consideracionItemEvaluados);
            Integer stars = itemEvaluado.calculateRating();
            rating.setRating(stars);
        }
    }

}