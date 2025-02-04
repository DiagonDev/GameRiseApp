package xyz.brawl.gamerise.ui.activities.brawlerdetails;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import xyz.brawl.gamerise.R;


public class BrawlerDetailsActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_brawler_details);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.brawlerDetailsActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //int brawlerId = getIntent().getIntExtra("brawlerId", -1);
        int brawlerId=0;
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("brawlerId")) {
            brawlerId = intent.getIntExtra("brawlerId", -1); // -1 è un valore di default nel caso il dato non sia presente

            // Ora puoi usare brawlerId per caricare i dettagli del brawler
            Log.d("BrawlerDetailsActivity", "Brawler ID ricevuto: " + brawlerId);
        }

        Toast.makeText(this, "BrawlerId: " + brawlerId, Toast.LENGTH_SHORT).show();
        LinearLayout previousBrawlerLayout = findViewById(R.id.previous_brawler_layout);
        LinearLayout nextBrawlerLayout = findViewById(R.id.next_brawler_layout);
        previousBrawlerLayout.setOnClickListener(v -> {
            Toast.makeText(this, "Previous Brawler Clicked", Toast.LENGTH_SHORT).show();
        });
        nextBrawlerLayout.setOnClickListener(v -> {
            Toast.makeText(this, "Next Brawler Clicked", Toast.LENGTH_SHORT).show();
        });
    }
}
