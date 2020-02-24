package pe.edu.continental.adaug.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pe.edu.continental.adaug.Entidades.Usuario;
import pe.edu.continental.adaug.R;
import pe.edu.continental.adaug.daoUsuario;

public class Home extends AppCompatActivity {
    TextView NombreAp;
    daoUsuario dao;
    Usuario u;
    int id=0;
    Button btnVoz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        NombreAp = findViewById(R.id.NombreAp);
        btnVoz = findViewById(R.id.btnVoz);

        Bundle b = getIntent().getExtras();
        id=b.getInt("IdNA");
        dao = new daoUsuario(this);
        u=dao.getUsuarioById(id);

        NombreAp.setText(u.getNombre()+" "+u.getApellidos());


    }
    public void IrVoz(View v){
        Intent i = new Intent(this, VozATexto.class);
        startActivity(i);
    }
}
