package com.ideas.actual.ui.consideracion;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.ideaas.services.domain.Consideracion;
import com.ideaas.services.domain.ConsideracionItemEvaluado;
import com.ideaas.services.domain.Item;
import com.ideas.actual.R;
import com.ideas.actual.adapter.ConsideracionArrayAdapter;

import java.util.ArrayList;

public class ConsideracionDialogFragment extends AppCompatActivity {

    private ConsideracionViewModel consideracionViewModel;
    private ListView listConsideraciones;
    private Button btnSave;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_consideracion_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listConsideraciones = (ListView) findViewById(R.id.list_consideraciones);
        consideracionViewModel = ViewModelProviders.of(this).get(ConsideracionViewModel.class);
        Item item = this.getItem();
        btnSave = new Button(this);
        btnSave.setBackgroundColor(Color.parseColor("#28a744"));
        btnSave.setTextColor(Color.WHITE);
        btnSave.setText("Listo");
        listConsideraciones.addFooterView(btnSave);

        btnSave.setOnClickListener(view -> save());

        consideracionViewModel.init(item.getConsideraciones());
        consideracionViewModel.getData().observe(this, consideracions -> {
            ConsideracionArrayAdapter adapter = new ConsideracionArrayAdapter(this, consideracions);
            listConsideraciones.setAdapter(adapter);
        });

    }

    private Item getItem() {
        return (Item) getIntent().getSerializableExtra("item");
    }

    public void save(){
        Intent intent = new Intent();
        intent.putExtra("consideraciones", this.getConsideraciones());
        intent.putExtra("item", this.getItem());
        setResult(Activity.RESULT_OK, intent);
        finish();
    }


    private ArrayList<ConsideracionItemEvaluado> getConsideraciones() {
        ArrayList<ConsideracionItemEvaluado> consideraciones = new ArrayList<>();
        for (int i = 0; i < listConsideraciones.getAdapter().getCount(); i++) {
            View v = getViewByPosition(i, listConsideraciones);
            if(v instanceof Button) continue;
            TextView id = v.findViewById(R.id.id);
            CheckBox checkBox = v.findViewById(R.id.check);
            Consideracion consideracion = new Consideracion();
            consideracion.setId(Long.parseLong(id.getText().toString()));
            ConsideracionItemEvaluado consideracionItemEvaluado = new ConsideracionItemEvaluado(consideracion,  checkBox.isChecked());
            consideraciones.add(consideracionItemEvaluado);
        }

        return consideraciones;
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
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}