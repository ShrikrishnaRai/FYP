package com.shreerai.digitalcard.activityFriendRequest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shreerai.digitalcard.R;
import com.shreerai.digitalcard.activityFriendRequest.FriendRequest;
import com.shreerai.digitalcard.activityFriendRequest.model.FriendRequestEntity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestAdapter.viewHolder> {
    private List<FriendRequestEntity> friendRequestEntityList;
    Context dco_context;
    FriendRequest friendRequest;

    public FriendRequestAdapter(List<FriendRequestEntity> friendRequestEntityList, Context dco_context, FriendRequest friendRequest) {
        this.friendRequestEntityList = friendRequestEntityList;
        this.dco_context = dco_context;
        this.friendRequest = friendRequest;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_friendrequest, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
        holder.name.setText(friendRequestEntityList.get(position).getFirstname() + " " + friendRequestEntityList.get(position).getLastname());
        holder.designation.setText(friendRequestEntityList.get(position).getPosition());
        holder.acceptProfile_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friendRequest.acceptFriend(
                        friendRequestEntityList.get(position).getId(),
                        friendRequestEntityList.get(position).getFirstname(),
                        friendRequestEntityList.get(position).getLastname(),
                        friendRequestEntityList.get(position).getCompany(),
                        friendRequestEntityList.get(position).getPosition(),
                        friendRequestEntityList.get(position).getImage()
                );
            }
        });
        holder.rejectProfile_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friendRequest.deleteFriendRequest(friendRequestEntityList.get(position).getFirstname());
                friendRequest.displayMessage("Friend request Rejected");
            }
        });
    }

    @Override
    public int getItemCount() {
        return friendRequestEntityList.size();
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
