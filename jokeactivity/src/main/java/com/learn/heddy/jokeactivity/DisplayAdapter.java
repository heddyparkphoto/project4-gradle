package com.learn.heddy.jokeactivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hyeryungpark on 1/11/17.
 */
public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.DisplayViewHolder>  {

    private Context mContext;
    private ArrayList<String> mJokesList;

    public DisplayAdapter(Context context){
        mContext = context;
    }

    public class DisplayViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public DisplayViewHolder(View view){
            super(view);
            textView = (TextView)view.findViewById(R.id.list_item_textview);
        }

    }

    @Override
    public DisplayAdapter.DisplayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_joke_item, parent, false);

        DisplayViewHolder holder = new DisplayViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DisplayViewHolder holder, int position) {
        if (mJokesList!=null) {
            String oneJoke = mJokesList.get(position);
            holder.textView.setText(oneJoke);
        }
    }

    public void completeManyJokes(ArrayList<String> jokesList){
        mJokesList = jokesList;
    }

        @Override
    public int getItemCount() {
        if (mJokesList!=null){
            return mJokesList.size();
        }
        return 0;
    }

}
