package com.shreerai.digitalcard.Contacts.ContactAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shreerai.digitalcard.Contacts.ContactDto.ContactDto;
import com.shreerai.digitalcard.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private ArrayList<ContactDto> contactDtoArrayList;
    private Context context_dco;

    public ContactAdapter(ArrayList<ContactDto> contactDtoArrayList, Context context_dco) {
        this.contactDtoArrayList = contactDtoArrayList;
        this.context_dco = context_dco;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.userType_v.setText(contactDtoArrayList.get(position).getType());
        holder.userName_v.setText(contactDtoArrayList.get(position).getName());
        holder.profileImage_v.setImageBitmap(contactDtoArrayList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return contactDtoArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView profileImage_v;
        private TextView userName_v;
        private TextView userType_v;

        public ViewHolder(View itemView) {
            super(itemView);
            profileImage_v = itemView.findViewById(R.id.user_image);
            userName_v = itemView.findViewById(R.id.user_name);
            userType_v = itemView.findViewById(R.id.user_type);
        }
    }
}