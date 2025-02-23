package com.example.chaiss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        // Find the PLAY button
        Button playButton = findViewById(R.id.button2);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to GameMode activity
                Intent intent = new Intent(MainMenu.this, GameMode.class);
                startActivity(intent);
            }
        });

        // Find the SETTINGS button
        Button settingsButton = findViewById(R.id.button7);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SettingsActivity
                Intent intent = new Intent(MainMenu.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}
