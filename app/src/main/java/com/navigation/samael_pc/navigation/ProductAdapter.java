package com.navigation.samael_pc.navigation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.navigation.samael_pc.navigation.R.id.area;
import static com.navigation.samael_pc.navigation.R.id.card_product;
import static com.navigation.samael_pc.navigation.R.id.producto_name;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private Context context;
    ArrayList<Producto> product_list;

    public ProductAdapter(Context context, ArrayList<Producto> product_list) {
        this.context = context;
        this.product_list = product_list;
    }

    @Override
    public ProductAdapter.ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_productos, null);
        ProductHolder holder = new ProductHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ProductHolder holder, int position) {

        Producto product = product_list.get(position);

        holder.product_name.setText("Producto: "+product.getNombre().toString());
        holder.price.setText("Precio: $"+product.getPrecio().toString());
        holder.expiration.setText("Fecha De Caducidad: "+product.getCaducidad().toString());
        holder.existence.setText("Existencia: "+String.valueOf(product.getTotal_existencia()).toString());
        holder.area.setText("Area: "+product.getArea().toString());

        if(product.area.toString().equals("Abarrotes")){
            holder.image.setImageResource(R.drawable.abarrotes);
        }
        if(product.area.toString().equals("Papeleria")){
            holder.image.setImageResource(R.drawable.papeleria);
        }
        if(product.area.toString().equals("Ropa")){
            holder.image.setImageResource(R.drawable.ropa);
        }

    }

    @Override
    public int getItemCount() {
        return product_list.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder{

        TextView product_name, price, expiration, existence, area;
        ImageView image;

        public ProductHolder(View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.producto_name);
            price = itemView.findViewById(R.id.price);
            expiration = itemView.findViewById(R.id.expiration);
            existence = itemView.findViewById(R.id.existence);
            area = itemView.findViewById(R.id.area);
            image = itemView.findViewById(R.id.image);
        }
    }
}
