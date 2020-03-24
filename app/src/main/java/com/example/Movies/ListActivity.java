package com.example.Movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListActivity extends AppCompatActivity {
    ArrayList<Pelicula> lpelis;
    RecyclerView RecyclerViewP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

                       lpelis = new ArrayList<>();
                       RecyclerViewP = findViewById(R.id.lstpeliculas);
                                     RecyclerViewP.setLayoutManager(new LinearLayoutManager(this));
                                       Intent I = getIntent();
                                       lpelis = I.getParcelableArrayListExtra("pelis");
        AdapterActivity a = new AdapterActivity(lpelis);
        RecyclerViewP.setAdapter(a);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        AdapterActivity a;

        switch (id) {
            case R.id.item_ordenargenero:
                Collections.sort(lpelis, new Comparator<Pelicula>() {
                    @Override
                    public int compare(Pelicula o1, Pelicula o2) {
                        if (o1.getGenero().compareTo(o2.getGenero()) < 0) {
                            return -1;
                        }
                        if (o1.getGenero().compareTo(o2.getGenero()) > 0) {
                            return 1;
                        }
                        return 0;
                    }
                });
                a = new AdapterActivity(lpelis);
                RecyclerViewP.setAdapter(a);
                break;
            case R.id.item_ordenaraz:
                Collections.sort(lpelis, new Comparator<Pelicula>() {
                    @Override
                    public int compare(Pelicula o1, Pelicula o2) {
                        if (o1.getNombre().compareTo(o2.getNombre()) < 0) {
                            return -1;
                        }
                        if (o1.getNombre().compareTo(o2.getNombre()) > 0) {
                            return 1;
                        }
                        return 0;
                    }
                });
                a = new AdapterActivity(lpelis);
                RecyclerViewP.setAdapter(a);
                break;
            case R.id.item_invertir:
                Collections.reverse(lpelis);
                a = new AdapterActivity(lpelis);
                RecyclerViewP.setAdapter(a);
                break;
            case R.id.item_eliminar:
                AlertDialog.Builder b = new AlertDialog.Builder(ListActivity.this);
                b.setMessage("¿Desea eliminar la primera pelicula?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                lpelis.remove(0);
                                AdapterActivity pa = new AdapterActivity(lpelis);
                                RecyclerViewP.setAdapter(pa);
                                Toast.makeText(getApplicationContext(), "La primera pelicula ha sio eliminada", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = b.create();
                alert.show();
                break;
            case android.R.id.home:
                Intent i = new Intent(this, MainActivity.class);
                i.putParcelableArrayListExtra("pelis", lpelis);
                setResult(Activity.RESULT_OK, i);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }













}
