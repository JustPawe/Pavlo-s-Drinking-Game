package com.markpaveszka.pavlosdrinkinggame;

/**
 * Created by Acer on 2018. 01. 05..
 */

public class Quest {
    private String playerName;
    private String task;
    private int howLong;
    private int startTurnNumber;
    private int endTurnNumber;
    private int sipsSucceed;
    private int sipsFail;

    public Quest(String playerName, String task, int howLong, int startTurnNumber, int endTurnNumber, int sipsSucceed, int sipsFail) {
        this.playerName = playerName;
        this.task = task;
        this.howLong = howLong;
        this.startTurnNumber = startTurnNumber;
        this.endTurnNumber = endTurnNumber;
        this.sipsFail = sipsFail;
        this.sipsSucceed= sipsSucceed;
    }
    public String getPlayerName(){return playerName;}
    public String getTask(){return task;}
    public int getHowLong(){return howLong;}
    public int getStartTurnNumber(){return startTurnNumber;}
    public int getEndTurnNumber(){return endTurnNumber;}
    public int getSipsSucceed(){return sipsSucceed;}
    public int getSipsFail(){return  sipsFail;}
}
