package xyz.brawl.gamerise.adapters.battlelog;

import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import xyz.brawl.gamerise.R;


public class BattleViewHolder extends RecyclerView.ViewHolder {
    ImageView gameMode;
    TextView title;
    TextView subTitle;
    TextView trophies;
    TableRow iconBackGroundBot;
    TableRow iconBackgroundTop;
    ImageView iconRanked;
    ImageView player1;
    ImageView player2;
    ImageView player3;

    public BattleViewHolder(@NonNull View itemView) {
        super(itemView);
        gameMode = itemView.findViewById(R.id.game_mode_imageView);
        title = itemView.findViewById(R.id.title_textView);
        subTitle = itemView.findViewById(R.id.subTitle_textView);
        trophies = itemView.findViewById(R.id.trophies_textView);
        iconBackgroundTop = itemView.findViewById(R.id.riga1);
        iconBackGroundBot = itemView.findViewById(R.id.riga2);
        iconRanked = itemView.findViewById(R.id.icon_ranked);
        player1 = itemView.findViewById(R.id.player1_image);
        player2 = itemView.findViewById(R.id.player2_image);
        player3 = itemView.findViewById(R.id.player3_image);
    }
}
