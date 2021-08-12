package ru.gb.notesapp;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import ru.gb.notesapp.Data.CardData;
import ru.gb.notesapp.Data.CardSource;
import ru.gb.notesapp.Data.CardsSourceImpl;
import ru.gb.notesapp.ui.ItemAdapter;


public class NotesFragment extends Fragment {

    public static final int DEFAULT_INDEX = 0;
    public static final String NOTES_FRAGMENT = "NotesFragment";
    public static final String NOTES_CONTENT = "Notes_Content";
    private boolean isLand = false;
    public static int currentPosition;

    private CardSource data;
    private ItemAdapter adapter;
    private RecyclerView recyclerView;



    public NotesFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycleview, container, false);
        initList(view);
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(NOTES_FRAGMENT);
        fragmentTransaction.commit();

        isLand = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLand) {
            showNotesContentLand(DEFAULT_INDEX);
        }


    }

    private void initList(View view) {

        recyclerView = view.findViewById(R.id.recycler_view_lines);
//        CardsSourceImpl cardsSource = new CardsSourceImpl(getResources());
        CardsSourceImpl cardsSource = CardsSourceImpl.getInstance(getResources());
        data = cardsSource.getData() ;

        setHasOptionsMenu(true);

        adapter = new ItemAdapter(data,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator,null));
        recyclerView.addItemDecoration(itemDecoration);

        adapter.setListener(position -> {
            showNotesContent(position);
//                updateText(position);
        });
        adapter.setLongClickListener(position -> {});

    }

    @Override
    public void onCreateOptionsMenu(@NonNull  Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull  MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_note:
                data.addCardData(new CardData("Simple notes"));
                data.addCardContentData(new CardData("Simple notes"));
                adapter.notifyItemInserted(data.size()-1);
                recyclerView.scrollToPosition(data.size()-1);
                return true;
            case R.id.delete_all:
                data.clearCardData();
                adapter.notifyDataSetChanged();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(@NonNull  ContextMenu menu, @NonNull  View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item1_context_menu) {
            currentPosition = adapter.getCurrentPosition();
            data.deleteCardData(currentPosition);
            adapter.notifyItemRemoved(currentPosition);
            Toast.makeText(getContext(), "Note has been deleted" + currentPosition, Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onContextItemSelected(item);
    }

//    public void initPopupMenu() {
//        Activity activity = requireActivity();
//        PopupMenu popupMenu = new PopupMenu(activity, getView());
//        activity.getMenuInflater().inflate(R.menu.context_menu, popupMenu.getMenu());
//        popupMenu.setOnMenuItemClickListener(item -> {
//            if (item.getItemId() == R.id.item1_popup) {
//                Toast.makeText(getContext(), "Note has been deleted", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//            return false;
//        });
//        popupMenu.show();
//
//    }





    private void showNotesContent(int finalIndex) {
        if (isLand) {
            showNotesContentLand(finalIndex);
        } else {
            showNotesContentPort(finalIndex);
        }
    }


    private void showNotesContentPort(int index) {

        NotesContentFragment fragment = NotesContentFragment.newInstance(index);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.notes_fragments_container,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();

    }

    private void showNotesContentLand(int index) {
        NotesContentFragment fragment = NotesContentFragment.newInstance(index);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.notes_content_fragments, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }


    void updateText(int index) {
        LinearLayout linearLayout = (LinearLayout) getView();
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i);
            textView.setBackgroundColor(Color.WHITE);
        }
        ((TextView) linearLayout.getChildAt(index)).setBackgroundColor(getResources().getColor(R.color.secondaryLightColor));
    }


}