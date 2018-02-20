package com.example.carlos.frutas.ui.principal;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.carlos.frutas.R;
import com.example.carlos.frutas.ui.AnadirEditar.vista.AnadirEditarFragment;
import com.example.carlos.frutas.ui.lista.vista.ListaFragment;

public class FrutasActivity extends AppCompatActivity implements ListaFragment.OnListaListener, AnadirEditarFragment.OnAnadirEditarListener {

    ListaFragment listaFragment;
    AnadirEditarFragment anadirEditarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        cargarPrimerFragment();
    }

    private void cargarPrimerFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (listaFragment == null) {
            listaFragment = ListaFragment.newInstance();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, listaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void finalizado() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }

    @Override
    public void AnadirEditar(Bundle bundle) {
        FragmentManager fm = getSupportFragmentManager();
        anadirEditarFragment = AnadirEditarFragment.newInstance(bundle);
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, anadirEditarFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
