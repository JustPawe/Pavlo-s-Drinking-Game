package com.markpaveszka.pavlosdrinkinggame;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class RulesActivity extends AppCompatActivity {



    private Button goToQuestionsActivityBtn;
    private ArrayList<String>playerNames = new ArrayList<>();
    private ArrayList<Integer> usedquestions = new ArrayList<>();
    private TextView theRulesDisplayTextView;
    private Typeface face;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        face = Typeface.createFromAsset(getAssets(),
                "Digitalt.ttf");



        theRulesDisplayTextView = (TextView) findViewById(R.id.theRulesTextView);
        theRulesDisplayTextView.setTypeface(face);
        usedquestions.add(0);

        goToQuestionsActivityBtn =(Button)findViewById(R.id.goToDrinkQuestionsBtn);
        playerNames = this.getIntent().getStringArrayListExtra("players");

        goToQuestionsActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToTurnActivity = new Intent(RulesActivity.this, TurnActivity.class).putExtra("players", playerNames).putExtra("usedQuestions", usedquestions);
                startActivity(goToTurnActivity);
                finish();
            }
        });
    }
}
