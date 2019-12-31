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
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;


public class Productos extends Fragment {

    RecyclerView recycler_product;
    ProductAdapter adapter;
    ArrayList<Producto> product_list;
    FloatingActionButton add_prod;
    SearchView search_prod;
    SwipeRefreshLayout ref_prod;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view_frag = inflater.inflate(R.layout.fragment_productos, container, false);

        recycler_product = view_frag.findViewById(R.id.recycler_productos);
        recycler_product.setLayoutManager(new LinearLayoutManager(view_frag.getContext()));
        product_list = new ArrayList<>();
        search_prod = view_frag.findViewById(R.id.search_prod);
        ref_prod = view_frag.findViewById(R.id.ref_prod);

        final General gen = General.getInstance();
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Productos");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot product_obj:dataSnapshot.getChildren()){
                    Producto prod = product_obj.getValue(Producto.class);
                    product_list.add(prod);
                }
                adapter = new ProductAdapter(view_frag.getContext(), product_list);
                recycler_product.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        add_prod = view_frag.findViewById(R.id.add_prod);

        add_prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(view_frag.getContext());
                LayoutInflater inflate = LayoutInflater.from(v.getContext());
                final View view = inflate.inflate(R.layout.add_product,null);
                dialog.setView(view);
                dialog.create().show();

                final Hashtable<Integer, String> meses = new Hashtable<>();
                meses.put(1,"Enero");
                meses.put(2,"Febrero");
                meses.put(3,"Marzo");
                meses.put(4,"Abril");
                meses.put(5,"MAyo");
                meses.put(6,"Junio");
                meses.put(7,"Julio");
                meses.put(8,"Agosto");
                meses.put(9,"Septiembre");
                meses.put(10,"Octubre");
                meses.put(11,"Noviembre");
                meses.put(12,"Diciembre");

                final TextView caducidad = view.findViewById(R.id.caducidad);
                TextView register_prod = view.findViewById(R.id.register_prod);
                final DatabaseReference child = gen.reference.child("Productos");

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
                                        gen.caducidad = String.format("%d/%s/%d",day, meses.get(month+1),year);
                                        caducidad.setText(String.format("%d/%s/%d",day, meses.get(month+1),year));
                                        //Toast.makeText(getContext(), String.valueOf(year+"/"+month+"/"+day),Toast.LENGTH_LONG).show();
                                    }
                                },year,month,day);

                                dpc.show();
                            }

                        });
                        gen.area = area.getSelectedItem().toString().trim();
                        //Toast.makeText(getContext(),String.valueOf(area.getSelectedItem().toString()), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                register_prod.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            String key_producto = gen.reference.push().getKey();
                            EditText name_product = view.findViewById(R.id.name_product);
                            EditText contenido = view.findViewById(R.id.contenido);
                            EditText bar_code = view.findViewById(R.id.bar_codes);
                            Toast.makeText(getContext(), String.valueOf(gen.area+"-"+gen.caducidad),Toast.LENGTH_LONG).show();
                            EditText existencia = view.findViewById(R.id.existencia);
                            EditText precio = view.findViewById(R.id.precio);
                            if (!name_product.getText().toString().isEmpty() && !existencia.getText().toString().isEmpty()){
                                child.child(key_producto).setValue(new Producto(name_product.getText().toString(), contenido.getText().toString(),
                                        bar_code.getText().toString(), gen.caducidad,
                                        gen.area, Integer.parseInt(existencia.getText().toString()),
                                        Double.parseDouble(precio.getText().toString()), key_producto));
                                name_product.setText("");
                                contenido.setText("");
                                bar_code.setText("");
                                existencia.setText("");
                                precio.setText("");
                                gen.area = "Abarrotes";
                                gen.caducidad = "";
                                caducidad.setText("Caducidad");
                                Toast.makeText(getContext(), "Producto registrado exitosamente!!!",Toast.LENGTH_LONG).show();


                                //OBTENER FECHA Y HORA ACTUALES

                                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a", Locale.getDefault());
                                Date date = new Date();

                                String fecha = format.format(date);



                                DatabaseReference ref_bit = FirebaseDatabase.getInstance().getReference("Bitacora");
                                Bitacora_Obj obj_bit = new Bitacora_Obj("Abarrotes",fecha, "Agregar Producto", "A", "manuel@hotmail.com",ref_bit.push().getKey().toString(),2);
                                ref_bit.child(ref_bit.push().getKey()).setValue(obj_bit);
                            }
                            else{

                                name_product.setError("Vacio");
                                existencia.setError("Vacio");
                                Toast.makeText(getContext(),"Campos vacios, verifique por favor!!!",Toast.LENGTH_LONG).show();
                            }
                        }catch (Exception e){
                            Toast.makeText(getContext(), String.valueOf(e),Toast.LENGTH_LONG).show();
                            Log.e("Error",String.valueOf(e));
                        }

                        //child.child(key_producto).setValue("");
                    }
                });
            }
        });

        search_prod.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast.makeText(getContext(), String.valueOf(newText), Toast.LENGTH_LONG).show();
                //ref.orderByChild("nombre").startAt(newText)
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        ref_prod.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //adapter = new ProductAdapter(view_frag.getContext(), product_list);
                //recycler_product.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                ref_prod.setRefreshing(false);
            }
        });

        return view_frag;
    }
}
