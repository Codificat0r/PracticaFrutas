package com.example.carlos.frutas.ui.lista.presenter;

import com.example.carlos.frutas.data.model.Fruta;
import com.example.carlos.frutas.ui.lista.contract.ListaContract;
import com.example.carlos.frutas.ui.lista.interactor.ListaInteractor;
import com.example.carlos.frutas.ui.lista.interactor.ListaInteractorImpl;

import java.util.ArrayList;

/**
 * Created by carlos on 19/02/18.
 */

public class ListaPresenter implements ListaContract.Presenter, ListaInteractorImpl.OnActionFinishedListener {

    private ListaContract.Vista vista;
    private ListaInteractor interactor;

    public ListaPresenter(ListaContract.Vista vista) {
        this.vista = vista;
        this.interactor = new ListaInteractorImpl(this);
    }

    @Override
    public void onSuccess(ArrayList<Fruta> frutas) {
        vista.DejarDeMostrarProgressDialog();
        vista.onSuccess(frutas);
    }

    @Override
    public void cargarFrutasPorPeso() {
        vista.MostrarProgressDialog();
        interactor.obtenerFrutasPorPeso();
    }

    @Override
    public void cargarFrutasPorNombre() {
        vista.MostrarProgressDialog();
        interactor.obtenerFrutasPorNombre();
    }

    @Override
    public void cargarFrutas() {
        vista.MostrarProgressDialog();
        interactor.obtenerFrutas();
    }

    @Override
    public void pedirBorrarFruta(Fruta fruta) {
        interactor.borrarFruta(fruta);
    }
}
