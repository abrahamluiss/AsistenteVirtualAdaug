package pe.edu.continental.adaug

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        var animation= AnimationUtils.loadAnimation(this,R.anim.animation)
        splashActivityIvLogo.startAnimation(animation)
        txtBienvenido.startAnimation(animation)
        var handler= Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        },3000)
        finish()
    }
}
