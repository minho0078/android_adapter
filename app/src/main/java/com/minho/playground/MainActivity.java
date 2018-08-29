package com.minho.playground;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView main_gridView;
    private RecyclerView main_recyclerView;

    int TargetPosition_X = 0;
    int recyclerView_WIDTH = 0;
    int item_WIDTH = 0;
    int recyclerView_PADDING = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ItemModel> itemList = getItemList();

        /* TODO - GridView Start */
        main_gridView = findViewById(R.id.main_gridView);
        GridItemAdapter adapter = new GridItemAdapter(getApplicationContext(), R.layout.ittem_default, itemList);
        main_gridView.setAdapter(adapter);

        /* TODO - RecyclerView Start */
        main_recyclerView = findViewById(R.id.main_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        main_recyclerView.setLayoutManager(linearLayoutManager);

        RecyclerView.Adapter recyclerViewAdapter = new RecyclerAdapter(getApplicationContext(), R.layout.item_cardview, itemList);

        recyclerView_WIDTH = main_recyclerView.getMeasuredWidth();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        item_WIDTH = size.x;
        recyclerView_PADDING = (recyclerView_WIDTH - item_WIDTH) / 2;

        main_recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                synchronized (this) {
                    if(newState == RecyclerView.SCROLL_STATE_IDLE){
                        calculatePositionAndScrollCard(recyclerView);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                TargetPosition_X += dx;
            }
        });

        main_recyclerView.setAdapter(recyclerViewAdapter);

    }

    private void calculatePositionAndScrollCard(RecyclerView recyclerView) {
        int expectedPosition = Math.round(TargetPosition_X / item_WIDTH);

        if (expectedPosition == -1) {
            expectedPosition = 0;
        } else if (expectedPosition >= recyclerView.getAdapter().getItemCount() - 2) {
            expectedPosition--;
        }
        scrollListToPosition(recyclerView, expectedPosition);
    }

    private void scrollListToPosition(RecyclerView recyclerView, int expectedPositionDate) {
        float targetScrollPosition = expectedPositionDate * item_WIDTH;
        float targetPixcelPosition = targetScrollPosition - TargetPosition_X;
        if (targetPixcelPosition != 0) {
            recyclerView.smoothScrollBy((int) targetPixcelPosition, 0);
        }
    }

    public ArrayList<ItemModel> getItemList(){
        ArrayList<ItemModel> itemList = new ArrayList<>();
        for (int i=0;i<100;i++){
            ItemModel itemModel = new ItemModel();
            itemModel.setTitle("test_"+i);
            itemModel.setSubTitle("subtitle_"+i);

            itemList.add(itemModel);
        }
        return itemList;
    }
}
