package com.example.carlos.frutas.ui.AnadirEditar.presenter;

import com.example.carlos.frutas.data.model.Ciudad;
import com.example.carlos.frutas.data.model.Fruta;
import com.example.carlos.frutas.ui.AnadirEditar.contrato.AnadirEditarContract;
import com.example.carlos.frutas.ui.AnadirEditar.interactor.AnadirEditarInteractorImpl;

import java.util.ArrayList;

/**
 * Created by carlos on 20/02/18.
 */

public class AnadirEditarPresenter implements AnadirEditarContract.Presenter, AnadirEditarInteractorImpl.OnAccionCompletadaListener {
    private AnadirEditarContract.Vista vista;
    private AnadirEditarInteractorImpl interactor;

    public AnadirEditarPresenter(AnadirEditarContract.Vista vista) {
        this.vista = vista;
        this.interactor = new AnadirEditarInteractorImpl(this);
    }

    @Override
    public void obtenerCiudades() {
        interactor.obtenerCiudades();
    }

    @Override
    public void pedirActualizarFruta(Fruta fruta, int ciudad) {
        interactor.actualizarFruta(fruta, ciudad);
    }

    @Override
    public void pedirAnadirFruta(Fruta fruta, int ciudad) {
        interactor.anadirFruta(fruta, ciudad);
    }

    @Override
    public void onSuccessCiudades(ArrayList<Ciudad> ciudades) {
        vista.onSuccessCiudades(ciudades);
    }

    @Override
    public void onSuccessAnadirEditar() {
        vista.onSuccessAnadirEditar();
    }

    @Override
    public void onNombreError() {
        vista.mostrarErrorNombre();
    }

    @Override
    public void onPesoError() {
        vista.mostrarErrorPeso();
    }
}
