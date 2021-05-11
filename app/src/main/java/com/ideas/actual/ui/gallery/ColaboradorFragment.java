package com.ideas.actual.ui.gallery;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ideas.actual.R;

public class ColaboradorFragment extends Fragment {

    private ColaboradorViewModel colaboradorViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        colaboradorViewModel = ViewModelProviders.of(this).get(ColaboradorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);

        return root;
    }

}