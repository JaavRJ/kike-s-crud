package com.example.crudjaviercin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.crudjaviercin.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button baltas, bbajas, bcambios, bconsultas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baltas = findViewById(R.id.altas);
        bcambios = findViewById(R.id.cambios);
        bbajas = findViewById(R.id.bajas);
        bconsultas = findViewById(R.id.consul);

        baltas.setOnClickListener(this);
        bbajas.setOnClickListener(this);
        bcambios.setOnClickListener(this);
        bconsultas.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        String cadenita = ((Button) view).getText().toString();

        if (cadenita.equals("Altas")) {
            Intent intent = new Intent(this, Altas.class);
            startActivity(intent);
        }
    }
}
