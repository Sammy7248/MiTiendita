package com.navigation.samael_pc.navigation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> implements Filterable {

    private Context myContext;
    List<Usuario> list;
    List<Usuario> list_all;

    public UserAdapter(Context myContext, List<Usuario> list) {
        this.myContext = myContext;
        this.list = list;
        this.list_all = new ArrayList<>(list);
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


        //holder.apellido.setText(user.getApellido().toString());
        char[] name_div = user.getUsername().toCharArray();
        holder.ident.setText(String.valueOf(name_div[0]));
        holder.ident.setBackgroundColor(getRandomColor());
        //holder.header.setBackgroundColor(getRandomColor());
        holder.username.setText(user.getUsername().toString().toLowerCase());
        holder.name.setText(user.getName().toString() + " " + user.getApellido());
        if(user.getFecha().equals(general.fecha)){
            holder.user_Date.setText(user.getFecha());
        }
        else{
            holder.user_Date.setText(user.getHora().toString());
        }
//        holder.local.setText(user.getId_local().toString());
        //holder.super_user.setText( su );
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

    public int getRandomColor(){
        Random rd = new Random();
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.argb(255,5,30,97));
        colors.add(Color.argb(255,28,3,50));
        colors.add(Color.argb(255,8,3,50));
        colors.add(Color.argb(255,3,25,50));
        colors.add(Color.argb(255,35,3,50));
        return colors.get(rd.nextInt(colors.size()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Usuario> filtered_user = new ArrayList<>();

            if(constraint.toString().isEmpty()){
                filtered_user.addAll(list_all);
            }
            else{
                for (Usuario us:list_all){
                    if(us.getUsername().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filtered_user.add(us);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filtered_user;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            list.clear();
            list.addAll((Collection<? extends Usuario>) results.values);
            notifyDataSetChanged();
        }
    };

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView username, name, local, super_user, user_Date, ident, header;
        TextView delete_value, edit_value;


        public UserViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            name = itemView.findViewById(R.id.name);
            //apellido = itemView.findViewById(R.id.apellido);
            //local = itemView.findViewById(R.id.local);
            user_Date = itemView.findViewById(R.id.user_date);
            delete_value =  itemView.findViewById(R.id.delete_value);
            edit_value = itemView.findViewById(R.id.edit_value);
            //super_user = itemView.findViewById(R.id.super_user);
            ident = itemView.findViewById(R.id.ident);
            //header = itemView.findViewById(R.id.header);

        }
    }
}

