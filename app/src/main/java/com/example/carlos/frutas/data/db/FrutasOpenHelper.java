package com.example.carlos.frutas.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.carlos.frutas.ui.principal.FrutasApplication;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by carlos on 19/02/18.
 */

public class FrutasOpenHelper extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;
    private volatile static FrutasOpenHelper singleton;
    private AtomicInteger opens = new AtomicInteger();

    static {
        singleton = new FrutasOpenHelper();
    }

    private FrutasOpenHelper() {
        super(FrutasApplication.getContext(), FrutasContract.DATABASE_NAME, null, FrutasContract.DATABASE_VERSION);
    }

    public synchronized static FrutasOpenHelper getInstance() {
        return singleton;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.execSQL(FrutasContract.CiudadEntry.CREATE_ENTRIES);
            sqLiteDatabase.execSQL(FrutasContract.FrutaEntry.CREATE_ENTRIES);
            sqLiteDatabase.execSQL(FrutasContract.CiudadEntry.INSERT_ENTRIES);
            sqLiteDatabase.execSQL(FrutasContract.FrutaEntry.INSERT_ENTRIES);

            sqLiteDatabase.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.execSQL(FrutasContract.FrutaEntry.DELETE_ENTRIES);
            sqLiteDatabase.execSQL(FrutasContract.CiudadEntry.DELETE_ENTRIES);
            onCreate(sqLiteDatabase);
            Log.d("HOLA", "BORRADO");
            sqLiteDatabase.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=1");
            }
        }
    }

    public synchronized SQLiteDatabase openDatabase() {
        if (opens.incrementAndGet() == 1) {
            sqLiteDatabase = getWritableDatabase();
        }
        return sqLiteDatabase;
    }

    public synchronized void closeDatabase() {
        if (opens.decrementAndGet() == 0) {
            sqLiteDatabase.close();
        }
    }
}
