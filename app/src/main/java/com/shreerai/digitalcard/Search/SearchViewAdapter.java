package com.shreerai.digitalcard.Search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shreerai.digitalcard.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.ViewHolder> {

    private ArrayList<SearchDto> searchDtoArrayList;
    private Context context_dco;

    public SearchViewAdapter(ArrayList<SearchDto> searchDtoArrayList, Context context_dco) {
        this.searchDtoArrayList = searchDtoArrayList;
        this.context_dco = context_dco;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_search, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name_v.setText(searchDtoArrayList.get(position).getName());
        holder.nickName_v.setText(searchDtoArrayList.get(position).getNickName());
    }

    @Override
    public int getItemCount() {
        return searchDtoArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name_v;
        private TextView nickName_v;
        private CircleImageView circleImageView_v;

        public ViewHolder(View itemView) {
            super(itemView);
            name_v = itemView.findViewById(R.id.search_name);
            nickName_v = itemView.findViewById(R.id.search_type);
            circleImageView_v = itemView.findViewById(R.id.search_image);
        }
    }
}
