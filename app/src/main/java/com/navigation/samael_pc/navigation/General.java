package com.navigation.samael_pc.navigation;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class General {

    private static final General ourInstance = new General();
    public String area = "Abarrotes";
    public String caducidad = "";
    String usario="";
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
