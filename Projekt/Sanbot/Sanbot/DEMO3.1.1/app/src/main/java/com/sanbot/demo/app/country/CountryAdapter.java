package com.sanbot.demo.app.country;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sanbot.demo.R;
import com.sanbot.demo.app.BaseAdapter;

import java.util.List;
import java.util.Locale;


public class CountryAdapter extends BaseAdapter<Country> {

    public static final String TAG = "CountryAdapter";

    public CountryAdapter(List<Country> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CountryViewHolder(createView(parent, R.layout.item_country));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i(TAG, "position=" + position);
        Country country = mList.get(position);
        CountryViewHolder viewHolder = (CountryViewHolder) holder;
        String letter = country.getLetter();
        viewHolder.letterTv.setText(letter);
        viewHolder.nameTv.setText(country.getName());
        viewHolder.codeTv.setText(String.format(Locale.getDefault(), "+%d", country.getCode()));

        if (position == 0) {
            viewHolder.letterTv.setVisibility(View.VISIBLE);
        } else {
            Country lastCountry = mList.get(position - 1);
            String lastLetter = lastCountry.getLetter();
            if (!TextUtils.isEmpty(letter) && letter.equals(lastLetter)) {
                viewHolder.letterTv.setVisibility(View.GONE);
            } else {
                viewHolder.letterTv.setVisibility(View.VISIBLE);
            }
        }
    }


    private class CountryViewHolder extends RecyclerView.ViewHolder {

        TextView letterTv;
        LinearLayout mNameLayout;
        TextView nameTv;
        TextView codeTv;

        private CountryViewHolder(View itemView) {
            super(itemView);
            letterTv = (TextView) itemView.findViewById(R.id.item_country_letter_tv);
            mNameLayout = (LinearLayout) itemView.findViewById(R.id.item_country_name_layout);
            nameTv = (TextView) itemView.findViewById(R.id.item_country_name_tv);
            codeTv = (TextView) itemView.findViewById(R.id.item_country_code_tv);

            mNameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(v, getLayoutPosition(), mList.get(getLayoutPosition()));
                    }
                }
            });

        }
    }

    public int StringToPosition(String text) {
        if (TextUtils.isEmpty(text) || mList == null) {
            return 0;
        }
        for (int i = 0, size = mList.size(); i < size; i++) {
            String letter = mList.get(i).getLetter();
            int value = text.compareToIgnoreCase(letter);
            if (value == 0) {
                return i;
            }
        }
        return -1;
    }

}
