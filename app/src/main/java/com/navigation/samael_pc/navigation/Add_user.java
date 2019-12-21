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

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class Add_user extends Fragment {

    General general = General.getInstance();
    DatabaseReference child;
    TextInputEditText username, password, nombre, apellido, confirm_password;
    TextView register;
    public static final String AES = "AES";

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
                            String key = general.reference.push().getKey();
                            String pass = null;
                            pass = encriptar(password.getText().toString());
                            Usuario user = new Usuario(username.getText().toString(), pass, nombre.getText().toString(), apellido.getText().toString(), 1, false,key);
                            //child(key).
                            child.child(user.getKey()).setValue(user);
                            username.setText("");
                            password.setText("");
                            nombre.setText("");
                            apellido.setText("");
                            username.requestFocus();
                            confirm_password.setText("");
                            Toast.makeText(v.getContext(), "Usuario registrado correctamente"+key, Toast.LENGTH_LONG).show();
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
                    Toast.makeText(view.getContext(), "Usuario no registrado, campos incorrectos", Toast.LENGTH_LONG).show();
                }
            }
        });



        return view;
    }

    public static String encriptar(String password) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        keyGenerator.init(128);
        SecretKey scrKey = keyGenerator.generateKey();
        byte[] bytesecret = scrKey.getEncoded();
        SecretKeySpec secSpec = new SecretKeySpec(bytesecret, AES);
        Cipher cip = Cipher.getInstance(AES);
        cip.init(Cipher.ENCRYPT_MODE,secSpec);
        byte[] encryptPass = cip.doFinal(password.getBytes());

        //PARA DESENCRIPTAR EL PASSWORD
        /*cip.init(Cipher.DECRYPT_MODE,secSpec);
        byte[] decryptPass = cip.doFinal(encryptPass);*/

        return new String(encryptPass.toString());
    }
}
