package com.example.carlos.frutas.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.BaseColumns;
import android.util.Log;
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

    public static long insert(Fruta fruta, int ciudad) {
        SQLiteDatabase db = FrutasOpenHelper.getInstance().openDatabase();

        ContentValues cv = new ContentValues();

        cv.put(FrutasContract.FrutaEntry.COLUMN_NOMBRE, fruta.getNombre());
        cv.put(FrutasContract.FrutaEntry.COLUMN_PESO, fruta.getPeso());
        cv.put(FrutasContract.FrutaEntry.COLUMN_CIUDAD, ciudad);

        long resultado = db.insert(FrutasContract.FrutaEntry.TABLE_NAME, null, cv);

        FrutasOpenHelper.getInstance().closeDatabase();

        return resultado;
    }

    public static long update(Fruta fruta, int ciudad) {
        SQLiteDatabase db = FrutasOpenHelper.getInstance().openDatabase();

        ContentValues cv = new ContentValues();

        cv.put(FrutasContract.FrutaEntry.COLUMN_NOMBRE, fruta.getNombre());
        cv.put(FrutasContract.FrutaEntry.COLUMN_PESO, fruta.getPeso());
        cv.put(FrutasContract.FrutaEntry.COLUMN_CIUDAD, ciudad);

        long resultado = db.update(FrutasContract.FrutaEntry.TABLE_NAME, cv, BaseColumns._ID + "=" + fruta.getId(), null);

        FrutasOpenHelper.getInstance().closeDatabase();

        return resultado;
    }
}
