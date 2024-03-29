package com.ideas.actual.ui.colaborador;

import android.app.AlertDialog;
import android.app.Dialog;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ideas.actual.R;
import com.ideas.actual.adapter.ColaboradorArrayAdapter;
import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import com.wdullaer.swipeactionadapter.SwipeDirection;

public class ColaboradorFragment extends Fragment {

    private ColaboradorViewModel colaboradorViewModel;
    private ListView listViewColaboradores;
    private SwipeActionAdapter mAdapter;
    private Dialog dialog;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listViewColaboradores = (ListView) view.findViewById(R.id.list_colaboradores);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.progress);
        dialog = builder.create();
        dialog.show();
        colaboradorViewModel = ViewModelProviders.of(this).get(ColaboradorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_colaborador_list, container, false);
        colaboradorViewModel.init();
        colaboradorViewModel.getData().observe(this, colaboradores -> {
            ColaboradorArrayAdapter adapter = new ColaboradorArrayAdapter(getContext(), colaboradores);
            adapter.notifyDataSetChanged();
            mAdapter = new SwipeActionAdapter(adapter);
            mAdapter.setListView(listViewColaboradores);
            listViewColaboradores.setAdapter(mAdapter);
            mAdapter.addBackground(SwipeDirection.DIRECTION_FAR_LEFT,R.layout.row_bg_left_far)
                    .addBackground(SwipeDirection.DIRECTION_NORMAL_LEFT,R.layout.row_bg_left)
                    .addBackground(SwipeDirection.DIRECTION_FAR_RIGHT,R.layout.row_bg_right_far)
                    .addBackground(SwipeDirection.DIRECTION_NORMAL_RIGHT,R.layout.row_bg_right);
            mAdapter.setSwipeActionListener(new ColaboradorSwipeActionListener(getContext(), mAdapter));
            dialog.dismiss();
        });

        return root;
    }
}
