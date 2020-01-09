package com.navigation.samael_pc.navigation;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class General {

    private static final General ourInstance = new General();
    public String area = "Abarrotes";
    public String caducidad = "";
    int local = 2;
    String usario = "samael@gmail.com";
    public String pass = "";
    public static final String AES = "AES";


    SimpleDateFormat format_date = new SimpleDateFormat("dd/MM/YYYY", Locale.getDefault());
    Date date = new Date();

    String fecha = format_date.format(date);

    SimpleDateFormat format_hour = new SimpleDateFormat("HH:mm:ss a", Locale.getDefault());
    String hour = format_hour.format(date);

    SimpleDateFormat format_hour_ws = new SimpleDateFormat("HH:mm a", Locale.getDefault());
    String hour_ws = format_hour_ws.format(date);

    //Firebase conecction variables
    public DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    public DatabaseReference myChild = reference.child("Mensaje");
    public DatabaseReference usuarios = reference.child("Usuarios");

    public DatabaseReference bitacora = reference.child("Bitacora");

    String uri_1 = "";
    //------------------------------------------------------...
    private General() {

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

    static General getInstance() {
        return ourInstance;
    }

    public int item = 0;




}
