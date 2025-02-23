// Dinosaurs
package com.example.chaiss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Set up a listener for taps anywhere on the screen
        View rootView = findViewById(android.R.id.content);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Proceed to MainActivity
                Intent intent = new Intent(WelcomeActivity.this, MainMenu.class);
                startActivity(intent);
                finish(); // Close WelcomeActivity
            }
        });
    }
}
