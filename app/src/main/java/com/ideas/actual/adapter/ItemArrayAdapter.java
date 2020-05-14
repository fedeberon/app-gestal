package com.ideas.actual.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ideaas.services.domain.Item;
import com.ideas.actual.R;
import com.ideas.actual.ui.consideracion.ConsideracionDialogFragment;
import com.ideas.actual.ui.evaluacion.EvaluacionActivity;

import java.util.List;

public class ItemArrayAdapter extends ArrayAdapter<Item> {

    private Activity pActivity;
    private Context context;

    public ItemArrayAdapter(Context context, List<Item> items, Activity pActivity){
        super(context, 0, items);
        this.context = context;
        this.pActivity = pActivity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Item item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rating_bar_with_text, parent, false);
        }
        // Lookup view for data population
        TextView name = convertView.findViewById(R.id.item_to_evaluate);
        name.setText(item.getValue());
        TextView id = convertView.findViewById(R.id.id_item_to_evaluate);
        id.setText(item.getId().toString());

        Button buttonConsideraciones = convertView.findViewById(R.id.buttonConsideraciones);
        buttonConsideraciones.setText("Consideraciones");
        buttonConsideraciones.setOnClickListener(v -> {
            Intent intent = new Intent(pActivity, ConsideracionDialogFragment.class);
            intent.putExtra("item", item);
            pActivity.startActivityForResult(intent, EvaluacionActivity.PICK_CONTACT_REQUEST);
        });

        return convertView;
    }

}