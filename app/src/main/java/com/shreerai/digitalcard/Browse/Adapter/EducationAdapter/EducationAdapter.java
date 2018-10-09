package com.shreerai.digitalcard.Browse.Adapter.EducationAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shreerai.digitalcard.Browse.Dto.EducationDto.EducationDto;
import com.shreerai.digitalcard.CardSelectId;
import com.shreerai.digitalcard.EnlargeCard.EnlargeCard;
import com.shreerai.digitalcard.R;

import java.util.ArrayList;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.ViewHolder> {
    ArrayList<EducationDto> educationDtos;
    Context dcoContext;
    CardSelectId cardSelect_ic;

    public EducationAdapter(ArrayList<EducationDto> educationDtos, Context dcoContext) {
        this.educationDtos = educationDtos;
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
        holder.name_v.setText(educationDtos.get(position).getName());
        holder.address_v.setText(educationDtos.get(position).getAddress());
        holder.phone_v.setText(String.valueOf(educationDtos.get(position).getPhone()));
        cardSelect_ic.setId(educationDtos.get(position).getId());
        holder.relativeLayout_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dcoContext, EnlargeCard.class);
                intent.putExtra("name", educationDtos.get(position).getName());
                intent.putExtra("address", educationDtos.get(position).getAddress());
                intent.putExtra("phone", String.valueOf(educationDtos.get(position).getPhone()));
                intent.putExtra("cardValue", educationDtos.get(position).getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                dcoContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return educationDtos.size();
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
