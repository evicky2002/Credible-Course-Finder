package com.example.crediblecoursefinder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>  {
    private List<CourseViewModel> localDataSet;
    public ItemAdapter(List<CourseViewModel> courseViewModelList) {
        localDataSet = courseViewModelList;
        Log.i("HELLOBRO", "item : "+localDataSet.get(0).getTitle());

    }
    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        holder.getTitleTextView().setText(localDataSet.get(position).getTitle());
        holder.getRatingTextView().setText(localDataSet.get(position).getRatings().toString());

    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final TextView tvRating;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvRating = (TextView) view.findViewById(R.id.tvRating);
        }
        public TextView getTitleTextView() {
            return tvTitle;
        }
        public TextView getRatingTextView() {
            return tvRating;
        }

    }
}
