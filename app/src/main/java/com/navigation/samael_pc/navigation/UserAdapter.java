package com.navigation.samael_pc.navigation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context myContext;
    List<Usuario> list;

    public UserAdapter(Context myContext, List<Usuario> list) {
        this.myContext = myContext;
        this.list = list;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(myContext);
        View view = inflate.inflate(R.layout.cardview, null);
        UserViewHolder holder = new UserViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {

        final Usuario user = list.get(position);
        String su= "";
        final General general = General.getInstance();

        if(user.getIs_super_user() == true){
            su = "Si";
        }
        else {
            su = "No";
        }
        //holder.apellido.setText(user.getApellido().toString());
        holder.username.setText("Username: "+user.getUsername().toString().toLowerCase());
        holder.name.setText("Nombre: "+user.getName().toString() + " " + user.getApellido());
        holder.local.setText("Local: "+user.getId_local().toString());
        holder.super_user.setText("SuperUsuario: " + su );
        holder.delete_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), holder.username.getText(), Toast.LENGTH_LONG).show();
            }
        });


        holder.edit_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(myContext);
                LayoutInflater inflate = LayoutInflater.from(v.getContext());
                View view = inflate.inflate(R.layout.delete_confirm,null);
                dialog.setView(view);
                dialog.create().show();


                TextView delete, no_delete;
                delete = view.findViewById(R.id.delete);
                no_delete = view.findViewById(R.id.no_delete);


                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Delete...", Toast.LENGTH_LONG).show();
                        Log.i("Key",user.getKey());
                        general.reference.child("Usuarios").child(user.getKey()).removeValue();
                    }
                });

                no_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView username, name, local, super_user;
        ImageView delete_value, edit_value;


        public UserViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            name = itemView.findViewById(R.id.name);
            //apellido = itemView.findViewById(R.id.apellido);
            local = itemView.findViewById(R.id.local);
            delete_value =  itemView.findViewById(R.id.delete_value);
            edit_value = itemView.findViewById(R.id.edit_value);
            super_user = itemView.findViewById(R.id.super_user);

        }
    }
}

