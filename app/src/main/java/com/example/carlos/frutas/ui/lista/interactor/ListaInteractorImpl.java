package com.example.carlos.frutas.ui.lista.interactor;

import android.os.AsyncTask;

import com.example.carlos.frutas.data.model.Fruta;
import com.example.carlos.frutas.data.repositorio.FrutasRepositorio;

import java.util.ArrayList;

/**
 * Created by carlos on 19/02/18.
 */

public class ListaInteractorImpl implements ListaInteractor {

    private OnActionFinishedListener listener;

    public interface OnActionFinishedListener {
        void onSuccess(ArrayList<Fruta> frutas);
    }

    public ListaInteractorImpl(OnActionFinishedListener listener) {
        this.listener = listener;
    }

    @Override
    public void obtenerFrutas() {
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                listener.onSuccess(FrutasRepositorio.getInstance().getFrutas());
                return null;
            }
        }.execute();
    }

    @Override
    public void obtenerFrutasPorPeso() {
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                listener.onSuccess(FrutasRepositorio.getInstance().getFrutasPorPeso());
                return null;
            }
        }.execute();
    }

    @Override
    public void obtenerFrutasPorNombre() {
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                listener.onSuccess(FrutasRepositorio.getInstance().getFrutasPorNombre());
                return null;
            }
        }.execute();
    }

    @Override
    public void borrarFruta(Fruta fruta) {
        FrutasRepositorio.getInstance().deleteFruta(fruta);
    }
}
