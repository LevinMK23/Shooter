package com.acid.shooter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.acid.shooter.core.GameView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
}
