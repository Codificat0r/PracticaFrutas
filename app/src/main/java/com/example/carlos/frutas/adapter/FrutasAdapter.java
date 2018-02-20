package com.example.carlos.frutas.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.carlos.frutas.R;
import com.example.carlos.frutas.data.model.Fruta;

import java.util.ArrayList;

/**
 * Created by carlos on 19/02/18.
 */

public class FrutasAdapter extends ArrayAdapter<Fruta>{
    public FrutasAdapter(@NonNull Context context) {
        super(context, R.layout.item_fruta, new ArrayList<Fruta>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FrutaHolder frutaHolder;
        LayoutInflater inflater;

        View view = convertView;

        if (view == null) {
            inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            frutaHolder = new FrutaHolder();

            view = inflater.inflate(R.layout.item_fruta, null);

            frutaHolder.txvNombre = view.findViewById(R.id.txvNombre);
            frutaHolder.txvCiudad = view.findViewById(R.id.txvCiudad);

            view.setTag(frutaHolder);
        } else {
            frutaHolder = (FrutaHolder) view.getTag();
        }

        Fruta actual = getItem(position);

        frutaHolder.txvNombre.setText(actual.getNombre());
        frutaHolder.txvCiudad.setText(actual.getCiudad());

        return view;
    }

    private class FrutaHolder {
        private TextView txvNombre;
        private TextView txvCiudad;
    }
}
