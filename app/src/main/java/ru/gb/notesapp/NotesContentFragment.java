package ru.gb.notesapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import ru.gb.notesapp.Data.CardData;
import ru.gb.notesapp.Data.CardSource;
import ru.gb.notesapp.Data.CardsSourceFirebaseImpl;
import ru.gb.notesapp.Data.CardsSourceImpl;


public class NotesContentFragment extends Fragment {

    public static final String ARG_INDEX = "index";
    CardData cardData;
    CardData cardDataContent;
    CardsSourceImpl dataSource;
    CardsSourceImpl cardsSource;


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
        setReenterTransition(true);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_notes_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTextView(view);
    }

    private void initTextView(View view) {
//        cardsSource = CardsSourceImpl.getInstance(getResources());
//        cardsSource = new CardsSourceImpl(getResources());
        CardSource cardsSource = CardsSourceFirebaseImpl.getInstance();
//        dataSource = cardsSource.getData();
        cardData = cardsSource.getCard(index);
        cardDataContent = cardsSource.getCardContent(index);

        EditText editText = view.findViewById(R.id.notes_content);
//        String[] notes = getResources().getStringArray(R.array.notes_content);
        editText.setText(cardDataContent.getNotes());

        EditText editTextHeadLine = view.findViewById(R.id.notes_headline);
//        String[] notesHeadlines = getResources().getStringArray(R.array.notes);
//        editTextHeadLine.setText(notesHeadlines[index]);
        editTextHeadLine.setText(cardData.getNotes());



    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
         inflater.inflate(R.menu.notes_content_fragment,menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull  MenuItem item) {
        if(item.getItemId() == R.id.delete_note){
            Toast.makeText(getContext(),"This note has been deleted", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}