package com.example.timeline;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myPostHolder>{

    ArrayList<Post> postHolder;     // ARRAYLIST TO HOLD THE POST ARRAY

    public MyAdapter(ArrayList<Post> postHolder ){ // CONSTRUCTOR OF THIS CLASS
        this.postHolder = postHolder;
    }

    // RECYCLERVIEW ADDED THE SINGLE POST
    @NonNull
    @Override
    public myPostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_single_post,parent,false);
        return new myPostHolder(view);
    }

    // HOLDS THE PARTICULAR POST NAME, DESCRIPTION AND DATE POSTED
    @Override
    public void onBindViewHolder(@NonNull myPostHolder holder, int position) {
        holder.name.setText(postHolder.get(position).getName());
        holder.description.setText(postHolder.get(position).getDescription());
        holder.post_date.setText(postHolder.get(position).getDate());
    }

    // RETURN THE SIZE OF THE POST IN DATABASE
    @Override
    public int getItemCount() {
        return postHolder.size();
    }

    // GETS THE SINGLE POST NAME DESCRIPTOIN AND POST DATE
    class myPostHolder extends RecyclerView.ViewHolder{
        TextView name,description,post_date;

        public myPostHolder(@NonNull View itemView) {
            super(itemView);        // CAN GET UI VARIABLES FROM ANY LAYOUT
            name = itemView.findViewById(R.id.my_timeline_user_name);
            description = itemView.findViewById(R.id.my_timeline_user_content);
            post_date = itemView.findViewById(R.id.my_timeline_user_post_date);
        }
    }



}
