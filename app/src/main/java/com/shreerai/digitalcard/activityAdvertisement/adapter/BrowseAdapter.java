package com.shreerai.digitalcard.activityAdvertisement.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shreerai.digitalcard.activityAdvertisement.model.BrowseDto;
import com.shreerai.digitalcard.R;

import java.util.ArrayList;

public class BrowseAdapter extends RecyclerView.Adapter<BrowseAdapter.ViewHolder> {

    private Context context_dco;
    private ArrayList<BrowseDto> browseDtoArrayList;

    public BrowseAdapter(Context context_dco, ArrayList<BrowseDto> browseDtoArrayList) {
        this.context_dco = context_dco;
        this.browseDtoArrayList = browseDtoArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_browse, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.browseImageView_v.setImageBitmap(browseDtoArrayList.get(position).getImage());
        holder.browseTextView_v.setText(browseDtoArrayList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return browseDtoArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView browseImageView_v;
        private TextView browseTextView_v;

        public ViewHolder(View itemView) {
            super(itemView);
            browseImageView_v = itemView.findViewById(R.id.imageView_browse);
            browseTextView_v = itemView.findViewById(R.id.title_browse);
        }
    }
}
