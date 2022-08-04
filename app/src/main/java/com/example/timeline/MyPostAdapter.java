package com.example.timeline;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyPostAdapter extends RecyclerView.Adapter<MyPostAdapter.myOwnPostHolder>{

    ArrayList<Post> postHolder;     // ARRAYLIST TO HOLD THE POSTS
    private OnEditListener onEditListener;  // METHOD TO EDIT THE POST
    private Context context;
    ConnectDatabase connectDatabase; // CONNECTION TO DATABASE

    // CONSTRUCTOR OF THIS CLASS
    public MyPostAdapter(ArrayList<Post> postHolder, OnEditListener onEditListener, Context context){
        this.postHolder = postHolder;
        this.onEditListener = onEditListener;
        this.context = context;
        connectDatabase = new ConnectDatabase(context); // DATABASE CONNECTION
    }

    // GETS A SINGLE POST
    @NonNull
    @Override
    public myOwnPostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.own_single_post,parent,false);
        return new myOwnPostHolder(view);
    }

    // SETS THE POST DESCRIPTION, USERNAME AND DATE OF POSTED
    @Override
    public void onBindViewHolder(@NonNull myOwnPostHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(postHolder.get(position).getName());
        holder.description.setText(postHolder.get(position).getDescription());
        holder.post_date.setText(postHolder.get(position).getDate());

        String post_id = postHolder.get(position).getPostId();

        // DELETE A POST FROM THE SELECTED POSITION
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectDatabase.deletePost(post_id);
                postHolder.remove(position);
                notifyDataSetChanged();
            }
        });

        // EDIT THE POST FROM THE SELECTED POSITION
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEditListener.onClickEdit(postHolder.get(position),position);      // CALLS FUNCTION WITHE THE PARAMETERS POSION
                //Log.i("position::",  postHolder.get(position).getPostId());
            }
        });
    }

    // GET THE NUMBER OF POST IN THE DATABASE
    @Override
    public int getItemCount() {
        return postHolder.size();
    }

    // CLASS TO HOLD THE DETAILS OF POST
    class myOwnPostHolder extends RecyclerView.ViewHolder{
        TextView name,description,post_date;
        Button delete,edit;

        public myOwnPostHolder(@NonNull View itemView) {
            super(itemView);
            // UI VATIABLES ARE DECLARED
            name = itemView.findViewById(R.id.own_single_post_name);
            description = itemView.findViewById(R.id.own_single_post_content);
            post_date = itemView.findViewById(R.id.own_single_post_date);
            delete = itemView.findViewById(R.id.own_single_post_delete);
            edit = itemView.findViewById(R.id.own_single_post_Edit);
        }
    }

    // INTERFACE IS CREATED TO EDIT THE POST
    public interface OnEditListener{
        void onClickEdit(Post post, int position);
    }

}
