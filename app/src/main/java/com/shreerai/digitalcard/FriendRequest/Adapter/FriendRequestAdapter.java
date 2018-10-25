package com.shreerai.digitalcard.FriendRequest.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shreerai.digitalcard.FriendRequest.Dto.FriendRequestDto;
import com.shreerai.digitalcard.R;
import com.shreerai.digitalcard.Search.Dto.SearchDto;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestAdapter.viewHolder> {
    private ArrayList<SearchDto> searchDtoList;
    Context dco_context;

    public FriendRequestAdapter(ArrayList<SearchDto> searchDtoList, Context dco_context) {
        this.searchDtoList = searchDtoList;
        this.dco_context = dco_context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_friendrequest, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.name.setText(searchDtoList.get(position).getFirstname() + "" + searchDtoList.get(position).getLastname());
        holder.designation.setText(searchDtoList.get(position).getPosition());
        holder.acceptProfile_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchDtoList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView designation;
        CircleImageView acceptProfile_v;
        CircleImageView rejectProfile_v;


        public viewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_friendRequest);
            designation = itemView.findViewById(R.id.designation);
            acceptProfile_v = itemView.findViewById(R.id.acceptProfile);
            rejectProfile_v = itemView.findViewById(R.id.rejectProfile);
        }
    }
}
