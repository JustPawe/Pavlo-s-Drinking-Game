package com.markpaveszka.pavlosdrinkinggame;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayerNamesActivity extends AppCompatActivity {


    private ArrayList<String>playerNames = new ArrayList<>();
    private TextView playerNumberDisplayTextView;
    private EditText playerNameEdiText;
    private Button submitNameBtn;
    private ImageView imageView;
    private ImageView imageViewMakeInviseble1;
    private ImageView imageViewMakeInviseble2;
    private ImageView imageViewMakeInviseble3;
    private ImageView imageViewMakeInviseble4;
    private int playerSubmitCounter=0;
    private int numberOfPlayers=0;
    private Typeface face;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_names);

        playerNameEdiText = (EditText) findViewById(R.id.playerNameEditText);
        face = Typeface.createFromAsset(getAssets(),
                "Digitalt.ttf");
        playerNameEdiText.setTypeface(face);
        submitNameBtn = (Button) findViewById(R.id.submitNameBtn);
        imageView = (ImageView) findViewById(R.id.playerNumberImageView);
        numberOfPlayers = this.getIntent().getIntExtra("numberOfPlayers", 0);
        imageViewMakeInviseble1 = (ImageView) findViewById(R.id.imageView6);
        imageViewMakeInviseble2 = (ImageView) findViewById(R.id.imageView8);
        imageViewMakeInviseble3 = (ImageView) findViewById(R.id.imageView5);
        imageViewMakeInviseble4 = (ImageView) findViewById(R.id.playerNumberImageView);

        submitNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerSubmitCounter==numberOfPlayers){
                    Intent goToRulesActivity = new Intent(PlayerNamesActivity.this, RulesActivity.class).putExtra("players", playerNames);
                    startActivity(goToRulesActivity);
                    finish();
                }

                else if (playerSubmitCounter<numberOfPlayers){
                    if (playerNameEdiText.getText().toString().equals("")) {
                        Toast.makeText(PlayerNamesActivity.this, "Please give the name of the player!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        String name = playerNameEdiText.getText().toString();
                        if(!playerNames.contains(name))
                        {
                            playerNames.add(name);
                            playerNameEdiText.setText("");
                            playerSubmitCounter++;
                            if(playerSubmitCounter<numberOfPlayers){
                                int id = getResources().getIdentifier("com.markpaveszka.pavlosdrinkinggame:drawable/p"+(playerSubmitCounter+1), null, null);
                                imageView.setImageResource(id);
                            }//if
                            else if (playerSubmitCounter==numberOfPlayers){

                                playerNameEdiText.setVisibility(View.INVISIBLE);
                                imageViewMakeInviseble4.setVisibility(View.INVISIBLE);
                                int id = getResources().getIdentifier("com.markpaveszka.pavlosdrinkinggame:drawable/beer", null, null);
                                imageViewMakeInviseble3.setImageResource(id);
                                int id1 = getResources().getIdentifier("com.markpaveszka.pavlosdrinkinggame:drawable/startthegame", null, null);
                                imageViewMakeInviseble2.setImageResource(id1);
                                imageViewMakeInviseble1.setVisibility(View.INVISIBLE);
                                submitNameBtn.setText("Start game");

                            }//elseif
                        }//if
                        else
                        {
                            Toast.makeText(PlayerNamesActivity.this, "This name is already in use!", Toast.LENGTH_SHORT).show();
                            playerNameEdiText.setText("");
                        }



                    }//else


                }


            }
        });


    }
}
