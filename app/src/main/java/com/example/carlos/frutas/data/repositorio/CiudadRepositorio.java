package com.example.carlos.frutas.data.repositorio;

import com.example.carlos.frutas.data.db.dao.CiudadDao;
import com.example.carlos.frutas.data.model.Ciudad;

import java.util.ArrayList;

/**
 * Created by carlos on 19/02/18.
 */

public class CiudadRepositorio {
    private static CiudadRepositorio ciudadRepositorio;

    static {
        ciudadRepositorio = new CiudadRepositorio();
    }

    private CiudadRepositorio() {

    }

    public static CiudadRepositorio getInstance() {
        return ciudadRepositorio;
    }

    public void anadirCiudad(Ciudad ciudad) {

    }

    public boolean existsCiudad(Ciudad ciudad) {
        return false;
    }

    public ArrayList<Ciudad> getCiudades() {
        return CiudadDao.loadAll();
    }

    public boolean deleteCiudad(Ciudad ciudad) {
        return false;
    }

    public boolean updateCiudad(Ciudad ciudad) {
        return false;
    }
}
