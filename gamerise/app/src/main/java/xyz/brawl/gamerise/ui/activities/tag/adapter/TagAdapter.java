package xyz.brawl.gamerise.ui.activities.tag.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.data.tag.Tag;

public class TagAdapter extends RecyclerView.Adapter<TagViewHolder> {
    private final List<Tag> recentTags = new ArrayList<>();  // Tag dal database
    private final List<Tag> runtimeTags = new ArrayList<>(); // Tag aggiunti durante l'esecuzione

    public TagAdapter(List<Tag> initialTags) {
        if (initialTags != null) {
            this.recentTags.addAll(initialTags);
        }
    }

    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TagViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TagViewHolder holder, int position) {
        //combina le due liste per ottenere la lista finale di dtag da visualzzare
        Tag tag = getAllTags().get(position);
        holder.tagTextView.setText(tag.getTag());
        holder.nomeGiocatoreTextView.setText(tag.getNomeGiocatore());
    }

    @Override
    public int getItemCount() {
        // Combina recentTags e runtimeTags
        return getAllTags().size();
    }

    public void updateTags(List<Tag> updatedTags) {
        this.recentTags.clear();
        this.runtimeTags.clear();
        this.recentTags.addAll(updatedTags);
        notifyDataSetChanged();
    }

    public void addTag(Tag newTag) {
        this.recentTags.add(0, newTag); // Aggiungi in cima ai tag recenti
        notifyItemInserted(0); // Notifica il cambiamento
    }

    // Metodo per ottenere la lista unificata di tag
    private List<Tag> getAllTags() {
        List<Tag> allTags = new ArrayList<>();
        allTags.addAll(recentTags);  // Aggiungi i tag dal database
        allTags.addAll(runtimeTags); // Aggiungi i tag runtime
        return allTags;
    }
}
