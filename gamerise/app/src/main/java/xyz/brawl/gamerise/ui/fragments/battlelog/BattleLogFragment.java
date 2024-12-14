package xyz.brawl.gamerise.ui.fragments.battlelog;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.data.battle.Battle;

import xyz.brawl.gamerise.model.data.battle.BattleMapper;

import xyz.brawl.gamerise.model.data.battle.api.BattleLogEntry;
import xyz.brawl.gamerise.model.repository.battlelog.BattleLogMockRepository;
import xyz.brawl.gamerise.model.repository.battlelog.BattleLogRepository;
import xyz.brawl.gamerise.model.repository.battlelog.IBattleLogRepository;
import xyz.brawl.gamerise.ui.fragments.battlelog.adapter.BattleAdapter;
import xyz.brawl.gamerise.ui.viewmodels.battlelog.BattleLogViewModel;
import xyz.brawl.gamerise.util.Constants;
import xyz.brawl.gamerise.util.ResponseCallback;

public class BattleLogFragment extends Fragment implements ResponseCallback {

    private BattleLogViewModel battleLogViewModel;
    private IBattleLogRepository battleLogRepository;
    private List<Battle> battles = new ArrayList<>();
    private RecyclerView recyclerView;
    public static BattleLogFragment newInstance() {
        return new BattleLogFragment();
    }

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
        if(requireActivity().getResources().getBoolean(R.bool.debug_mode)){
            battleLogRepository = new BattleLogMockRepository(this.getContext());
        }
        else battleLogRepository = new BattleLogRepository(this.getContext(), this);

        battleLogRepository.fetchBattleLog(Constants.tagTeo, 10);

        //battles sembra non avere assegnati i valori dal mapper
        //battles = BattleMapper.mapToBattles(battleLogRepository.fetchBattleLog(Constants.tagTeo, 10));



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        battleLogViewModel = new ViewModelProvider(this).get(BattleLogViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public <T> void onSuccess(Object o, long lastUpdate) {
        List<T> list = (List<T>) o;
        if(list != null){
            this.battles.clear();
            this.battles.addAll(BattleMapper.mapToBattles((List<BattleLogEntry>)list));
        }
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onFailure(String errorMessage) {

    }
}