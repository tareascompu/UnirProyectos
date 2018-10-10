package com.centroeduc.dao;

import com.centroeduc.dao.Conexion;
import com.centroeduc.model.Seccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeccionDAO extends Conexion {

    private String sql;
    PreparedStatement ejecutar;
    private String ingreso;
    ResultSet resultado;

    Seccion sec = new Seccion();

    public String insertarSeccion(Seccion sec) {

        ingreso = null;
        try {
            this.Conectar();
            sql = "insert into seccion(descripcion) values(?)";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setString(1, sec.getDescripcion());

            ejecutar.executeUpdate();
            ingreso = "datos almacenados";

        } catch (SQLException ex) {
            ingreso = "error al almacenar los datos";

        } finally {
            this.cerrarConex();

        }
        return ingreso;

    }

    public ArrayList<Seccion> MostrarSeccion() {
        ArrayList<Seccion> clase = null;
        ResultSet resultado;
        try {
            this.Conectar();
            sql = "select * from seccion";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            resultado = ejecutar.executeQuery();
            clase = new ArrayList();
            while (resultado.next()) {
                Seccion sec = new Seccion();
                sec.setCodigo(resultado.getInt("cod_sec"));
                sec.setDescripcion(resultado.getString("descripcion"));

                clase.add(sec);

            }

        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            this.cerrarConex();
        }
        return clase;
    }

    public String editarSeccion(Seccion sec) {
        ingreso = null;
        try {
            this.Conectar();
            sql = "Update seccion set descripcion=? where cod_sec=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setString(1, sec.getDescripcion());
            ejecutar.setInt(2, sec.getCodigo());

            ejecutar.executeUpdate();
            ingreso = "dato editados exitosamente";

        } catch (SQLException ex) {
            System.out.println("error al editar los datos" + ex);
            ingreso = "no se puede modificar";

        } finally {
            this.cerrarConex();
        }
        return ingreso;
    }

    public Seccion buscarCodigo(int Codigo) {
        Seccion sec = new Seccion();
        try {
            this.Conectar();
            sql = "select * from seccion where cod_sec=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, Codigo);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                sec.setCodigo(resultado.getInt("cod_sec"));
                sec.setDescripcion(resultado.getString("descripcion"));
            }

            ejecutar.close();
            resultado.close();

        } catch (SQLException ex) {
            System.out.println("error en seccion DAO" + ex);

        } finally {
        }
        this.cerrarConex();
        return sec;
    }

    public String eliminarSeccion(Seccion set) {
        ingreso = null;
        try {
            this.Conectar();;
            sql = "delete from seccion where descripcion=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setString(1, set.getDescripcion());
            ejecutar.executeUpdate();
            ingreso = "regristro eliminado";

        } catch (SQLException ex) {
            System.out.println("error en conexion: " + ex);
            ingreso = "error, no se puede eliminar el registro";
        }
        return ingreso;
    }

}
