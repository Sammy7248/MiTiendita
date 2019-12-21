package com.navigation.samael_pc.navigation;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Interpolator;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class Productos extends Fragment {

    RecyclerView recycler_product;
    ProductAdapter adapter;
    ArrayList<Producto> product_list;
    FloatingActionButton add_prod;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_productos, container, false);

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

        add_prod = view.findViewById(R.id.add_prod);

        add_prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                LayoutInflater inflate = LayoutInflater.from(v.getContext());
                View view = inflate.inflate(R.layout.add_product,null);
                dialog.setView(view);
                dialog.create().show();

                final TextView caducidad = view.findViewById(R.id.caducidad);

                final Spinner area = view.findViewById(R.id.area);
                ArrayList<String> areas = new ArrayList();
                areas.add("Abarrotes");
                areas.add("Papeleria");
                areas.add("Ropa");

                ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_dropdown_item_1line,areas);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                area.setAdapter(adapter);

                area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (area.getSelectedItem().toString().equals("Papeleria") || area.getSelectedItem().toString().equals("Ropa")){
                            caducidad.setVisibility(View.GONE);
                        }
                        else{
                            caducidad.setVisibility(View.VISIBLE);
                        }


                        caducidad.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Calendar c = Calendar.getInstance();
                                int day = c.get(Calendar.DAY_OF_MONTH);
                                int month = c.get(Calendar.MONTH);
                                int year = c.get(Calendar.YEAR);
                                DatePickerDialog dpc = new DatePickerDialog(getContext(), R.style.DialogTheme ,new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int day) {
                                        Toast.makeText(getContext(), String.valueOf(year+"/"+month+"/"+day),Toast.LENGTH_LONG).show();
                                    }
                                },day,month,year);

                                dpc.show();
                            }

                        });
                        //Toast.makeText(getContext(),String.valueOf(area.getSelectedItem().toString()), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
        return view;
    }
}
