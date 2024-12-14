package xyz.brawl.gamerise.ui.activities.tag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import xyz.brawl.gamerise.R;

import xyz.brawl.gamerise.model.data.singleton.GameAccountSingleton;
import xyz.brawl.gamerise.ui.activities.main.MainActivity;
import xyz.brawl.gamerise.model.data.tag.Tag;
import xyz.brawl.gamerise.ui.activities.tag.adapter.TagAdapter;
import xyz.brawl.gamerise.ui.viewmodels.tag.TagViewModel;

/// Se guardate il logCat vedrete generarsi un warnining al crearsi di questa classe
/// è dovuto al fatto che non avendo item nel recycler view, l'inflate non riesce a trovare
/// il colore da applicare. Non è un problema bloccante e si risolve appena popoliamo il recycler
public class TagActivity extends AppCompatActivity {

    private boolean checked = false;
    private TagViewModel tagViewModel;
    private EditText insertTag;
    private ImageButton checkboxButton;

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
        tagViewModel.initDatabase(this);


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
                if (handlerInvioTag()) {
                    Intent intent = new Intent(TagActivity.this, MainActivity.class);
                    startActivity(intent);
                }
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
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TagAdapter tagAdapter = new TagAdapter(new ArrayList<>());
        recyclerView.setAdapter(tagAdapter);

        // Osserva i cambiamenti nei tag

        tagViewModel.getTags().observe(this, updatedTags -> {
            if (updatedTags != null && tagAdapter != null) {
                tagAdapter.updateTags(updatedTags); // Aggiungi o aggiorna i tag
            }
        });

        // Carica i primi 3 tag dal database
        tagViewModel.loadRecentTags();
    }

    /**
     * Gestisce l'invio del tag tramite il ViewModel.
     * Richiede al tagViewModel di aggiornare la lista di tag se il tag è valido.
     *
     * @return true se il tag è valido, false altrimenti.
     */
    public boolean handlerInvioTag() {
        String inputTag = insertTag.getText().toString().trim();
        GameAccountSingleton.getInstance().setUserTag(inputTag);
        if (tagViewModel.isTagValid(inputTag)) {
            /*if (checked)
                tagViewModel.addTag(new Tag("Nuovo Giocatore", inputTag)); // Aggiungi il tag tramite il ViewModel se l'utente vuole salvarlo
            */
            Tag newTag = new Tag("Nuovo Giocatore", inputTag);
            tagViewModel.addTag(newTag); // Salva il tag sia nella memoria che nel database

            insertTag.setText(""); // Resetta il campo di testo
            Toast.makeText(TagActivity.this, "Tag aggiunto!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(TagActivity.this, "Tag non valido!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tagViewModel != null) {
            tagViewModel.saveRecentTagsToDatabase();
        }
    }

}