package com.example.carlos.frutas.ui.AnadirEditar.vista;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.carlos.frutas.R;
import com.example.carlos.frutas.data.model.Ciudad;
import com.example.carlos.frutas.data.model.Fruta;
import com.example.carlos.frutas.ui.AnadirEditar.contrato.AnadirEditarContract;
import com.example.carlos.frutas.ui.AnadirEditar.presenter.AnadirEditarPresenter;

import java.util.ArrayList;

/**
 * Created by usuario on 20/02/18.
 */

public class AnadirEditarFragment extends Fragment implements AnadirEditarContract.Vista {

    private EditText edtNombre;
    private EditText edtPeso;
    private Spinner spnrCiudades;
    private FloatingActionButton fab;

    private OnAnadirEditarListener listener;
    private static int mode;
    public static final int MODE_EDIT = 0;
    public static final int MODE_ADD = 1;
    public static final String ARGS_FRUTA = "fruta";
    private Fruta fruta;
    private ArrayList<Ciudad> ciudades;
    private ArrayAdapter<Ciudad> adapter;
    private int selectedCiudad;

    private AnadirEditarContract.Presenter presenter;

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

        void finalizado();
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
        View rootView = inflater.inflate(R.layout.fragment_anadir_editar, null);

        presenter = new AnadirEditarPresenter(this);

        edtNombre = rootView.findViewById(R.id.edtNombre);
        edtPeso = rootView.findViewById(R.id.edtPeso);
        spnrCiudades = rootView.findViewById(R.id.spnrCiudades);
        fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mode == MODE_EDIT) {
                    try {
                        presenter.pedirActualizarFruta(new Fruta(fruta.getId(), edtNombre.getText().toString(), Integer.parseInt(edtPeso.getText().toString()), ((Ciudad) spnrCiudades.getSelectedItem()).getNombre()), ciudades.get(spnrCiudades.getSelectedItemPosition()).getId());
                    } catch (Exception e) {
                        mostrarErrorGenerico();
                    }
                } else {
                    try {
                        presenter.pedirAnadirFruta(new Fruta(0, edtNombre.getText().toString(), Integer.parseInt(edtPeso.getText().toString()), ((Ciudad) spnrCiudades.getSelectedItem()).getNombre()), ciudades.get(spnrCiudades.getSelectedItemPosition()).getId());
                    } catch (Exception e) {
                        mostrarErrorGenerico();
                    }
                }
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        presenter.obtenerCiudades();

        if (mode == MODE_EDIT) {
            fruta = (Fruta) getArguments().getSerializable(ARGS_FRUTA);
            edtPeso.setText(fruta.getPeso() + "");
            edtNombre.setText(fruta.getNombre());

            for (int i = 0; i < ciudades.size(); i++) {
                Ciudad tmp = ciudades.get(i);
                if (tmp.getNombre().equals(fruta.getCiudad())) {
                    selectedCiudad = i;
                }
            }

            spnrCiudades.setSelection(selectedCiudad);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSuccessCiudades(ArrayList<Ciudad> ciudades) {
        this.ciudades = ciudades;
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, ciudades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrCiudades.setAdapter(adapter);
    }

    @Override
    public void mostrarErrorPeso() {
        Toast.makeText(getContext(), "El peso no puede estar vacío", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarErrorNombre() {
        Toast.makeText(getContext(), "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
    }

    public void mostrarErrorGenerico() {
        Toast.makeText(getContext(), "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessAnadirEditar() {
        listener.finalizado();
    }
}
