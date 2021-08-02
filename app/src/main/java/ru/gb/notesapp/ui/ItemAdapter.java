package ru.gb.notesapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import ru.gb.notesapp.NotesFragment;
import ru.gb.notesapp.R;

public class ItemAdapter extends RecyclerView.Adapter <ItemAdapter.ItemViewHolder> {

    private String[] dataSource;
    private OnItemClickListener listener;
    private OnItemLongClickListener longClickListener;

    public void setLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public ItemAdapter(String[] dataSource) {
        this.dataSource = dataSource;
    }

    public void setListener(@Nullable OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  ItemAdapter.ItemViewHolder holder, int position) {
        holder.getTextView().setText(dataSource[position]);
    }

    @Override
    public int getItemCount() {
        return dataSource.length;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public ItemViewHolder(@NonNull  View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            textView.setOnClickListener(v -> {
                listener.onItemClick(getAdapterPosition());
            });
            textView.setOnLongClickListener(v -> {
                longClickListener.onItemLongClick(getAdapterPosition());
                return true;
            });
        }

        public TextView getTextView() {
            return textView;
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(int position);
    }

}
