package ru.gb.notesapp.Data;

import android.content.res.Resources;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CardsSourceFirebaseImpl implements CardSource{

    private static final String CARDS_COLLECTION = "NOTES";
    private static final String TAG = "[CardsSourceFirebaseImpl]";
    private static CardsSourceFirebaseImpl instance;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collection = db.collection(CARDS_COLLECTION);

    private List<CardData> cardsData = new ArrayList<>();
//    private List<CardData> cardsContentData = new ArrayList<>();


    private CardsSourceFirebaseImpl() {

    }

    public static CardsSourceFirebaseImpl getInstance() {
        if (instance == null) {
            instance = new CardsSourceFirebaseImpl();
        }
        return instance;
    }

    @Override
    public CardSource init(CardsSourceResponse cardsSourceResponse) {
        collection.get()
                .addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                cardsData = new ArrayList<>();
//                cardsContentData = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    CardData data = document.toObject(CardData.class);
                    data.setId(document.getId());
                    cardsData.add(data);
//                    cardsContentData.add(data);
                }


                cardsSourceResponse.initialized(this);
                Log.d(TAG, "succeses" + cardsData.size());
            } else {
                Log.e(TAG,"failed", task.getException());
            }

        })
                .addOnFailureListener(e -> {
                    Log.e(TAG,"failed",e);
                });
        return null;
    }

    @Override
    public CardData getCard(int position) {
        return cardsData.get(position);
    }
//
//    @Override
//    public CardData getCardContent(int position) {
//        return cardsContentData.get(position);
//    }

    @Override
    public int size() {
        if (cardsData == null){
            return 0;
        }
        return cardsData.size();
    }

    @Override
    public void deleteCardData(int position) {

        collection.document(cardsData.get(position).getId()).delete();
        cardsData.remove(position);

    }

    @Override
    public void addCardData(CardData cardData) {

        cardsData.add(cardData);
        collection.document(cardData.getId()).set(cardData);
//        collection.add(cardData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                cardData.setId(documentReference.getId());
//            }
//        });


    }

    @Override
    public void updateCardData(CardData cardData, int position) {
        collection.document(cardData.getId()).set(cardData);
        cardsData.set(position,cardData);


    }


    @Override
    public void clearCardData() {
        for (CardData cardsData : cardsData) {
            collection.document(cardsData.getId()).delete();
        }
        
        cardsData.clear();
    }
}
