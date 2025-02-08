package xyz.brawl.gamerise.ui.activities.brawlerdetails;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.brawler.GadgetEntry;
import xyz.brawl.gamerise.model.data.brawler.StarPowerEntry;
import xyz.brawl.gamerise.model.data.singleton.GameAccountSingleton;
import xyz.brawl.gamerise.model.repository.brawler.BrawlersRepository;
import xyz.brawl.gamerise.model.repository.gadget.GadgetRepository;
import xyz.brawl.gamerise.model.repository.starpower.StarPowerRepository;
import xyz.brawl.gamerise.ui.viewmodels.brawlers.BrawlersViewModel;
import xyz.brawl.gamerise.ui.viewmodels.brawlers.BrawlersViewModelFactory;
import xyz.brawl.gamerise.util.ServiceLocator;


public class BrawlerDetailsActivity extends AppCompatActivity {
    BrawlersViewModel brawlersViewModel;

    private TextView powerLevelText;
    private TextView trophiesText;
    private ProgressBar trophiesProgress;
    private ProgressBar powerLevelProgress;

    private TextView gadgetText1;
    private ImageView gadgetImg1;
    private TextView gadgetText2;
    private ImageView gadgetImg2;

    private TextView starPowerText1;
    private ImageView starPowerImg1;
    private TextView starPowerText2;
    private ImageView starPowerImg2;

    private LinearLayout starPowerLayout;
    private TextView nome;
    private ImageView immagineBrawler;
    private ImageView immagineBrawlerPrec;
    private ImageView immagineBrawlerSucc;

