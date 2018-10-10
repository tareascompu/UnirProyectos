/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.centroeduc.model;

/**
 *
 * @author Usuario
 */
public class DatosProfesor {
    Integer cod_asignar;
    String nombre;
    String apellido;
    String nomre_grado;
    String nombre_seccion;
    String nombre_curso;

    public DatosProfesor() {
        
    }

    public Integer getCod_asignar() {
        return cod_asignar;
    }

    public void setCod_asignar(Integer cod_asignar) {
        this.cod_asignar = cod_asignar;
    }

   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    

    public String getNomre_grado() {
        return nomre_grado;
    }

    public void setNomre_grado(String nomre_grado) {
        this.nomre_grado = nomre_grado;
    }

    

    public String getNombre_seccion() {
        return nombre_seccion;
    }

    public void setNombre_seccion(String nombre_seccion) {
        this.nombre_seccion = nombre_seccion;
    }


    public String getNombre_curso() {
        return nombre_curso;
    }

    public void setNombre_curso(String nombre_curso) {
        this.nombre_curso = nombre_curso;
    }

    @Override
    public String toString() {
        return "DatosProfesor{" + "cod_asignar=" + cod_asignar + ", nombre=" + nombre + ", apellido=" + apellido + ", nomre_grado=" + nomre_grado + ", nombre_seccion=" + nombre_seccion + ", nombre_curso=" + nombre_curso + '}';
    }

   

   

    
    
    
    
    
}
