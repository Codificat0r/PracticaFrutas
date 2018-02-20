package com.example.carlos.frutas.ui.AnadirEditar.interactor;

import com.example.carlos.frutas.data.model.Ciudad;
import com.example.carlos.frutas.data.model.Fruta;
import com.example.carlos.frutas.data.repositorio.CiudadRepositorio;
import com.example.carlos.frutas.data.repositorio.FrutasRepositorio;
import com.example.carlos.frutas.ui.utils.CommonUtils;

import java.util.ArrayList;

/**
 * Created by carlos on 20/02/18.
 */

public class AnadirEditarInteractorImpl implements AnadirEditarInteractor {
    private OnAccionCompletadaListener listener;

    public interface OnAccionCompletadaListener {
        void onSuccessCiudades(ArrayList<Ciudad> ciudades);
        void onSuccessAnadirEditar();
        void onNombreError();
        void onPesoError();
    }

    public AnadirEditarInteractorImpl(OnAccionCompletadaListener listener) {
        this.listener = listener;
    }

    @Override
    public void obtenerCiudades() {
        listener.onSuccessCiudades(CiudadRepositorio.getInstance().getCiudades());
    }

    @Override
    public void actualizarFruta(Fruta fruta, int ciudad) {
        if (CommonUtils.estaVacio(fruta.getNombre()))
            listener.onNombreError();
        else {
            FrutasRepositorio.getInstance().updateFruta(fruta, ciudad);
            listener.onSuccessAnadirEditar();
        }
    }

    @Override
    public void anadirFruta(Fruta fruta, int ciudad) {
        if (CommonUtils.estaVacio(fruta.getNombre()))
            listener.onNombreError();
        else {
            FrutasRepositorio.getInstance().anadirFruta(fruta, ciudad);
            listener.onSuccessAnadirEditar();
        }
    }
}
