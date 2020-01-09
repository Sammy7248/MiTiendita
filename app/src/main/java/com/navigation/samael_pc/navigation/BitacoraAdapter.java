package com.navigation.samael_pc.navigation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BitacoraAdapter extends RecyclerView.Adapter<BitacoraAdapter.BitacoraViewHolder> {


    private Context myContext;
    List<Bitacora_Obj> list_bit;
    General gen;

    public BitacoraAdapter(Context myContext, List<Bitacora_Obj> list_bit) {
        this.myContext = myContext;
        this.list_bit = list_bit;
        this.gen = General.getInstance();
    }

    @Override
    public BitacoraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(myContext);
        View view = inflate.inflate(R.layout.card_bit,null);
        BitacoraViewHolder holder = new BitacoraViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BitacoraViewHolder holder, int position) {

        Bitacora_Obj bit = list_bit.get(position);
        holder.Accion.setText(bit.Accion);
        holder.Area.setText(bit.Area);
        holder.Username.setText(bit.Usuario);
        if(bit.getFecha().equals(gen.fecha)){
            holder.Fecha.setText(bit.Hora);
        }
        else{
            holder.Fecha.setText(bit.Fecha);
        }

        char[] first = bit.Area.toCharArray();
        holder.Img_let.setText(String.valueOf(first[0]));

    }

    @Override
    public int getItemCount() {
        return list_bit.size();
    }

    class BitacoraViewHolder extends RecyclerView.ViewHolder{

        TextView Area, Fecha, Accion, Username, Img_let;
        public BitacoraViewHolder(View itemView) {
            super(itemView);
            Area = itemView.findViewById(R.id.area);
            Fecha = itemView.findViewById(R.id.fecha);
            Accion = itemView.findViewById(R.id.accion);
            Username = itemView.findViewById(R.id.username);
            Img_let = itemView.findViewById(R.id.img_let);
        }
    }
}
