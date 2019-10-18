package com.navigation.samael_pc.navigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Consulta_User extends Fragment {


    RecyclerView recycler_user;
    UserAdapter adapter;
    List<Usuario> user_list;

    public Consulta_User() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consulta__user, container, false);
        user_list = new ArrayList<>();
        recycler_user = view.findViewById(R.id.recycler_users);
        //recycler_user.setHasFixedSize(true);
        recycler_user.setLayoutManager(new LinearLayoutManager(view.getContext()));
        user_list.add(new Usuario("jesuslive1970@gmail.com", "Molj7248", "Samael", "Mora Lemus", 1234));
        user_list.add(new Usuario("jesuslive1970@gmail.com", "Molj7248", "Samael", "Mora Lemus", 1234));
        user_list.add(new Usuario("jesuslive1970@gmail.com", "Molj7248", "Samael", "Mora Lemus", 1234));
        user_list.add(new Usuario("jesuslive1970@gmail.com", "Molj7248", "Samael", "Mora Lemus", 1234));

        adapter = new UserAdapter(view.getContext(), user_list);
        recycler_user.setAdapter(adapter);
        return view;
    }

}
