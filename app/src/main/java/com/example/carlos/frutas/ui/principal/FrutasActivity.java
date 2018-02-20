package com.example.carlos.frutas.ui.principal;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.carlos.frutas.R;
import com.example.carlos.frutas.ui.lista.vista.ListaFragment;

public class FrutasActivity extends AppCompatActivity implements ListaFragment.OnListaListener {

    ListaFragment listaFragment;

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
        fragmentTransaction.commit();
    }
}
