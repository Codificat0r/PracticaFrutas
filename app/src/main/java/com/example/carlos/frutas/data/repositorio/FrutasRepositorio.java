package com.example.carlos.frutas.data.repositorio;

import com.example.carlos.frutas.data.db.dao.FrutasDao;
import com.example.carlos.frutas.data.model.Fruta;

import java.util.ArrayList;

/**
 * Created by carlos on 19/02/18.
 */

public class FrutasRepositorio {
    private static FrutasRepositorio frutasRepositorio;

    static {
        frutasRepositorio = new FrutasRepositorio();
    }

    private FrutasRepositorio() {

    }

    public static FrutasRepositorio getInstance() {
        return frutasRepositorio;
    }

    public void anadirFruta(Fruta fruta, int ciudad) {
        FrutasDao.insert(fruta, ciudad);
    }

    public boolean existsFruta(Fruta fruta) {
        return false;
    }

    public ArrayList<Fruta> getFrutas() {
        return FrutasDao.loadAll();
    }

    public ArrayList<Fruta> getFrutasPorNombre() {
        return FrutasDao.loadAllPorNombre();
    }

    public ArrayList<Fruta> getFrutasPorPeso() {
        return FrutasDao.loadAllPorPeso();
    }

    public boolean deleteFruta(Fruta fruta) {
        FrutasDao.delete(fruta);
        return true;
    }

    public boolean updateFruta(Fruta fruta, int ciudad) {
        FrutasDao.update(fruta, ciudad);
        return true;
    }
}
