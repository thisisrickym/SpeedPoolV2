package com.beigerooster.testapp;
import java.util.Scanner;

public class StopWatch {
    public long milliSecondsRemaining;
    public long startTime;
    public long timeLeft;
    public int timeLeftInt;
    public Scanner sc;

    StopWatch(long milliSecondsRemaining){
        sc = new Scanner(System.in);
        //System.out.print("Input seconds => : ");
        // String secs = sc.nextLine();
        this.milliSecondsRemaining = milliSecondsRemaining;
        //System.out.println("Press Enter to start timer:");


    }

    public void startTimer(){
        startTime = System.currentTimeMillis();
    }

    public void stopTimer(){

    }
//    public int getTimeLeft(){
//
//        timeLeft = (interval - (System.currentTimeMillis()-startTime))/1000;
//        timeLeftInt = (int)timeLeft;
//        System.out.println(timeLeftInt);
//        return timeLeftInt;
//    }

}
