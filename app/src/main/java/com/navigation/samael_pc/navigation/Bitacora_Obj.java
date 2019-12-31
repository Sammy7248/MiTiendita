package com.navigation.samael_pc.navigation;

public class Bitacora_Obj {

    String Area, Fecha, Accion, Modulo, Usuario, KeyReg;
    int Local;

    public Bitacora_Obj(){

    }

    public Bitacora_Obj(String area, String fecha, String accion, String modulo, String usuario, String keyReg, int local) {
        Area = area;
        Fecha = fecha;
        Accion = accion;
        Modulo = modulo;
        Usuario = usuario;
        KeyReg = keyReg;
        Local = local;
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

    public String getAccion() {
        return Accion;
    }

    public void setAccion(String accion) {
        Accion = accion;
    }

    public String getModulo() {
        return Modulo;
    }

    public void setModulo(String modulo) {
        Modulo = modulo;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getKeyReg() {
        return KeyReg;
    }

    public void setKeyReg(String keyReg) {
        KeyReg = keyReg;
    }

    public int getLocal() {
        return Local;
    }

    public void setLocal(int local) {
        Local = local;
    }
}
