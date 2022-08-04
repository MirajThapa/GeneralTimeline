package com.example.timeline;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.adminSeeUsers>{

    ArrayList<Person> postHolder;   // ARRAYLIST TO HOLD ALL THE USERS PASS FROM THE OTHER CLASS
    private Context context;
    ConnectDatabase connectDatabase;

    public AdminAdapter(ArrayList<Person> postHolder,Context context){  //CONSTRUCTOR OF THIS CLASS
        this.postHolder = postHolder;
        this.context = context;
        connectDatabase = new ConnectDatabase(context);     // CONNECTING TO DATABASE
    }

    // GETS CONNECTED TO THE SINGLE LAYOUT BUILT FOR RECYCLERVIEW IN UI
    @NonNull
    @Override
    public adminSeeUsers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_view_user_data,parent,false);
        return new AdminAdapter.adminSeeUsers(view);
    }

    // SETS PARTICULAR NAME AND EMAIL AND FUNCTION TO DELETE USER
    @Override
    public void onBindViewHolder(@NonNull adminSeeUsers holder, @SuppressLint("RecyclerView") int position) {
        holder._name.setText(postHolder.get(position).getFirstName());
        holder._email.setText(postHolder.get(position).getEmailAddress());

        // DELETE USER
        holder.btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectDatabase.deletePostByAdmin(postHolder.get(position).getPersonId()); // DELETE USER FROM DATABASE
                connectDatabase.deleteUser(postHolder.get(position).getPersonId());     // DELETE ITS ALL POSTS
                postHolder.remove(position);
                notifyDataSetChanged();

            }
        });
    }

    // COUNT OF THE USER IN DATABASE
    @Override
    public int getItemCount() {
        return postHolder.size();
    }

    class adminSeeUsers extends RecyclerView.ViewHolder{
        TextView _name,_email;
        Button btnDeleteUser;

        public adminSeeUsers(@NonNull View itemView) {
            super(itemView);        // GETS THE VIEW FROM ANY LAYOUT
            //SETS VARIABLES OF VIEW
            _name = itemView.findViewById(R.id.admin_view_user_name);
            _email = itemView.findViewById(R.id.admin_view_user_email);
            btnDeleteUser = itemView.findViewById(R.id.admin_view_user_delete);
        }
    }

}
