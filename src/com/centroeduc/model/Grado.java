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
public class Grado {

    private Integer codigo;
    private String descripcion;
    private Integer estado;

    public Grado() {
        this.codigo = null;
        this.descripcion = null;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Grado{" + "codigo=" + codigo + ", descripcion=" + descripcion + '}';
    }

}
