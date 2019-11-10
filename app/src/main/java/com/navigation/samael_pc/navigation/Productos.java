package com.navigation.samael_pc.navigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Productos extends Fragment {

    RecyclerView recycler_product;
    ProductAdapter adapter;
    ArrayList<Producto> product_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_productos, container, false);

        recycler_product = view.findViewById(R.id.recycler_productos);
        recycler_product.setLayoutManager(new LinearLayoutManager(view.getContext()));
        product_list = new ArrayList<>();

        product_list.add(new Producto("Jabon Zezt", "150g","7506306238336","22/12/2019","Papeleria",20,12.50));
        product_list.add(new Producto("Jabon Zezt", "150g","7506306238336","22/12/2019","Abarrotes",20,12.50));
        product_list.add(new Producto("Jabon Zezt", "150g","7506306238336","22/12/2019","Ropa",20,12.50));
        product_list.add(new Producto("Jabon Zezt", "150g","7506306238336","22/12/2019","Abarrotes",20,12.50));
        product_list.add(new Producto("Jabon Zezt", "150g","7506306238336","22/12/2019","Papeleria",20,12.50));

        adapter = new ProductAdapter(view.getContext(), product_list);
        recycler_product.setAdapter(adapter);
        return view;
    }
}
