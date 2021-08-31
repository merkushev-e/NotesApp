package ru.gb.notesapp.Data;

import android.os.Parcel;
import android.os.Parcelable;

public class CardData implements Parcelable {

    private String id;

    private String notes;
    private String text;

    public CardData(){
    }

    public CardData(String notes, String text) {
        this.notes = notes;
        this.text = text;
    }


    protected CardData(Parcel in) {
        id = in.readString();
        notes = in.readString();
        text = in.readString();
    }

    public static final Creator<CardData> CREATOR = new Creator<CardData>() {
        @Override
        public CardData createFromParcel(Parcel in) {
            return new CardData(in);
        }

        @Override
        public CardData[] newArray(int size) {
            return new CardData[size];
        }
    };

    public String getNotes() {
        return notes;
    }

    public String getText(){
        return text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(notes);
        dest.writeString(text);
    }
}


