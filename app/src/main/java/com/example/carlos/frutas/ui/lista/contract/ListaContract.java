package com.example.carlos.frutas.ui.lista.contract;

import com.example.carlos.frutas.data.model.Fruta;

import java.util.ArrayList;

/**
 * Created by carlos on 19/02/18.
 */

public interface ListaContract {
    interface Vista {
        void MostrarProgressDialog();
        void DejarDeMostrarProgressDialog();
        void onSuccess(ArrayList<Fruta> frutas);
    }

    interface Presenter {
        void cargarFrutasPorPeso();
        void cargarFrutasPorNombre();
        void cargarFrutas();
        void pedirBorrarFruta(Fruta fruta);
    }
}
