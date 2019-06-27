package com.shreerai.digitalcard.activityAdvertisement.adapter.EducationAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shreerai.digitalcard.activityAdvertisement.model.educationDto.EducationEntity;
import com.shreerai.digitalcard.CardSelectId;
import com.shreerai.digitalcard.activityCardDisplay.CardDisplayActivity;
import com.shreerai.digitalcard.R;

import java.util.ArrayList;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.ViewHolder> {
    ArrayList<EducationEntity> educationEntities;
    Context dcoContext;
    CardSelectId cardSelect_ic;

    public EducationAdapter(ArrayList<EducationEntity> educationEntities, Context dcoContext) {
        this.educationEntities = educationEntities;
        this.dcoContext = dcoContext;
        cardSelect_ic = new CardSelectId();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_education, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.name_v.setText(educationEntities.get(position).getName());
        holder.address_v.setText(educationEntities.get(position).getAddress());
        holder.phone_v.setText(String.valueOf(educationEntities.get(position).getPhone()));
        cardSelect_ic.setId(educationEntities.get(position).getId());
        holder.relativeLayout_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dcoContext, CardDisplayActivity.class);
                intent.putExtra("name", educationEntities.get(position).getName());
                intent.putExtra("address", educationEntities.get(position).getAddress());
                intent.putExtra("phone", String.valueOf(educationEntities.get(position).getPhone()));
                intent.putExtra("cardValue", educationEntities.get(position).getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                dcoContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return educationEntities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout relativeLayout_v;
        private TextView name_v;
        private TextView address_v;
        private TextView phone_v;

        public ViewHolder(View itemView) {
            super(itemView);
            name_v = itemView.findViewById(R.id.education_name);
            address_v = itemView.findViewById(R.id.education_address);
            phone_v = itemView.findViewById(R.id.education_phone);
            relativeLayout_v = itemView.findViewById(R.id.education_relative);
        }
    }

}
