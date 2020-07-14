package com.beigerooster.testapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import static com.beigerooster.testapp.MainActivity.team1;
import static com.beigerooster.testapp.MainActivity.team2;

public class FirstFragment extends Fragment {


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText editText1 = (EditText)view.findViewById(R.id.editText);
        final EditText editText2 = (EditText)view.findViewById(R.id.editText2);
        final EditText timeText = (EditText)view.findViewById(R.id. timeTotal);
        final EditText extraTimeText = (EditText)view.findViewById(R.id. extraTime);


        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                team1.setName(editText1.getText());
                team2.setName(editText2.getText());

                team1.setTime(Integer.parseInt(timeText.getText().toString()));
                team2.setTime(Integer.parseInt(timeText.getText().toString()));

                team1.setExtraBallTime(Integer.parseInt(extraTimeText.getText().toString()));
                team2.setExtraBallTime(Integer.parseInt(extraTimeText.getText().toString()));
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
}
