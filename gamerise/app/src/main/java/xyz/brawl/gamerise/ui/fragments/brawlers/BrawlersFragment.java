package xyz.brawl.gamerise.ui.fragments.brawlers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.battle.Battle;
import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.singleton.GameAccountSingleton;
import xyz.brawl.gamerise.model.repository.battlelog.BattleLogRepository;
import xyz.brawl.gamerise.model.repository.brawler.BrawlersRepository;
import xyz.brawl.gamerise.ui.fragments.brawlers.adapter.BrawlerAdapter;
import xyz.brawl.gamerise.ui.viewmodels.brawlers.BrawlersViewModel;
import xyz.brawl.gamerise.ui.viewmodels.brawlers.BrawlersViewModelFactory;
import xyz.brawl.gamerise.util.NetworkUtil;
import xyz.brawl.gamerise.util.ServiceLocator;

public class BrawlersFragment extends Fragment {

    private BrawlersViewModel brawlersViewModel;
    private List<BrawlerEntry> brawlersList;
    private RecyclerView recyclerView;
    private FrameLayout noInternetView;
    public static BrawlersFragment newInstance() {
        return new BrawlersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BrawlersRepository brawlersRepository =
                ServiceLocator.getInstance().getBrawlersRepository(requireActivity().getApplication(),
                        requireActivity().getApplication().getResources().getBoolean(R.bool.debug_mode));

        brawlersViewModel = new ViewModelProvider(
                requireActivity(),
                new BrawlersViewModelFactory(brawlersRepository)).get(BrawlersViewModel.class);

        brawlersList = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Era già qua, ma non so se sia corretta o meno
        //brawlersViewModel = new ViewModelProvider(this).get(BrawlersViewModel.class);
        View view = inflater.inflate(R.layout.fragment_brawlers, container, false);

        String tag = GameAccountSingleton.getInstance().getUserTag();
        if (tag == null || tag.isEmpty()) {
            Toast.makeText(requireContext(), "Nessun tag trovato! Inseriscilo in TagActivity.", Toast.LENGTH_SHORT).show();
            return view; // Se non c'è nessun tag, esci
        }

        String lastUpdate = "0";

        //COntrollare
        if(!NetworkUtil.isInternetAvailable(this.getContext())){
            noInternetView.setVisibility(View.VISIBLE);

            lastUpdate = System.currentTimeMillis() + "";
        }

        /*List<BrawlerEntry> brawlers = new ArrayList<>();
        *//*brawlers.add(new BrawlerEntry(R.drawable._bit_pin, 16000027, "8BIT"));
        brawlers.add(new BrawlerEntry(R.drawable._bit_pin, 16000027, "8BIT"));
        brawlers.add(new BrawlerEntry(R.drawable._bit_pin, 16000027, "8BIT"));
        brawlers.add(new BrawlerEntry(R.drawable._bit_pin, 16000027, "8BIT"));
        brawlers.add(new BrawlerEntry(R.drawable._bit_pin, 16000027, "8BIT"));
        brawlers.add(new BrawlerEntry(R.drawable._bit_pin, 16000027, "8BIT"));*/

        GridView gridView = view.findViewById(R.id.brawlers_gridview);

        // Inizializza l'adapter con una lista vuota
        BrawlerAdapter adapter = new BrawlerAdapter(view.getContext(), R.layout.layout_grid_brawlers, new ArrayList<>());

        // Collega l'adapter al GridView
        gridView.setAdapter(adapter);

        /// fine provvisorio

        //TODO: controllare se funziona
        brawlersViewModel.getBrawlers(tag, Long.parseLong(lastUpdate)).observe(getViewLifecycleOwner(),
                result -> {
                    if (result.isSuccess()) {
                        List<BrawlerEntry> newBrawlers = (List<BrawlerEntry>) ((Result.Success) result).getData();
                        adapter.updateData(newBrawlers); // Usa il metodo personalizzato
                        gridView.setVisibility(View.VISIBLE);
                    } else {
                        String errorMessage = ((Result.Error) result).getMessage();
                        Snackbar.make(view, errorMessage, Snackbar.LENGTH_SHORT).show();
                    }
                });
        return view;
    }
}