package com.ideas.actual.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ideas.actual.R;
import com.ideas.actual.model.EvaluacionDelColaborador;
import com.ideas.actual.ui.colaborador.ColaboradorActivity;

import java.util.List;

public class EvaluacionDeColaboradorArrayAdapter extends ArrayAdapter<EvaluacionDelColaborador> {


    public EvaluacionDeColaboradorArrayAdapter(Context context, List<EvaluacionDelColaborador> evaluaciones) {
        super(context, 0, evaluaciones);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EvaluacionDelColaborador evaluacion = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_row_colaborador, parent, false);
        }

        TextView id = convertView.findViewById(R.id.id);
        id.setText(String.valueOf(evaluacion.getId()));

        TextView name = convertView.findViewById(R.id.name);
        name.setText(evaluacion.getColaborador().getName());

        TextView lastName = convertView.findViewById(R.id.lastName);
        //lastName.setText(evaluacion.getColaborador().getLastName());

        TextView rol = convertView.findViewById(R.id.rol);
        //rol.setText(evaluacion.getColaborador().getRol().getName());

        Button buttonToEvaluate = convertView.findViewById(R.id.button_evaluate);
        buttonToEvaluate.setText("Ver");
        buttonToEvaluate.setBackgroundColor(Color.parseColor("#24a2b8"));
        buttonToEvaluate.setOnClickListener(v -> goToEvaluation(v, evaluacion));

        return convertView;
    }


    private void goToEvaluation(View v, EvaluacionDelColaborador evaluacionDelColaborador) {
        Intent intent = new Intent(v.getContext(), ColaboradorActivity.class);
        intent.putExtra("evaluacionDelColaborador", evaluacionDelColaborador);
        getContext().startActivity(intent);
    }
}
