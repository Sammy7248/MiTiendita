package com.navigation.samael_pc.navigation;

public class Producto {

    //<!--Nombre, Contenido_Neto, Precio, Codigo_Barras, Caducidad, Total_Existencia, Area(Tienda, Papeleria, Ropa)-->
    String nombre, contenido_neto, codigo_barras, caducidad, area;
    int total_existencia;
    Double precio;

    public Producto(String nombre, String contenido_neto, String codigo_barras, String caducidad, String area, int total_existencia, Double precio) {
        this.nombre = nombre;
        this.contenido_neto = contenido_neto;
        this.codigo_barras = codigo_barras;
        this.caducidad = caducidad;
        this.area = area;
        this.total_existencia = total_existencia;
        this.precio = precio;
    }

    public Producto(){

    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContenido_neto() {
        return contenido_neto;
    }

    public void setContenido_neto(String contenido_neto) {
        this.contenido_neto = contenido_neto;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getTotal_existencia() {
        return total_existencia;
    }

    public void setTotal_existencia(int total_existencia) {
        this.total_existencia = total_existencia;
    }
}
