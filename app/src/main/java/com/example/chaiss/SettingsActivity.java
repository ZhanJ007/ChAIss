package com.example.chaiss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings); // Ensure this matches your settings XML file name

        // Find the Back button (imageButton3)
        ImageButton backButton = findViewById(R.id.imageButton3);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to MainMenu
                Intent intent = new Intent(SettingsActivity.this, MainMenu.class);
                startActivity(intent);
                finish(); // Close SettingsActivity
            }
        });
    }
}
