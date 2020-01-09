package com.navigation.samael_pc.navigation;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

import static com.navigation.samael_pc.navigation.R.id.area;
import static com.navigation.samael_pc.navigation.R.id.card_product;
import static com.navigation.samael_pc.navigation.R.id.producto_name;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> implements Filterable {

    private Context context;
    ArrayList<Producto> product_list;
    ArrayList<Producto> filter_prod;

    public ProductAdapter(Context context, ArrayList<Producto> product_list) {
        this.context = context;
        this.product_list = product_list;
        this.filter_prod = new ArrayList<>(product_list);
    }

    @Override
    public ProductAdapter.ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_productos, null);
        ProductHolder holder = new ProductHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ProductHolder holder, int position) {

        final Producto product = product_list.get(position);

        holder.product_name.setText("Producto: "+product.getNombre().toString() + " " + product.getContenido_neto());
        holder.price.setText("Precio: $"+product.getPrecio().toString()+"0");
        holder.expiration.setText(product.getCaducidad().toString());
        holder.existence.setText("Existencia: "+String.valueOf(product.getTotal_existencia()).toString());
        holder.area.setText("Area: "+product.getArea().toString());

        if(product.area.equals("Papeleria") || product.area.equals("Ropa")){
            holder.expiration.setVisibility(View.INVISIBLE);
        }
        else{
            holder.expiration.setVisibility(View.VISIBLE);
        }
        char[] nam = product.nombre.toCharArray();
        holder.image.setText(String.valueOf(nam[0]));
        /*if(product.area.toString().equals("Abarrotes")){
            holder.image.setImageResource(R.drawable.abarrotes);
        }
        if(product.area.toString().equals("Papeleria")){
            holder.image.setImageResource(R.drawable.papeleria);
        }
        if(product.area.toString().equals("Ropa")){
            holder.image.setImageResource(R.drawable.ropa);
        }*/

        holder.vendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, String.valueOf(product.getTotal_existencia()),Toast.LENGTH_LONG).show();
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                LayoutInflater inflate = LayoutInflater.from(context);
                View view_vend = inflate.inflate(R.layout.add_prod, null);
                dialog.setView(view_vend);
                dialog.create().show();

                final General general = General.getInstance();

                TextView head = view_vend.findViewById(R.id.head);
                head.setText("Venta");

                TextView prod_nam_text = view_vend.findViewById(R.id.prod_nam_text);
                prod_nam_text.setText(product.getNombre() + " " + product.getContenido_neto());

                final EditText vend_cant = view_vend.findViewById(R.id.add_cant);
                TextView vender = view_vend.findViewById(R.id.send_add_cant);
                vender.setText("Vender");

                final String bit_key = general.bitacora.push().getKey();
                final Bitacora_Obj obj_bit = new Bitacora_Obj("Productos",general.fecha, general.hour, "Vendió Producto", "U", general.usario,bit_key.toString(),general.local);

                vender.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!vend_cant.getText().toString().isEmpty() && Integer.parseInt(vend_cant.getText().toString()) < product.getTotal_existencia()){
                            int tot = product.getTotal_existencia() - Integer.parseInt(vend_cant.getText().toString());
                            general.reference.child("Productos").child(product.getKey_product()).child("total_existencia").setValue(tot);
                            Toast.makeText(context, "Se vendio el producto", Toast.LENGTH_LONG).show();
                            general.bitacora.child(bit_key).setValue(obj_bit);

                            Double total = (Double) (Integer.parseInt(vend_cant.getText().toString()) * product.precio);
                            String key = general.reference.child("Ventas").push().getKey();
                            Ventas vent = new Ventas(general.fecha, general.hour_ws,product.getNombre(),product.getContenido_neto(), key, general.usario, Integer.parseInt(vend_cant.getText().toString()), total, product.precio, product.getArea());
                            general.reference.child("Ventas").child(key).setValue(vent);
                            vend_cant.setText("");
                        }
                        else{
                            Toast.makeText(context, "Verifique la existencia del producto", Toast.LENGTH_LONG).show();
                            vend_cant.setText("");
                        }
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return product_list.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Producto> filtered = new ArrayList<>();

            if(constraint.toString().isEmpty()){
                filtered.addAll(filter_prod);
            }
            else{
                for (Producto prod:filter_prod){
                    if(prod.nombre.toLowerCase().contains(constraint.toString().toLowerCase())){
                        filtered.add(prod);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filtered;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            product_list.clear();
            product_list.addAll((Collection<? extends Producto>) results.values);
            notifyDataSetChanged();
        }
    };



    class ProductHolder extends RecyclerView.ViewHolder{

        TextView product_name, price, expiration, existence, area, vendido, edit_prod, del_prod;
        TextView image;

        public ProductHolder(View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.producto_name);
            price = itemView.findViewById(R.id.price);
            expiration = itemView.findViewById(R.id.expiration);
            existence = itemView.findViewById(R.id.existence);
            area = itemView.findViewById(R.id.area);
            image = itemView.findViewById(R.id.image);
            vendido = itemView.findViewById(R.id.vendido);
            edit_prod = itemView.findViewById(R.id.edit_prod);
            del_prod = itemView.findViewById(R.id.del_prod);
        }
    }
}
