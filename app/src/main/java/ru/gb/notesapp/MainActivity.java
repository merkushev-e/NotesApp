package ru.gb.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {

     getSupportFragmentManager().popBackStack();
     super.onBackPressed();
    }
}