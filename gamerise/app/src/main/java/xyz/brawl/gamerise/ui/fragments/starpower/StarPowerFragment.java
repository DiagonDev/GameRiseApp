package xyz.brawl.gamerise.ui.fragments.starpower;

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
import java.util.Objects;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.battle.Battle;
import xyz.brawl.gamerise.model.data.brawler.StarPowerEntry;
import xyz.brawl.gamerise.model.repository.starpower.StarPowerRepository;
import xyz.brawl.gamerise.ui.fragments.battlelog.adapter.BattleAdapter;
import xyz.brawl.gamerise.ui.viewmodels.starpower.StarPowerViewModel;
import xyz.brawl.gamerise.ui.viewmodels.starpower.StarPowerViewModelFactory;
import xyz.brawl.gamerise.util.NetworkUtil;
import xyz.brawl.gamerise.util.ServiceLocator;

public class StarPowerFragment extends Fragment {
    private StarPowerViewModel starPowerViewModel;
    private List<StarPowerEntry> starPowerList;
    private RecyclerView recyclerView;
    public static StarPowerFragment newInstance() {
        return new StarPowerFragment();
    }
    private GameRiseDatabase database;
    private FrameLayout noInternetView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StarPowerRepository starPowerRepository =
                ServiceLocator.getInstance().getStarPowerRepository(requireActivity().getApplication(),
                        requireActivity().getApplication().getResources().getBoolean(R.bool.debug_mode));
        starPowerViewModel = new ViewModelProvider(
                requireActivity(),
                new StarPowerViewModelFactory(starPowerRepository)).get(StarPowerViewModel.class);
        starPowerList = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //TODO: controllo fragment_brawler
        View view = inflater.inflate(R.layout.fragment_brawlers, container, false);
        String lastUpdate = "0";
        Long brawlerId = 1L;
        //controllo
        if(!NetworkUtil.isInternetAvailable(this.getContext())){
            noInternetView.setVisibility(View.VISIBLE);

            lastUpdate = System.currentTimeMillis() + "";
        }
        //TODO: implementare il codice per mostrare le starPower

        //TODO: controllare se funziona
        starPowerViewModel.getStarPower(brawlerId, Long.parseLong(lastUpdate)).observe(getViewLifecycleOwner(),
                result -> {
                    if (result.isSuccess()) {
                        List<StarPowerEntry> newStarPower = (List<StarPowerEntry>) ((Result.Success) result).getData();
                        int initialSize = starPowerList.size();
                        starPowerList.clear();
                        starPowerList.addAll(newStarPower);
                        Objects.requireNonNull(recyclerView.getAdapter()).notifyItemRangeInserted(initialSize, newStarPower.size());
                        recyclerView.setVisibility(View.VISIBLE);
                    } else {
                        String errorMessage = ((Result.Error) result).getMessage();
                        Snackbar.make(view, errorMessage, Snackbar.LENGTH_SHORT).show();
                    }
                });
        return view;
    }

}
