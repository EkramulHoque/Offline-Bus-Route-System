package com.example.ekram.imagemap4;

/**
 * Created by ekram on 7/29/2015.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<RowItem> rowItems;
    private ArrayList<RowItem> arraylist;

    CustomAdapter(Context context, List<RowItem> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
        this.arraylist = new ArrayList<RowItem>();
        this.arraylist.addAll(rowItems);
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }

    /* private view holder class */
    private class ViewHolder {
        ImageView profile_pic;
        TextView member_name;
        TextView textView;
        TextView  textView2;
        TextView textView3;
        TextView textView4;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       final ViewHolder holder ;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();

            holder.member_name = (TextView) convertView
                    .findViewById(R.id.member_name);
            holder.profile_pic = (ImageView) convertView
                    .findViewById(R.id.profile_pic);
            holder.textView = (TextView) convertView
                    .findViewById(R.id.textView);
            holder.textView2 = (TextView) convertView
                    .findViewById(R.id.textView2);

            holder.textView3 = (TextView) convertView
                    .findViewById(R.id.textView3);

            holder.textView4 = (TextView) convertView
                    .findViewById(R.id.textView4);



            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RowItem row_pos = rowItems.get(position);
        holder.profile_pic.setImageResource(row_pos.getProfile_pic_id());
        holder.member_name.setText(row_pos.getMember_name());
        holder.textView.setText(row_pos.getDestination());
        holder.textView2.setText(row_pos.getBus1());
        holder.textView3.setText(row_pos.getBus2());
        holder.textView4.setText(row_pos.getBus3());
        return convertView;
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        rowItems.clear();
        if (charText.length() == 0) {
            rowItems.addAll(arraylist);
        }
        else
        {
            for (RowItem wp : arraylist)
            {
                if (wp.getBus1().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    rowItems.add(wp);
                }
                if (wp.getBus2().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    rowItems.add(wp);
                }
                if (wp.getBus3().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    rowItems.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}