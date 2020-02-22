package pe.edu.continental.adaug

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnIniciaSesion.setOnClickListener{
            val i=Intent(this@LoginActivity, VozATexto::class.java)
            startActivity(i)
        }
    }
}
