package umg.prograii.gabriels.ppt_pii;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.BitSet;
import java.util.Random;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {

    //Array con las imagenes a utilizar para las selecciones de usuario y npc
    public int [] pictures = {R.drawable.rock, R.drawable.paper, R.drawable.scissors, R.drawable.rock2, R.drawable.paperf, R.drawable.scissorsf, };
    //Manejo de Etiquetas
    private static final String TAG = "MainActivity";
    //Manejo de Puntajes
    private int userScore = 0, npcScore = 0;
    //Vistas
    private TextView userSelectionTextView, npcSelectionTextView, WonLostTieTextView, scoreTextView;
    private ImageView userImage, npcImage;
    //Control para restaurar de 0 al finalizar
    private boolean timeToReset = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Creamos una relacion entre atributos y Vistas de XML
        userSelectionTextView = findViewById(R.id.userSelectionTextView);
        npcSelectionTextView = findViewById(R.id.npcSelectionTextView);
        WonLostTieTextView = findViewById(R.id.WonLostTieTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        userImage = findViewById(R.id.userImageView);
        npcImage = findViewById(R.id.npcImageView);



        //Aseguramos que estas vistas esten vacias
        userSelectionTextView.setText("");
        npcSelectionTextView.setText("");
        WonLostTieTextView.setText("");

    }

    //Restaura los valores a 0 definido por el usuario
    public void resetButton(View view) {
        resetScore();
    }

    //Restaura los valores a 0 en base a puntaje
    public void resetScore() {
        WonLostTieTextView.setText("");
        userSelectionTextView.setText("");
        npcSelectionTextView.setText("");
        userScore = 0;
        npcScore = 0;
        setScoreTextView(userScore, npcScore);
        timeToReset = false;
    }

    //Este metodo activa el juego y puntajes
    public void rpsButtonSelected(View view) {
        int userSelection  = Integer.parseInt(view.getTag().toString());
        Log.i(TAG, "rpsButtonSelected:" + userSelection);
        matchGame(userSelection);
    }

    //Metodo que maneja el juego en si
    private void matchGame(int userSelection){

        //Genera la opcion del npc
        int npcChoice  = ((int)(Math.random()*3)+1);

        //Una vez uno de los dos alcance 3 pts reinicia el juego
        if (timeToReset)
            resetScore();

        //Jugador 1 Gana
            if(userSelection - npcChoice % 3 == 1){
                userScore++;
            WonLostTieTextView.setText("");
        }

        //npc Gana
                else if (userSelection == npcChoice){
            WonLostTieTextView.setText("Empate, otra vez!");
        }
                else{
                npcScore++;
                WonLostTieTextView.setText("");
            }

                //Resultado Final
                if(userScore == 3 || npcScore == 3){

                    //Empate
                    if (userSelection == npcChoice){
                        WonLostTieTextView.setText("Empate");
                    }


                    //Jugador 1 Gana
                    else if(userSelection - npcChoice % 3 == 1){
                        WonLostTieTextView.setText("Ganaste!!!");
                    }

                    //npc Gana
                    else{
                        WonLostTieTextView.setText("Upss, perdiste!");
                    }
                    timeToReset = true;
                }

                //Actualizamos opcion elegida usando metodos
        userchoice(userSelection);
       npcChoice(npcChoice);


       //Llamamos al metodo para actualizar el marcador
        setScoreTextView(userScore, npcScore);
    }

    //Este metodo modifica el texto dependiendo de la eleccion del usuario
    private void userchoice(int userSelection){
        //Estas variables las usamos para agregar sonidos
        final MediaPlayer rockSound = MediaPlayer.create(this, R.raw.rocksound);
        final MediaPlayer paperSound = MediaPlayer.create(this, R.raw.papersound);
        final MediaPlayer scissorsSound = MediaPlayer.create(this, R.raw.scissorsound);


        switch (userSelection){
            case 1:
                userSelectionTextView.setText("Piedra");
                userImage.setImageResource(pictures[userSelection+2]);
                rockSound.start();
                break;
            case 2:
                userSelectionTextView.setText("Papel");
                userImage.setImageResource(pictures[userSelection+2]);
                paperSound.start();
                break;
            case 3:
                userSelectionTextView.setText("Tijera");
                userImage.setImageResource(pictures[userSelection+2]);
                scissorsSound.start();
        }
    }

    //Metodo para modificar el texto dependiendo de la eleccion random
    private void npcChoice(int npcChoice){
        switch (npcChoice){
            case 1:
                npcSelectionTextView.setText("Piedra");
                npcImage.setImageResource(pictures[npcChoice-1]);
                break;
            case 2:
                npcSelectionTextView.setText("Papel");
                npcImage.setImageResource(pictures[npcChoice-1]);
                break;
            case 3:
                npcSelectionTextView.setText("Tijera");
                npcImage.setImageResource(pictures[npcChoice-1]);
        }
    }

    //Actualiza el marcador en el tablero
    private void setScoreTextView(int userScore, int npcScore){
        scoreTextView.setText(String.valueOf(userScore)+ ":" + String.valueOf(npcScore));
    }
}