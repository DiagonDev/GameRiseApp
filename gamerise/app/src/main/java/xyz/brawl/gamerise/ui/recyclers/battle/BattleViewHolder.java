package xyz.brawl.gamerise.ui.recyclers.battle;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.ui.activities.tag.TagActivity;


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
        //Provvisorio
        itemView.setOnClickListener(view -> {
            Toast.makeText(itemView.getContext(), "SI PUO' FARE", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(itemView.getContext(), TagActivity.class);
            startActivity(itemView.getContext(), i, null);
        });
    }
}
