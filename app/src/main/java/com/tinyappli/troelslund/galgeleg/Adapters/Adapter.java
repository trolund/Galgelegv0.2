package com.tinyappli.troelslund.galgeleg.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tinyappli.troelslund.galgeleg.R;
import com.tinyappli.troelslund.galgeleg.data.DTO.HighscoreDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by troelslund on 09/11/2017.
 */





public class Adapter extends ArrayAdapter<HighscoreDTO> {


    public Date dato;
    private String ord;
    private int score;
    private Context mContext;

    private class ViewHolder {
        public TextView score = null;
        public TextView dato = null;
        public TextView ord = null;
    }

    private List<HighscoreDTO> dtoList;

    public Adapter(@NonNull Context context, int resource, List<HighscoreDTO> dtoList) {
        super(context, resource);
        this.dtoList = dtoList;
    }

    private int lastPos= -1;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mConvertView = convertView;

        View view;
        ViewHolder viewHolder;

        HighscoreDTO dto = dtoList.get(position);


        if (mConvertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            mConvertView = inflater.inflate(R.layout.listeelement, parent, false);

            viewHolder.dato = mConvertView.findViewById(R.id.dato);
            viewHolder.ord = mConvertView.findViewById(R.id.ord);
            viewHolder.score = mConvertView.findViewById(R.id.score);



            view = mConvertView;

            mConvertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) mConvertView.getTag();
            view = mConvertView;
        }

        lastPos = position;

        viewHolder.score.setText(dto.getScore());
        viewHolder.ord.setText(dto.getOrd());
        viewHolder.dato.setText(dto.getDato().toString());

        return super.getView(position, convertView, parent);
    }
}

