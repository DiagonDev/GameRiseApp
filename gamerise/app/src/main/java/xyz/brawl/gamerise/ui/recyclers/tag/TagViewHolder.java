package xyz.brawl.gamerise.ui.recyclers.tag;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import xyz.brawl.gamerise.R;

public class TagViewHolder extends RecyclerView.ViewHolder {

    TextView nomeGiocatoreTextView;
    TextView tagTextView;
    Button searchSaved;

    public TagViewHolder(@NonNull View itemView) {
        super(itemView);
        nomeGiocatoreTextView = itemView.findViewById(R.id.nomeGiocatoreTextView);
        tagTextView = itemView.findViewById(R.id.tagTextView);
        searchSaved = itemView.findViewById(R.id.search_saved_button);
        searchSaved.setOnClickListener(view -> {
            // Do something
            Toast.makeText(itemView.getContext(),  nomeGiocatoreTextView.getText().toString().trim(), Toast.LENGTH_SHORT).show();
        });
        //test searchSaved
        searchSaved.setOnLongClickListener(view -> {
            Toast.makeText(itemView.getContext(), "TO-DO animation", Toast.LENGTH_SHORT).show();
            return true;
        });
    }

}
