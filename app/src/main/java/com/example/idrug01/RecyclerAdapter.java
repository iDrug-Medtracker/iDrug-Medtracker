package com.example.idrug01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ExampleViewHolder> {
    private ArrayList<medication> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;


        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.medicationView);
            mTextView2 = itemView.findViewById(R.id.timesView);
        }
    }

    public RecyclerAdapter(ArrayList<medication> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        medication currentItem = mExampleList.get(position);

        holder.mTextView1.setText(currentItem.getName());
        holder.mTextView2.setText(currentItem.getTimes());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
