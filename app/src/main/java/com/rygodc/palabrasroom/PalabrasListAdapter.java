package com.rygodc.palabrasroom;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class PalabrasListAdapter extends ListAdapter<Palabra, PalabrasViewHolder> {

    public PalabrasListAdapter(@NonNull DiffUtil.ItemCallback<Palabra> diffCallback) {
        super(diffCallback);
    }

    @Override
    public PalabrasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PalabrasViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(PalabrasViewHolder holder, int position) {
        Palabra current = getItem(position);
        holder.bind(current.getPalabra());
    }

    static class WordDiff extends DiffUtil.ItemCallback<Palabra> {

        @Override
        public boolean areItemsTheSame(@NonNull Palabra oldItem, @NonNull Palabra newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Palabra oldItem, @NonNull Palabra newItem) {
            return oldItem.getPalabra().equals(newItem.getPalabra());
        }
    }
}