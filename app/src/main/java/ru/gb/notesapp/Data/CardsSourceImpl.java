package ru.gb.notesapp.Data;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import ru.gb.notesapp.R;

public class CardsSourceImpl implements CardSource {

    private List<Card> dataSource;
    private Resources resources;

    public CardsSourceImpl(Resources resources) {
        dataSource = new ArrayList<>(7);
        this.resources = resources;
    }

    public CardsSourceImpl init() {
        String[] notes = resources.getStringArray(R.array.notes);

        for (int i = 0; i < notes.length; i++) {
            dataSource.add(new Card(notes[i]));
        }
        return this;
    }

    public Card getCard(int position) {
        return dataSource.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }


}
