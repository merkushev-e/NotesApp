package ru.gb.notesapp.Data;

public interface CardSource {
    CardSource init(CardsSourceResponse cardsSourceResponse);
    CardData getCard(int position);
    CardData getCardContent(int position);
    int size();
    void deleteCardData(int position);
    void addCardData(CardData cardData);
    void addCardContentData(CardData cardData);
    void clearCardData();
}

