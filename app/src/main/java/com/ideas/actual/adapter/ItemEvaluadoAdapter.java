package com.ideas.actual.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ideaas.services.domain.ItemEvaluado;
import com.ideas.actual.R;

import java.util.List;

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
        ratingBar.setRating(itemEvaluado.getRating());
        ratingBar.setIsIndicator(true);

        return convertView;
    }

}
