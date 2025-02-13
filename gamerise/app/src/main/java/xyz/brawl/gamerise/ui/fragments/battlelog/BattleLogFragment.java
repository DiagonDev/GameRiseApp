package xyz.brawl.gamerise.ui.fragments.battlelog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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
<<<<<<< Updated upstream
import xyz.brawl.gamerise.adapters.battlelog.BattleAdapter;
=======
>>>>>>> Stashed changes
import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.battle.Battle;
import xyz.brawl.gamerise.repository.battlelog.BattleLogRepository;
<<<<<<< Updated upstream
import xyz.brawl.gamerise.ui.viewmodels.battlelog.BattleLogViewModel;
import xyz.brawl.gamerise.ui.viewmodels.battlelog.BattleLogViewModelFactory;
import xyz.brawl.gamerise.util.GameAccountSingleton;
=======
import xyz.brawl.gamerise.adapters.battlelog.BattleAdapter;
import xyz.brawl.gamerise.viewmodels.battlelog.BattleLogViewModel;
import xyz.brawl.gamerise.viewmodels.battlelog.BattleLogViewModelFactory;
>>>>>>> Stashed changes
import xyz.brawl.gamerise.util.NetworkUtil;
import xyz.brawl.gamerise.util.ServiceLocator;


public class BattleLogFragment extends Fragment {

    private BattleLogViewModel battleLogViewModel;
    private List<Battle> battles;
    private RecyclerView recyclerView;

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
        recyclerView = view.findViewById(R.id.battle_log_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new BattleAdapter(battles, this.getContext()));

        String tag = GameAccountSingleton.getInstance().getUserTag();

        boolean connected = NetworkUtil.isInternetAvailable(this.getContext());

        battleLogViewModel.getBattles(tag, connected, GameAccountSingleton.getInstance().getLastUpdate()).observe(getViewLifecycleOwner(),
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