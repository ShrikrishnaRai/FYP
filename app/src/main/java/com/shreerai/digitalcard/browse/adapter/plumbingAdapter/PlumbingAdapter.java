package com.shreerai.digitalcard.browse.adapter.plumbingAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shreerai.digitalcard.browse.dto.plumbingDto.PlumbingDto;
import com.shreerai.digitalcard.R;

import java.util.ArrayList;

public class PlumbingAdapter extends RecyclerView.Adapter<PlumbingAdapter.ViewHolder> {
    private ArrayList<PlumbingDto> plumbingDtoArrayList;
    private Context context_dco;

    public PlumbingAdapter(ArrayList<PlumbingDto> plumbingDtoArrayList, Context context_dco) {
        this.plumbingDtoArrayList = plumbingDtoArrayList;
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
        holder.hotelCard_v.setImageBitmap(plumbingDtoArrayList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return plumbingDtoArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView hotelCard_v;

        public ViewHolder(View itemView) {
            super(itemView);
            hotelCard_v = itemView.findViewById(R.id.plumbing_card);
        }
    }
}
