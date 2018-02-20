package com.example.carlos.frutas.ui.AnadirEditar;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carlos.frutas.data.model.Fruta;

/**
 * Created by usuario on 20/02/18.
 */

public class AnadirEditarFragment extends Fragment {

    private OnAnadirEditarListener listener;
    private static int mode;
    public static final int MODE_EDIT = 0;
    public static final int MODE_ADD = 1;
    public static final String ARGS_FRUTA = "fruta";
    private Fruta fruta;

    public static AnadirEditarFragment newInstance(Bundle args) {
        AnadirEditarFragment fragment = new AnadirEditarFragment();
        if (args != null) {
            fragment.setArguments(args);
            mode = MODE_EDIT;
        } else {
            mode = MODE_ADD;
        }
        return fragment;
    }

    public interface OnAnadirEditarListener {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAnadirEditarListener) {
            listener = (OnAnadirEditarListener) context;
        } else {
            throw new RuntimeException(context.toString() + " debe implementar OnAnadirEditarListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
