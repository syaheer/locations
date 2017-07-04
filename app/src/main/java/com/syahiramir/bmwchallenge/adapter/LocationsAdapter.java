package com.syahiramir.bmwchallenge.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syahiramir.bmwchallenge.R;
import com.syahiramir.bmwchallenge.activity.MapActivity;
import com.syahiramir.bmwchallenge.model.Location;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Syahir on 7/3/17.
 * locations adapter for recyclerView
 */

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.MyViewHolder> {

    private final List<Location> locationList;
    private final Context context;

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.location) TextView location;
        @BindView(R.id.address) TextView address;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public LocationsAdapter(List<Location> locationList, Context context) {
        this.locationList = locationList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_location, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Location location = locationList.get(position);
        holder.location.setText(location.getName());
        holder.address.setText(location.getAddress());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, MapActivity.class);
                i.putExtra("location", location);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }
}