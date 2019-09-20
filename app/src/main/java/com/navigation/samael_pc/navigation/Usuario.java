package com.navigation.samael_pc.navigation;

public class Usuario {

    private String username, password, name, apellido;
    private Integer id_local;

    public Usuario(String username, String password, String name, String apellido, Integer id_local){
        this.username = username;
        this.password = password;
        this.name = name;
        this.apellido = apellido;
        this.id_local = id_local;
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