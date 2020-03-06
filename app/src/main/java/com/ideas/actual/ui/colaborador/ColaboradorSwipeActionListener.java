package com.ideas.actual.ui.colaborador;

import android.content.Context;
import android.widget.Toast;

import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import com.wdullaer.swipeactionadapter.SwipeDirection;

public class ColaboradorSwipeActionListener implements SwipeActionAdapter.SwipeActionListener {


    private Context context;
    private SwipeActionAdapter mAdapter;

    public ColaboradorSwipeActionListener(Context context, SwipeActionAdapter mAdapter) {
        this.context = context;
        this.mAdapter = mAdapter;
    }

    @Override
    public boolean hasActions(int position, SwipeDirection direction) {
        return false;
    }

    @Override
    public boolean shouldDismiss(int position, SwipeDirection direction) {
        return false;
    }

    @Override
    public void onSwipe(int[] positionList, SwipeDirection[] directionList) {
        for(int i=0;i<positionList.length;i++) {
            int position = positionList[i];
            SwipeDirection direction = directionList[i];
            String dir = "";

            if(direction.isLeft()){
                dir = "Far left";
            }
            else if(direction.isRight()){
                dir = "Right";
            }
            Toast.makeText(context,dir + " swipe Action triggered on " + mAdapter.getItem(position),Toast.LENGTH_SHORT).show();
            mAdapter.notifyDataSetChanged();
        }
    }
}
