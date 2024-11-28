package xyz.brawl.gamerise.ui.recyclers.battle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.data.battle.Battle;

public class BattleAdapter extends RecyclerView.Adapter<BattleViewHolder> {
    List<Battle> battles;

    public BattleAdapter(List<Battle> battles) {
        this.battles = battles;
    }

    @NonNull
    @Override
    public BattleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_recycler_battlelog, parent, false);
        return new BattleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BattleViewHolder holder, int position) {
        holder.title.setText("TEST");
        //TODO: inserire altri elementi
    }

    @Override
    public int getItemCount() {
        return battles.size();
    }

}
