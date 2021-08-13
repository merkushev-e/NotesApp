package ru.gb.notesapp.Data;

public class CardData {

    private String id;

    private String notes;

    public CardData(){
    }

    public CardData(String notes) {
        this.notes = notes;
    }


    public String getNotes() {
        return notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


