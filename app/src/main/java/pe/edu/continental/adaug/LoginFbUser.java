package pe.edu.continental.adaug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pe.edu.continental.adaug.Actividades.Registrar;
import pe.edu.continental.adaug.Entidades.Usuario;

public class LoginFbUser extends AppCompatActivity {
    Intent i;
    TextView txtRegistro;
    Button btnIngresar;
    EditText txtUser, txtPass;
    daoUsuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fb_user);
        btnIngresar=findViewById(R.id.btnIniciaSesion);
        txtRegistro=findViewById(R.id.txtRegistro);
        txtUser=findViewById(R.id.inputUser);
        txtPass=findViewById(R.id.inputContrasena);
        dao = new daoUsuario(this);

    }
    public void Ingresar(View v){

        String u = txtUser.getText().toString();
        String p = txtPass.getText().toString();
        if(u.equals("")&&p.equals("")){
            Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show();
        }else if(dao.login(u,p)==1){
            Usuario ux=dao.getUsuario(u,p); //guardar dato
            Toast.makeText(this, "Datos correctos", Toast.LENGTH_SHORT).show();
           i= new Intent (this, VozATexto.class);
           i.putExtra("Id", ux.getId());
            startActivity(i);
            finish();
        }else {
            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
        }


    }
    public void Registro(View v){
        i = new Intent(this, Registrar.class);
        startActivity(i);
    }
}
