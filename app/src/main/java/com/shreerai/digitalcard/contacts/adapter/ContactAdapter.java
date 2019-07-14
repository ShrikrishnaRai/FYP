package com.shreerai.digitalcard.contacts.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shreerai.digitalcard.contacts.model.ContactEntity;
import com.shreerai.digitalcard.enlargeContact.EnlargeCardContact;
import com.shreerai.digitalcard.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private List<ContactEntity> contactEntityArrayList;
    private Context context_dco;

    public ContactAdapter(List<ContactEntity> contactEntityArrayList, Context context_dco) {
        this.contactEntityArrayList = contactEntityArrayList;
        this.context_dco = context_dco;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.userName_v.setText(contactEntityArrayList.get(position).getFirstname() + " " + contactEntityArrayList.get(position).getLastname());
        holder.userType_v.setText(contactEntityArrayList.get(position).getPosition());
        holder.recyclerView_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context_dco, EnlargeCardContact.class);
                intent.putExtra("firstname", contactEntityArrayList.get(position).getFirstname());
                intent.putExtra("lastname", contactEntityArrayList.get(position).getLastname());
                intent.putExtra("position", contactEntityArrayList.get(position).getPosition());
                intent.putExtra("cardValue", contactEntityArrayList.get(position).getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context_dco.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactEntityArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView profileImage_v;
        private TextView userName_v;
        private TextView userType_v;
        private RelativeLayout recyclerView_v;

        public ViewHolder(View itemView) {
            super(itemView);
            profileImage_v = itemView.findViewById(R.id.user_image);
            userName_v = itemView.findViewById(R.id.user_name);
            userType_v = itemView.findViewById(R.id.user_type);
            recyclerView_v = itemView.findViewById(R.id.rel_contact);
        }
    }
}
