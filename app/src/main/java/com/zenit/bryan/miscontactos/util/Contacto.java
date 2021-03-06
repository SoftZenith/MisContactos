package com.zenit.bryan.miscontactos.util;

/**
 * Created by Bryan on 13/03/2016.
 */
public class Contacto {
    private String nombre, telefono, email, direccion;

    public Contacto(String nombre, String telefono, String email, String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    //<editor-fold desc="GETTER METHODS">
    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getDirección() {
        return direccion;
    }
    //</editor-fold>

    //<editor-fold desc="SETTER METHODS">
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDirección(String dirección) {
        this.direccion = direccion;
    }
    //</editor-fold>

}
