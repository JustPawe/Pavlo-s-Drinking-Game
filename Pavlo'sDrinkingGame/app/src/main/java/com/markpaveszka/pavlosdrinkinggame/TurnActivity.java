package com.markpaveszka.pavlosdrinkinggame;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class TurnActivity extends AppCompatActivity {


    public TurnActivity() throws IOException {
    }

    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<Integer> usedPlayerNumbers = new ArrayList<>();
    private ArrayList<String>playerNames = new ArrayList<>();
    private ArrayList<Question>questions = new ArrayList<>();
    private ArrayList<forTheList> questPlayerNames = new ArrayList<>();
    private ArrayList<Quest> quests = new ArrayList<>();
    private BufferedReader reader;
    private Button acceptQuestBtn;
    private Button answerABtn;
    private Button answerBBtn;
    private Button answerCBtn;
    private Button answerDBtn;
    private Button backToListBtn;
    private Button backToTurnsBtn;
    private Button backToTurnVQBtn;
    private Button checkSipsBtn;
    private Button continueACBtn;
    private Button continueCABtn;
    private Button continuePABtn;
    private Button continueTCBtn;
    private Button continueWABtn;
    private Button falseBtn;
    private Button passBtn;
    private Button passBtnQuests;
    private Button questsBtn;
    private Button startTurnBtn;
    private Button taskCompletedBtn;
    private Button trueBtn;
    private int currentPlayerNumber=0;
    private int generateRange;
    private int howManyLines=0;
    private int turnNumber =0;
    private ListAdapater listAdapater;
    private ListView listView;
    private TextView currentTurnDisplayTextView;
    private TextView currentTurnNumberTextView;
    private TextView doOrDrinkTextView;
    private TextView doOrDrinkTextViewQuest;
    private TextView endTurnDisplayTextView;
    private TextView endTurnNumberTextView;
    private TextView everyBodyDrinksTextView;
    private TextView everyBodyDrinksTextViewTC;
    private TextView howManySipsTextViewCA;
    private TextView howManySipsTextViewPA;
    private TextView howManySipsTextViewTC;
    private TextView howManySipsTextViewWA;
    private TextView infoTextViewAC;
    private TextView multipChoiceQuestionTextView;
    private TextView multipChoiceAnswerATextView;
    private TextView multipChoiceAnswerBTextView;
    private TextView multipChoiceAnswerCTextView;
    private TextView multipChoiceAnswerDTextView;
    private TextView nextTurnIsYoursTextView;
    private TextView playerNameTextView;
    private TextView questDisplayTextView;
    private TextView sipsCATextView;
    private TextView sipsPATextView;
    private TextView sipsTCTextView;
    private TextView sipsWATextView;
    private TextView trueOrFalseQuestionTextView;
    private TextView youDrinkTextView;
    private TextView youDrinkTextViewPA;
    private Typeface face;
    private int[] pointsForPlayers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn);
        face = Typeface.createFromAsset(getAssets(),
                "Digitalt.ttf");

        String lines = null;
        try {
            reader =  new BufferedReader(new InputStreamReader(getAssets().open("questions.txt")));
            lines = reader.readLine();
            while (lines != null) {
                String[] values = lines.split(";");
                int id = Integer.parseInt(values[0]);
                String type = values[1];
                String question = values[2];
                String correctAnswer ;
                String wrongAnswer1 ;
                String wrongAnswer2;
                String wrongAnswer3;

                if (values[3].equals("#NoAnswerHere")){
                    correctAnswer = null;
                }
                else{
                    correctAnswer = values[3];
                }
                if (values[4].equals("#NoAnswerHere")){
                    wrongAnswer1 = null;
                }
                else{
                    wrongAnswer1 = values[4];
                }
                if (values[5].equals("#NoAnswerHere")){
                    wrongAnswer2 = null;
                }
                else{
                    wrongAnswer2 = values[5];
                }
                if (values[6].equals("#NoAnswerHere")){
                    wrongAnswer3 = null;
                }
                else{
                    wrongAnswer3 = values[6];
                }
                Question currentQuestion = new Question(id, type, question, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3);
                questions.add(currentQuestion);
                lines = reader.readLine();
                howManyLines++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        generateRange = questions.size();
        playerNames = this.getIntent().getStringArrayListExtra("players");

        pointsForPlayers = new int[playerNames.size()];
        nextTurn();



        //TrueOrFalse:






    }

    private void nextTurn(){
        setContentView(R.layout.activity_turn);

        startTurnBtn =(Button) findViewById(R.id.startTurnBtn);
        questsBtn = (Button) findViewById(R.id.questsBtn);
        playerNameTextView = (TextView) findViewById(R.id.playerNameTextView);

        nextTurnIsYoursTextView =(TextView) findViewById(R.id.nexTurnIsYoursTextView);
        nextTurnIsYoursTextView.setTypeface(face);


        Random random = new Random();
        int playerNumber = random.nextInt(playerNames.size());
        if (usedPlayerNumbers.size()== playerNames.size())
        {
            usedPlayerNumbers.clear();
        }
        while (usedPlayerNumbers.contains(playerNumber)){
            Random random2 = new Random();
            playerNumber = random2.nextInt(playerNames.size());
        }
        usedPlayerNumbers.add(playerNumber);
        currentPlayerNumber = playerNumber;
        final String currentPlayer = playerNames.get(playerNumber);
        playerNameTextView.setText(currentPlayer);

        Random random1 = new Random();
        int questionNumber=0;

        while (questionNumber ==0){
            questionNumber = random1.nextInt(generateRange);
        }
        final Question placerVariable = questions.get(questionNumber);
        questions.remove(questionNumber);
        questions.add(placerVariable);
        generateRange--;
        if (generateRange==1){
            generateRange=questions.size();
        }


        startTurnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //type for line 116, 115,38,22,
                turnNumber++;
                if (placerVariable.returnQuestionType().equals("TrueOrFalse")){
                    setContentView(R.layout.true_or_false_layout);
                    trueOrFalse(placerVariable);

                }
                else if (placerVariable.returnQuestionType().equals("MultipChoice")){
                    setContentView(R.layout.multiple_choice_layout);
                    multipleChoice(placerVariable);
                }
                else if (placerVariable.returnQuestionType().equals("DrinkOrDoIt")){
                    setContentView(R.layout.do_or_drink_layout);
                    doOrDrink(placerVariable);
                }
                else if(placerVariable.returnQuestionType().equals("DrinkOrDoIt#")){
                    setContentView(R.layout.do_or_drink_layout);
                    doOrDrinkWithNames(placerVariable);
                }
                else if(placerVariable.returnQuestionType().equals("DrinkOrDoItQuest")){
                    setContentView(R.layout.do_or_drink_quest);
                    doOrDrinkQuest(placerVariable);
                }
                else if(placerVariable.returnQuestionType().equals("DrinkOrDoItQuest#")){
                    setContentView(R.layout.do_or_drink_quest);
                    doOrDrinkQuestWithNames(placerVariable);
                }
                else
                {
                    new AlertDialog.Builder(TurnActivity.this)
                            .setMessage("Error: Question type not recognized")
                            .setPositiveButton("Please contact the developer, and report", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    nextTurn();
                                }
                            })
                            .show();
                }

            }
        });
