package com.example.agenda;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText txtnombre;
    EditText txttelefono;
    EditText txtcorreo;
    Button btnguardar;
    Button btnbuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtnombre = findViewById(R.id.txtnombre);
        txttelefono = findViewById(R.id.txttelefono);
        txtcorreo = findViewById(R.id.txtcorreo);
        btnguardar = findViewById(R.id.btnguardar);
        btnbuscar = findViewById(R.id.btnbuscar);

    }

    public void btnguardar_onClick(View v){
        String nombre= txtnombre.getText().toString();
        String telefono= txttelefono.getText().toString();
        String correo= txtcorreo.getText().toString();
        SharedPreferences preferences = getSharedPreferences( "datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nombre", nombre);
        editor.putString("telefono", telefono);
        editor.putString("correo", correo);
        editor.commit();

        Toast.makeText(this, "Datos Guardados correctamente", Toast.LENGTH_SHORT).show();
    }

    public void btnbuscar_onclick(View v) {
        // Obtener el nombre ingresado por el usuario
        String nombreBuscado = txtnombre.getText().toString();

        // Obtener los datos guardados en SharedPreferences
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String nombreGuardado = preferences.getString("nombre", "");
        String telefonoGuardado = preferences.getString("telefono", "");
        String correoGuardado = preferences.getString("correo", "");

        // Verificar si el nombre guardado coincide con el nombre buscado
        if (nombreGuardado.equals(nombreBuscado)) {
            // Mostrar los datos guardados en los EditText correspondientes
            txtnombre.setText(nombreGuardado);
            txttelefono.setText(telefonoGuardado);
            txtcorreo.setText(correoGuardado);

            Toast.makeText(this, "Datos encontrados", Toast.LENGTH_SHORT).show();
        } else {
            // Mostrar un mensaje si no se encontraron datos o el nombre no coincide
            Toast.makeText(this, "No se encontraron datos para el nombre ingresado", Toast.LENGTH_SHORT).show();
        }
    }

}


