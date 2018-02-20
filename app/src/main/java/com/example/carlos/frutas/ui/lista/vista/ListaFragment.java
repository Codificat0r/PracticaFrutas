package com.example.carlos.frutas.ui.lista.vista;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.carlos.frutas.R;
import com.example.carlos.frutas.adapter.FrutasAdapter;
import com.example.carlos.frutas.data.db.FrutasContract;
import com.example.carlos.frutas.data.db.FrutasOpenHelper;
import com.example.carlos.frutas.data.model.Fruta;
import com.example.carlos.frutas.ui.AnadirEditar.vista.AnadirEditarFragment;
import com.example.carlos.frutas.ui.lista.contract.ListaContract;
import com.example.carlos.frutas.ui.lista.presenter.ListaPresenter;
import com.example.carlos.frutas.ui.utils.SharedPreferencesConstants;

import java.util.ArrayList;
import java.util.prefs.Preferences;

public class ListaFragment extends Fragment implements ListaContract.Vista {

    private Toolbar toolbar;
    private ListView lstvFrutas;
    private ListaContract.Presenter presenter;
    private ProgressDialog progressDialog;
    private FrutasAdapter adapter;
    private SharedPreferences preferences;
    private FloatingActionButton fab;

    private OnListaListener listener;

    public interface OnListaListener {
        void AnadirEditar(Bundle bundle);
    }

    public ListaFragment() {

    }

    public static ListaFragment newInstance() {
        ListaFragment fragment = new ListaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista, container, false);

        presenter = new ListaPresenter(this);

        preferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        setHasOptionsMenu(true);

        toolbar = rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        lstvFrutas = rootView.findViewById(R.id.lstvFrutas);

        adapter = new FrutasAdapter(getContext());

        lstvFrutas.setAdapter(adapter);

        lstvFrutas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fruta clickedFruta = adapter.getItem(i);
                Bundle b = new Bundle();
                b.putSerializable(AnadirEditarFragment.ARGS_FRUTA, clickedFruta);
                listener.AnadirEditar(b);
            }
        });

        fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.AnadirEditar(null);
            }
        });

        registerForContextMenu(lstvFrutas);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cargarLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Acciones sobre la fruta");
        getActivity().getMenuInflater().inflate(R.menu.lista_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.mitmBorrar:
                presenter.pedirBorrarFruta(adapter.getItem(info.position));
                cargarLista();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.lista_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences.Editor editor = preferences.edit();
        switch (item.getItemId()) {
            case R.id.mitmOrdenarNombre:
                editor.putString(SharedPreferencesConstants.ORDEN_PREF, FrutasContract.FrutaEntry.COLUMN_NOMBRE);
                presenter.cargarFrutasPorNombre();
                break;
            case R.id.mitmOrdenarPeso:
                editor.putString(SharedPreferencesConstants.ORDEN_PREF, FrutasContract.FrutaEntry.COLUMN_PESO);
                presenter.cargarFrutasPorPeso();
                break;
        }
        editor.commit();
        return true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListaListener) {
            listener = (OnListaListener) context;
        } else {
            throw new RuntimeException(context.toString() + " debe implementar OnListaListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void MostrarProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Cargando frutas . . .");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void DejarDeMostrarProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void onSuccess(final ArrayList<Fruta> frutas) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                adapter.addAll(frutas);
            }
        });

    }

    private void cargarLista() {
        String orden = preferences.getString(SharedPreferencesConstants.ORDEN_PREF, "defecto");

        if (orden.equals("defecto"))
            presenter.cargarFrutas();
        else if (orden.equals(FrutasContract.FrutaEntry.COLUMN_NOMBRE))
            presenter.cargarFrutasPorNombre();
        else
            presenter.cargarFrutasPorPeso();
    }
}
