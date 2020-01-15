package com.example.qistas;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ThirdAdapter extends RecyclerView.Adapter<ThirdAdapter.viewHolder> {

    Context context;
    ArrayList<String> arrayList;
    public final int VIEW_TYPE_FOOTER = 0;
    private TextInterface textInterface;

    public ThirdAdapter(Context context, ArrayList<String> arrayList, TextInterface textInterface) {
        this.context = context;
        this.arrayList = arrayList;
        this.textInterface = textInterface;
    }

    @Override
    public  ThirdAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.third_list, viewGroup, false);
        return new viewHolder(view);
    }
    @Override
    public  void onBindViewHolder(ThirdAdapter.viewHolder holder, final int position) {
        holder.title.setText(arrayList.get(position));
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInterface.setThirdText(arrayList.get(position));
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return (position == arrayList.size()) ? VIEW_TYPE_FOOTER : 0;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    /*public void filterList(ArrayList<String> searchItems) {
        arrayList.clear();
        arrayList.addAll(searchItems);
        this.notifyDataSetChanged();
    }*/

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public viewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title3);

        }
    }
}
