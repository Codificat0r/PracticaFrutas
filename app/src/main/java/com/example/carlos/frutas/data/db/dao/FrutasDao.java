package com.example.carlos.frutas.data.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.carlos.frutas.data.db.FrutasContract;
import com.example.carlos.frutas.data.db.FrutasOpenHelper;
import com.example.carlos.frutas.data.model.Fruta;

import java.util.ArrayList;

/**
 * Created by carlos on 20/02/18.
 */

public class FrutasDao {
    public static ArrayList<Fruta> loadAll() {
        ArrayList<Fruta> frutas = new ArrayList<>();

        SQLiteDatabase db = FrutasOpenHelper.getInstance().openDatabase();

        Cursor cursor = db.query(FrutasContract.FrutaEntry.TABLE_NAME, FrutasContract.FrutaEntry.ALL_COLUMNS, null,null,null,null,null, null);

        if (cursor.moveToFirst()) {
            do {
                Fruta fruta = new Fruta(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), CiudadDao.getCiudadName(cursor.getInt(3)));
                frutas.add(fruta);
            } while (cursor.moveToNext());
        }

        FrutasOpenHelper.getInstance().closeDatabase();

        return frutas;
    }

    public static ArrayList<Fruta> loadAllPorNombre() {
        ArrayList<Fruta> frutas = new ArrayList<>();

        SQLiteDatabase db = FrutasOpenHelper.getInstance().openDatabase();

        Cursor cursor = db.query(FrutasContract.FrutaEntry.TABLE_NAME, FrutasContract.FrutaEntry.ALL_COLUMNS, null,null,null,null, FrutasContract.FrutaEntry.COLUMN_NOMBRE, null);

        if (cursor.moveToFirst()) {
            do {
                Fruta fruta = new Fruta(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), CiudadDao.getCiudadName(cursor.getInt(3)));
                frutas.add(fruta);
            } while (cursor.moveToNext());
        }

        FrutasOpenHelper.getInstance().closeDatabase();

        return frutas;
    }

    public static ArrayList<Fruta> loadAllPorPeso() {
        ArrayList<Fruta> frutas = new ArrayList<>();

        SQLiteDatabase db = FrutasOpenHelper.getInstance().openDatabase();

        Cursor cursor = db.query(FrutasContract.FrutaEntry.TABLE_NAME, FrutasContract.FrutaEntry.ALL_COLUMNS, null,null,null,null,FrutasContract.FrutaEntry.COLUMN_PESO, null);

        if (cursor.moveToFirst()) {
            do {
                Fruta fruta = new Fruta(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), CiudadDao.getCiudadName(cursor.getInt(3)));
                frutas.add(fruta);
            } while (cursor.moveToNext());
        }

        FrutasOpenHelper.getInstance().closeDatabase();

        return frutas;
    }

    public static long delete(Fruta fruta) {
        SQLiteDatabase db = FrutasOpenHelper.getInstance().openDatabase();

        long resultado = db.delete(FrutasContract.FrutaEntry.TABLE_NAME, BaseColumns._ID + "=" + fruta.getId(), null);

        FrutasOpenHelper.getInstance().closeDatabase();

        return resultado;
    }
}
