package com.centroeduc.dao;

import com.centroeduc.model.Maestro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaestroDAO extends Conexion {

    private String sql;
    PreparedStatement ejecutar;
    private String ingreso;
    ResultSet resultado;

    Maestro maes = new Maestro();

    public String insertarMaestro(Maestro maes) {
        ingreso = null;
        try {
            this.Conectar();
            sql = "INSERT INTO profesor values(?,?,?,?,?,?,?,?,?,?,MD5(?),?)";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setString(1, maes.getCodigo());
            ejecutar.setString(2, maes.getNombre());
            ejecutar.setString(3, maes.getApellido());
            ejecutar.setString(4, maes.getDireccion());
            ejecutar.setString(5, maes.getEmail());
            ejecutar.setInt(6, maes.getTelCasa());
            ejecutar.setInt(7, maes.getTelMovil());
            ejecutar.setString(8, maes.getFechanac());
            ejecutar.setLong(9, maes.getCui());
            ejecutar.setString(10, maes.getCodigoA());
            ejecutar.setString(11, maes.getPass());
            ejecutar.setInt(12, 1);

            ejecutar.executeUpdate();
            ingreso = "datos almacenados exitosamente";

        } catch (SQLException ex) {

            ingreso = "Error al almacenar los datos" + ex;

        } finally {
            this.cerrarConex();
        }

        return ingreso;

    }

    public ArrayList<Maestro> Mostrarprofesor() {
        ArrayList<Maestro> lista = null;
        ResultSet resultado;
        try {
            this.Conectar();
            sql = "select * from profesor where estado=1";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            resultado = ejecutar.executeQuery();
            lista = new ArrayList();
            while (resultado.next()) {
                Maestro maes = new Maestro();
                maes.setCodigo(resultado.getString("cod_prof"));
                maes.setNombre(resultado.getString("nombre"));
                maes.setApellido(resultado.getString("apellido"));
                maes.setDireccion(resultado.getString("direccion"));
                maes.setEmail(resultado.getString("email"));
                maes.setTelCasa(resultado.getInt("tel_casa"));
                maes.setTelMovil(resultado.getInt("tel_movil"));
                maes.setFechanac(resultado.getString("fechanac"));
                maes.setCui(resultado.getLong("cui"));
                maes.setCodigoA(resultado.getString("cod_admin"));
                maes.setEstado(resultado.getInt("estado"));
                maes.setPass(resultado.getString("password"));

                lista.add(maes);

            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        } finally {
            this.cerrarConex();
        }
        return lista;

    }

    public String eliminarMaestro(String codigo) {
        ingreso = null;
        try {
            this.Conectar();
            sql = "delete from profesor where cod_prof=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setString(1, codigo);
            ejecutar.executeUpdate();
            ingreso = "Registro Eliminado";

        } catch (SQLException ex) {
            //Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en Conexion: " + ex);
            ingreso = "Error, no se puede eliminar el registro";
        }
        return ingreso;
    }

    public String editarMaestro(Maestro maes) {
        ingreso = null;
        try {
            this.Conectar();
            sql = "Update profesor set nombre=?,apellido=?,email=?,direccion=?,tel_casa=?,tel_movil=?,fechanac=?,cui=?, cod_admin=?, password=? where cod_prof=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setString(1, maes.getNombre());
            ejecutar.setString(2, maes.getApellido());
            ejecutar.setString(3, maes.getEmail());
            ejecutar.setString(4, maes.getDireccion());
            ejecutar.setInt(5, maes.getTelCasa());
            ejecutar.setInt(6, maes.getTelMovil());
            ejecutar.setString(7, maes.getFechanac());
            ejecutar.setLong(8, maes.getCui());
            ejecutar.setString(9, maes.getCodigoA());
            ejecutar.setString(10, maes.getPass());
            ejecutar.setString(11, maes.getCodigo());
            ejecutar.executeUpdate();
            ingreso = "datos editados exitosamente";

        } catch (SQLException ex) {

            System.out.println("Error al editar los datos" + ex);
            ingreso = "no se puede modificar";

        } finally {
            this.cerrarConex();
        }

        return ingreso;

    }

    public Maestro buscarCodigo(String Codigo) {
        Maestro maes = new Maestro();
        try {
            this.Conectar();
            sql = "select * from profesor where cod_prof=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setString(1, Codigo);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                maes.setCodigo(resultado.getString("cod_prof"));
                maes.setNombre(resultado.getString("nombre"));
                maes.setApellido(resultado.getString("apellido"));
                maes.setDireccion(resultado.getString("direccion"));
                maes.setEmail(resultado.getString("email"));
                maes.setTelCasa(resultado.getInt("tel_casa"));
                maes.setTelMovil(resultado.getInt("tel_movil"));
                maes.setFechanac(resultado.getString("fechanac"));
                maes.setCui(resultado.getLong("cui"));
                maes.setCodigoA(resultado.getString("cod_admin"));
                maes.setEstado(resultado.getInt("estado"));
                maes.setPass(resultado.getString("password"));
            }

            //cerrar el Prepared Statement
            //cerrar el ResultSet
            ejecutar.close();
            resultado.close();

        } catch (SQLException ex) {
            System.out.println("Error en ClienteDao" + ex);

        } finally {
            this.cerrarConex();
        }

        return maes;
    }

    public String estadoMaestro(Maestro maes) {
        ingreso = null;
        try {
            this.Conectar();
            sql = "update profesor set estado=? where cod_prof=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, 2);
            ejecutar.setString(2, maes.getCodigo());

            ejecutar.executeUpdate();
            ingreso = "se cambiado el estado correctamente";

        } catch (SQLException ex) {
            System.out.println("error en conexion" + ex);
            ingreso = "error, no se puede cambiar el estado del registro";
        }
        return ingreso;
    }

}
