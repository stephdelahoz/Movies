package com.example.Movies;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ArrayList<Pelicula> pelis = new ArrayList<>();
    TextInputEditText tinombre, tidirector;
    String generop;
    RadioGroup rg;
    Spinner spgeneros;
    Button btnguardar, btncancelar;
    RadioButton rbespañol, rbingles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

                                  rg = findViewById(R.id.radioGroup);
                                  tidirector = findViewById(R.id.edtdirector);
                                  spgeneros = findViewById(R.id.sgenero);
                                  tinombre = findViewById(R.id.edtnombre);
                                  rbespañol = findViewById(R.id.respañol);
                                  btnguardar = findViewById(R.id.btnguardar);
                                  rbingles = findViewById(R.id.ringles);
                                  btncancelar = findViewById(R.id.btncancelar);



        ArrayAdapter<CharSequence> ar = ArrayAdapter.createFromResource(this
                , R.array.sgeneros, R.layout.support_simple_spinner_dropdown_item);
        spgeneros.setAdapter(ar);
        spgeneros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                generop = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnguardar.setOnClickListener(this);
        btncancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnguardar:

                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage("¿Quieres guardar esta pelicula?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String idioma;
                        switch (rg.getCheckedRadioButtonId()) {
                            case R.id.respañol:
                                idioma = getString(R.string.txt_español);
                                break;
                            case R.id.ringles:
                                idioma = getString(R.string.txt_ingles);
                                break;
                            default:
                                idioma = getString(R.string.txt_español);
                                break;
                        }
                        Pelicula pelicula = new Pelicula(tinombre.getText().toString(), tidirector.getText().toString(), idioma, generop);
                        pelis.add(pelicula);
                        tinombre.setText("");
                        tidirector.setText("");
                        Toast.makeText(getApplicationContext(), "La pelicula ha sido guardada", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = b.create();
                alert.show();



                break;
            case R.id.btncancelar:
                tinombre.setText("");
                tidirector.setText("");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {

            case R.id.item_listado:
                Intent i = new Intent(this, ListActivity.class);
                i.putParcelableArrayListExtra("pelis", pelis);
                startActivityForResult(i, 5);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 5) {
            pelis = data.getParcelableArrayListExtra("pelis");

        }

    }
}
