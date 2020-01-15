package com.example.qistas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.viewHolder> {

    Context context;
    ArrayList<String> arrayList;
    TextInterface textInterface;

    public SecondAdapter(Context context, ArrayList<String> arrayList, TextInterface textInterface) {
        this.context = context;
        this.arrayList = arrayList;
        this.textInterface = textInterface;
    }

    @Override
    public  SecondAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.movies_list, viewGroup, false);
        return new viewHolder(view);
    }
    @Override
    public  void onBindViewHolder(SecondAdapter.viewHolder holder, final int position) {
        holder.title.setText(arrayList.get(position));
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInterface.setSecondText(arrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public viewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
        }
    }

}
