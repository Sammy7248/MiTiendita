package com.navigation.samael_pc.navigation;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;


public class Faltantes extends Fragment {

    RecyclerView faltantes;
    FaltantesAdapter adapter;
    ArrayList<Producto> prod_faltantes;
    ArrayList<String> product_name;
    TextView filtrar;
    ListView list_falt;
    ArrayAdapter<String> list_falt_adp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_faltantes, container, false);

        //faltantes = view.findViewById(R.id.recycler_faltantes);
        //faltantes.setLayoutManager(new LinearLayoutManager(view.getContext()));
        prod_faltantes = new ArrayList<>();
        //filtrar = view.findViewById(R.id.filter_date);
        list_falt = view.findViewById(R.id.list_falt);
        product_name = new ArrayList<>();


        /*
        filtrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int dia = cal.get(Calendar.DAY_OF_MONTH);
                int mes = cal.get(Calendar.MONTH);
                int anio = cal.get(Calendar.YEAR);

                DatePickerDialog dial = new DatePickerDialog(getContext(), R.style.DialogThemeFalt, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    }
                }, anio, mes, dia

                );
                dial.show();
            }
        });*/


        General gen = General.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Productos");
        ref.orderByChild("total_existencia").equalTo(0).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot prod_falt: dataSnapshot.getChildren()){
                        Producto prod = prod_falt.getValue(Producto.class);
                        prod_faltantes.add(prod);
                        product_name.add(prod.nombre);
                    }

                    list_falt_adp = new ArrayAdapter<>(getContext(),android.R.layout.simple_expandable_list_item_1,product_name);
                    list_falt.setAdapter(list_falt_adp);
                    //adapter = new FaltantesAdapter(view.getContext(), prod_faltantes);
                    //faltantes.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }
}