/*
        final int finalQuestionNumber = questionNumber;*/
        questsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCharts = new Intent(TurnActivity.this, ChartActivity.class).putExtra("scores", pointsForPlayers).putExtra("names", playerNames);
                startActivity(goToCharts);
                finish();
            }
        });
    }

    private void trueOrFalse(final Question question){


        trueOrFalseQuestionTextView = (TextView)findViewById(R.id.questionTFDisplayTextView);
        String currentQuestion = question.returnQuestionContent();
        trueOrFalseQuestionTextView.setTypeface(face);
        trueOrFalseQuestionTextView.setText(currentQuestion);
        trueBtn = (Button) findViewById(R.id.trueBtn);
        falseBtn = (Button)findViewById(R.id.falseBtn);



        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (question.returnCorrectAnswer().equals("True")){
                    correctAnswer();
                }
                if(!question.returnCorrectAnswer().equals("True")) {
                    wrongAnswer();
                }
            }
        });
        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (question.returnCorrectAnswer().equals("False")){
                    correctAnswer();
                }
                if(!question.returnCorrectAnswer().equals("False")){
                    wrongAnswer();

                }
            }
        });
    }


    //multiple choice:

    private void multipleChoice(final Question question){
        multipChoiceQuestionTextView = (TextView) findViewById(R.id.questionMCDisplayTextView);
        multipChoiceQuestionTextView.setTypeface(face);
        multipChoiceAnswerATextView =(TextView) findViewById(R.id.answerATextView);
        multipChoiceAnswerATextView.setTypeface(face);
        multipChoiceAnswerBTextView = (TextView) findViewById(R.id.answerBTextView);
        multipChoiceAnswerBTextView.setTypeface(face);
        multipChoiceAnswerCTextView = (TextView) findViewById(R.id.answerCTextView);
        multipChoiceAnswerCTextView.setTypeface(face);
        multipChoiceAnswerDTextView = (TextView) findViewById(R.id.answerDTextView);
        multipChoiceAnswerDTextView.setTypeface(face);
        String currentQuestion = question.returnQuestionContent();

        multipChoiceQuestionTextView.setText(currentQuestion);
        final String correctAnswerOfCurrentQuestion = question.returnCorrectAnswer();
        final String wrongAnswer1 = question.returnWrongAnswer1();
        String wrongAnswer2 = question.returnWrongAnswer2();
        String wrongAnswer3 = question.returnWrongAnswer3();
        Random random2 = new Random();
        final int placeOfTheCorrectAnswer = random2.nextInt(4);
        int placeOfWrongAnswer1 = (placeOfTheCorrectAnswer+1)%4;
        int placeOfWrongAnswer2;
        if(placeOfWrongAnswer1==3){
            placeOfWrongAnswer2 = 0;
        }
        else{
            placeOfWrongAnswer2 = placeOfWrongAnswer1+1;

        }

        int placeOfWrongAnswer3 = (placeOfTheCorrectAnswer+3)%4;

        placingAnswersForMultipChoice(placeOfTheCorrectAnswer,correctAnswerOfCurrentQuestion);
        placingAnswersForMultipChoice(placeOfWrongAnswer1, wrongAnswer1);
        placingAnswersForMultipChoice(placeOfWrongAnswer2, wrongAnswer2);
        placingAnswersForMultipChoice(placeOfWrongAnswer3, wrongAnswer3);



        answerABtn = (Button) findViewById(R.id.answer1Btn);
        answerBBtn = (Button) findViewById(R.id.answer2Btn);
        answerCBtn = (Button) findViewById(R.id.answer3Btn);
        answerDBtn = (Button) findViewById(R.id.answer4Btn);

        answerABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(placeOfTheCorrectAnswer==0){
                 correctAnswer();
             }

             else{
                 wrongAnswer();
             }
            }
        });
        answerBBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(placeOfTheCorrectAnswer==1){
                    correctAnswer();
                }

                else{
                    wrongAnswer();
                }
            }
        });
        answerCBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(placeOfTheCorrectAnswer==2){
                    correctAnswer();
                }

                else{
                    wrongAnswer();
                }
            }
        });

        answerDBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(placeOfTheCorrectAnswer==3){

                    correctAnswer();
                }

                else{
                    wrongAnswer();
                }
            }
        });

    }//multipchoice



    //do or drink
    private void doOrDrink(final Question question){
        doOrDrinkTextView =(TextView) findViewById(R.id.questionDDDisplayTextView);
        doOrDrinkTextView.setTypeface(face);
        String currentQuestion = question.returnQuestionContent();
        doOrDrinkTextView.setText(currentQuestion);

        taskCompletedBtn = (Button) findViewById(R.id.taskCompletedBtn);
        passBtn = (Button) findViewById(R.id.passBtn);
        taskCompletedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeTask();
            }
        });
        passBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passTask();
            }
        });


    }//do or drink


    private void doOrDrinkWithNames(final Question question){
        doOrDrinkTextView =(TextView) findViewById(R.id.questionDDDisplayTextView);
        doOrDrinkTextView.setTypeface(face);
        String currentQuestion = question.returnQuestionContent();
        String[] parts = currentQuestion.split("#");
        String questionPart1 = parts[0];
        String questionPart2 = parts[1];


        int playerNumber = currentPlayerNumber;
        while (playerNumber==currentPlayerNumber){
            Random random1 = new Random();
            playerNumber = random1.nextInt(playerNames.size());
        }
        String currentPlayer = playerNames.get(playerNumber);
        String finalQuestion = questionPart1  + currentPlayer+ questionPart2;

        doOrDrinkTextView.setText(finalQuestion);
        taskCompletedBtn = (Button) findViewById(R.id.taskCompletedBtn);
        passBtn = (Button) findViewById(R.id.passBtn);
        taskCompletedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeTask();
            }
        });
        passBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passTask();
            }
        });


    }//do or drink

    //other methods

    private void correctAnswer(){
        setContentView(R.layout.correct_answer_layout);
        howManySipsTextViewCA = (TextView) findViewById(R.id.howManySipsTextViewCA);
        everyBodyDrinksTextView = (TextView) findViewById(R.id.everyBodyDrinksTextView);
        sipsCATextView =(TextView) findViewById(R.id.sipsCATextView);
        continueCABtn =(Button) findViewById(R.id.continueCABtn);
        sipsCATextView.setTypeface(face);
        everyBodyDrinksTextView.setTypeface(face);
        howManySipsTextViewCA.setTypeface(face);
        Random random = new Random();
        int howManyShots =0;
        while (howManyShots==0){
            howManyShots= random.nextInt(3);
        }//while
        for (int i=0; i<playerNames.size();i++)
        {
            if(i!=currentPlayerNumber)
            {
                pointsForPlayers[i]+= howManyShots;
            }

        }

        howManySipsTextViewCA.setText(""+howManyShots);
        continueCABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quests.size()-1>=0)
                {
                    showQuests();
                }
                else {
                    nextTurn();
                }
            }
        });
    }//correctAnswer



    private void wrongAnswer(){
        setContentView(R.layout.wrong_answer_layout);
        howManySipsTextViewWA = (TextView) findViewById(R.id.howManySipsTextViewWA);
        youDrinkTextView = (TextView) findViewById(R.id.youDrinkTextViewWA);
        sipsWATextView =(TextView) findViewById(R.id.sipsWATextView);
        continueWABtn =(Button) findViewById(R.id.continueWABtn);
        sipsWATextView.setTypeface(face);
        youDrinkTextView.setTypeface(face);
        howManySipsTextViewWA.setTypeface(face);
        Random random = new Random();
        int howManyShots =0;
        while (howManyShots==0){
            howManyShots= random.nextInt(3);
        }//while
        pointsForPlayers[currentPlayerNumber]+= howManyShots;
        howManySipsTextViewWA.setText(""+howManyShots);
        continueWABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quests.size()-1>=0)
                {
                    showQuests();
                }
                else {
                    nextTurn();
                }
            }
        });
    }

    private void completeTask(){
        setContentView(R.layout.task_completed_layout);
        howManySipsTextViewTC = (TextView) findViewById(R.id.howManySipsTextViewTC);
        everyBodyDrinksTextViewTC = (TextView) findViewById(R.id.everyBodyDrinksTextViewTC);
        sipsTCTextView =(TextView) findViewById(R.id.sipsTCTextView);
        continueTCBtn =(Button) findViewById(R.id.continueTCBtn);
        sipsTCTextView.setTypeface(face);
        everyBodyDrinksTextViewTC.setTypeface(face);
        howManySipsTextViewTC.setTypeface(face);
        Random random = new Random();
        int howManyShots =0;
        while (howManyShots==0){
            howManyShots= random.nextInt(3);
        }//while
        for (int i=0; i<playerNames.size();i++)
        {
            if(i!=currentPlayerNumber)
            {
                pointsForPlayers[i]+= howManyShots;
            }

        }
        howManySipsTextViewTC.setText(""+howManyShots);
        continueTCBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quests.size()-1>=0)
                {
                    showQuests();
                }
                else {
                    nextTurn();
                }
            }
        });
    }

    private void passTask(){
        setContentView(R.layout.passed_layout);
        howManySipsTextViewPA = (TextView) findViewById(R.id.howManySipsTextViewPA);
        youDrinkTextViewPA = (TextView) findViewById(R.id.youDrinkTextViewPA);
        sipsPATextView =(TextView) findViewById(R.id.sipsPATextView);
        continuePABtn =(Button) findViewById(R.id.continuePABtn);
        sipsPATextView.setTypeface(face);
        youDrinkTextViewPA.setTypeface(face);
        howManySipsTextViewPA.setTypeface(face);
        Random random = new Random();
        int howManyShots =0;
        while (howManyShots==0){
            howManyShots= random.nextInt(3);
        }//while
        pointsForPlayers[currentPlayerNumber]+= howManyShots;
        howManySipsTextViewPA.setText(""+howManyShots);
        continuePABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quests.size()-1>=0)
                {
                    showQuests();
                }
                else {
                    nextTurn();
                }
            }
        });
    }

    private void placingAnswersForMultipChoice(int placeOfAnswer, String actualAnswer){
        // 0-A, 1-B, 2-C, 3-D
        switch (placeOfAnswer){
            case 0:
                multipChoiceAnswerATextView.setText("A: "+actualAnswer);
                break;
            case 1:
                multipChoiceAnswerBTextView.setText("B: "+actualAnswer);
                break;
            case 2:
                multipChoiceAnswerCTextView.setText("C: "+actualAnswer);
                break;
            case 3:
                multipChoiceAnswerDTextView.setText("D: "+actualAnswer);
                break;
        }
    }


    private void showQuests(){
        setContentView(R.layout.activity_quests);
        listView = (ListView) findViewById(R.id.listview);
        checkList(quests,questPlayerNames);
        //arrayAdapter = new ArrayAdapter<String>(listView.getContext(), android.R.layout.simple_list_item_1,questPlayerNames);
        listAdapater = new ListAdapater(listView.getContext(), R.layout.listview_item_row, questPlayerNames);
        listView.setAdapter(listAdapater);
        backToTurnsBtn = (Button) findViewById(R.id.backBtn);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setContentView(R.layout.view_quest_layout);
                //viewQuestDetails(id, playerNameForStore,questionNumberForStore);
                viewQuestDetails(id);
            }
        });

        backToTurnsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.activity_turn);
               // returnToTurnAfterQuest(questionNumberForStore,playerNameForStore);

                nextTurn();
            }
        });

    }//showQuests
    /*
    MAJOR REDESIGN:

            -NO QUEST MENU:
                -IF THERE ARE ONGOING QUESTS, DISPLAY THEM AFTER EACH TURN
                insert this line to the quests:
                    pointsForPlayers[currentPlayerNumber]= howManyShots;

     */

    private void acceptTask(String theCurrentQuestion, int lenghtOfTheQuest){
        setContentView(R.layout.accept_task_layout);
        continueACBtn = (Button) findViewById(R.id.continueACBtn);
        infoTextViewAC = (TextView) findViewById(R.id.infoTextViewAC);
        infoTextViewAC.setTypeface(face);
        String currentPlayerName = playerNames.get(currentPlayerNumber);
        int endTurn = turnNumber+lenghtOfTheQuest;
        Random randomForSips = new Random();
        int sipsSucceed= randomForSips.nextInt(2)+1;

        int sipsFail= randomForSips.nextInt(2)+1;
        final Quest quest = new Quest(currentPlayerName, theCurrentQuestion,lenghtOfTheQuest, turnNumber,endTurn,sipsSucceed,sipsFail);
        quests.add(quest);
        questPlayerNames.add(new forTheList(currentPlayerName));
        continueACBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quests.size()-1>=0)
                {
                    showQuests();
                }
                else {
                    nextTurn();
                }
            }
        });

    }

    private void doOrDrinkQuest(Question question){

        doOrDrinkTextViewQuest =(TextView) findViewById(R.id.questionDDQDisplayTextView);
        doOrDrinkTextViewQuest.setTypeface(face);
        final String currentQuestion = question.returnQuestionContent();
        String[] getTheLengthOfQuest = currentQuestion.split("[@]");
        final int lengthOfTheQuest = Integer.parseInt(getTheLengthOfQuest[1]);
        final String finalQuestion = getTheLengthOfQuest[0]+getTheLengthOfQuest[1]+getTheLengthOfQuest[2];
        doOrDrinkTextViewQuest.setText(finalQuestion);
        acceptQuestBtn = (Button) findViewById(R.id.acceptQuestBtn);
        passBtnQuests = (Button) findViewById(R.id.passBtnQuests);
        acceptQuestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptTask(finalQuestion, lengthOfTheQuest);
            }
        });
        passBtnQuests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passTask();
            }
        });
    }

    private void doOrDrinkQuestWithNames(Question question){
        doOrDrinkTextViewQuest =(TextView) findViewById(R.id.questionDDQDisplayTextView);
        doOrDrinkTextViewQuest.setTypeface(face);
        final String currentQuestion = question.returnQuestionContent();
        String[] parts = currentQuestion.split("#");
        String questionPart1 = parts[0];
        String questionPart2 = parts[1];


        int playerNumber = currentPlayerNumber;
        while (playerNumber==currentPlayerNumber){
            Random random1 = new Random();
            playerNumber = random1.nextInt(playerNames.size());
        }
        String currentPlayer = playerNames.get(playerNumber);
        String finalQuestion = questionPart1  + currentPlayer+ questionPart2;
        String[] getTheLengthOfQuest = finalQuestion.split("[@]");
        final int lengthOfTheQuest = Integer.parseInt(getTheLengthOfQuest[1]);
        final String theFinalQuestion = getTheLengthOfQuest[0]+getTheLengthOfQuest[1]+getTheLengthOfQuest[2];
        doOrDrinkTextViewQuest.setText(theFinalQuestion);

        acceptQuestBtn = (Button) findViewById(R.id.acceptQuestBtn);
        passBtnQuests = (Button) findViewById(R.id.passBtnQuests);
        acceptQuestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptTask(theFinalQuestion, lengthOfTheQuest);
            }
        });
        passBtnQuests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passTask();
            }
        });
    }




    private void checkList(ArrayList<Quest> questsForCheck, ArrayList<forTheList> questsplayers){
        for (int listCounter =0; listCounter<questsForCheck.size();listCounter++){
            Quest currentQuest = questsForCheck.get(listCounter);
            if (currentQuest.getEndTurnNumber()<=turnNumber){
                for (int i =0; i<playerNames.size();i++)
                {
                    if(!currentQuest.getPlayerName().equals(playerNames.get(i))){
                        pointsForPlayers[i]+= currentQuest.getSipsSucceed();
                    }

                }

                questsForCheck.remove(listCounter);
                questsplayers.remove(listCounter);

            }
        }//for
    }//checklist


    private int cPlayerNumber;

    private void viewQuestDetails(long id /*final String playerNameforTurn, final int questionIdForTurn*/ ){
        backToListBtn = (Button) findViewById(R.id.backToListBtn);
        backToTurnVQBtn = (Button) findViewById(R.id.backtoTurnBtn);
        checkSipsBtn = (Button) findViewById(R.id.checkSipsBtn);
        questDisplayTextView = (TextView) findViewById(R.id.questDisplayTextView);
        currentTurnDisplayTextView = (TextView) findViewById(R.id.currentTurnDisplay);
        currentTurnNumberTextView = (TextView) findViewById(R.id.currentTurnNumberTextView);
        endTurnDisplayTextView = (TextView) findViewById(R.id.endTurnDisplay);
        endTurnNumberTextView = (TextView) findViewById(R.id.endTurnNumberTextView);
        questDisplayTextView.setTypeface(face);
        currentTurnNumberTextView.setTypeface(face);
        currentTurnDisplayTextView.setTypeface(face);
        endTurnNumberTextView.setTypeface(face);
        endTurnDisplayTextView.setTypeface(face);
        int idOfQuest = (int) id;
        final Quest currentQuest = quests.get(idOfQuest);
        String questContent = currentQuest.getTask();
        questDisplayTextView.setText(questContent);
        endTurnNumberTextView.setText(""+currentQuest.getEndTurnNumber());
        currentTurnNumberTextView.setText(""+ turnNumber);

        backToListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.activity_quests);
                showQuests(/*questionIdForTurn, playerNameforTurn*/);
            }
        });

        backToTurnVQBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextTurn();
                //setContentView(R.layout.activity_turn);
                //returnToTurnAfterQuest(questionIdForTurn, playerNameforTurn);
            }
        });

        checkSipsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int succeedSips = currentQuest.getSipsSucceed();
                int failSips = currentQuest.getSipsFail();

                for (int i=0; i<playerNames.size();i++)
                {
                    if(playerNames.get(i).equals(currentQuest.getPlayerName()))
                    {
                        cPlayerNumber=i;
                    }


                }
                new AlertDialog.Builder(TurnActivity.this)
                        .setTitle("If you fail or succeed here is how much you or the others have to drink")
                        .setMessage("Succeed: " + succeedSips +" sip(s) for everyone;\nFail: " + failSips+" sip(s) for you")
                        .setNeutralButton("Back", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Fail", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                pointsForPlayers[cPlayerNumber] += currentQuest.getSipsFail();
                            }
                        })
                        .show();
            }
        });




    }
}

