package ru.gb.notesapp.Data;

public interface CardSource {
    CardData getCard(int position);
    CardData getCardContent(int position);
    int size();
    void deleteCardData(int position);
    void updateCardData(int position, CardData cardData);
    void addCardData(CardData cardData);
    void addCardContentData(CardData cardData);
    void clearCardData();
}

