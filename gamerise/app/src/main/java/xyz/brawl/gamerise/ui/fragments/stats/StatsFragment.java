package xyz.brawl.gamerise.ui.fragments.stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.battle.Battle;
import xyz.brawl.gamerise.model.data.player.ClubEntry;
import xyz.brawl.gamerise.model.data.player.PlayerApiResponse;
import xyz.brawl.gamerise.model.data.player.PlayerMapper;
import xyz.brawl.gamerise.model.data.singleton.GameAccountSingleton;
import xyz.brawl.gamerise.model.data.stat.Stat;
import xyz.brawl.gamerise.model.repository.battlelog.BattleLogRepository;
import xyz.brawl.gamerise.model.repository.stats.StatsRepository;
import xyz.brawl.gamerise.ui.fragments.battlelog.adapter.BattleAdapter;
import xyz.brawl.gamerise.ui.viewmodels.battlelog.BattleLogViewModel;
import xyz.brawl.gamerise.ui.viewmodels.battlelog.BattleLogViewModelFactory;
import xyz.brawl.gamerise.ui.viewmodels.stats.StatsViewModel;
import xyz.brawl.gamerise.ui.viewmodels.stats.StatsViewModelFactory;
import xyz.brawl.gamerise.util.NetworkUtil;
import xyz.brawl.gamerise.util.ResponseCallback;
import xyz.brawl.gamerise.util.ServiceLocator;

public class StatsFragment extends Fragment  {
    public static final String TAG = "StatsFragment";
    private StatsViewModel statsViewModel;
    public LinearLayout chartContainer;
    private TextView trofei;
    private TextView livello;
    private TextView club;
    private TextView vittorieSolo;
    private TextView vittorieDuo;
    private TextView vittorie3vs3;
    private ClubEntry c;
    private FrameLayout noInternetView;
    private BattleLogViewModel battleLogViewModel;
    private List<Battle> battles;
    private Stat stats;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatsRepository statsRepository =
                ServiceLocator.getInstance().getStatsRepository(requireActivity().getApplication(),
                        requireActivity().getApplication().getResources().getBoolean(R.bool.debug_mode));

        statsViewModel = new ViewModelProvider(
                requireActivity(),
                new StatsViewModelFactory(statsRepository)).get(StatsViewModel.class);

        stats=new Stat();

        BattleLogRepository battleLogRepository =
                ServiceLocator.getInstance().getBattleLogRepository(requireActivity().getApplication(),
                        requireActivity().getApplication().getResources().getBoolean(R.bool.debug_mode));

        battleLogViewModel = new ViewModelProvider(
                requireActivity(),
                new BattleLogViewModelFactory(battleLogRepository)).get(BattleLogViewModel.class);

        battles = new ArrayList<>();
        //Qui collegare il viewModel e il repository -------------------------------
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        noInternetView = view.findViewById(R.id.no_internet_view);
        trofei=view.findViewById(R.id.valoreTrofei);
        livello=view.findViewById(R.id.valoreLivello);
        club=view.findViewById(R.id.valoreClub);
        vittorieSolo=view.findViewById(R.id.valoreVittorieSolo);
        vittorieDuo=view.findViewById(R.id.valoreVittorieDuo);
        vittorie3vs3=view.findViewById(R.id.valoreVittorie3vs3);
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
        statsViewModel.getStats(tag, Long.parseLong(lastUpdate)).observe(getViewLifecycleOwner(),
                result -> {
                    if (result.isSuccess()) {
                        Stat stat= (Stat) ((Result.Success) result).getData();
                        trofei.setText(""+stat.trophies);
                        livello.setText(""+stat.expLevel);
                        c=stat.club;
                        club.setText(""+c.getName());
                        vittorieSolo.setText(""+stat.soloVictories);
                        vittorieDuo.setText(""+stat.duoVictories);
                        vittorie3vs3.setText(""+stat._3vs3Victories);

                    } else {
                        String errorMessage = ((Result.Error) result).getMessage();
                        Snackbar.make(view, errorMessage, Snackbar.LENGTH_SHORT).show();
                    }
                });


        //TODO: spostare codice nel viewModel
        chartContainer = view.findViewById(R.id.chart_container);
        battleLogViewModel.getBattles(tag, Long.parseLong(lastUpdate)).observe(getViewLifecycleOwner(),
                result -> {
                    if (result.isSuccess()) {
                        List<Battle> battleList= (List<Battle>) ((Result.Success) result).getData();
                        battles.clear();
                        battles.addAll(battleList);
                        Map<String, Integer> battleCount = new HashMap<>();
                        for (Battle battle : battles) {
                            if(Integer.parseInt(battle.trophies)>0){
                                battleCount.put(battle.title, battleCount.getOrDefault(battle.title, 0) + 1);
                            }
                        }
                        addScatterChart("Game Modes Win", battleCount);
                    } else {
                        String errorMessage = ((Result.Error) result).getMessage();
                        Snackbar.make(view, errorMessage, Snackbar.LENGTH_SHORT).show();
                    }
                });

        String title = "Maps WinsXGames";
        Map<String, Integer> battleCount = new HashMap<>();
        addScatterChart(title, battleCount);

        return view;
    }

    private void addScatterChart(String title, Map<String, Integer> battleCount) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View chartView = inflater.inflate(R.layout.layout_chart, chartContainer, false);

        TextView charTitle = chartView.findViewById(R.id.chart_title);
        charTitle.setText(title);
        // Recupera il grafico a dispersione
        ScatterChart scatterChart = chartView.findViewById(R.id.scatterChart);
        List<Entry> entries = new ArrayList<>();
        int x=1;
        if(title.equals("Game Modes Win")){
            for (Map.Entry<String, Integer> entry : battleCount.entrySet()) {
                entries.add(new Entry(x, entry.getValue()));
                x++; // Incremento X per ogni tipo di battaglia
            }
        }else{// Crea i dati per il grafico

            entries.add(new Entry(1, 2)); // Punto (x = 1, y = 2)
            entries.add(new Entry(2, 3)); // Punto (x = 2, y = 3)
            entries.add(new Entry(3, 1)); // Punto (x = 3, y = 1)//
        }
        // Crea i dati per il grafico



        ScatterDataSet dataSet = new ScatterDataSet(entries, "");
        dataSet.setColor(R.color.black); // Colore dei punti
        dataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE); // Forma dei punti
        dataSet.setValueTextSize(10);
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getPointLabel(Entry entry) {
                return "Mappa"+entry.getX();
            }
        });

        // Personalizzazioni
        //TODO: implementare la description (es. stats ultime n partite)
        scatterChart.setMinimumHeight(500);
        scatterChart.getDescription().setEnabled(false); // Rimuove la descrizione del grafico
        scatterChart.getLegend().setEnabled(false); // Nasconde la legenda
        scatterChart.getAxisRight().setEnabled(false); // Rimuove l'asse destro
        scatterChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM); // Posiziona l'asse X in basso
        scatterChart.getAxisLeft().setDrawLabels(true); // Mostra solo l'asse sinistro
        scatterChart.getXAxis().setGranularity(1f); // Passo di 1
        scatterChart.getXAxis().setGranularityEnabled(true);

        scatterChart.getAxisLeft().setGranularity(1f); // Passo di 1
        scatterChart.getAxisLeft().setGranularityEnabled(true);

        ScatterData scatterData = new ScatterData(dataSet);

        // Imposta i dati sul grafico
        scatterChart.setData(scatterData);

        // Aggiorna il grafico
        scatterChart.invalidate();
        chartContainer.addView(chartView);
    }


}