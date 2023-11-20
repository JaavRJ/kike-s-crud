package com.example.crudjaviercin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Consultas extends AppCompatActivity implements View.OnClickListener {

    EditText codig, desc, cali;
    Spinner artis, album;
    RadioButton rsi, rno;
    Button bBusca, bReg;
    String guta;
    RadioGroup teguta;
    AutoCompleteTextView acvistita;
    ArrayAdapter<String> lasBuscadas, vacioAD;
    ArrayList<String> cancionesBase;

    Cursor cursor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        acvistita = findViewById(R.id.autoc);
        Base admin = new Base(this, "mispoti", null, 1);
        SQLiteDatabase basesilla = admin.getWritableDatabase();
        String query = "SELECT descripcion FROM canciones";
        cursor = basesilla.rawQuery(query, null);

        // Inicializa cancionesBase antes de agregar elementos
        cancionesBase = new ArrayList<>();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                cancionesBase.add(cursor.getString(0));
            }
            cursor.close(); // Recuerda cerrar el cursor cuando hayas terminado de usarlo
        }

        vacioAD = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<>());
        lasBuscadas = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, cancionesBase);

        acvistita.setAdapter(lasBuscadas);


        artis = findViewById(R.id.artista);
        album = findViewById(R.id.album);
        bBusca = findViewById(R.id.buscar);
        bBusca.setOnClickListener(this);
        bReg = findViewById(R.id.regresar);
        bReg.setOnClickListener(this);
        codig = findViewById(R.id.codi);
        cali = findViewById(R.id.cali);
        rsi = findViewById(R.id.si);
        rno = findViewById(R.id.no);
        teguta = findViewById(R.id.tegusto);
    }

    @Override
    public void onClick(View view) {
        String cadena = ((Button) view).getText().toString();

        if (cadena.equals("Regresar")) {
            Intent intentito = new Intent(this, MainActivity.class);
            startActivity(intentito);
        } else if (cadena.equals("Buscar")) {
            Base admin = new Base(this, "mispoti", null, 1);
            SQLiteDatabase baseisilla = admin.getWritableDatabase();
            String cod = acvistita.getText().toString();
            String[] projection = {"codigo", "descripcion", "artista", "album", "teguta", "cali"};
            String selection = "descripcion = ?";
            String[] selectionArgs = {cod};
            Cursor cursor1 = baseisilla.query("canciones", // Nombre de la tabla
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null);


            if (cursor1 != null && cursor1.moveToFirst()) {
                int resultCodi = cursor1.getInt(cursor1.getColumnIndexOrThrow("codigo"));
                String resultDesc = cursor1.getString(cursor1.getColumnIndexOrThrow("descripcion"));
                int resultCali = cursor1.getInt(cursor1.getColumnIndexOrThrow("cali"));
                String resultTeguta = cursor1.getString(cursor1.getColumnIndexOrThrow("teguta"));
                String resultArtis = cursor1.getString(cursor1.getColumnIndexOrThrow("artista"));
                String resultAlbum = cursor1.getString(cursor1.getColumnIndexOrThrow("album"));
                codig.setText(String.valueOf(resultCodi));
                cali.setText(String.valueOf(resultCali));
                ArrayList<String> resultsA = new ArrayList<>();
                resultsA.add(resultArtis);
                ArrayAdapter<String> resulA = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, resultsA);
                artis.setAdapter(resulA);
                artis.setEnabled(false);

                ArrayList<String> resultsAl = new ArrayList<>();
                resultsAl.add(resultAlbum);
                ArrayAdapter<String> resulAl = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, resultsAl);
                album.setAdapter(resulAl);
                album.setEnabled(false);

                Toast.makeText(this, "encontrado", Toast.LENGTH_SHORT).show();
                baseisilla.close();
                cursor1.close();
            } else {
                Toast.makeText(this, "no existe", Toast.LENGTH_SHORT).show();
                baseisilla.close();
            }
        }
    }
}