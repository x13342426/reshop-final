package com.example.myblog.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.myblog.Models.Shoe;
import com.example.myblog.R;
import com.example.myblog.ShoeDetailActivity;

import java.util.List;

public class ShoeAdapter extends RecyclerView.Adapter<ShoeAdapter.MyViewHolder> {

    Context mContext;
    List<Shoe> mData1 ;


    public ShoeAdapter(Context mContext, List<Shoe> mData1) {
        this.mContext = mContext;
        this.mData1 = mData1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.row_shoe_item ,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvTitle.setText(mData1.get(position).getTitle());
        Glide.with(mContext).load(mData1.get(position).getPicture()).into(holder.shoeImg);
        Glide.with(mContext).load(mData1.get(position).getUserPhoto()).into(holder.shoeProfile);

    }

    @Override
    public int getItemCount() {
        return mData1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageView shoeImg;
        ImageView shoeProfile;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.row_shoe_title);
            shoeImg = itemView.findViewById(R.id.row_shoe_img);
            shoeProfile = itemView.findViewById(R.id.row_shoe_profile_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent shoeDetailActivity = new Intent(mContext, ShoeDetailActivity.class);
                    int position = getAdapterPosition();

                    shoeDetailActivity.putExtra("title",mData1.get(position).getTitle());
                    shoeDetailActivity.putExtra("shoeImage",mData1.get(position).getPicture());
                    shoeDetailActivity.putExtra("shoedescription",mData1.get(position).getDescription());
                    shoeDetailActivity.putExtra("shoeKey",mData1.get(position).getShoeKey());
                    shoeDetailActivity.putExtra("userPhoto",mData1.get(position).getUserPhoto());
                    // will fix this later i forgot to add user name to post object
                    //postDetailActivity.putExtra("userName",mData.get(position).getUsername);
                    long timestamp  = (long) mData1.get(position).getTimeStamp();
                    shoeDetailActivity.putExtra("shoeDate",timestamp) ;
                    mContext.startActivity(shoeDetailActivity);



                }
            });

        }


    }
}
