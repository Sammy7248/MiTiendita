package com.navigation.samael_pc.navigation;

import android.content.Context;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class Estadisticas extends Fragment {

    /*TextView show_file;
    Button agregar, leer;
    EditText txt;

    final String filename = "faltantes_1.txt";
    FileOutputStream fos = null;
    FileInputStream fis;
    General gen;*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_estadisticas, container, false);
        /*ConnectivityManager manager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        show_file = view.findViewById(R.id.show_file);
        txt = view.findViewById(R.id.txt);
        agregar = view.findViewById(R.id.add);
        leer = view.findViewById(R.id.leer);
        gen = General.getInstance();*/




        /*agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    fos = getContext().openFileOutput(filename, getContext().MODE_PRIVATE);
                    OutputStreamWriter writer = new OutputStreamWriter(fos);*/
                    //writer.write(System.getProperty("line.separator"));
                    //writer.write(txt.getText().toString());
                    /*writer.write("");
                    writer.close();
                }
                catch (Exception e){

                }

            }
        });*/

        /*leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {*/
                /*try {
                    fis = getContext().openFileInput(filename);
                    InputStreamReader reader = new InputStreamReader(fis, "UTF-8");
                    BufferedReader read = new BufferedReader(reader);
                    StringBuilder builder = new StringBuilder();
                    String row = "";

                    while((row=read.readLine())!=null){
                        builder.append(row).append("\n");
                    }
                    show_file.setText(builder.toString());
                }
                catch (Exception e){
                    e.printStackTrace();
                }*/
                //Uri file = Uri.fromFile(new File(filename));
                //StorageReference riversRef = FirebaseStorage.getInstance().getReference().child("faltantes/"+file.getLastPathSegment());
                //uploadTask = riversRef.putFile(file);

// Register observers to listen for when the download is done or if it fails

                /*riversRef.putFile(file).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {*/
                        // Handle unsuccessful uploads
                        /*Toast.makeText(getContext(), "no se subio correctamente", Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getContext(), "see subio correctamente", Toast.LENGTH_LONG).show();*/
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                        // ...
                 /*   }
                });
            }
        });*/




        return view;
    }
}
