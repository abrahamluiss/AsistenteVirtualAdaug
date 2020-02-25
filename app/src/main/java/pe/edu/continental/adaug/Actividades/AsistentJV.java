package pe.edu.continental.adaug.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.ArrayList;

import pe.edu.continental.adaug.R;
import pe.edu.continental.adaug.Clases.Respuestas;

public class AsistentJV extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private static final int RECONOCEDOR_VOZ = 7;
    private TextView escuchando;
    private TextView respuesta;
    private ArrayList<Respuestas> respuest;
    private TextToSpeech leer;
    String miUbicacion, distancia ;
    Intent llamar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistent_jv);

        if(ActivityCompat.checkSelfPermission(
                AsistentJV.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(
                AsistentJV.this,Manifest
                        .permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AsistentJV.this,new String[]
                    { Manifest.permission.SEND_SMS,},1000);
        }else{}


            miUbicacion=getIntent().getStringExtra("direccione");
        distancia=getIntent().getStringExtra("distancia");

        inicializar();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == RECONOCEDOR_VOZ){
            ArrayList<String> reconocido = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String escuchado = reconocido.get(0);
            escuchando.setText(escuchado);
            prepararRespuesta(escuchado);
        }
    }

    private void prepararRespuesta(String escuchado) {
        String normalizar = Normalizer.normalize(escuchado, Normalizer.Form.NFD);
        String sintilde = normalizar.replaceAll("[^\\p{ASCII}]", "");

        int resultado;
        String respuesta = respuest.get(0).getRespuestas();
        for (int i = 0; i < respuest.size(); i++) {
            resultado = sintilde.toLowerCase().indexOf(respuest.get(i).getCuestion());
            if(resultado != -1){
                respuesta = respuest.get(i).getRespuestas();
                if (!actividades(respuest.get(i).getCuestion()).equals("")){
                    respuesta += actividades(respuest.get(i).getCuestion());
                }

            }
        }
        responder(respuesta);
    }

    private String actividades(String cuestion) {
        String rptas = "";
        if(cuestion.equals("llamar")){

            String numero="955446977";
            String mensaje="Mensaje de prueba";
            EnviarMensaje(numero,mensaje);
            Toast.makeText(this, "Enviado a: "+numero+mensaje, Toast.LENGTH_SHORT).show();


            //Uri number = Uri.parse("tel:5551234");
            //llamar = new Intent(Intent.ACTION_DIAL, number);
            //startActivity(llamar);
        }

        return rptas;
    }

    private void responder(String respuestita) {
        respuesta.setText(respuestita);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            leer.speak(respuestita, TextToSpeech.QUEUE_FLUSH, null, null);
        }else {
            leer.speak(respuestita, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public void inicializar(){
        escuchando = findViewById(R.id.tvEscuchado);
        respuesta = findViewById(R.id.tvRespuesta);
        respuest = proveerDatos();
        leer = new TextToSpeech(this, this);


    }

    public void hablar(View v){
        Intent hablar = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        hablar.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-PE");
        startActivityForResult(hablar, RECONOCEDOR_VOZ);
    }


    public ArrayList<Respuestas> proveerDatos(){
        ArrayList<Respuestas> respuestas = new ArrayList<>();
        respuestas.add(new Respuestas("defecto", "¡Aun no estoy programada para responder eso, lo siento!"));
        respuestas.add(new Respuestas("hola", "hola "));
        respuestas.add(new Respuestas("chiste", "¿Sabes que mi hermano anda en bicicleta desde los 4 años? Mmm, ya debe estar lejos"));
        respuestas.add(new Respuestas("adios", "que descanses"));
        respuestas.add(new Respuestas("como estas", "esperando serte de ayuda"));
        respuestas.add(new Respuestas("nombre", "mis amigos me llaman Adaug"+miUbicacion));
        respuestas.add(new Respuestas("mi direccion", " "+miUbicacion));
        respuestas.add(new Respuestas("distancia", " "+distancia));
        respuestas.add(new Respuestas("llamar", " "+"claro"));



        return respuestas;
    }

    @Override
    public void onInit(int status) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void EnviarMensaje(String numero, String mensaje){

        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(numero, null, mensaje, null, null);
            Toast.makeText(this, "Mensaje enviado", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Mensaje no enviado", Toast.LENGTH_SHORT).show();
        }
    }
}