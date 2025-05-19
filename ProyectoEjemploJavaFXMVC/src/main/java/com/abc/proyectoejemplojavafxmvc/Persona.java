package com.abc.proyectoejemplojavafxmvc;

import java.time.LocalDate;

public class Persona implements IPersona {

    private String nombre;
    private int aNac;
    private String genero;

    public Persona() {}
    public Persona(String nombre, int aNac, String genero) {
        this.nombre = nombre;
        this.aNac = aNac;
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

}
