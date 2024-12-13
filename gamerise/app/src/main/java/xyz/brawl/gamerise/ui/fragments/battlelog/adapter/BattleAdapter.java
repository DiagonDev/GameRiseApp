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
import xyz.brawl.gamerise.model.repository.BattleLogRepository;
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

        ///Provvisorio: Se gamemode == KNOCKOUT e mappa Stormy Plains in non ranked
        holder.gameMode.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.game_mode_knockout));
        holder.title.setText(Constants.knockout);
        holder.subTitle.setText(Constants.StormyPlains);
        holder.iconBackgroundTop.setBackgroundColor(ContextCompat.getColor(context, R.color.orange));
        holder.trophies.setText("+8");
        holder.iconRanked.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.bronze_belt));
        holder.iconBackGroundBot.setBackground(ContextCompat.getDrawable(context, R.drawable.stormy_plains_background));
        holder.player1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable._bit_pin));
        holder.player2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable._bit_pin));
        holder.player3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable._bit_pin));
        //TODO: inserire altri elementi
    }

    @Override
    public int getItemCount() {
        return battles.size();
    }

}
