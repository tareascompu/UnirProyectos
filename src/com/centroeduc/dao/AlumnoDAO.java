   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.centroeduc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.centroeduc.model.Alumno;

/**
 *
 * @author Usuario
 */
public class AlumnoDAO extends Conexion {

    private String sql;
    private PreparedStatement ejecutar;
    private String respuesta;
    ResultSet clonarTabla;

    Alumno alum = new Alumno();

    public String ingresarAlum(Alumno alum) {
        respuesta = null;
        try {
            this.Conectar();
            sql = "insert into alumno(nombre, apellido, direccion, email, tel_emergencia, cod_enc, cod_secre, fechanac, padecimiento, estado) values(?,?,?,?,?,?,?,?,?,?)";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setString(1, alum.getNombre());
            ejecutar.setString(2, alum.getApellido());
            ejecutar.setString(3, alum.getDireccion());
            ejecutar.setString(4, alum.getEmail());
            ejecutar.setInt(5, alum.getTelEmergencia());
            ejecutar.setString(6, alum.getCodEncargado());
            ejecutar.setString(7, alum.getCodSecretaria());
            ejecutar.setString(8, alum.getFechanac());
            ejecutar.setString(9, alum.getPadecimiento());
            ejecutar.setInt(10, 1);

            ejecutar.executeUpdate();
            respuesta = "Registro almacenado con Exito";

        } catch (SQLException ex) {
            respuesta = "Error al almacenar los datos" + ex;

        } finally {
            this.cerrarConex();
        }
        return respuesta;
    }

    public String estadoAlumno(Alumno estado) {
        respuesta = null;
        try {
            this.Conectar();
            sql = "update alumno set estado=? where cod_alumno=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, 2);
            ejecutar.setInt(2, estado.getCodAlumno());

            ejecutar.executeUpdate();
            respuesta = "Se han actualizado los datos correctamente";

        } catch (SQLException ex) {
            //Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en Conexion: " + ex);
            respuesta = "Error, no se puede cambiar el estado del registro";
        }
        return respuesta;
    }

    public String modificarAlumno(Alumno dato) {
        respuesta = null;

        try {
            this.Conectar();
            sql = "update alumno set nombre=?, apellido=?, email=?, direccion=?, tel_emergencia=?, fechanac=?, padecimiento=?, estado=? where cod_alumno=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setString(1, dato.getNombre());
            ejecutar.setString(2, dato.getApellido());
            ejecutar.setString(3, dato.getEmail());
            ejecutar.setString(4, dato.getDireccion());
            ejecutar.setInt(5, dato.getTelEmergencia());
            ejecutar.setString(6, dato.getFechanac());
            ejecutar.setString(7, dato.getPadecimiento());
            ejecutar.setInt(8, dato.getEstado());
            ejecutar.setString(9, dato.getCodigo());

            ejecutar.executeUpdate();
            respuesta = "Datos actualizados correctamente";
        } catch (SQLException ex) {
            System.out.println("error en conexion" + ex);
            respuesta = "error en conexion, datos no se actualizaron" + ex;
        } finally {
            this.cerrarConex();
        }
        return respuesta;
    }

    public Alumno busquedaDatos(int codigo) {
        // busqueda por codigo, devuelve 1 o ninguno
        Alumno alum = new Alumno();
        try {
            this.Conectar();
            sql = "select * from alumno where cod_alumno=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, codigo);
            clonarTabla = ejecutar.executeQuery();
            if (clonarTabla.next()) {
                alum.setCodAlumno(clonarTabla.getInt("cod_alumno"));
                alum.setNombre(clonarTabla.getString("nombre"));
                alum.setApellido(clonarTabla.getString("apellido"));
                alum.setEmail(clonarTabla.getString("email"));
                alum.setTelEmergencia(clonarTabla.getInt("tel_emergencia"));
                alum.setCodEncargado(clonarTabla.getString("cod_enc"));
                alum.setCodSecretaria(clonarTabla.getString("cod_secre"));
                alum.setDireccion(clonarTabla.getString("direccion"));
                alum.setFechanac(clonarTabla.getString("fechanac"));
                alum.setPadecimiento(clonarTabla.getString("padecimiento"));
                alum.setEstado(clonarTabla.getInt("estado"));

            }
            // cerrar el preparedStatement
            //cerrar el Resultset
            ejecutar.close();
            clonarTabla.close();

        } catch (SQLException e) {
            System.out.println("error en el alumnodao buscar datos" + e);
        } finally {
            this.cerrarConex();
        }
        return alum;
    }

    public ArrayList<Alumno> mostrarAlumno() {
        ArrayList<Alumno> alumno = new ArrayList();

        try {
            this.Conectar();
            sql = "select* from alumno where estado=1";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            clonarTabla = this.ejecutar.executeQuery();
            while (clonarTabla.next()) {// se posiciona en el primero
                Alumno alum = new Alumno();
                alum.setCodAlumno(clonarTabla.getInt("cod_alumno"));
                alum.setNombre(clonarTabla.getString("nombre"));
                alum.setApellido(clonarTabla.getString("apellido"));
                alum.setEmail(clonarTabla.getString("email"));
                alum.setTelEmergencia(clonarTabla.getInt("tel_emergencia"));
                alum.setCodEncargado(clonarTabla.getString("cod_enc"));
                alum.setCodSecretaria(clonarTabla.getString("cod_secre"));
                alum.setDireccion(clonarTabla.getString("direccion"));
                alum.setFechanac(clonarTabla.getString("fechanac"));
                alum.setPadecimiento(clonarTabla.getString("padecimiento"));
                alum.setEstado(clonarTabla.getInt("estado"));
                alumno.add(alum);

            }

        } catch (SQLException ex) {
            System.out.println("error en MostrarAlumno en dao: " + ex);
        } finally {
            this.cerrarConex();
        }
        return alumno;
    }

}
