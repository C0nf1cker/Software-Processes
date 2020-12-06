package com.example.database;

import android.os.CountDownTimer;
import android.widget.TextView;

public class Temporizador {
    private CountDownTimer timer;
    private TextView timeText;
    private long timeRemining;
    private boolean ended;

    public Temporizador(TextView timeText) {
        this.timeText = timeText;
        this.timer = crearTimer(30000);
    }

    private CountDownTimer crearTimer(long time) {
        CountDownTimer countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText(millisUntilFinished/1000+"sg");
                timeRemining = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                timeText.setText("Perdiste :(");
                ended = true;
            }
        };
        return countDownTimer;
    }

    /**
     * Metodo para reducir el tiempo del temporizador simbolizando el castigo al usuario
     * por fallar en el juego. Se resta 2 segundos al tiempo actual
     */
    public void fallar(){
        timer.cancel();
        timer = crearTimer(timeRemining-2000);
        timer.start();
    }
    /**
     * Metodo para aumentar el tiempo del temporizador simbolizando la recompensa al usuario
     * por acertar en el juego. Se suman 4 segundos al tiempo actual
     */
    public void acertar(){
        timer.cancel();
        timer = crearTimer(timeRemining+4000);
        timer.start();
    }

    public void star(){
        timer.start();
        ended = false;
    }

    public boolean hasEnd() {
        return ended;
    }
}
