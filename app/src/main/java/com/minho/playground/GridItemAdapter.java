package com.minho.playground;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GridItemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ItemModel> gridItemList;
    private int layout;

    LayoutInflater layoutInflater;

    public GridItemAdapter(Context context, int layout, ArrayList<ItemModel> gridItemList){
        this.context = context;
        this.layout = layout;
        this.gridItemList = gridItemList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return gridItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return gridItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final GridViewHolder holder;

        if(view == null){
            view = layoutInflater.inflate(layout, null);

            holder = new GridViewHolder();
            holder.tv_title = view.findViewById(R.id.grid_item_title);
            holder.tv_subTitle = view.findViewById(R.id.grid_item_subTitle);
            view.setTag(holder);
        }else{
            holder = (GridViewHolder) view.getTag();
        }

        holder.tv_title.setText(gridItemList.get(i).getTitle());
        holder.tv_subTitle.setText(gridItemList.get(i).getSubTitle());

        return view;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}


class GridViewHolder{
    TextView tv_title;
    TextView tv_subTitle;
}