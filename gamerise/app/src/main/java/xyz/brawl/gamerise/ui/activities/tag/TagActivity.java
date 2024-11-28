package xyz.brawl.gamerise.ui.activities.tag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.data.api.ApiController;
import xyz.brawl.gamerise.ui.activities.main.MainActivity;
import xyz.brawl.gamerise.model.data.tag.Tag;
import xyz.brawl.gamerise.ui.recyclers.tag.TagAdapter;

public class TagActivity extends AppCompatActivity {

    private boolean checked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tag);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText insertTag = findViewById(R.id.insertTag);
        ImageButton searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TagActivity.this, MainActivity.class);

                System.out.println("abbiamo premuto il pulsante");

                //TO-DO controllo dentro editText per permettere di cercare solo a requisiti soddisfatti
                ApiController apiController = new ApiController();
                String userInputTag = String.valueOf(insertTag.getText());

                if (apiController.tagIsPlayer(userInputTag)) {
                    Toast.makeText(TagActivity.this, "TAG VALIDA", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
                else
                    Toast.makeText(TagActivity.this, "TAG NON VALIDA", Toast.LENGTH_SHORT).show();

            }
        });

        //test animation checkbox
        ImageButton checkboxButton = findViewById(R.id.checkbox_button);
        checkboxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checked) {
                    checkboxButton.setImageResource(R.drawable.baseline_check_box_24);
                    checked = true;
                } else {
                    checkboxButton.setImageResource(R.drawable.baseline_check_box_outline_blank_24);
                    checked = false;
                }

            }
        });

        //il recycler view verrà gestito nel ViewModel tramite un Pattern Observer
        //test recycler view
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag("Giocatore1", "Tag1"));
        tags.add(new Tag("Giocatore2", "Tag2"));
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TagAdapter(tags));
    }
}