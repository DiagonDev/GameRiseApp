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
        this.brawlers = brawlers;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(layout, parent, false);

        ImageView brawlerPinImageView = convertView.findViewById(R.id.brawler_pin_imageView);
        brawlerPinImageView.setImageResource(brawlers.get(position).getBrawlerPin());
        TextView brawlerNameTextView = convertView.findViewById(R.id.brawler_name_textView);
        brawlerNameTextView.setText(brawlers.get(position).getName());

        convertView.setOnClickListener(view -> {
            // Quando il brawler viene cliccato, naviga
            Intent i = new Intent(getContext(), BrawlerDetailsActivity.class);
            i.putExtra("brawlerId", brawlers.get(position).getId());
            getContext().startActivity(i);
        });

        return convertView;
    }
}

