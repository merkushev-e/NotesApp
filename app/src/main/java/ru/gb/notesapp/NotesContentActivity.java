package ru.gb.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class NotesContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_content);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        if (savedInstanceState == null) {
            NotesContentFragment fragment = new NotesContentFragment();
            fragment.setArguments(getIntent().getExtras());


            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.notes_content_container, fragment)
                    .commit();
        }
    }
}