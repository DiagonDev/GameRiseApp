package xyz.brawl.gamerise.ui.recyclers.tag;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import xyz.brawl.gamerise.R;

public class TagViewHolder extends RecyclerView.ViewHolder {

    TextView nomeGiocatoreTextView;
    TextView tagTextView;

    public TagViewHolder(@NonNull View itemView) {
        super(itemView);
        nomeGiocatoreTextView = itemView.findViewById(R.id.nomeGiocatoreTextView);
        tagTextView = itemView.findViewById(R.id.tagTextView);
    }

}
