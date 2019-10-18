package com.navigation.samael_pc.navigation;

class General {

    private static final General ourInstance = new General();

    private General() {

    }

    static General getInstance() {
        return ourInstance;
    }

    public int item = 0;


}
