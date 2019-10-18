package com.navigation.samael_pc.navigation;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class Usuarios extends Fragment {

    BottomNavigationView botnav;
    FragmentManager manager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usuarios, container, false);
        manager = getFragmentManager();
        botnav = view.findViewById(R.id.botnav);
        manager.beginTransaction().replace(R.id.cont_user, new Consulta_User()).commit();

        botnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.consulta){
                    //Toast.makeText(getActivity(), "Consulta", Toast.LENGTH_LONG).show();
                    manager.beginTransaction().replace(R.id.cont_user, new Consulta_User()).commit();
                }

                if(id == R.id.add_user){
                    Toast.makeText(getActivity(), "Añadir", Toast.LENGTH_LONG).show();
                }

                if(id == R.id.remove_user){
                    Toast.makeText(getActivity(), "Eliminar", Toast.LENGTH_LONG).show();
                }

                if(id == R.id.edit_user){
                    Toast.makeText(getActivity(), "Editar", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });


        return view;
    }




}
