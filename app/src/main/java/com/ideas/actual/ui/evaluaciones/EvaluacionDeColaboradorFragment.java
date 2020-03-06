package com.ideas.actual.ui.evaluaciones;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.ideas.actual.R;
import com.ideas.actual.adapter.EvaluacionDeColaboradorArrayAdapter;

public class EvaluacionDeColaboradorFragment extends Fragment  implements SwipeRefreshLayout.OnRefreshListener {

    private EvaluacionDeColaboradorArrayAdapter adapter;
    private EvaluacionDeColaboradorViewModel viewModel;
    private ListView listViewEvaluacionColaboradores;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Integer page = 0;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listViewEvaluacionColaboradores = (ListView) view.findViewById(R.id.list_colaboradores);
        swipeRefreshLayout = view.findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(EvaluacionDeColaboradorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_colaborador_list, container, false);
        viewModel.init(this.page);
        viewModel.getData().observe(this, evaluaciones -> {
            adapter =  new EvaluacionDeColaboradorArrayAdapter(getContext(), evaluaciones);
            listViewEvaluacionColaboradores.setAdapter(adapter);
        });

        return root;
    }

    @Override
    public void onRefresh() {
        page++;
        viewModel.init(this.page);
        viewModel.getData().observe(this, evaluaciones -> {
            adapter.addAll(evaluaciones);
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getContext(), "Se agregaron ".concat(String.valueOf(evaluaciones.size())).concat(" registros."), Toast.LENGTH_SHORT).show();
        });
    }
}
