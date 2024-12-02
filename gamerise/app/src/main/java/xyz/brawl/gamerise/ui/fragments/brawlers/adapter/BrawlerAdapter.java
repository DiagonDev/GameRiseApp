package xyz.brawl.gamerise.ui.fragments.brawlers.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import java.util.List;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.data.brawler.Brawler;
import xyz.brawl.gamerise.ui.fragments.brawlers.BrawlersFragment;


public class BrawlerAdapter extends ArrayAdapter<Brawler> {


    private int layout;
    private List<Brawler> brawlers;

    public BrawlerAdapter(Context context, int layout, List<Brawler> brawlers) {
        super(context, layout, brawlers);
        this.layout = layout;
        this.brawlers = brawlers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(layout, parent, false);

        ImageView brawlerPinImageView = convertView.findViewById(R.id.brawler_pin_imageView);
        brawlerPinImageView.setImageResource(brawlers.get(position).brawlerPin);
        TextView brawlerNameTextView = convertView.findViewById(R.id.brawler_name_textView);
        brawlerNameTextView.setText(brawlers.get(position).brawlerName);

        convertView.setOnClickListener(view -> {
            // Quando il brawler viene cliccato, naviga
        });

        return convertView;
    }
}

