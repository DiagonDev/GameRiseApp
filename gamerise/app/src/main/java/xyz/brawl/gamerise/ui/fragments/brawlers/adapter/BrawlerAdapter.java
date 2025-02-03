package xyz.brawl.gamerise.ui.fragments.brawlers.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.ui.activities.brawlerdetails.BrawlerDetailsActivity;

public class BrawlerAdapter extends ArrayAdapter<BrawlerEntry> {

    private int layout;
    private List<BrawlerEntry> brawlers;

    public BrawlerAdapter(Context context, int layout, List<BrawlerEntry> brawlers) {
        super(context, layout, brawlers);
        this.layout = layout;
        this.brawlers = new ArrayList<>(brawlers); // Usa una copia per evitare problemi di riferimenti
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        // Se convertView è null, creiamo una nuova view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.brawlerPinImageView = convertView.findViewById(R.id.brawler_pin_imageView);
            viewHolder.brawlerNameTextView = convertView.findViewById(R.id.brawler_name_textView);
            convertView.setTag(viewHolder);
        } else {
            // Riutilizza la View esistente
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Ottieni il brawler alla posizione corrente
        BrawlerEntry brawler = brawlers.get(position);

        // Imposta i dati nella View
        viewHolder.brawlerPinImageView.setImageResource(brawler.getBrawlerPin());
        viewHolder.brawlerNameTextView.setText(brawler.getName());

        // Click per aprire i dettagli del brawler
        convertView.setOnClickListener(view -> {
            Intent i = new Intent(getContext(), BrawlerDetailsActivity.class);
            i.putExtra("brawlerId", brawler.getId());
            getContext().startActivity(i);
        });

        return convertView;
    }

    // ViewHolder: Ottimizza il caricamento delle View
    private static class ViewHolder {
        ImageView brawlerPinImageView;
        TextView brawlerNameTextView;
    }

    // Metodo per aggiornare la lista dei brawlers dinamicamente
    public void updateData(List<BrawlerEntry> newBrawlers) {
        brawlers.clear();
        brawlers.addAll(newBrawlers);
        notifyDataSetChanged(); // Notifica l'Adapter che i dati sono cambiati
    }
}