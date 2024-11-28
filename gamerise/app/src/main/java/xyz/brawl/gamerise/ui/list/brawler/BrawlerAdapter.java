package xyz.brawl.gamerise.ui.list.brawler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.data.brawler.Brawler;

public class BrawlerAdapter extends ArrayAdapter<Brawler> {

    private int layout;
    private List<Brawler> brawlers;

    public BrawlerAdapter(@NonNull Context context, int layout, @NonNull List<Brawler> brawlers) {
        super(context, layout, brawlers);
        this.layout = layout;
        this.brawlers = brawlers;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(layout, parent, false);

        ImageView brawlerPinImageView = convertView.findViewById(R.id.brawler_pin_imageView);
        brawlerPinImageView.setImageResource(brawlers.get(position).brawlerPin);

        convertView.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Brawler " + position + " clicked", Toast.LENGTH_SHORT).show();
        });
        return convertView;
    }
}
