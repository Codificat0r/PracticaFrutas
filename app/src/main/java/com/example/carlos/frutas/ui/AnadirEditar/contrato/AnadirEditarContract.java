package com.example.carlos.frutas.ui.AnadirEditar.contrato;

import com.example.carlos.frutas.data.model.Ciudad;
import com.example.carlos.frutas.data.model.Fruta;

import java.util.ArrayList;

/**
 * Created by carlos on 20/02/18.
 */

public interface AnadirEditarContract {

    interface Vista {
        void onSuccessCiudades(ArrayList<Ciudad> ciudades);
        void mostrarErrorPeso();
        void mostrarErrorNombre();
        void onSuccessAnadirEditar();
    }

    interface Presenter {

        void obtenerCiudades();

        void pedirActualizarFruta(Fruta fruta, int ciudad);

        void pedirAnadirFruta(Fruta fruta, int ciudad);
    }
}
