package ru.gb.notesapp;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class NotesContentFragment extends Fragment {

    public static final String ARG_INDEX = "index";
    public static final int DEF_VALUE = 0;

    private int index;


    public NotesContentFragment() {
        // Required empty public constructor
    }


    public static NotesContentFragment newInstance(int index) {
        NotesContentFragment fragment = new NotesContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTextView(view);
    }

    private void initTextView(View view) {
        TextView textview = view.findViewById(R.id.notes_content);

        String[] notes = getResources().getStringArray(R.array.notes_content);
        textview.setText(notes[index]);

    }
}