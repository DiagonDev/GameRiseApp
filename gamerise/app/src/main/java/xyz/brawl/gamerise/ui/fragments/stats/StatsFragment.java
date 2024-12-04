package xyz.brawl.gamerise.ui.fragments.stats;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.R;

public class StatsFragment extends Fragment {

    public LinearLayout chartContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        //TODO: spostare codice nel viewModel
        chartContainer = view.findViewById(R.id.chart_container);
        String title = "Maps WinsXGames";
        for (int i = 0; i < 3; i++) {
            addScatterChart(title);
        }

        return view;
    }

    private void addScatterChart(String title) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View chartView = inflater.inflate(R.layout.layout_chart, chartContainer, false);

        TextView charTitle = chartView.findViewById(R.id.chart_title);
        charTitle.setText(title);
        // Recupera il grafico a dispersione
        ScatterChart scatterChart = chartView.findViewById(R.id.scatterChart);

        // Crea i dati per il grafico
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, 2)); // Punto (x = 1, y = 2)
        entries.add(new Entry(2, 3)); // Punto (x = 2, y = 3)
        entries.add(new Entry(3, 1)); // Punto (x = 3, y = 1)

        ScatterDataSet dataSet = new ScatterDataSet(entries, "");
        dataSet.setColor(R.color.black); // Colore dei punti
        dataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE); // Forma dei punti
        dataSet.setValueTextSize(10);
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getPointLabel(Entry entry) {
                return "Mappa1";
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