package com.example.carlos.frutas.data.db;

import android.provider.BaseColumns;

/**
 * Created by carlos on 19/02/18.
 */

public class FrutasContract {

    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "frutas";

    private FrutasContract() {}

    public static class FrutaEntry implements BaseColumns {
        public static final String TABLE_NAME = "fruta";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_PESO = "peso";
        public static final String COLUMN_CIUDAD = "ciudad";
        public static final String[] ALL_COLUMNS = {
                BaseColumns._ID, COLUMN_NOMBRE, COLUMN_PESO, COLUMN_CIUDAD
        };

        public static final String CREATE_ENTRIES = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT NOT NULL," +
                "%s INTEGER NOT NULL," +
                "%s INTEGER NOT NULL," +
                "FOREIGN KEY (%s) REFERENCES %s(%s) ON UPDATE CASCADE ON DELETE RESTRICT)",
                TABLE_NAME,
                BaseColumns._ID,
                COLUMN_NOMBRE,
                COLUMN_PESO,
                COLUMN_CIUDAD,
                COLUMN_CIUDAD,
                CiudadEntry._ID,
                CiudadEntry.TABLE_NAME);

        public static final String DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);

        public static final String INSERT_ENTRIES = String.format("INSERT INTO %s (%s,%s,%s) VALUES ('%s',%s,%s),('%s',%s,%s),('%s',%s,%s)",
                TABLE_NAME,
                COLUMN_NOMBRE,
                COLUMN_PESO,
                COLUMN_CIUDAD,
                "Pera",1,1,
                "Manzana",2,1,
                "Calabaza",10,2);
    }

    public static class CiudadEntry implements BaseColumns {
        public static final String TABLE_NAME = "ciudad";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String[] ALL_COLUMNS = {
                BaseColumns._ID, COLUMN_NOMBRE
        };

        public static final String CREATE_ENTRIES = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT NOT NULL)",
                TABLE_NAME,
                BaseColumns._ID,
                COLUMN_NOMBRE);

        public static final String DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);

        public static final String INSERT_ENTRIES = String.format("INSERT INTO %s (%s) VALUES ('%s'),('%s'),('%s')",
                TABLE_NAME,
                COLUMN_NOMBRE,
                "Málaga","Huelva","Sevilla");
    }
}
