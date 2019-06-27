package com.shreerai.digitalcard.activitySearch.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shreerai.digitalcard.addCardActivity.AddCardActivity;
import com.shreerai.digitalcard.R;
import com.shreerai.digitalcard.activitySearch.model.SearchEntity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.ViewHolder> {

    private ArrayList<SearchEntity> searchEntityArrayList;
    private Context context_dco;

    public SearchViewAdapter(ArrayList<SearchEntity> searchEntityArrayList, Context context_dco) {
        this.searchEntityArrayList = searchEntityArrayList;
        this.context_dco = context_dco;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_search, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name_v.setText(searchEntityArrayList.get(position).getFirstname() + " " + searchEntityArrayList.get(position).getLastname());
        holder.nickName_v.setText(searchEntityArrayList.get(position).getPosition());
        holder.cardSearchRelative_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context_dco, AddCardActivity.class);
                intent.putExtra("firstname", searchEntityArrayList.get(position).getFirstname());
                intent.putExtra("lastname", searchEntityArrayList.get(position).getLastname());
                intent.putExtra("position", searchEntityArrayList.get(position).getPosition());
                intent.putExtra("id", searchEntityArrayList.get(position).getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context_dco.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchEntityArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name_v;
        private TextView nickName_v;
        private CircleImageView circleImageView_v;
        private RelativeLayout cardSearchRelative_v;

        public ViewHolder(View itemView) {
            super(itemView);
            name_v = itemView.findViewById(R.id.search_name);
            nickName_v = itemView.findViewById(R.id.search_type);
            circleImageView_v = itemView.findViewById(R.id.search_image);
            cardSearchRelative_v = itemView.findViewById(R.id.cardSearch_relative);
        }
    }
}
