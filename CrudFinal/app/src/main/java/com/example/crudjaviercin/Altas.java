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

    String[] brattyCanciones = {
            "Intro",
            "Cruel",
            "Quiero Estar",
            "Honey, No Estás",
            "Decir Adiós",
            "Quédate",
            "Delusión",
            "Quédate (Acústico - Bonus Track)",
            "epílogo",
            "tdbn",
            "tarde",
            "tuviste",
            "tú",
            "jules",
            "contigo",
            "chocolate y nata",
            "lejos",
            "tusa",
            "Un Nuevo Disco",
            "Radio",
            "Ya No Es Lo Mismo",
            "La Última Vez",
            "Agosto",
            "Nada Que Decir",
            "Estos Días",
            "Así TQ Recordar",
            "Que Yo A Tí",
            "Esta Ciudad (con Yawners)",
            "¿Qué Será de Mí? (con Ivana)",
            "Nunca Supe…"
    };
    String[] nbhdCanciones = {
            "How",
            "Afraid",
            "Everybody’s Watching Me (Uh Oh)",
            "Sweater Weather",
            "Let It Go",
            "Alleyways",
            "W.D.Y.W.F.M.?",
            "Flawless",
            "Female Robbery",
            "Staying Up",
            "Float",
            "A Moment of Silence",
            "Prey",
            "Cry Baby",
            "Wiped Out!",
            "The Beach",
            "Daddy Issues",
            "Baby Came Home 2 / Valentines",
            "Greetings from Califournia",
            "Ferrari",
            "Single",
            "R.I.P. 2 My Youth"
    };

    String[] cancionesArcticMonkeys = {
            "Brianstorm",
            "Teddy Picker",
            "D is for Dangerous",
            "Balaclava",
            "Fluorescent Adolescent",
            "Only Ones Who Know",
            "Do Me a Favour",
            "This House is a Circus",
            "If You Were There, Beware",
            "The Bad Thing",
            "Old Yellow Bricks",
            "505",
            "Do I Wanna Know?",
            "R U Mine?",
            "One for the Road",
            "Arabella",
            "I Want It All",
            "No. 1 Party Anthem",
            "Mad Sounds",
            "Fireside",
            "Why’d You Only Call Me When You’re High?",
            "Snap Out of It",
            "Knee Socks",
            "I Wanna Be Yours"
    };

    String[] lanaCanciones = {
            "The Grants", "Did you know that there’s a tunnel under Ocean Blvd", "Sweet", "AW",
            "Judah Smith Interlude", "Candy Necklace", "Jon Batiste Interlude", "Born to Die",
            "Off to the Races", "Blue Jeans", "Video Games", "Diet Mountain Dew", "National Anthem",
            "Dark Paradise", "Radio", "Carmen", "Million Dollar Man", "Summertime Sadness",
            "This Is What Makes Us Girls", "Cruel World", "Ultraviolence", "Shades of Cool",
            "Brooklyn Baby", "West Coast", "Sad Girl", "Pretty When You Cry", "Money Power Glory",
            "Fucked My Way Up to the Top", "Old Money", "The Other Woman"
    };
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
            acvistita.setThreshold(1);
            acvistita.setAdapter(topCancionesAD);
        } else if (posicion == 2) {
            albums.add("Albums de Bratty: ");
            albums.add("Delusion");
            albums.add("tdb");
            albums.add("TRES");
            ArrayAdapter<String>brattyCancionesAD = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, brattyCanciones);
            acvistita.setThreshold(1);
            acvistita.setAdapter(brattyCancionesAD);
        } else if (posicion == 3) {
            albums.add("Albums de the nbhd");
            albums.add("I Love You");
            albums.add("Wiped Out!");
            albums.add("Hard to imagine the Neighbourhood Ever Changing");
            albums.add("Chip Chrome & The Mono-Tones");
            ArrayAdapter<String>nbhhdCancionesAD = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, nbhdCanciones);
            acvistita.setThreshold(1);
            acvistita.setAdapter(nbhhdCancionesAD);
        } else if (posicion == 4) {
            albums.add("Albums de Arctic Monkeys: ");
            albums.add("Whatever People Say I Am, That's What I'm Not");
            albums.add("Favourite Rorst Nightmare");
            albums.add("Humbug");
            albums.add("Suck it and See");
            albums.add("AM");
            ArrayAdapter<String>amCancionesAD = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, cancionesArcticMonkeys);
            acvistita.setThreshold(1);
            acvistita.setAdapter(amCancionesAD);
        } else if (posicion == 5) {
            albums.add("Albuns de Lana del Rey");
            albums.add("Born to die");
            albums.add("Ultraviolence");
            albums.add("Lust for Life");
            albums.add("Berman Fucking Racket!");
            albums.add("Norman Fucking Rockwell!");
            albums.add("Lux Banisters");
            albums.add("Dis You Know That There's a Tunnel, Under Ocean Svar");
            ArrayAdapter<String>lanaCancionesAD = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, lanaCanciones);
            acvistita.setThreshold(1);
            acvistita.setAdapter(lanaCancionesAD);

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
        codig.setText("");
        cali.setText("");
}

}


