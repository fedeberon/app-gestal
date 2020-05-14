package com.ideas.actual.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ideaas.services.domain.Consideracion;
import com.ideas.actual.R;

import java.util.List;

public class ConsideracionArrayAdapter extends ArrayAdapter<Consideracion> {

    public ConsideracionArrayAdapter(Context context, List<Consideracion> consideraciones) {
        super(context, 0, consideraciones);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Consideracion consideracion = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_row_consideracion, parent, false);
        }

        TextView id = convertView.findViewById(R.id.id);
        id.setText(String.valueOf(consideracion.getId()));

        TextView name = convertView.findViewById(R.id.name);
        name.setText(consideracion.getValue());

        return convertView;
    }
}