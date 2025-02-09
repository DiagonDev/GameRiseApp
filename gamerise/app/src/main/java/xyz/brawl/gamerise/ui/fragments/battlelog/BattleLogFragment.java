package xyz.brawl.gamerise.ui.fragments.battlelog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.battle.Battle;
import xyz.brawl.gamerise.model.data.singleton.GameAccountSingleton;
import xyz.brawl.gamerise.model.repository.battlelog.BattleLogRepository;
import xyz.brawl.gamerise.ui.fragments.battlelog.adapter.BattleAdapter;
import xyz.brawl.gamerise.ui.viewmodels.battlelog.BattleLogViewModel;
import xyz.brawl.gamerise.ui.viewmodels.battlelog.BattleLogViewModelFactory;
import xyz.brawl.gamerise.util.NetworkUtil;
import xyz.brawl.gamerise.util.ServiceLocator;

public class BattleLogFragment extends Fragment {

    private BattleLogViewModel battleLogViewModel;
    private List<Battle> battles;
    private RecyclerView recyclerView;
    public static BattleLogFragment newInstance() {
        return new BattleLogFragment();
    }
    private GameRiseDatabase database;
    private FrameLayout noInternetView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BattleLogRepository battleLogRepository =
                ServiceLocator.getInstance().getBattleLogRepository(requireActivity().getApplication(),
                        requireActivity().getApplication().getResources().getBoolean(R.bool.debug_mode));

        battleLogViewModel = new ViewModelProvider(
                requireActivity(),
                new BattleLogViewModelFactory(battleLogRepository)).get(BattleLogViewModel.class);

        battles = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battle_log, container, false);
        noInternetView = view.findViewById(R.id.no_internet_view);
        //Provvisorio
        recyclerView = view.findViewById(R.id.battle_log_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new BattleAdapter(battles, this.getContext()));
        String lastUpdate = "0";
        String tag = GameAccountSingleton.getInstance().getUserTag();
        if (tag == null || tag.isEmpty()) {
            Toast.makeText(requireContext(), "Nessun tag trovato! Inseriscilo in TagActivity.", Toast.LENGTH_SHORT).show();
            return view; // Se non c'è nessun tag, esci
        }

        if(!NetworkUtil.isInternetAvailable(this.getContext())){
            noInternetView.setVisibility(View.VISIBLE);

            lastUpdate = System.currentTimeMillis() + "";
        }
        //TODO: implementare il codice per mostrare le battaglie
        /*
         * Debug mode è una variabile che sta nella local.properties
         * guardare build.gradle:
         * resValue("bool", "debug_mode", gradleLocalProperties(rootDir, providers).getProperty("debug_mode"))
         * se sto in debug mode prendo sempre dal file .json locale
         */
        /* Deprecated: Riutilizzare da altre parti
        if (requireActivity().getResources().getBoolean(R.bool.debug_mode)) {
            battleLogRepository = new BattleLogMockRepository(this.getContext());
        } else battleLogRepository = new BattleLogRepository(this.getContext());
        */

        //TODO: controllare se funziona
        battleLogViewModel.getBattles(tag, Long.parseLong(lastUpdate)).observe(getViewLifecycleOwner(),
            result -> {
                if (result.isSuccess()) {
                    List<Battle> newBattles = (List<Battle>) ((Result.Success) result).getData();
                    recyclerView.setVisibility(View.VISIBLE);
                    ((BattleAdapter) recyclerView.getAdapter()).updateData(newBattles);
                } else {
                    String errorMessage = ((Result.Error) result).getMessage();
                    Snackbar.make(view, errorMessage, Snackbar.LENGTH_SHORT).show();
                }
            });
        return view;
    }
}