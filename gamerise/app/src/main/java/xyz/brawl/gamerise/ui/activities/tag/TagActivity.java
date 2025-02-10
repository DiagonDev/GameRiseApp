package xyz.brawl.gamerise.ui.activities.tag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.singleton.GameAccountSingleton;
import xyz.brawl.gamerise.model.data.tag.Tag;
import xyz.brawl.gamerise.model.repository.player.PlayerRepository;
import xyz.brawl.gamerise.model.repository.tag.TagRepository;
import xyz.brawl.gamerise.ui.activities.main.MainActivity;
import xyz.brawl.gamerise.ui.viewmodels.tag.TagViewModel;
import xyz.brawl.gamerise.ui.viewmodels.tag.TagViewModelFactory;
import xyz.brawl.gamerise.util.NetworkUtil;
import xyz.brawl.gamerise.util.ServiceLocator;

/// Se guardate il logCat vedrete generarsi un warnining al crearsi di questa classe
/// è dovuto al fatto che non avendo item nel recycler view, l'inflate non riesce a trovare
/// il colore da applicare. Non è un problema bloccante e si risolve appena popoliamo il recycler
public class TagActivity extends AppCompatActivity {

    private boolean checked = false;
    private TagViewModel tagViewModel;
    private EditText insertTag;
    private ImageButton checkboxButton;
    private Button googleButton;
    private TextView savedTagTextView;
    private FrameLayout noInternetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tag);

        insertTag = findViewById(R.id.insertTag);
        savedTagTextView = findViewById(R.id.savedTagTextView);
        noInternetView = findViewById(R.id.no_internet_view);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TagRepository tagRepository =
                ServiceLocator.getInstance().getTagRepository(getApplication(),
                        getApplication().getResources().getBoolean(R.bool.debug_mode));
        PlayerRepository playerRepository =
                ServiceLocator.getInstance().getPlayerRepository(getApplication(),
                        getApplication().getResources().getBoolean(R.bool.debug_mode));


        tagViewModel = new ViewModelProvider(
                this,
                new TagViewModelFactory(tagRepository, playerRepository)).get(TagViewModel.class);

        String lastUpdate = "0";
        if(!NetworkUtil.isInternetAvailable(this)){
            noInternetView.setVisibility(View.VISIBLE);

            lastUpdate = System.currentTimeMillis() + "";
        }

        tagViewModel.getSavedTag().observe(this, result -> {
            if (result.isSuccess()) {
                Tag savedTag = (Tag) ((Result.Success) result).getData();
                if (savedTag != null)
                    savedTagTextView.setText(savedTag.getTag());
            }
        });

        savedTagTextView.setOnClickListener(view -> {
            if (handlerInvioTagSalvato()) {
                Intent intent = new Intent(TagActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

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
                if (handlerInvioTag()) {
                    Intent intent = new Intent(TagActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button googleButton = findViewById(R.id.btnGoogleCustom);
        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: leo metti qui la logica del bottone google
            }
        });

        //test animation checkbox
        checkboxButton = findViewById(R.id.checkbox_button);
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
        /*RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TagAdapter tagAdapter = new TagAdapter(new ArrayList<>());
        recyclerView.setAdapter(tagAdapter);*/

        // Osserva i cambiamenti nei tag
        /*tagViewModel.getTags().observe(this, updatedTags -> {
            if (updatedTags != null && tagAdapter != null) {
                tagAdapter.updateTags(updatedTags); // Aggiungi o aggiorna i tag
            }
        });*/

        // Carica i primi 3 tag dal database
        /*tagViewModel.loadRecentTags();*/


        /////////////////////////////////////
        //TODO: da controllare
        /*BrawlerRepository br = new BrawlerRepository(this.getApplication(), this);
        br.fetchBrawlerList();
        br.fetchBrawler(16000000);*/
    }

    /**
     * Gestisce l'invio del tag tramite il ViewModel.
     * Richiede al tagViewModel di aggiornare la lista di tag se il tag è valido.
     * Richiede al tagViewModel di effettuare la Query all'API se il tag non è presente nel database.
     * Richiede al tagViewModel
     *
     * @return true se il tag è valido, false altrimenti.
     */

    public boolean handlerInvioTag() {
        String inputTag = insertTag.getText().toString().trim();
        GameAccountSingleton.getInstance().setUserTag(inputTag);
        GameAccountSingleton.getInstance().setChecked(checked);
        if (tagViewModel.isTagValid(inputTag)) {
            if (checked) {
                Tag newTag = new Tag("Nuovo Giocatore", inputTag);
                tagViewModel.addTag(newTag);// Salva il tag sia nella memoria che nel database2
            }
            insertTag.setText(""); // Resetta il campo di testo
            Toast.makeText(TagActivity.this, "Tag aggiunto!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(TagActivity.this, "Tag non valido!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean handlerInvioTagSalvato() {
        String inputTag = savedTagTextView.getText().toString().trim();
        GameAccountSingleton.getInstance().setUserTag(inputTag);
        GameAccountSingleton.getInstance().setChecked(true);
        return true;
    }
}