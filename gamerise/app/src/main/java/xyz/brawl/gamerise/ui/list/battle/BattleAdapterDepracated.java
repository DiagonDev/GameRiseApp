package xyz.brawl.gamerise.ui.list.battle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.data.battle.Battle;

public class BattleAdapterDepracated extends ArrayAdapter<Battle> {

    private int layout;
    private List<Battle> battles;

    public BattleAdapterDepracated(@NonNull Context context, int layout, @NonNull List<Battle> battles) {
        super(context, layout, battles);
        this.layout = layout;
        this.battles = battles;
    }

    /**
     * TODO: guardare chatGtp per implementazione con ViewHolder
     * questa è implementazione del prof
     *
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        ImageView gameMode = convertView.findViewById(R.id.game_mode_imageView);
        gameMode.setImageDrawable(battles.get(position).iconMod);
        return convertView;
    }
}