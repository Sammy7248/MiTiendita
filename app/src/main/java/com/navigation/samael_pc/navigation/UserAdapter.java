package com.navigation.samael_pc.navigation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public void onBindViewHolder(UserViewHolder holder, int position) {

        Usuario user = list.get(position);
        //holder.apellido.setText(user.getApellido().toString());
        holder.username.setText(user.getUsername().toString());
        holder.name.setText(user.getName().toString());
        holder.local.setText(user.getId_local().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView username, name, apellido, local;


        public UserViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            name = itemView.findViewById(R.id.name);
            //apellido = itemView.findViewById(R.id.apellido);
            local = itemView.findViewById(R.id.local);
        }
    }
}

