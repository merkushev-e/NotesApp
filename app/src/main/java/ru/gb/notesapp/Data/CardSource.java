package ru.gb.notesapp.Data;

public interface CardSource {
    CardSource init(CardsSourceResponse cardsSourceResponse);
    CardData getCard(int position);
    int size();
    void deleteCardData(int position);
    void addCardData(CardData cardData);
    void updateCardData(CardData cardData, int position);

    void clearCardData();
}

