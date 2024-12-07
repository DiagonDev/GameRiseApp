package xyz.brawl.gamerise.ui.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.navigation.NavigationBarView;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.ui.activities.tag.TagActivity;
import xyz.brawl.gamerise.ui.fragments.battlelog.BattleLogFragment;
import xyz.brawl.gamerise.ui.fragments.brawlers.BrawlersFragment;
import xyz.brawl.gamerise.ui.fragments.ranked.RankedFragment;
import xyz.brawl.gamerise.ui.fragments.stats.StatsFragment;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button nomeGiocatoreButton = findViewById(R.id.nome_giocatore_button);
        nomeGiocatoreButton.setOnClickListener(v -> {
            Intent i = new Intent(this, TagActivity.class);
            startActivity(i);
        });
        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigation);

        navigationBarView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.item_1) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_view, new BattleLogFragment())
                        .commit();
                return true;
            } else if (item.getItemId() == R.id.item_2) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_view, new BrawlersFragment())
                        .commit();
                return true;
            } else if (item.getItemId() == R.id.item_3) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_view, new RankedFragment())
                        .commit();
                return true;
            } else if (item.getItemId() == R.id.item_4) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_view, new StatsFragment())
                        .commit();
                return true;
            }
            return false;
        });
    }
}
