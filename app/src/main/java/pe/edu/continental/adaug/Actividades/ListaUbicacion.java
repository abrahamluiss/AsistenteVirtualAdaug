package pe.edu.continental.adaug.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import pe.edu.continental.adaug.Entidades.DatabaseHelper;
import pe.edu.continental.adaug.R;

public class ListaUbicacion extends AppCompatActivity {

    DatabaseHelper miBD;
    ListView listaUbicacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ubicacion);
        listaUbicacion = findViewById(R.id.listaUbicacion);
        miBD = new DatabaseHelper(this);

        ArrayList<String> listaUbic = new ArrayList<>();
        Cursor data = miBD.getListaUbic();
        if(data.getCount()==0){
            Toast.makeText(this, "No hay lista q mostrar", Toast.LENGTH_SHORT).show();
        }else {
            while (data.moveToNext()){
                listaUbic.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(
                        this, android.R.layout.simple_list_item_1,listaUbic); //asigna datos
                listaUbicacion.setAdapter(listAdapter);


            }
        }
    }
}
