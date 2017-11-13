package com.tinyappli.troelslund.galgeleg.Adapters;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tinyappli.troelslund.galgeleg.R;
import com.tinyappli.troelslund.galgeleg.data.DTO.HighscoreDTO;

import java.util.List;

public class ListAdapter extends ArrayAdapter<HighscoreDTO> {

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapter(Context context, int resource, List<HighscoreDTO> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.listeelement, null);
        }

        HighscoreDTO p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.dato);
            TextView tt2 = (TextView) v.findViewById(R.id.score);
            TextView tt3 = (TextView) v.findViewById(R.id.ord);

            if (tt1 != null) {
                tt1.setText(p.getDato().toString());
            }

            if (tt2 != null) {
                tt2.setText(p.getScore());
            }

            if (tt3 != null) {
                tt3.setText(p.getOrd());
            }
        }

        return v;
    }

}