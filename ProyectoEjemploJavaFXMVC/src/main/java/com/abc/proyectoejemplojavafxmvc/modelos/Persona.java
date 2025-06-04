package com.abc.proyectoejemplojavafxmvc.modelos;

import java.time.LocalDate;

public class Persona implements IPersona {

    //cambio: se agrego el id
    private int id;
    private String nombre;
    private int aNac;
    private String genero;

    public Persona() {}
    //se agrego el id
    public Persona(int id, String nombre, int aNac, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.aNac = aNac;
        this.genero = genero;
    }
    // Cambio: setter/getter id
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getANac() {
        return aNac;
    }
    public void setANac(int aNac) {
        this.aNac = aNac;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public int getEdad(){
        return calcularEdad();
    }


    /**
     * Este metodo calcula la edad
     *
     * <p>
     *  La fórmula utilizada es:
     *  edad = añoActual - añoNac
     *
     * </p>
     */
    public int calcularEdad(){
        return LocalDate.now().getYear() - getANac();
    }

    @Override
    public String toString(){
        return "Id: " + id + ", Nombre:" + nombre + ", Género: " + genero + ", Año de nacimiento: "+ aNac + ", Edad: " + calcularEdad() + " años";
    }

}
