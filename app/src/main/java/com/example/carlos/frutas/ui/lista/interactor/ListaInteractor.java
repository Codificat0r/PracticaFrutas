package com.example.carlos.frutas.ui.lista.interactor;

import com.example.carlos.frutas.data.model.Fruta;

/**
 * Created by carlos on 19/02/18.
 */

public interface ListaInteractor {
    void obtenerFrutas();
    void obtenerFrutasPorPeso();
    void obtenerFrutasPorNombre();
    void borrarFruta(Fruta fruta);
}
