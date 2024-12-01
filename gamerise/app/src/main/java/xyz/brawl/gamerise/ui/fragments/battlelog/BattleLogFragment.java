package xyz.brawl.gamerise.ui.fragments.battlelog;

import static xyz.brawl.gamerise.util.Constants.getBattleList;

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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.data.battle.Battle;

import xyz.brawl.gamerise.ui.recyclers.battle.BattleAdapter;
import xyz.brawl.gamerise.ui.recyclers.tag.TagAdapter;
import xyz.brawl.gamerise.ui.viewmodel.battlelog.BattleLogViewModel;

public class BattleLogFragment extends Fragment {

    private BattleLogViewModel mViewModel;

    public static BattleLogFragment newInstance() {
        return new BattleLogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battle_log, container, false);

        //Provvisorio
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
        mViewModel = new ViewModelProvider(this).get(BattleLogViewModel.class);
        // TODO: Use the ViewModel
    }

}