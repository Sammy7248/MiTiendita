package com.navigation.samael_pc.navigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;


public class Add_user extends Fragment {

    General general = General.getInstance();
    DatabaseReference child;
    TextInputEditText username, password, nombre, apellido, confirm_password;
    TextView register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        child = general.reference.child("Usuarios");
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.user_password);
        nombre = view.findViewById(R.id.name);
        apellido = view.findViewById(R.id.apellido_user);
        register = view.findViewById(R.id.user_register);
        confirm_password = view.findViewById(R.id.user_confirm_password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario user = new Usuario(username.getText().toString(), password.getText().toString(), nombre.getText().toString(), apellido.getText().toString(), 1, false);
                if(username.getText().toString().isEmpty()){
                    username.setError("Vacio");
                }
                if(password.getText().toString().isEmpty()){
                    password.setError("Vacio");
                }
                if(nombre.getText().toString().isEmpty()){
                    nombre.setError("Vacio");
                }
                if(apellido.getText().toString().isEmpty()){
                    apellido.setError("Vacio");
                }
                if(confirm_password.getText().toString().isEmpty()){
                    confirm_password.setError("Vacio");
                }
                if(password.getText().toString().equals(confirm_password.getText().toString())){
                    child.push().setValue(user);
                    username.setText("");
                    password.setText("");
                    nombre.setText("");
                    apellido.setText("");
                    username.requestFocus();
                    confirm_password.setText("");
                    Toast.makeText(v.getContext(), "Usuario registrado correctamente", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(view.getContext(), "Las contraseñas no son iguales", Toast.LENGTH_LONG).show();
                    username.setText("");
                    password.setText("");
                    nombre.setText("");
                    apellido.setText("");
                    username.requestFocus();
                    confirm_password.setText("");
                }
            }
        });



        return view;
    }
}
