package com.navigation.samael_pc.navigation;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
    FloatingActionButton gen_rep;
    ArrayList<String> name_falt;
    int cont;
    General gen;
    private static final int PERMISSION_STORAGE_CODE = 1000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_faltantes, container, false);
        gen_rep = view.findViewById(R.id.gen_rep);


        //faltantes = view.findViewById(R.id.recycler_faltantes);
        //faltantes.setLayoutManager(new LinearLayoutManager(view.getContext()));
        prod_faltantes = new ArrayList<>();
        //filtrar = view.findViewById(R.id.filter_date);
        list_falt = view.findViewById(R.id.list_falt);
        product_name = new ArrayList<>();
        name_falt = new ArrayList<>();
        cont = 0;


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


        gen = General.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Productos");
        ref.orderByChild("total_existencia").equalTo(0).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot prod_falt: dataSnapshot.getChildren()){
                        Producto prod = prod_falt.getValue(Producto.class);
                        String producto_na = "";
                        producto_na = prod.nombre + " " + prod.contenido_neto + "\n" + prod.area;
                        prod_faltantes.add(prod);
                        name_falt.add(prod.nombre+"\n");
                        product_name.add(producto_na);
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



        list_falt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                //Añadir un  AlertDialog al momento de oprimir un item de la lista
                final AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                LayoutInflater inflate = LayoutInflater.from(view.getContext());
                View view_add = inflate.inflate(R.layout.add_prod,null);
                dialog.setView(view_add);
                dialog.create().show();

                TextView prod_nam_text = view_add.findViewById(R.id.prod_nam_text);

                prod_nam_text.setText(prod_faltantes.get(position).nombre + " " + prod_faltantes.get(position).contenido_neto);

                final EditText add_cant = view_add.findViewById(R.id.add_cant);
                TextView add_cant_lay = view_add.findViewById(R.id.send_add_cant);

                add_cant_lay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!add_cant.getText().toString().equals("")){
                            gen.reference.child("Productos").child(prod_faltantes.get(position).getKey_product()).child("total_existencia").setValue(Integer.parseInt(add_cant.getText().toString()));
                            add_cant.setText("");
                            //dialog.show().cancel();
                            Bitacora_Obj bit = new Bitacora_Obj("Abarrotes",gen.fecha,gen.hour,"Agrego productos al inventario","E",gen.usario,gen.bitacora.push().getKey(), gen.local);
                            gen.bitacora.child(gen.bitacora.push().getKey()).setValue(bit);
                            Toast.makeText(getContext(), "Cantidad Agregada A Inventario", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getContext(), "Error, campos vacios", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                //Toast.makeText(getContext(), prod_faltantes.get(position).nombre, Toast.LENGTH_LONG).show();
            }
        });


        gen_rep.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Se oprimio el FAB", Toast.LENGTH_LONG).show();

                String filename = "faltantes_1.txt";
                FileOutputStream fos;
                FileInputStream fis;



                try {
                    fos = getContext().openFileOutput(filename, getContext().MODE_APPEND);
                    for (String prod: name_falt){
                        fos.write(prod.getBytes());
                    }
                    //fos.write("Algn textp".getBytes());
                    Toast.makeText(getContext(), getContext().getFilesDir().toString(), Toast.LENGTH_LONG).show();
                    fos.close();


                    fis = v.getContext().openFileInput(filename);
                    InputStreamReader reader = new InputStreamReader(fis, "UTF-8");
                    BufferedReader read = new BufferedReader(reader);
                    StringBuilder builder = new StringBuilder();
                    String row = "";

                    while((row=read.readLine())!=null){
                        Toast.makeText(getContext(), row,Toast.LENGTH_LONG).show();
                    }




                }
                catch (Exception e){
                    e.printStackTrace();
                }


                StorageReference myStorage = FirebaseStorage.getInstance().getReference();
                FirebaseStorage storage;
                myStorage.child("coordenadas.xlsx").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String uri_1 = uri.toString();
                        gen.uri_1 = uri_1;
                        /*File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                        downloadFile(getContext(),"coordenadas",".xlsx", path.toString(), uri_1);*/

                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                            if(getContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                                requestPermissions(permissions, PERMISSION_STORAGE_CODE);
                            }
                            else{
                                startDownloading(uri_1);
                            }
                        }
                        else{
                            startDownloading(uri_1);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });



            }
        });
        return view;
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_STORAGE_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startDownloading(gen.uri_1);
                }
                else{
                    Toast.makeText(getContext(), "Permiso denegado", Toast.LENGTH_LONG).show();
                }
            }

        }
    }

    public void startDownloading(String url){
        DownloadManager.Request req = new DownloadManager.Request(Uri.parse(url.toString().trim()));
        req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI|DownloadManager.Request.NETWORK_MOBILE);
        req.setTitle("Download");
        req.setDescription("Downloading File...");
        req.allowScanningByMediaScanner();
        req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        req.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,""+System.currentTimeMillis());

        DownloadManager manager = (DownloadManager) getContext().getSystemService(getContext().DOWNLOAD_SERVICE);
        manager.enqueue(req);

    }


    public void downloadFile(Context context, String filename, String fileExtention, String detinationDirectory, String url){

        DownloadManager dmanager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, detinationDirectory, filename + fileExtention);
        dmanager.enqueue(request);

    }

}
