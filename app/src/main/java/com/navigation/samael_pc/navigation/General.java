package com.navigation.samael_pc.navigation;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class General {

    private static final General ourInstance = new General();
    public String area = "Abarrotes";
    public String caducidad = "";
    String usario="";


    SimpleDateFormat format_date = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
    Date date = new Date();

    String fecha = format_date.format(date);

    SimpleDateFormat format_hour = new SimpleDateFormat("HH:mm:ss a", Locale.getDefault());
    String hour = format_hour.format(date);

    //Firebase conecction variables
    public DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    public DatabaseReference myChild = reference.child("Mensaje");
    //------------------------------------------------------...
    private General() {

    }

    static General getInstance() {
        return ourInstance;
    }

    public int item = 0;


}
