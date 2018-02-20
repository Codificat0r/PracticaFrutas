package com.example.carlos.frutas.ui.AnadirEditar.interactor;

import com.example.carlos.frutas.data.model.Fruta;

/**
 * Created by carlos on 20/02/18.
 */

public interface AnadirEditarInteractor {
    void obtenerCiudades();

    void actualizarFruta(Fruta fruta, int ciudad);

    void anadirFruta(Fruta fruta, int ciudad);
}
