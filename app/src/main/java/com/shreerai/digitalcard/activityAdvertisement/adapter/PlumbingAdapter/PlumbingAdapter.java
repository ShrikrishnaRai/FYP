package com.shreerai.digitalcard.activityAdvertisement.adapter.PlumbingAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shreerai.digitalcard.activityAdvertisement.model.plumbingDto.PlumbingEntity;
import com.shreerai.digitalcard.R;

import java.util.ArrayList;

public class PlumbingAdapter extends RecyclerView.Adapter<PlumbingAdapter.ViewHolder> {
    private ArrayList<PlumbingEntity> plumbingEntityArrayList;
    private Context context_dco;

    public PlumbingAdapter(ArrayList<PlumbingEntity> plumbingEntityArrayList, Context context_dco) {
        this.plumbingEntityArrayList = plumbingEntityArrayList;
        this.context_dco = context_dco;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_plumbing, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.hotelCard_v.setImageBitmap(plumbingEntityArrayList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return plumbingEntityArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView hotelCard_v;

        public ViewHolder(View itemView) {
            super(itemView);
            hotelCard_v = itemView.findViewById(R.id.plumbing_card);
        }
    }
}
