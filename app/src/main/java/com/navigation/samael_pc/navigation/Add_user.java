package com.navigation.samael_pc.navigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


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
        child = FirebaseDatabase.getInstance().getReference("Usuarios");
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.user_password);
        nombre = view.findViewById(R.id.name);
        apellido = view.findViewById(R.id.apellido_user);
        register = view.findViewById(R.id.user_register);
        confirm_password = view.findViewById(R.id.user_confirm_password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                if(!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty() && !nombre.getText().toString().isEmpty() && !apellido.getText().toString().isEmpty() && !confirm_password.getText().toString().isEmpty()){
                    //password.getText().toString().equals(confirm_password.getText().toString())
                    if(password.getText().toString().equals(confirm_password.getText().toString())){
                        try {
                            String key = child.push().getKey();
                            String pass = null;
                            pass = general.encriptar(password.getText().toString());
                            Usuario user = new Usuario(username.getText().toString(), pass, nombre.getText().toString(), apellido.getText().toString(), 1, false,key, general.fecha.toString(), general.hour_ws.toString());
                            //child(key).
                            child.child(key).setValue(user);

                            String bit_key = general.bitacora.push().getKey();
                            Bitacora_Obj obj_bit = new Bitacora_Obj("Usuarios",general.fecha, general.hour, "Agregó Usuario", "A", general.usario,bit_key,general.local);
                            general.bitacora.child(bit_key).setValue(obj_bit);

                            username.setText("");
                            password.setText("");
                            nombre.setText("");
                            apellido.setText("");
                            username.requestFocus();
                            confirm_password.setText("");
                            nombre.setFocusable(true);
                            Toast.makeText(v.getContext(), "Usuario registrado correctamente", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    else{
                        confirm_password.setText("");
                        Toast.makeText(getContext(), "Contraseñas no son guales, verifique por favor!!!", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    username.setText("");
                    password.setText("");
                    nombre.setText("");
                    apellido.setText("");
                    username.requestFocus();
                    confirm_password.setText("");
                    nombre.setFocusable(true);
                    Toast.makeText(view.getContext(), "Usuario no registrado, campos incorrectos", Toast.LENGTH_LONG).show();
                }
            }
        });



        return view;
    }




}
