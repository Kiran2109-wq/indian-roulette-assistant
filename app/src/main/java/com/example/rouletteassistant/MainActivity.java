package com.example.rouletteassistant;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private List<Integer> spinHistory = new ArrayList<>();
    private TextView historyView, balanceView;
    private MediaPlayer bgMusic;
    private int balance = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        historyView = findViewById(R.id.historyView);
        balanceView = findViewById(R.id.balanceView);

        Button spinButton = findViewById(R.id.spinButton);
        Button resetButton = findViewById(R.id.resetButton);

        spinButton.setOnClickListener(v -> spinRoulette());
        resetButton.setOnClickListener(v -> resetHistory());

        balanceView.setText("₹" + balance);
        bgMusic = MediaPlayer.create(this, R.raw.har_ghar_mein);
        bgMusic.setLooping(true);
        bgMusic.start();
        updateHistory();
    }

    private void spinRoulette() {
        int next = new Random().nextInt(37);
        spinHistory.add(next);
        balance -= 100;
        balanceView.setText("₹" + balance);
        updateHistory();
        int prediction = new Random().nextInt(37);
        Toast.makeText(this, "AI Prediction: " + prediction, Toast.LENGTH_SHORT).show();
    }

    private void resetHistory() {
        spinHistory.clear();
        updateHistory();
    }

    private void updateHistory() {
        historyView.setText(spinHistory.toString());
    }

    @Override
    protected void onDestroy() {
        if (bgMusic != null) bgMusic.release();
        super.onDestroy();
    }
}
