package com.ideas.actual.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ideaas.services.domain.Item;
import com.ideas.actual.R;

import java.util.List;

public class ItemArrayAdapter extends ArrayAdapter<Item> {

    public ItemArrayAdapter(Context context, List<Item> items){
        super(context, 0, items);
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

        // Return the completed view to render on screen
        return convertView;
    }

}
