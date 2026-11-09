package com.example.tsdmh_7s5_p2_proyecto_adriel;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ControlFragment extends Fragment {
    private Spinner spinnerCategorias;
    private Button btnSolicitar;
    private ProgressBar progressBar;
    private TextView tvEstadoCarga;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_control, container, false);

        spinnerCategorias = view.findViewById(R.id.spinnerCategorias);
        btnSolicitar = view.findViewById(R.id.btnSolicitar);
        progressBar = view.findViewById(R.id.progressBar);
        tvEstadoCarga = view.findViewById(R.id.tvEstadoCarga);


        String[] categorias = {"Productos de Oficina", "Base de Datos", "Red", "Sincronización", "Reportes"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, categorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorias.setAdapter(adapter);


        progressBar.setVisibility(View.VISIBLE);
        tvEstadoCarga.setVisibility(View.VISIBLE);
        tvEstadoCarga.setText("Listo para solicitar datos...");
        progressBar.setProgress(0);

        btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoriaSeleccionada = spinnerCategorias.getSelectedItem().toString();


                tvEstadoCarga.setText("Cargando datos...");
                btnSolicitar.setEnabled(false);
                progressBar.setProgress(0);

                final Handler handler = new Handler(Looper.getMainLooper());
                new Thread(new Runnable() {
                    int progreso = 0;
                    @Override
                    public void run() {

                        while (progreso < 100) {
                            try {
                                Thread.sleep(25);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progreso += 5;

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (!isAdded()) return;
                                    progressBar.setProgress(progreso);
                                }
                            });
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (!isAdded()) return;
                                transicionarAResultado(categoriaSeleccionada);
                            }
                        });
                    }
                }).start();
            }
        });

        return view;
    }

    private void transicionarAResultado(String categoria) {
        if (getActivity() == null) return;

        ResultadoActivity resultFragment = new ResultadoActivity();
        Bundle args = new Bundle();
        args.putString("CATEGORIA_SELECCIONADA", categoria);
        resultFragment.setArguments(args);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, resultFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}