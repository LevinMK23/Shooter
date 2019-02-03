package com.acid.shooter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.startGame);
        start.setOnClickListener(view->{
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });
    }
}
