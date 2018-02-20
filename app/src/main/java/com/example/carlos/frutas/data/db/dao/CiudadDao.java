package com.example.carlos.frutas.data.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carlos.frutas.data.db.FrutasContract;
import com.example.carlos.frutas.data.db.FrutasOpenHelper;
import com.example.carlos.frutas.data.model.Ciudad;

import java.util.ArrayList;

/**
 * Created by carlos on 20/02/18.
 */

public class CiudadDao {

    public static ArrayList<Ciudad> loadAll() {
        ArrayList<Ciudad> ciudades = new ArrayList<>();

        SQLiteDatabase db = FrutasOpenHelper.getInstance().openDatabase();

        Cursor cursor = db.query(FrutasContract.CiudadEntry.TABLE_NAME, FrutasContract.CiudadEntry.ALL_COLUMNS, null,null,null,null, FrutasContract.CiudadEntry.COLUMN_NOMBRE, null);

        if (cursor.moveToFirst()) {
            do {
                Ciudad ciudad = new Ciudad(cursor.getInt(0), cursor.getString(1));
                ciudades.add(ciudad);
            } while (cursor.moveToNext());
        }

        FrutasOpenHelper.getInstance().closeDatabase();

        return ciudades;
    }

    public static String getCiudadName(int id) {
        SQLiteDatabase db = FrutasOpenHelper.getInstance().openDatabase();

        Cursor cursor = db.query(FrutasContract.CiudadEntry.TABLE_NAME, FrutasContract.CiudadEntry.ALL_COLUMNS, FrutasContract.CiudadEntry._ID + "=" + id, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            FrutasOpenHelper.getInstance().closeDatabase();
            return cursor.getString(1);
        } else {
            FrutasOpenHelper.getInstance().closeDatabase();
            return "";
        }
    }
}
