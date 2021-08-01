package ru.gb.notesapp;

import android.app.FragmentManagerNonConfig;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class NotesFragment extends Fragment {

    public static final int DEFAULT_INDEX = 0;
    public static final String NOTES_FRAGMENT = "NotesFragment";
    public static final String NOTES_CONTENT = "Notes_Content";
    private boolean isLand = false;

    public NotesFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(NOTES_FRAGMENT);
        fragmentTransaction.commit();

        isLand = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLand) {
            showNotesContentLand(DEFAULT_INDEX);
        }

        initList(view);
    }

    private void initList(View view) {
        LinearLayout linearLayout = view.findViewById(R.id.notes_container);
        String[] notes = getResources().getStringArray(R.array.notes);
        for (int i = 0; i < notes.length; i++) {
            TextView textView = new TextView(getContext());
            textView.setText(notes[i]);
            textView.setTextSize(30);
            textView.setPadding(20, 5, 20, 5);
            final int finalIndex = i;
            textView.setOnClickListener(v -> {
                showNotesContent(finalIndex);
                updateText(finalIndex);
            });

            linearLayout.addView(textView);

        }
    }

    private void showNotesContent(int finalIndex) {
        if (isLand) {
            showNotesContentLand(finalIndex);
        } else {
            showNotesContentPort(finalIndex);
        }
    }

    private void showNotesContentPort(int index) {


        NotesContentFragment fragment = NotesContentFragment.newInstance(index);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.notes_fragments_container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
//        Intent intent = new Intent(getActivity(), NotesContentActivity.class);
//        intent.putExtra(NotesContentFragment.ARG_INDEX, finalIndex);
//        startActivity(intent);

    }

    private void showNotesContentLand(int index) {
        NotesContentFragment fragment = NotesContentFragment.newInstance(index);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.notes_content_fragments, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }


    void updateText(int index) {
        LinearLayout linearLayout = (LinearLayout) getView();
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i);
            textView.setBackgroundColor(Color.WHITE);
        }
        ((TextView) linearLayout.getChildAt(index)).setBackgroundColor(getResources().getColor(R.color.secondaryLightColor));
    }



}