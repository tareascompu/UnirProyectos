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
public class Alumno extends Persona {

    private Integer codAlumno;
    private Integer telEmergencia;
    private String padecimiento;
    private String codEncargado;
    private String codSecretaria;
    private Integer estado;

    public Alumno() {
        this.codAlumno = null;
        this.telEmergencia = null;
        this.padecimiento = null;
        this.codEncargado = null;
        this.codSecretaria = null;
        this.estado = null;

    }

    public Integer getCodAlumno() {
        return codAlumno;
    }

    public void setCodAlumno(Integer codAlumno) {
        this.codAlumno = codAlumno;
    }
    
    public String getCodEncargado() {
        return codEncargado;
    }

    public void setCodEncargado(String codEncargado) {
        this.codEncargado = codEncargado;
    }

    public String getCodSecretaria() {
        return codSecretaria;
    }

    public void setCodSecretaria(String codSecretaria) {
        this.codSecretaria = codSecretaria;
    }

    public Integer getTelEmergencia() {
        return telEmergencia;
    }

    public void setTelEmergencia(Integer telEmergencia) {
        this.telEmergencia = telEmergencia;
    }

    public String getPadecimiento() {
        return padecimiento;
    }

    public void setPadecimiento(String padecimiento) {
        this.padecimiento = padecimiento;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    
    @Override
    public String toString() {
        return "Alumno{" + "codAlumno=" + codAlumno + ", telEmergencia=" + telEmergencia + ", padecimiento=" + padecimiento + ", codEncargado=" + codEncargado + ", codSecretaria=" + codSecretaria + ", estado=" + estado + '}';
    }
}

