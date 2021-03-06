package com.navigation.samael_pc.navigation;

import android.content.ClipData;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    General general;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        general = General.getInstance();
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor, new Productos()).commit();
        toolbar.setTitle("");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.FragmentManager fragment = getSupportFragmentManager();

        /*if (id == R.id.home) {
            Log.e("valor de item", String.valueOf(general.item));
            fragment.beginTransaction().replace(R.id.contenedor,new Home()).commit();
            toolbar.setTitle("");

        } else */
        if (id == R.id.usuarios || general.item == 1) {
            fragment.beginTransaction().replace(R.id.contenedor,new Usuarios()).commit();
            toolbar.setTitle(item.getTitle());
        } else if (id == R.id.productos) {
            fragment.beginTransaction().replace(R.id.contenedor,new Productos()).commit();
            toolbar.setTitle(item.getTitle());
        } else if (id == R.id.faltantes) {
            fragment.beginTransaction().replace(R.id.contenedor,new Faltantes()).commit();
            toolbar.setTitle(item.getTitle());
        } else if (id == R.id.estadisticas) {
            //Toast.makeText(getApplicationContext(), "Cerrar sesion", Toast.LENGTH_SHORT).show();
            fragment.beginTransaction().replace(R.id.contenedor, new Estadisticas()).commit();
            toolbar.setTitle(item.getTitle());
        }
        else if (id == R.id.bitacora){
            fragment.beginTransaction().replace(R.id.contenedor, new Bitacora()).commit();
            toolbar.setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
