package com.navigation.samael_pc.navigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Home extends Fragment {

    Button send;
    TextView show_text;
    EditText edit;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myChild = reference.child("Mensaje");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        //send = view.findViewById(R.id.send);
        //edit = view.findViewById(R.id.edit);
        //show_text = view.findViewById(R.id.show_text);

/*        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Texto para mostrar en prueba", Toast.LENGTH_LONG).show();
                //edit = view.findViewById(R.id.edit);
                Usuario user = new Usuario("jmora@mitiendita.com","12345678a","Samael", "Mora",1);
                reference.child("Usuarios").child(user.getName().toString()).push().setValue(user);

            }
        });*/

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // String value = dataSnapshot.getValue(String.class);
                //show_text.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }
}
