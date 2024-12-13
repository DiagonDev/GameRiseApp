package xyz.brawl.gamerise.ui.fragments.battlelog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.data.battle.Battle;
import xyz.brawl.gamerise.util.Constants;

public class BattleAdapter extends RecyclerView.Adapter<BattleViewHolder> {
    List<Battle> battles;
    Context context;

    public BattleAdapter(List<Battle> battles, Context context) {
        this.battles = battles;
        this.context = context;
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

    }

    @Override
    public int getItemCount() {
        return battles.size();
    }

}
