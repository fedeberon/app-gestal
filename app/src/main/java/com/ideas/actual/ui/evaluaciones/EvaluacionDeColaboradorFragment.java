package com.ideas.actual.ui.evaluaciones;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.ideas.actual.R;
import com.ideas.actual.adapter.EvaluacionDeColaboradorArrayAdapter;

public class EvaluacionDeColaboradorFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    private EvaluacionDeColaboradorArrayAdapter adapter;
    private EvaluacionDeColaboradorViewModel viewModel;
    private ListView listViewEvaluacionColaboradores;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Integer page = 0;
    private Dialog dialog;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listViewEvaluacionColaboradores = (ListView) view.findViewById(R.id.list_colaboradores);
        swipeRefreshLayout = view.findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.progress);
        dialog = builder.create();
        dialog.show();
        viewModel = ViewModelProviders.of(this).get(EvaluacionDeColaboradorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_colaborador_list, container, false);
        viewModel.init(this.page);
        viewModel.getData().observe(this, evaluaciones -> {
            adapter =  new EvaluacionDeColaboradorArrayAdapter(getContext(), evaluaciones);
            listViewEvaluacionColaboradores.setAdapter(adapter);
            dialog.dismiss();
        });

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.buscar, menu);
        MenuItem menuItem = menu.findItem(R.id.buscar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Ingrese un colaborador ...");
        searchView.setOnQueryTextListener(this);

        super.onCreateOptionsMenu(menu,inflater);
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

    @Override
    public boolean onQueryTextSubmit(String valueToSearch) {
        dialog.show();
        viewModel.init(this.page, valueToSearch);
        adapter.clear();
        viewModel.getData().observe(this, evaluaciones -> {
            adapter.addAll(evaluaciones);
            dialog.dismiss();
            Toast.makeText(getContext(), "Se agregaron ".concat(String.valueOf(evaluaciones.size())).concat(" registros."), Toast.LENGTH_SHORT).show();
        });
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return true;
    }
}
