package com.example.chaiss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class GameMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamemode);

        // 2D Mode Button
        Button button2D = findViewById(R.id.button_2d);
        button2D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameMode.this, PlayActivity.class);
                startActivity(intent);
            }
        });

        // 3D Mode Button
        Button button3D = findViewById(R.id.button_3d);
        button3D.setEnabled(true);
        button3D.setAlpha(1.0f);
        button3D.setText("3D Mode");
        button3D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameMode.this, CameraActivity.class);
                startActivity(intent);
            }
        });

        // Home Button
        Button buttonHome = findViewById(R.id.button_home);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameMode.this, MainMenu.class);
                startActivity(intent);
            }
        });

        // Back Button
        findViewById(R.id.imageButtonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
