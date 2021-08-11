package ru.gb.notesapp.Data;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import ru.gb.notesapp.R;

public class CardsSourceImpl implements CardSource {

    private List<CardData> dataSource;
    private List<CardData> dataSourceContent;
    private Resources resources;

    public CardsSourceImpl(Resources resources) {
        dataSource = new ArrayList<>(10);
        dataSourceContent = new ArrayList<>(10);
        this.resources = resources;
    }

    public CardsSourceImpl init() {
        String[] notes = resources.getStringArray(R.array.notes);
        String[] notesContent = resources.getStringArray(R.array.notes_content);
        for (int i = 0; i < notes.length; i++) {
            dataSource.add(new CardData(notes[i]));
            dataSourceContent.add(new CardData(notesContent[i]));
        }
        return this;
    }

    public CardData getCard(int position) {
        return dataSource.get(position);
    }

    @Override
    public CardData getCardContent(int position) {
        return dataSourceContent.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public void deleteCardData(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateCardData(int position, CardData cardData) {
        dataSource.set(position, cardData);
    }

    @Override
    public void addCardData(CardData cardData) {
        dataSource.add(cardData);
    }

    @Override
    public void addCardContentData(CardData cardData) {
        dataSourceContent.add(cardData);
    }

    @Override
    public void clearCardData() {
        dataSource.clear();
    }


}
