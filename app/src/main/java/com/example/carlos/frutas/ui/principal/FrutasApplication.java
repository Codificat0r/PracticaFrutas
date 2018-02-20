package com.example.carlos.frutas.ui.principal;

import android.app.Application;
import android.content.Context;

import com.example.carlos.frutas.data.db.FrutasOpenHelper;

/**
 * Created by carlos on 19/02/18.
 */

public class FrutasApplication extends Application {
    private static Context context;

    public FrutasApplication() {
        this.context = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FrutasOpenHelper.getInstance().openDatabase();
    }

    public static Context getContext() {
        return context;
    }
}
