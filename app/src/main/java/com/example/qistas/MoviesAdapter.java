package com.example.qistas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    private List<String> moviesList;
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
        }
    }
    public MoviesAdapter(List<String> moviesList) {
        this.moviesList = moviesList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movies_list, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String movie = moviesList.get(position);
        holder.title.setText(movie);
    }
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}