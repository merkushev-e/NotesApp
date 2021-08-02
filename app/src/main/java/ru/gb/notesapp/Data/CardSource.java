package ru.gb.notesapp.Data;

public interface CardSource {
    Card getCard(int position);
    int size();
}

