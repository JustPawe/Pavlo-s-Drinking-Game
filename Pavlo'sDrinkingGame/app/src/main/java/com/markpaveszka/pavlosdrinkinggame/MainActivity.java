package com.markpaveszka.pavlosdrinkinggame;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText numberOfPlayersEdiText;
    private Button goToPlayersNameBtn;
    private Typeface face;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        face = Typeface.createFromAsset(getAssets(),
                "Digitalt.ttf");

        numberOfPlayersEdiText = (EditText) findViewById(R.id.playerNumberEditText);
        numberOfPlayersEdiText.setTypeface(face);
        goToPlayersNameBtn =(Button) findViewById(R.id.goToPlayersNameBtn);

        goToPlayersNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberOfPlayersString = numberOfPlayersEdiText.getText().toString();
                if (numberOfPlayersString.equals("")){
                    Toast.makeText(MainActivity.this, "Please give a number between 4 and 12!", Toast.LENGTH_SHORT).show();
                }
                else {
                    int howManyPlayers = Integer.parseInt(numberOfPlayersEdiText.getText().toString());

                    if (howManyPlayers < 4 || howManyPlayers > 12) {
                        Toast.makeText(MainActivity.this, "Please give a number between 4 and 12!", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent goToPlayerNamesActivity = new Intent(MainActivity.this, PlayerNamesActivity.class).putExtra("numberOfPlayers", howManyPlayers);
                        startActivity(goToPlayerNamesActivity);
                        finish();
                    }
                }
            }
        });
    }
}
