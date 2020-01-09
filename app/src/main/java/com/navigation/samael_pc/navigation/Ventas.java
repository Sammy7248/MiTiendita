package com.navigation.samael_pc.navigation;

public class Ventas {

    String Fecha, Hora, Producto, Contenido, Venta_Key, Usuario;
    Integer Cantidad;
    Double Total_Venta, Precio_Unitario;
    String Area;


    public Ventas(String fecha, String hora, String producto, String contenido, String venta_Key, String usuario, Integer cantidad, Double total_Venta, Double precio_Unitario, String area) {
        Fecha = fecha;
        Hora = hora;
        Producto = producto;
        Contenido = contenido;
        Venta_Key = venta_Key;
        Usuario = usuario;
        Cantidad = cantidad;
        Total_Venta = total_Venta;
        Precio_Unitario = precio_Unitario;
        Area = area;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String producto) {
        Producto = producto;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String contenido) {
        Contenido = contenido;
    }

    public String getVenta_Key() {
        return Venta_Key;
    }

    public void setVenta_Key(String venta_Key) {
        Venta_Key = venta_Key;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public Integer getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Integer cantidad) {
        Cantidad = cantidad;
    }

    public Double getTotal_Venta() {
        return Total_Venta;
    }

    public void setTotal_Venta(Double total_Venta) {
        Total_Venta = total_Venta;
    }

    public Double getPrecio_Unitario() {
        return Precio_Unitario;
    }

    public void setPrecio_Unitario(Double precio_Unitario) {
        Precio_Unitario = precio_Unitario;
    }
}
