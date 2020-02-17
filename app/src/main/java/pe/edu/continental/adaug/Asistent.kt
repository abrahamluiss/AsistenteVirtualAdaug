package pe.edu.continental.adaug

import ai.api.AIConfiguration
import ai.api.AIDataService
import ai.api.AIListener
import ai.api.android.AIService
import ai.api.model.AIError
import ai.api.model.AIResponse
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_asistent.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class Asistent :AppCompatActivity(), AIListener, TextToSpeech.OnInitListener {
    override fun onInit(status: Int) {//cuando intenta comunicarse con leer

    }

    override fun onResult(result: AIResponse?) {//recibe las rpts
        val rpta = result?.result//los intentos
        val escuchando = rpta?.resolvedQuery
        val responder = rpta?.fulfillment?.speech //segun lo q respondio

        reemplazarTextos(escuchando, responder)
    }

    fun reemplazarTextos(escuchando: String?, respuesta: String?) {
        tv_escuchando.text = escuchando
        tv_respuesta.text = respuesta
        hablar(respuesta)
    }

    fun hablar(respuesta: String?) {

        leer?.speak(respuesta, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onListeningStarted() {

    }

    override fun onAudioLevel(level: Float) {

    }

    override fun onError(error: AIError?) {

        val error = "Ups paso algo inesperado"
        reemplazarTextos(error, error)
    }

    override fun onListeningCanceled() {

    }

    override fun onListeningFinished() {

    }

    val accessToken = "ff2321fa1f1640138ad6d0770050b8e7"
    val REQUEST = 200
    var leer: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asistent)
        leer = TextToSpeech(this, this)
        validar()
        configAsistente()
    }

    fun configAsistente() {
        val configuracion = ai.api.android.AIConfiguration(
            accessToken,
            AIConfiguration.SupportedLanguages.Spanish,
            ai.api.android.AIConfiguration.RecognitionEngine.System
        )
        val service = AIService.getService(this, configuracion)
        service.setListener(this)
        micButton.setOnClickListener { service.startListening() }
    }

    fun validar() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            solicitarPermiso()
        }
    }

    fun solicitarPermiso() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.RECORD_AUDIO),
                REQUEST
            )
        }
    }
}
