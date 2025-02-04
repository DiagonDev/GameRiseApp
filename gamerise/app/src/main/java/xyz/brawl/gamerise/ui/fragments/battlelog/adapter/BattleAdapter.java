package xyz.brawl.gamerise.ui.fragments.battlelog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
        // Ottieni il battle corrente
        Battle battle = battles.get(position);

        // Imposta il titolo, sottotitolo e trofei
        holder.title.setText(battle.title);
        holder.subTitle.setText(battle.subTitle);
        holder.trophies.setText(battle.trophies);

        // Imposta le immagini delle icone usando gli ID delle risorse
        holder.gameMode.setImageResource(battle.iconMod);
        holder.iconRanked.setImageResource(battle.iconRanked);
        //holder.iconBackGroundBot.setBackgroundResource(battle.iconBackGroundBot);
        //holder.iconBackgroundTop.setBackgroundResource(battle.iconBackgroundTop);
        holder.iconBackGroundBot.setBackgroundResource(R.color.orange);
        holder.iconBackgroundTop.setBackgroundResource(R.color.green_start);

        // Imposta le immagini per i giocatori
        holder.player1.setImageResource(battle.iconPlayer1);
        holder.player2.setImageResource(battle.iconPlayer2);
        if(battle.iconPlayer2 == Constants.iconStandard){
            holder.player2.setVisibility(View.GONE);
        }
        holder.player3.setImageResource(battle.iconPlayer3);
        if(battle.iconPlayer3 == Constants.iconStandard){
            holder.player3.setVisibility(View.GONE);
        }

        // Imposta eventuali logica di background o altre personalizzazioni
        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(context, "Hai selezionato " + battle.title, Toast.LENGTH_SHORT).show();
        });

    }
    public void updateData(List<Battle> newBattles) {
        this.battles.clear();
        this.battles.addAll(newBattles);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return battles.size();
    }

}
