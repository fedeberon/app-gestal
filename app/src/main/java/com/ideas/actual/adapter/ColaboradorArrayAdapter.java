package com.ideas.actual.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ideas.actual.R;
import com.ideas.actual.model.Colaborador;
import com.ideas.actual.ui.evaluacion.EvaluacionActivity;

import java.util.List;

public class ColaboradorArrayAdapter extends ArrayAdapter<Colaborador> {

    public ColaboradorArrayAdapter(Context context, List<Colaborador> colaboradores) {
        super(context, 0, colaboradores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Colaborador colaborador = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_row_colaborador, parent, false);
        }

        TextView id = convertView.findViewById(R.id.id);
        id.setText(String.valueOf(colaborador.getId()));

        TextView name = convertView.findViewById(R.id.name);
        name.setText(colaborador.getName());

        TextView lastName = convertView.findViewById(R.id.lastName);
        lastName.setText(colaborador.getUsername());

        TextView rol = convertView.findViewById(R.id.rol);
        rol.setText(colaborador.getPuesto().getName());

        Button buttonToEvaluate = convertView.findViewById(R.id.button_evaluate);
        buttonToEvaluate.setOnClickListener(v -> goToEvaluation(v, colaborador));

        return convertView;
    }

    private void goToEvaluation(View v, Colaborador colaborador) {
        Intent intent = new Intent(v.getContext(), EvaluacionActivity.class);
        intent.putExtra("colaborador", colaborador);
        getContext().startActivity(intent);
    }
}
