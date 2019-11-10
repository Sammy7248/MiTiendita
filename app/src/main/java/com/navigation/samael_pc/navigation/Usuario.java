package com.navigation.samael_pc.navigation;

public class Usuario {

    private String username, password, name, apellido;
    private Integer id_local;
    private boolean is_super_user;
    String key;

    public Usuario(String username, String password, String name, String apellido, Integer id_local, boolean is_super_user, String key){
        this.username = username;
        this.password = password;
        this.name = name;
        this.apellido = apellido;
        this.id_local = id_local;
        this.is_super_user = is_super_user;
        this.key = key;
    }

    public Usuario(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean getIs_super_user() {
        return is_super_user;
    }

    public void setIs_super_user(boolean is_super_user) {
        this.is_super_user = is_super_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getId_local() {
        return id_local;
    }

    public void setId_local(Integer id_local) {
        this.id_local = id_local;
    }
}
