package xyz.brawl.gamerise.ui.listviews.battle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import xyz.brawl.gamerise.R;

public class BattleAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;

    public BattleAdapter(Context context, LayoutInflater inflater) {
        this.context = context;
        this.inflater = inflater;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.layout_list_battlelog, container, false);
        }
        ((TextView) convertView.findViewById(android.R.id.text1)).setText((String) getItem(position));
        return convertView;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
