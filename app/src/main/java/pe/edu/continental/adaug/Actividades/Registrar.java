package pe.edu.continental.adaug.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pe.edu.continental.adaug.Entidades.Usuario;
import pe.edu.continental.adaug.R;
import pe.edu.continental.adaug.daoUsuario;

public class Registrar extends AppCompatActivity implements View.OnClickListener {

    Intent i;
    EditText us, pass, nom, ap;
    Button registrar;
    daoUsuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        us=findViewById(R.id.ingresaCorreo);
        pass=findViewById(R.id.inputContrasena);
        nom=findViewById(R.id.inputNombre);
        ap=findViewById(R.id.inputApellido);
        registrar = findViewById(R.id.btnRegistro);
        registrar.setOnClickListener(this);
        dao = new daoUsuario(this);
    }

    @Override
    public void onClick(View v) {
        Usuario u = new Usuario();
        u.setUsuario(us.getText().toString());
        u.setPass(pass.getText().toString());
        u.setNombre(nom.getText().toString());
        u.setApellidos(ap.getText().toString());
        if(!u.isNull()){
            Toast.makeText(this, "Error campos vacios", Toast.LENGTH_SHORT).show();
        }else if(dao.insertUsuario(u)){
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            i = new Intent(this, LoginFbUser.class);
            startActivity(i);
        }else {
            Toast.makeText(this, "Usuario ya registrado!!", Toast.LENGTH_SHORT).show();
        }
    }

}
