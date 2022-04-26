package com.android.foodorderapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.foodorderapp.R;
import com.android.foodorderapp.model.PlantStore;
import com.bumptech.glide.Glide;

import java.util.List;

public class PlantListAdapter extends RecyclerView.Adapter<PlantListAdapter.MyViewHolder> {

    private List<PlantStore> PlantStoreModelList;
    private PlantStoreListClickListener clickListener;

    public PlantListAdapter(List<PlantStore> PlantStoreModelList, PlantStoreListClickListener clickListener) {
        this.PlantStoreModelList = PlantStoreModelList;
        this.clickListener = clickListener;
    }

    public void updateData(List<PlantStore> PlantStoreModelList) {
        this.PlantStoreModelList = PlantStoreModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlantListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantListAdapter.MyViewHolder holder, int position) {
        holder.PlantStoreName.setText(PlantStoreModelList.get(position).getName());
        holder.PlantStoreAddress.setText("Address: "+PlantStoreModelList.get(position).getAddress());
        holder.PlantStoreHours.setText("Today's hours: " + PlantStoreModelList.get(position).getHours().getTodaysHours());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(PlantStoreModelList.get(position));
            }
        });
        Glide.with(holder.thumbImage)
                .load(PlantStoreModelList.get(position).getImage())
                .into(holder.thumbImage);

    }

    @Override
    public int getItemCount() {
        return PlantStoreModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  PlantStoreName;
        TextView  PlantStoreAddress;
        TextView  PlantStoreHours;
        ImageView thumbImage;

        public MyViewHolder(View view) {
            super(view);
            PlantStoreName = view.findViewById(R.id.PlantStoreName);
            PlantStoreAddress = view.findViewById(R.id.PlantStoreAddress);
            PlantStoreHours = view.findViewById(R.id.PlantStoreHours);
            thumbImage = view.findViewById(R.id.thumbImage);

        }
    }

    public interface PlantStoreListClickListener {
        public void onItemClick(PlantStore PlantStoreModel);
    }
}
