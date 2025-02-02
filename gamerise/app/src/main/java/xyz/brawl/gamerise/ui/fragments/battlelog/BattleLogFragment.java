package xyz.brawl.gamerise.ui.fragments.battlelog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.model.data.battle.Battle;
import xyz.brawl.gamerise.ui.fragments.battlelog.adapter.BattleAdapter;
import xyz.brawl.gamerise.ui.viewmodels.battlelog.BattleLogViewModel;

public class BattleLogFragment extends Fragment {

    private BattleLogViewModel battleLogViewModel;
    private List<Battle> battles = new ArrayList<>();
    private RecyclerView recyclerView;

    public static BattleLogFragment newInstance() {
        return new BattleLogFragment();
    }

    private GameRiseDatabase database;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battle_log, container, false);
        //Provvisorio
        recyclerView = view.findViewById(R.id.battle_log_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new BattleAdapter(battles, this.getContext()));

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
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        battleLogViewModel = new ViewModelProvider(this).get(BattleLogViewModel.class);
        // TODO: Use the ViewModel
    }

}