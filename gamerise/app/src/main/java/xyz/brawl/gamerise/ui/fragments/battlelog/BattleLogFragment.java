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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.data.battle.Battle;

import xyz.brawl.gamerise.model.data.battle.BattleMapper;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogEntry;
import xyz.brawl.gamerise.model.data.singleton.GameAccountSingleton;
import xyz.brawl.gamerise.model.repository.BattleLogRepository;
import xyz.brawl.gamerise.model.repository.IBattleLogRepository;
import xyz.brawl.gamerise.ui.fragments.battlelog.adapter.BattleAdapter;
import xyz.brawl.gamerise.ui.viewmodels.battlelog.BattleLogViewModel;
import xyz.brawl.gamerise.util.Constants;
import xyz.brawl.gamerise.util.JSONParserUtils;
import xyz.brawl.gamerise.util.ResponseCallback;

public class BattleLogFragment extends Fragment implements ResponseCallback {

    private BattleLogViewModel battleLogViewModel;
    private IBattleLogRepository battleLogRepository;

    public static BattleLogFragment newInstance() {
        return new BattleLogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battle_log, container, false);

        //Provvisorio
        battleLogRepository = new BattleLogRepository(this.getContext(), this);
        battleLogRepository.fetchBattleLog(Constants.tagTeo, 10);


        JSONParserUtils jsonParserUtils = new JSONParserUtils(getContext());
        try {
            BattleLogApiResponse battleLogApiResponse = jsonParserUtils.battleLogParseJSONWithGson("battlelog.json");
            // List<Battle> battles = BattleMapper.mapToBattles(battleLogApiResponse);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Battle> battles = new ArrayList<>();
        battles.add(new Battle(new Battle.BattleBuilder().title("Prova")));
        RecyclerView recyclerView = view.findViewById(R.id.battle_log_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new BattleAdapter(battles, this.getContext()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        battleLogViewModel = new ViewModelProvider(this).get(BattleLogViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onSuccess(List<BattleLogEntry> battleLogEntries, long lastUpdate) {

    }

    @Override
    public void onFailure(String errorMessage) {

    }
}