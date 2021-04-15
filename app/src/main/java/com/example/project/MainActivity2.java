package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageButton logoutButton;
        logoutButton = (ImageButton) findViewById(R.id.imageButton_logout);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstActivity();
            }
        });

    }
    private void FirstActivity(){
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        this.startActivity(intent);
    }
}