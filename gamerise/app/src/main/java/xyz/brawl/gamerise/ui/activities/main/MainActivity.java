package xyz.brawl.gamerise.ui.activities.main;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.navigation.NavigationBarView;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.ui.fragments.battlelog.BattleLogFragment;

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
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_view, new BattleLogFragment())
                .commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigation);

        navigationBarView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.item_1) {
                // Rispondi al clic sul primo elemento
                return true;
            } else if (item.getItemId() == R.id.item_2) {
                // Rispondi al clic sul secondo elemento
                return true;
            } else if (item.getItemId() == R.id.item_3) {
                // Rispondi al clic sul terzo elemento
                return true;
            }
            return false;
        });
    }
}
