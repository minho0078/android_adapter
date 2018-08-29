package com.minho.playground;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    private ArrayList<ItemModel> itemList;
    private int layout;

    public RecyclerAdapter(Context context, int layout, ArrayList<ItemModel> itemList){
        this.context = context;
        this.itemList = itemList;
        this.layout = layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(this.layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemModel itemModel = itemList.get(position);
        holder.title.setText(itemModel.getTitle());
        holder.subTitle.setText(itemModel.getSubTitle());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

class ViewHolder extends RecyclerView.ViewHolder{
    TextView title;
    TextView subTitle;

    public ViewHolder(View itemView) {
        super(itemView);
        this.title = itemView.findViewById(R.id.item_cardView_title);
        this.subTitle = itemView.findViewById(R.id.item_cardView_subTitle);
    }
}
