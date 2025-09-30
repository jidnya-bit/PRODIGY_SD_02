package com.example.guessinggame;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guessinggame.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int randomNumber;
    private int attempts;
    private final int MAX_ATTEMPTS = 4;

    private TextView feedbackText;
    private TextView attemptsText;
    private EditText guessInput;
    private Button guessButton, resetButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        feedbackText = findViewById(R.id.feedbackText);
        attemptsText = findViewById(R.id.attemptsText);
        guessInput = findViewById(R.id.guessInput);
        guessButton = findViewById(R.id.guessButton);
        resetButton = findViewById(R.id.resetButton);

        startNewGame();

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });
    }

    private void startNewGame() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1; // 1 to 100
        attempts = 0;
        feedbackText.setText("I have chosen a number between 1 and 100. Can you guess it?");
        attemptsText.setText("Attempts: 0");
        guessInput.setText("");
        guessButton.setEnabled(true);
    }

    private void checkGuess() {
        String guessStr = guessInput.getText().toString().trim();
        if (guessStr.isEmpty()) {
            feedbackText.setText("⚠️ Please enter a number!");
            return;
        }

        int guess = Integer.parseInt(guessStr);
        attempts++;
        attemptsText.setText("Attempts: " + attempts);

        if (guess == randomNumber) {
            showResultDialog("🎉 Correct! You guessed in " + attempts + " attempts.");
        } else if (attempts >= MAX_ATTEMPTS) {
            showResultDialog("❌ Out of attempts! The number was: " + randomNumber);
        } else if (guess < randomNumber) {
            feedbackText.setText("📉 Too low! Try again.");
        } else {
            feedbackText.setText("📈 Too high! Try again.");
        }
    }

    private void showResultDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Over")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startNewGame();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

        guessButton.setEnabled(false);
    }
}
