package com.ideas.actual.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ideaas.services.domain.ItemEvaluado;
import com.ideas.actual.R;
import com.ideas.actual.ui.consideracion.ConsideracionActivity;

import java.util.List;
import java.util.Objects;

public class ItemEvaluadoAdapter extends ArrayAdapter<ItemEvaluado> {

    public ItemEvaluadoAdapter(Context context, List<ItemEvaluado> items){
        super(context, 0, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ItemEvaluado itemEvaluado = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rating_bar_with_text, parent, false);
        }
        TextView name = convertView.findViewById(R.id.item_to_evaluate);
        name.setText(itemEvaluado.getItem().getValue());

        RatingBar  ratingBar = convertView.findViewById(R.id.ratingBar);
        ratingBar.setRating(Objects.nonNull(itemEvaluado.getRating()) ? itemEvaluado.getRating() : 0);
        ratingBar.setIsIndicator(true);

        Button buttonConsideraciones = convertView.findViewById(R.id.buttonConsideraciones);
        buttonConsideraciones.setText("Consideraciones Evaluadas");
        buttonConsideraciones.setOnClickListener(v -> {
            goToConsideraciones(v, itemEvaluado);
        });

        return convertView;
    }

    private void goToConsideraciones(View v, ItemEvaluado itemEvaluado) {
        Intent intent = new Intent(getContext(), ConsideracionActivity.class);
        intent.putExtra("itemEvaluado", itemEvaluado);
        getContext().startActivity(intent);
    }



}
