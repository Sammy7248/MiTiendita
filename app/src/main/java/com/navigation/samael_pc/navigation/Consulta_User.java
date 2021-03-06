package com.navigation.samael_pc.navigation;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Consulta_User extends Fragment {


    RecyclerView recycler_user;
    UserAdapter adapter;
    General general = General.getInstance();
    ArrayList<Usuario> all_users;
    SearchView search_user;

    public Consulta_User() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_consulta__user, container, false);
        all_users = new ArrayList<>();
        recycler_user = view.findViewById(R.id.recycler_users);
        //recycler_user.setHasFixedSize(true);
        recycler_user.setLayoutManager(new LinearLayoutManager(view.getContext()));
        search_user = view.findViewById(R.id.search_user);
        final ArrayList<Usuario> users = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Usuarios");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot user_object: dataSnapshot.getChildren()){
                    Usuario user = user_object.getValue(Usuario.class);
                    all_users.add(user);
                }
                adapter = new UserAdapter(view.getContext(), all_users);
                recycler_user.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        search_user.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return view;
    }

}
