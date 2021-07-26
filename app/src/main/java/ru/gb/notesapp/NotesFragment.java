package ru.gb.notesapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class NotesFragment extends Fragment {

    public NotesFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initList(view);
    }

    private void initList(View view) {
        LinearLayout linearLayout = view.findViewById(R.id.notes_container);
        String[] notes = getResources().getStringArray(R.array.notes);
        for (int i = 0; i < notes.length; i++) {
            TextView textView = new TextView(getContext());
            textView.setText(notes[i]);
            textView.setTextSize(30);
            textView.setPadding(20,5,20,5);
            final int finalIndex = i;
                textView.setOnClickListener(v -> {
                    showNotesContent(finalIndex);
                });

            linearLayout.addView(textView);

        }
    }

    private void showNotesContent(int finalIndex) {
        Intent intent = new Intent(getActivity(),NotesContentActivity.class);
        intent.putExtra(NotesContentFragment.ARG_INDEX,finalIndex);
        startActivity(intent);

    }
}