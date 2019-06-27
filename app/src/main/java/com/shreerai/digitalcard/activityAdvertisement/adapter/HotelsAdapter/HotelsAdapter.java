package com.shreerai.digitalcard.activityAdvertisement.adapter.HotelsAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shreerai.digitalcard.activityAdvertisement.model.hotelDto.HotelEntity;
import com.shreerai.digitalcard.R;

import java.util.ArrayList;

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.ViewHolder> {
    private ArrayList<HotelEntity> hotelEntityArrayList;
    private Context context_dco;

    public HotelsAdapter(ArrayList<HotelEntity> hotelEntityArrayList, Context context_dco) {
        this.hotelEntityArrayList = hotelEntityArrayList;
        this.context_dco = context_dco;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_hotel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardHotels_v.setImageBitmap(hotelEntityArrayList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return hotelEntityArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cardHotels_v;

        public ViewHolder(View itemView) {
            super(itemView);
            cardHotels_v = itemView.findViewById(R.id.hotel_card);
        }
    }
}
