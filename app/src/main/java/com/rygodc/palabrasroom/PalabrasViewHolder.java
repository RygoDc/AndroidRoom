package com.rygodc.palabrasroom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PalabrasViewHolder extends RecyclerView.ViewHolder {
    private final TextView wordItemView;
    public final ImageButton deleteButton;

    private PalabrasViewHolder(View itemView) {
        super(itemView);
        wordItemView = itemView.findViewById(R.id.textView);
        deleteButton = itemView.findViewById(R.id.deleteButton);
    }

    public void bind(String text) {
        wordItemView.setText(text);
    }

    static PalabrasViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new PalabrasViewHolder(view);
    }
}