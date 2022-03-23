package com.example.chess_clock;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 600000;

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;

    private TextView mTextViewCountDown2;
    private Button mButtonStartPause2;
    private Button mButtonReset2;

    private CountDownTimer mCountDownTimer;
    private CountDownTimer mCountDownTimer2;

    private boolean mTimerRunning;
    private boolean mTimerRunning2;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long mTimeLeftInMillis2 = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer TicTocVoice = MediaPlayer.create(this, R.raw.tictac_voice);
        final MediaPlayer TicTocVoice2 = MediaPlayer.create(this, R.raw.tictac_voice);
        TicTocVoice.setLooping(true);
        TicTocVoice2.setLooping(true);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);

        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonStartPause = (Button) this.findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);

        mTextViewCountDown2 = findViewById(R.id.text_view_countdown2);

        mButtonStartPause2 = findViewById(R.id.button_start_pause2);
        mButtonReset2 = findViewById(R.id.button_reset2);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicTocVoice.start();
                if (mTimerRunning) {
                    TicTocVoice.pause();
                    pauseTimer();

                } else {
                    TicTocVoice.start();
                    startTimer();
                }
            }
        });

        mButtonStartPause2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicTocVoice2.start();
                if (mTimerRunning2) {
                    TicTocVoice2.pause();
                    pauseTimer2();
                } else {
                    TicTocVoice2.start();
                    startTimer2();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        updateCountDownText();
        mButtonReset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer2();
            }
        });
        updateCountDownText2();

    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }



            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void startTimer2() {
        mCountDownTimer2 = new CountDownTimer(mTimeLeftInMillis2, 1000) {
            @Override
            public void onTick(long millisUntilFinished2) {
                mTimeLeftInMillis2 = millisUntilFinished2;
                updateCountDownText2();
            }



            @Override
            public void onFinish() {
                mTimerRunning2 = false;
                mButtonStartPause2.setText("Start");
                mButtonStartPause2.setVisibility(View.INVISIBLE);
                mButtonReset2.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning2 = true;
        mButtonStartPause2.setText("pause");
        mButtonReset2.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {

        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void pauseTimer2() {
        mCountDownTimer2.cancel();
        mTimerRunning2 = false;
        mButtonStartPause2.setText("Start");
        mButtonReset2.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void resetTimer2() {
        mTimeLeftInMillis2 = START_TIME_IN_MILLIS;
        updateCountDownText2();
        mButtonReset2.setVisibility(View.INVISIBLE);
        mButtonStartPause2.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }
    private void updateCountDownText2() {
        int minutes2 = (int) (mTimeLeftInMillis2 / 1000) / 60;
        int seconds2 = (int) (mTimeLeftInMillis2 / 1000) % 60;

        String timeLeftFormatted2 = String.format(Locale.getDefault(), "%02d:%02d", minutes2, seconds2);

        mTextViewCountDown2.setText(timeLeftFormatted2);
    }
}