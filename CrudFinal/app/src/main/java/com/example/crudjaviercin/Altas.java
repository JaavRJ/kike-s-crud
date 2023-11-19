package com.example.crudjaviercin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.crudjaviercin.R;

import java.util.ArrayList;

public class Altas extends AppCompatActivity implements View.OnClickListener {

    EditText codig, desc, cali;
    Spinner artis, album;
    RadioButton rsi, rno;
    Button bAlta, bReg;
    String guta;
    RadioGroup teguta;
    AutoCompleteTextView acvistita;
    ArrayAdapter<String> topCancionesAD, vacioAD;
    String[] topCanciones = {"Addict with a Pen",
            "Air Catcher",
            "A Car, a Torch, a Death",
            "Anathema",
            "Bandito",
            "Be Concerned",
            "Car Radio",
            "Chlorine",
            "Clear",
            "Cut My Lip",
            "Doubt",
            "Fake You Out",
            "Fall Away",
            "Forest",
            "Goner",
            "Heathens",
            "Holding On to You",
            "Hometown",
            "Jumpsuit",
            "Lane Boy",
            "Levitate",
            "Lovely",
            "March to the Sea",
            "Message Man",
            "My Blood",
            "Neon Gravestones",
            "Nico and the Niners",
            "No Chances",
            "Ode to Sleep",
            "Oh Ms Believer",
            "Redecorate",
            "Ride",
            "Run and Go",
            "Shy Away",
            "Smithereens",
            "Stressed Out",
            "The Hype",
            "The Judge",
            "The Pantaloon",
            "The Run and Go",
            "Tear in My Heart",
            "Trees",
            "Truce"};
    String horariosele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);

        vacioAD = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<>());
        topCancionesAD = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, topCanciones);


        acvistita = findViewById(R.id.autoc);
        acvistita.setAdapter(vacioAD);

        artis = findViewById(R.id.artista);
        album = findViewById(R.id.album);
        bAlta = findViewById(R.id.alta);
        bAlta.setOnClickListener(this);
        bReg = findViewById(R.id.regresar);
        bReg.setOnClickListener(this);
        codig = findViewById(R.id.codi);
        cali = findViewById(R.id.cali);
        rsi = findViewById(R.id.si);
        rno = findViewById(R.id.no);
        teguta = findViewById(R.id.tegusto);

        ArrayList<String> artistas = new ArrayList<>();
        artistas.add("Artistas:");
        artistas.add("Twenty One Pilots");
        artistas.add("Bratty");
        artistas.add("the nbhd:");
        artistas.add("Arctic Monkeys");
        artistas.add("Lana del Rey");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, artistas);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        artis.setAdapter(adapter);

        album.setVisibility(View.INVISIBLE);
        configListener();

        teguta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton boton = findViewById(checkedId);

                if (boton != null) {
                    guta = boton.getText().toString();
                }
            }
        });


        album.setVisibility(View.INVISIBLE);

        configListener();
    }

    private void configListener() {
        artis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    album.setVisibility(View.INVISIBLE);
                } else {
                    Act2doSpinner(position);
                    album.setVisibility(View.VISIBLE);
                    acvistita.setEnabled(true);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }


    private void Act2doSpinner(int posicion) {
        ArrayList<String> albums = new ArrayList<>();
        if (posicion == 1) {
            albums.add("Albues de TOP: ");
            albums.add("Self Titled");
            albums.add("Vessel");
            albums.add("Blurryface");
            albums.add("Trench");
            albums.add("Scaled and Icy");
            acvistita.setThreshold(2);
            acvistita.setAdapter(topCancionesAD);
        } else if (posicion == 2) {
            albums.add("Albums de Bratty: ");
            albums.add("Delusion");
            albums.add("tab");
            albums.add("TRES");
        } else if (posicion == 3) {
            albums.add("Albums de the nbhd");
            albums.add("I Love You");
            albums.add("Wiped Out!");
            albums.add("Hard to imagine the Neighbourhood Ever Changing");
            albums.add("Chip Chrome & The Mono-Tones");
        } else if (posicion == 4) {
            albums.add("Albums de Arctic Monkeys: ");
            albums.add("Whatever People Say I Am, That's What I'm Not");
            albums.add("Favourite Rorst Nightmare");
            albums.add("Humbug");
            albums.add("Suck it and See");
            albums.add("AM");
        } else if (posicion == 5) {
            albums.add("Albuns de Lana del Rey");
            albums.add("Burn to dia");
            albums.add("Ultraviolence");
            albums.add("Lust for Life");
            albums.add("Berman Fucking Racket!");
            albums.add("Norman Fucking Rockwell!");
            albums.add("Lux Banisters");
            albums.add("Dis You Know That There's a Tunnel, Under Ocean Svar");


        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, albums);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        album.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        String cadenita = ((Button) v).getText().toString();
        if (cadenita.equals("Regresar")) {
            Intent intentito = new Intent(this, MainActivity.class);
            startActivity(intentito);
        } else if (cadenita.equals("Alta")) {
            Base admin = new Base(this, "mispoti", null, 1);
            SQLiteDatabase basededatos = admin.getWritableDatabase();
            String cod = codig.getText().toString();
            String des = acvistita.getText().toString();
            String arti = artis.getSelectedItem().toString();
            String alb = album.getSelectedItem().toString();
            String gu = guta;
            String cal = cali.getText().toString();


            ContentValues registro = new ContentValues();
            registro.put("codigo", cod);
            registro.put("descripcion", des);
            registro.put("artista", arti);
            registro.put("album", alb);
            registro.put("teguta", gu);
            registro.put("cali", cal);
            basededatos.insert("canciones", null, registro);
            basededatos.close();
            Toast.makeText(this, "agregado", Toast.LENGTH_SHORT).show();
            limpiaAlta();
        }
    }

public void limpiaAlta(){
        acvistita.setEnabled(false);
        album.setVisibility(View.INVISIBLE);
        album.setAdapter(null);
}

}


