package com.example.projetairbnb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;

import java.util.List;

public class AirbnbAdapter extends BaseAdapter {

    Context context;
    List<Airbnb> list;

    public AirbnbAdapter(Context context, List<Airbnb> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ConstraintLayout layoutItem;
        LayoutInflater mInflater = LayoutInflater.from(context);

        if (convertView == null) {
            layoutItem = (ConstraintLayout) mInflater.inflate(R.layout.item_layout, parent, false);
        } else {
            layoutItem = (ConstraintLayout) convertView;
        }

        TextView nameTV = (TextView) layoutItem.findViewById(R.id.nameTV);
        TextView cityTV = (TextView) layoutItem.findViewById(R.id.cityTV);
        TextView propertyTV = (TextView) layoutItem.findViewById(R.id.propertyTV);
        TextView priceTV = (TextView) layoutItem.findViewById(R.id.priceTV);
        ImageView img = (ImageView) layoutItem.findViewById(R.id.imageView);

        ViewHolder viewHolder = (ViewHolder) layoutItem.getTag();

        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.nameTV = (TextView) layoutItem.findViewById(R.id.nameTV);
            viewHolder.cityTV = (TextView) layoutItem.findViewById(R.id.cityTV);
            viewHolder.propertyTV = (TextView) layoutItem.findViewById(R.id.propertyTV);
            viewHolder.priceTV = (TextView) layoutItem.findViewById(R.id.priceTV);
            viewHolder.imageView = (ImageView) layoutItem.findViewById(R.id.imageView);
            layoutItem.setTag(viewHolder);
        }

        viewHolder.nameTV.setText(list.get(position).getName());
        viewHolder.cityTV.setText(list.get(position).getCity());
        viewHolder.propertyTV.setText(list.get(position).getProperty());
        viewHolder.priceTV.setText(list.get(position).getPrice() + " €");
        Glide.with(this.context).load(list.get(position).getPicture()).into(img);

        return layoutItem;

    }

    private class ViewHolder {
        public TextView nameTV;
        public TextView cityTV;
        public TextView propertyTV;
        public TextView priceTV;
        public ImageView imageView;
    }
}