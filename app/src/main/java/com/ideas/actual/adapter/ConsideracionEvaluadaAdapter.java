package com.ideas.actual.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ideaas.services.domain.ConsideracionItemEvaluado;
import com.ideaas.services.domain.EvaluacionDelColaborador;
import com.ideas.actual.R;

import java.util.List;

public class ConsideracionEvaluadaAdapter extends ArrayAdapter<ConsideracionItemEvaluado> {

    public ConsideracionEvaluadaAdapter(Context context, List<ConsideracionItemEvaluado> consideracionItemEvaluados) {
        super(context, 0, consideracionItemEvaluados);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConsideracionItemEvaluado consideracionItemEvaluado = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_row_consideracion, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name);
        name.setText(String.valueOf(consideracionItemEvaluado.getId()));

        CheckBox checkBox = convertView.findViewById(R.id.check);
        checkBox.setChecked(consideracionItemEvaluado.isCheckeado());
        checkBox.setClickable(false);

        return convertView;
    }


}
