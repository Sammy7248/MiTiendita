package com.navigation.samael_pc.navigation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        Usuario user = list.get(position);
        String su= "";
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

