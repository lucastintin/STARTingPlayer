package br.com.miune.startingplayer;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    ConstraintLayout lytPrincipal;
    int clicado;
    ImageView estrelinha;
    List<ImageView> constelacao = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clicado = 0;
        lytPrincipal = (ConstraintLayout) findViewById(R.id.lytPrincipal);
        lytPrincipal.setOnTouchListener(this);


        //Que coisa horrorosa.
        new Timer().schedule(new TimerTask() {
            @Override
            public void run () {
                //Funcao de apagar as estrelas
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        apagarEstrelas();
                    }
                });
            }
        }, 10000);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

            clicado = clicado + 1;

            estrelinha = new ImageView(this);
            //Adicionando cada estrela criada a um listView
            constelacao.add(estrelinha);

            //DEPRECATED
            //ImageView estrelinha = new ImageView(this);

            //TODO: Tentar colocar cor na ImageView
            //TODO: Cobrir com a Máscara de Estrela que o Arthur enviar.
            //TODO: Resolver o fundo e o Layout da barra.
            estrelinha.setImageResource(R.drawable.shape);
            estrelinha.setId(clicado);
            //Posicao centralizada com um click
            estrelinha.setX(motionEvent.getX()-125);
            estrelinha.setY(motionEvent.getY()-125);
            //Tentando Colorir
            estrelinha.setBackgroundColor(clicado);


            //Defino um Constraint Layout, apenas para minha estrela, de tamanho fixo.
            ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(250,250);
            //DEPRECATED
            //ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

            lytPrincipal.addView(estrelinha, lp);
        }

        return false;
    }

    public void apagarEstrelas(){

        Random r = new Random();
        int resultado = r.nextInt(constelacao.size());
        int i = 0;

        while (i < constelacao.size() ){
            if (i!=resultado) {
                constelacao.get(i).setVisibility(View.INVISIBLE);
            }
            i++;
        }
    }

}
