package com.navigation.samael_pc.navigation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FaltantesAdapter extends RecyclerView.Adapter<FaltantesAdapter.FaltantesViewHolder> {

    private Context myContext;
    List<Producto> productos_falt;

    public FaltantesAdapter(Context myContext, List<Producto> productos_falt) {
        this.myContext = myContext;
        this.productos_falt = productos_falt;
    }

    @Override
    public FaltantesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(myContext);
        View view = inflate.inflate(R.layout.card_falt,null);
        FaltantesViewHolder holder = new FaltantesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FaltantesViewHolder holder, int position) {
        final Producto prod = productos_falt.get(position);
        holder.Producto.setText(prod.getNombre()+" "+prod.getContenido_neto());
        //holder.Area.setText(prod.getArea());
        holder.Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), prod.getNombre(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productos_falt.size();
    }

    class FaltantesViewHolder extends RecyclerView.ViewHolder{

        TextView Area, Producto, Agregar;
        public FaltantesViewHolder(View itemView) {
            super(itemView);
            //Area = itemView.findViewById(R.id.area);
            Producto = itemView.findViewById(R.id.name_falt);
            Agregar = itemView.findViewById(R.id.add_falt);

        }
    }
}