    private List<BrawlerEntry> brawlers;
    private int countG = 0;
    private int countA = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_brawler_details);
        powerLevelText = findViewById(R.id.livello_score);
        trophiesText = findViewById(R.id.trofei_score);
        trophiesProgress = findViewById(R.id.trofei_progress);
        powerLevelProgress = findViewById(R.id.livello_progress);

        gadgetText1 = findViewById(R.id.textViewGadget1);
        gadgetImg1 = findViewById(R.id.imageViewGadget1);
        gadgetText2 = findViewById(R.id.textViewGadget2);
        gadgetImg2 = findViewById(R.id.imageViewGadget2);

        starPowerText1 = findViewById(R.id.textViewStarPower1);
        starPowerImg1 = findViewById(R.id.imageViewStarPower1);
        starPowerText2 = findViewById(R.id.textViewStarPower2);
        starPowerImg2 = findViewById(R.id.imageViewStarPower2);

        starPowerLayout = findViewById(R.id.linearLayoutStarPower);
        nome = findViewById(R.id.textView);
        immagineBrawler = findViewById(R.id.imageView);
        immagineBrawlerPrec = findViewById(R.id.previous_brawler_image);
        immagineBrawlerSucc = findViewById(R.id.next_brawler_image);

        BrawlersRepository brawlersRepository = ServiceLocator.getInstance().getBrawlersRepository(getApplication(),
            getApplication().getResources().getBoolean(R.bool.debug_mode));

        StarPowerRepository starPowerRepository = ServiceLocator.getInstance().getStarPowerRepository(getApplication(),
            getApplication().getResources().getBoolean(R.bool.debug_mode));

        GadgetRepository gadgetRepository = ServiceLocator.getInstance().getGadgetRepository(getApplication(),
            getApplication().getResources().getBoolean(R.bool.debug_mode));

        brawlersViewModel = new ViewModelProvider(
                this,
                new BrawlersViewModelFactory(brawlersRepository, starPowerRepository, gadgetRepository)).get(BrawlersViewModel.class);

        long brawlerId = getIntent().getLongExtra("brawlerId", -1);
        String tag = GameAccountSingleton.getInstance().getUserTag();

        updateUI(brawlerId, tag);
        EdgeToEdge.enable(this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.brawlerDetailsActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Toast.makeText(this, "BrawlerId: " + brawlerId, Toast.LENGTH_SHORT).show();

    }

    private void updateUI(long brawlerId, String tag) {
        // RESETTA TUTTO PRIMA DI CARICARE NUOVI DATI
        gadgetText1.setText("");
        gadgetText2.setText("");
        starPowerText1.setText("");
        starPowerText2.setText("");
        trophiesText.setText("");
        powerLevelText.setText("");
        gadgetImg1.setImageResource(0);
        gadgetImg2.setImageResource(0);
        starPowerImg1.setImageResource(0);
        starPowerImg2.setImageResource(0);
        countG = 0;
        countA = 0;

        // RIMUOVE I VECCHI OBSERVER PRIMA DI AGGIUNGERE NUOVI
        brawlersViewModel.getGadgets(brawlerId, 0, tag).removeObservers(this);
        brawlersViewModel.getStarPower(brawlerId, 0, tag).removeObservers(this);

        // CARICA NUOVO BRAWLER
        brawlersViewModel.getBrawlers(tag, 0)
                .observe(this, result -> {
                    if (result.isSuccess()) {
                        brawlers = (List<BrawlerEntry>) ((Result.Success) result).getData();
                        for (BrawlerEntry brawler : brawlers){
                            if (brawler.getId() == brawlerId){
                                nome.setText(brawler.getName());
                                immagineBrawler.setImageResource(brawler.getBrawlerPin());
                                trophiesProgress.setProgress(brawler.getTrophies());
                                powerLevelProgress.setProgress(brawler.getPower());
                                trophiesText.setText(brawler.getTrophies() + "/1000");
                                powerLevelText.setText(brawler.getPower() + "/11");

                                int currentIndex = brawlers.indexOf(brawler);
                                BrawlerEntry brawlerPrec = (currentIndex == 0) ? brawlers.get(brawlers.size() - 1) : brawlers.get(currentIndex - 1);
                                BrawlerEntry brawlerSucc = (currentIndex == brawlers.size() - 1) ? brawlers.get(0) : brawlers.get(currentIndex + 1);

                                immagineBrawlerPrec.setImageResource(brawlerPrec.getBrawlerPin());
                                immagineBrawlerSucc.setImageResource(brawlerSucc.getBrawlerPin());
                                break;
                            }
                        }
                    }
                });

        // CARICA NUOVI GADGETS
        brawlersViewModel.getGadgets(brawlerId, 0, tag)
                .observe(this, result -> {
                    if (result.isSuccess()) {
                        List<GadgetEntry> gadgets = (List<GadgetEntry>) ((Result.Success) result).getData();
                        countG = 0; // RESETTA IL COUNT QUI
                        for(GadgetEntry gadget : gadgets){
                            if(gadget.getBrawlerId() == brawlerId){
                                if (countG == 0) {
                                    gadgetText1.setText(gadget.getName());
                                    countG++;
                                } else if (countG == 1){
                                    gadgetText2.setText(gadget.getName());
                                    countG = 0;
                                }
                            }
                        }
                    }
                });

        // CARICA NUOVI STAR POWER
        brawlersViewModel.getStarPower(brawlerId, 0, tag)
                .observe(this, result -> {
                    if (result.isSuccess()) {
                        List<StarPowerEntry> starPowers = (List<StarPowerEntry>) ((Result.Success) result).getData();
                        countA = 0; // RESETTA IL COUNT QUI
                        for(StarPowerEntry starPower : starPowers){
                            if(starPower.getBrawlerId() == brawlerId){
                                if (countA == 0) {
                                    starPowerText1.setText(starPower.getName());
                                    countA++;
                                } else if (countA == 1){
                                    starPowerText2.setText(starPower.getName());
                                    countA = 0;
                                }
                            }
                        }
                    }
                });

        LinearLayout previousBrawlerLayout = findViewById(R.id.previous_brawler_layout);
        LinearLayout nextBrawlerLayout = findViewById(R.id.next_brawler_layout);
        previousBrawlerLayout.setOnClickListener(v -> navigateToPreviousBrawler(brawlerId, tag));
        nextBrawlerLayout.setOnClickListener(v -> navigateToNextBrawler(brawlerId, tag));
    }

    private void navigateToPreviousBrawler(long currentBrawlerId, String tag) {
        int currentIndex = -1;
        for (int i = 0; i < brawlers.size(); i++) {
            if (brawlers.get(i).getId() == currentBrawlerId) {
                currentIndex = i;
                break;
            }
        }
        if (currentIndex == -1) return;

        BrawlerEntry brawlerPrec = (currentIndex == 0) ? brawlers.get(brawlers.size() - 1) : brawlers.get(currentIndex - 1);
        updateUI(brawlerPrec.getId(), tag);
    }

    private void navigateToNextBrawler(long currentBrawlerId, String tag) {
        int currentIndex = -1;
        for (int i = 0; i < brawlers.size(); i++) {
            if (brawlers.get(i).getId() == currentBrawlerId) {
                currentIndex = i;
                break;
            }
        }
        if (currentIndex == -1) return;

        BrawlerEntry brawlerSucc = (currentIndex == brawlers.size() - 1) ? brawlers.get(0) : brawlers.get(currentIndex + 1);
        updateUI(brawlerSucc.getId(), tag);
    }
}
