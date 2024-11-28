package xyz.brawl.gamerise.ui.recyclers.tag;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.data.tag.Tag;

public class TagAdapter extends RecyclerView.Adapter<TagViewHolder> {
    List<Tag> tags;

    public TagAdapter(List<Tag> tags) {
        this.tags = tags;
    }

    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TagViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TagViewHolder holder, int position) {
        holder.tagTextView.setText(tags.get(position).getTag());
        holder.nomeGiocatoreTextView.setText(tags.get(position).getNomeGiocatore());
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }
}
