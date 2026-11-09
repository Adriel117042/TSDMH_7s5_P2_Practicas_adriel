package com.example.tsdmh_7s5_p2_proyecto_adriel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultadoActivity extends Fragment {
    private TextView tvResultadoFinal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_resultado, container, false);
        tvResultadoFinal = view.findViewById(R.id.tvResultadoFinal);

        if (getArguments() != null) {
            String categoria = getArguments().getString("CATEGORIA_SELECCIONADA");
            String detalles = "";

            switch (categoria != null ? categoria : "") {
                case "Poductos de Oficina":
                    detalles = "• Bolígrafo Azul - $1.50\n" +
                            "• Cuaderno A4 - $4.00\n" +
                            "• Marcadores Color - $12.00\n" +
                            "• Cuaderno A2 - $4.00";
                    break;
                case "Base de Datos":
                    detalles = "• Servidor SQL - $45.00\n" +
                            "• Licencia Oracle - $120.00\n" +
                            "• Respaldo Cloud 1TB - $15.00\n" +
                            "• Soporte DBA - $50.00";
                    break;
                case "Red":
                    detalles = "• Cable UTP Cat6 (10m) - $10.00\n" +
                            "• Conectores RJ45 (Pack 10) - $3.50\n" +
                            "• Switch Administrable - $85.00\n" +
                            "• Router Wi-Fi 6 - $65.00";
                    break;
                case "Sincronización":
                    detalles = "• API Gateway Sync - $25.00\n" +
                            "• Certificado SSL Wildcard - $30.00\n" +
                            "• Webhook Handler - $10.00\n" +
                            "• Storage Sync Node - $20.00";
                    break;
                case "Reportes":
                    detalles = "• Generador PDF Pro - $18.00\n" +
                            "• Exportador Excel/CSV - $12.00\n" +
                            "• Dashboard Analítico - $40.00\n" +
                            "• Plantilla Ejecutiva - $8.00";
                    break;
                default:
                    detalles = "• Elemento genérico 1 - $5.00\n" +
                            "• Elemento genérico 2 - $10.00";
                    break;
            }

            tvResultadoFinal.setText("Categoría seleccionada:\n-> " + categoria + "\n\n" + detalles);
        }

        return view;
    }
}