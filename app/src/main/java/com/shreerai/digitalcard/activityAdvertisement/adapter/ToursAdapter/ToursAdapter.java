package com.shreerai.digitalcard.activityAdvertisement.adapter.ToursAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shreerai.digitalcard.activityAdvertisement.model.toursDto.ToursEntity;
import com.shreerai.digitalcard.R;

import java.util.ArrayList;

public class ToursAdapter extends RecyclerView.Adapter<ToursAdapter.ViewHolder> {
    private Context context_dto;
    private ArrayList<ToursEntity> toursEntityArrayList;

    public ToursAdapter(Context context_dto, ArrayList<ToursEntity> toursEntityArrayList) {
        this.context_dto = context_dto;
        this.toursEntityArrayList = toursEntityArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tours, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageTours_v.setImageBitmap(toursEntityArrayList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return toursEntityArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageTours_v;

        public ViewHolder(View itemView) {
            super(itemView);
            imageTours_v = itemView.findViewById(R.id.tours_card);
        }
    }
}
