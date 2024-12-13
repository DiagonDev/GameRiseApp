package xyz.brawl.gamerise.ui.activities.tag;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import xyz.brawl.gamerise.R;

import xyz.brawl.gamerise.model.data.brawler.Brawler;
import xyz.brawl.gamerise.model.data.brawler.BrawlerV2;
import xyz.brawl.gamerise.model.data.brawler.Gadget;
import xyz.brawl.gamerise.model.data.brawler.StarPower;
import xyz.brawl.gamerise.model.data.datasource.ApiService;
import xyz.brawl.gamerise.model.data.datasource.brawler.BrawlerRepository;
import xyz.brawl.gamerise.model.data.datasource.brawler.ItemsResponse;
import xyz.brawl.gamerise.ui.activities.main.MainActivity;
import xyz.brawl.gamerise.model.data.tag.Tag;
import xyz.brawl.gamerise.ui.activities.tag.adapter.TagAdapter;
import xyz.brawl.gamerise.ui.viewmodels.tag.TagViewModel;

public class TagActivity extends AppCompatActivity {

    private boolean checked = false;
    private TagViewModel tagViewModel;
    private EditText insertTag;

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

        tagViewModel = new ViewModelProvider(this).get(TagViewModel.class);
        insertTag = findViewById(R.id.insertTag);
        insertTag.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (handlerInvioTag()) {
                    Intent intent = new Intent(TagActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
            return false;
        });


        ImageButton searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Instantiate the repository
                BrawlerRepository brawlerRepository = new BrawlerRepository();

                try {
                    System.out.println("1");
                    CompletableFuture<ItemsResponse> futureResponse = brawlerRepository.getBrawlers();
                    System.out.println("2");
                    // Block and get the ItemResponse using join()
                    ItemsResponse lista_finale = futureResponse.join();
                    System.out.println("3");

                    // Print the items (assuming ItemResponse has this structure)
                    for (BrawlerV2 item : lista_finale.getItems()) {
                        System.out.println(item.getName());
                    }

                    System.out.println("finito");
                } finally {
                    // Shutdown the OkHttpClient after use
                    brawlerRepository.shutdownClient();
                }
                if (/*handlerInvioTag()*/false) {
                    Intent intent = new Intent(TagActivity.this, MainActivity.class);
                    startActivity(intent);
                }
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

        /// La visualizzazione della lista dei tag potrebbe dare problemi
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TagAdapter(new ArrayList<>()));

        // Osserva i cambiamenti nei tag
        tagViewModel.getTags().observe(this, updatedTags -> {
            TagAdapter adapter = (TagAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                if (updatedTags.size() > adapter.getItemCount()) {
                    // Aggiunto un nuovo tag
                    Tag newTag = updatedTags.get(updatedTags.size() - 1);
                    adapter.addTag(newTag);
                } else {
                    // Aggiornamento globale della lista
                    adapter.updateTags(updatedTags);
                }
            }
        });
    }

    /**
     * Gestisce l'invio del tag tramite il ViewModel.
     * Richiede al tagViewModel di aggiornare la lista di tag se il tag è valido.
     *
     * @return true se il tag è valido, false altrimenti.
     */
    public boolean handlerInvioTag() {
        String inputTag = insertTag.getText().toString().trim();
        if (tagViewModel.isTagValid(inputTag)) {
            if (checked)
                tagViewModel.addTag(new Tag("Nuovo Giocatore", inputTag)); // Aggiungi il tag tramite il ViewModel se l'utente vuole salvarlo
            insertTag.setText(""); // Resetta il campo di testo
            Toast.makeText(TagActivity.this, "Tag aggiunto!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(TagActivity.this, "Tag non valido!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}