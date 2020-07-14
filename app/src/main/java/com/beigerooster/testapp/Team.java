package com.beigerooster.testapp;

public class Team {
    public StopWatch timer;
    public CharSequence name;
    public long time;
    public long extraBallTime;
    Team(){
        timer = new StopWatch(10000);
    }

    public void setTime(long time){ this.time = 1000*time;}

    public long getTime(){return time;}

    public void setExtraBallTime(long time){ extraBallTime = time;}

    public long getExtraBallTime(){return extraBallTime;}
    public void setName(CharSequence name){
        this.name = name;
    }

    public CharSequence getName(){
        return name;
    }

}
