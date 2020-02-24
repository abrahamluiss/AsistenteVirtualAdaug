package pe.edu.continental.adaug.Actividades;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import pe.edu.continental.adaug.R;

public class VozATexto extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT=100;
    TextView mEntradaVoz;
    ImageButton mBtnHablar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voz_atexto);
        mEntradaVoz=findViewById(R.id.txtEntrada);
        mBtnHablar=findViewById(R.id.btnHablar);
        mBtnHablar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//lamar metodo inciar
                iniciarEntradaVoz();
            }
        });
    }
    private void iniciarEntradaVoz(){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);//reconocimiento de voz
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Te escucho");//texto q pondra en la ventana
        try {
            startActivityForResult(i, REQ_CODE_SPEECH_INPUT);
        }catch (ActivityNotFoundException e){//si no se realiza la funcion

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {//parametros
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)//codigo que recibio
        {
            case REQ_CODE_SPEECH_INPUT:{
                if (resultCode==RESULT_OK && null!=data){
                    ArrayList<String> result= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mEntradaVoz.setText(result.get(0));//agrega la palabra
                }
            }
            break;
        }
    }
}
