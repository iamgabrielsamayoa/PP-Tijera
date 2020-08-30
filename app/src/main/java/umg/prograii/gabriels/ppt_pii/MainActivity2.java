package umg.prograii.gabriels.ppt_pii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity2 extends AppCompatActivity {

    private Button playbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Nos agrega sonido al boton de jugar
        final MediaPlayer startSound = MediaPlayer.create(this, R.raw.startsound);
        playbtn = (Button) findViewById(R.id.playBtn);

        //Abre la segunda actividad
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
                startSound.start();
            }
        });

    }
}
