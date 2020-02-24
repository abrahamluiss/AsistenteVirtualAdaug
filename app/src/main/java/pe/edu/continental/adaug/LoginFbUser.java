package pe.edu.continental.adaug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pe.edu.continental.adaug.Actividades.Registrar;

public class LoginFbUser extends AppCompatActivity {
    Intent i;
    TextView txtRegistro;
    Button btnIngresar;
    EditText txtUser, txtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fb_user);
        btnIngresar=findViewById(R.id.btnIniciaSesion);
        txtRegistro=findViewById(R.id.txtRegistro);
        txtUser=findViewById(R.id.inputUser);
        txtPass=findViewById(R.id.inputContrasena);

    }
    public void Ingresar(View v){

        switch (v.getId()){

        }
    }
    public void Registro(View v){
        i = new Intent(this, Registrar.class);
        startActivity(i);
    }
}
