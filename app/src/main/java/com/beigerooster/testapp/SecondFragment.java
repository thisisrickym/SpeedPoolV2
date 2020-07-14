package com.beigerooster.testapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Locale;

import static com.beigerooster.testapp.MainActivity.team1;
import static com.beigerooster.testapp.MainActivity.team2;

public class SecondFragment extends Fragment {

    private CountDownTimer team1Timer;
    private CountDownTimer team2Timer;

    private long team1TimeInMillis;
    private long team2TimeInMillis;

    private long ballMadeTime;

    private int team1BallsLeft;
    private int team2BallsLeft;

    private boolean team1Running;
    private boolean team2Running;

    private TextView team1TimerView;
    private TextView team2TimerView;

    private TextView team1BallsView;
    private TextView team2BallsView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView teamview1 = (TextView)view.findViewById(R.id.textView);
        TextView teamview2 = (TextView)view.findViewById(R.id.textView2);
        team1TimerView = (TextView)view.findViewById(R.id.textView3);
        team2TimerView = (TextView)view.findViewById(R.id.textView5);
        team1BallsView = (TextView)view.findViewById(R.id.ballsLeft1);
        team2BallsView = (TextView)view.findViewById(R.id.ballsLeft2);

        teamview1.setText(team1.getName());
        teamview2.setText(team2.getName());

        team1TimeInMillis = team1.getTime();
        team2TimeInMillis = team2.getTime();

        ballMadeTime = team1.getExtraBallTime()*1000;

        updateCountDownText(team1TimeInMillis, team1TimerView);
        updateCountDownText(team2TimeInMillis, team2TimerView);

        team1BallsLeft = 7;
        team2BallsLeft = 7;


        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchTimer();
            }
        });

        view.findViewById(R.id.ballMade_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(team1Running){
                    team1Timer.cancel();
                    startTeam1Timer(team1TimeInMillis + ballMadeTime);
                } else {
                    team1TimeInMillis = team1TimeInMillis + ballMadeTime;
                    updateCountDownText(team1TimeInMillis, team1TimerView);
                }
                team1BallsLeft = team1BallsLeft - 1;
                if (team1BallsLeft < 0 ){
                    team1Timer.cancel();
                    team2Timer.cancel();
                    team1BallsView.setText("WIN!");
                }else{
                    updateBallsLeft(team1BallsLeft, team1BallsView);
                }

            }
        });

        view.findViewById(R.id.ballMade_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (team2Running) {
                    team2Timer.cancel();
                    startTeam2Timer(team2TimeInMillis + ballMadeTime);
                } else {
                    team2TimeInMillis = team2TimeInMillis + ballMadeTime;
                    updateCountDownText(team2TimeInMillis, team2TimerView);
                }
                team2BallsLeft = team2BallsLeft-1;
                if (team2BallsLeft < 0){
                    team1Timer.cancel();
                    team2Timer.cancel();
                    team2BallsView.setText("WIN!");
                } else{
                    updateBallsLeft(team2BallsLeft, team2BallsView);
                }

            }
        });
    }


    private void switchTimer(){
        if (team1Running){
            team1Timer.cancel();
            startTeam2Timer(team2TimeInMillis);
            team1Running = false;

        } else if (team2Running){
            team2Timer.cancel();
            startTeam1Timer(team1TimeInMillis);
            team2Running = false;
        } else {
            startTeam1Timer(team1TimeInMillis);
        }
    }

    private void updateCountDownText(long mTimeLeftInMillis, TextView mTextViewCountDown) {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void updateBallsLeft(int ballsRemaining, TextView mBallsLeftView){
        if (ballsRemaining == 0){
            ballsRemaining = 8;
        }
        String sBallsRemaing = Integer.toString(ballsRemaining);
        mBallsLeftView.setText(sBallsRemaing);
    }

    public void startTeam1Timer(long millisRemaining){
        team1Timer = new CountDownTimer(millisRemaining, 100){
            @Override
            public void onTick(long millisUntilFinished) {
                team1TimeInMillis = millisUntilFinished;
                updateCountDownText(team1TimeInMillis, team1TimerView);
            }

            @Override
            public void onFinish() {
                team1Running = false;
            }

        }.start();
        team1Running = true;
    }

    public void startTeam2Timer(long millisRemaining){
        team2Timer = new CountDownTimer(millisRemaining, 100){
            @Override
            public void onTick(long millisUntilFinished) {
                team2TimeInMillis = millisUntilFinished;
                updateCountDownText(team2TimeInMillis, team2TimerView);
            }

            @Override
            public void onFinish() {
                team2Running = false;
            }

        }.start();
        team2Running = true;
    }




}
