package com.navigation.samael_pc.navigation;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Bitacora extends Fragment {

    RecyclerView rec_bit;
    BitacoraAdapter adapter;
    List<Bitacora_Obj> bitacora_list;
    General gen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bitacora, container, false);
        rec_bit = view.findViewById(R.id.recycler_bit);
        rec_bit.setLayoutManager(new LinearLayoutManager(view.getContext()));
        bitacora_list = new ArrayList<>();
        gen = General.getInstance();

        gen.reference.child("Bitacora").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot bit:dataSnapshot.getChildren()){
                    bitacora_list.add(bit.getValue(Bitacora_Obj.class));
                }
                adapter = new BitacoraAdapter(getContext(), bitacora_list);
                rec_bit.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return view;
    }
}
