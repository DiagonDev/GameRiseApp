package xyz.brawl.gamerise.ui.fragments.brawlers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.adapters.brawler.BrawlerAdapter;
import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.brawler.BrawlerEntry;
import xyz.brawl.gamerise.repository.brawler.BrawlersRepository;
import xyz.brawl.gamerise.repository.gadget.GadgetRepository;
import xyz.brawl.gamerise.repository.starpower.StarPowerRepository;
import xyz.brawl.gamerise.ui.viewmodels.brawlers.BrawlersViewModel;
import xyz.brawl.gamerise.ui.viewmodels.brawlers.BrawlersViewModelFactory;
import xyz.brawl.gamerise.util.GameAccountSingleton;
import xyz.brawl.gamerise.util.NetworkUtil;
import xyz.brawl.gamerise.util.ServiceLocator;

public class BrawlersFragment extends Fragment {

    private BrawlersViewModel brawlersViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BrawlersRepository brawlersRepository =
                ServiceLocator.getInstance().getBrawlersRepository(requireActivity().getApplication(),
                        requireActivity().getApplication().getResources().getBoolean(R.bool.debug_mode));

        StarPowerRepository starPowerRepository =
                ServiceLocator.getInstance().getStarPowerRepository(requireActivity().getApplication(),
                        requireActivity().getApplication().getResources().getBoolean(R.bool.debug_mode));

        GadgetRepository gadgetRepository =
                ServiceLocator.getInstance().getGadgetRepository(requireActivity().getApplication(),
                        requireActivity().getApplication().getResources().getBoolean(R.bool.debug_mode));

        brawlersViewModel = new ViewModelProvider(
                requireActivity(),
                new BrawlersViewModelFactory(brawlersRepository, starPowerRepository, gadgetRepository)).get(BrawlersViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_brawlers, container, false);
        String tag = GameAccountSingleton.getInstance().getUserTag();
        boolean connected = NetworkUtil.isInternetAvailable(this.getContext());
        GridView gridView = view.findViewById(R.id.brawlers_gridview);
        BrawlerAdapter adapter = new BrawlerAdapter(view.getContext(), R.layout.layout_grid_brawlers, new ArrayList<>());
        gridView.setAdapter(adapter);

        brawlersViewModel.getBrawlers(tag, connected).observe(getViewLifecycleOwner(),
                result -> {
                    if (result.isSuccess()) {
                        List<BrawlerEntry> newBrawlers = (List<BrawlerEntry>) ((Result.Success) result).getData();
                        adapter.updateData(newBrawlers);
                        gridView.setVisibility(View.VISIBLE);
                    } else {
                        String errorMessage = ((Result.Error) result).getMessage();
                        Snackbar.make(view, errorMessage, Snackbar.LENGTH_SHORT).show();
                    }
                });
        return view;
    }
}