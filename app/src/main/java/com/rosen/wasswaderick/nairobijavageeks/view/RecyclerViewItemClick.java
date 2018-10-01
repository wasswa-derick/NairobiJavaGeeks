package com.rosen.wasswaderick.nairobijavageeks.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Derick W on 27,September,2018
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
public class RecyclerViewItemClick implements RecyclerView.OnItemTouchListener{

    private onItemClickListener my_listener;

    public interface onItemClickListener{
        public void onItemClick(View view, int position);
    }

    GestureDetector gestureDetector;

    public RecyclerViewItemClick(Context context, onItemClickListener my_listener) {
        this.my_listener = my_listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        View v = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (v != null && my_listener != null && gestureDetector.onTouchEvent(motionEvent)) {
            my_listener.onItemClick(v, recyclerView.getChildPosition(v));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        //
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {
    }

}
