package com.centroeduc.dao;

import com.centroeduc.model.AsignacionGSCP;
import com.centroeduc.model.DatosProfesor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AsignGSCPDAO extends Conexion {

    private String sql;
    private PreparedStatement run;
    private String answer;
    ResultSet values;

    AsignacionGSCP asign = new AsignacionGSCP();

    public String newAsing(int cdGrado, int cdSeccion, int cdCurso, String cdMeastro) {
        answer = null;
        try {
            this.Conectar();
            sql = "insert into cursgradsecprof(cod_grad, cod_sec, cod_curso, cod_prof) values(?,?,?,?)";
            run = this.getMiconexion().prepareStatement(sql);
            run.setInt(1, cdGrado);
            run.setInt(2, cdSeccion);
            run.setInt(3, cdCurso);
            run.setString(4, cdMeastro);
            run.executeUpdate();

            answer = "Asignacion Realizada con Exito";
            run.close();

        } catch (SQLException e) {
            System.out.println("Error al asignar: " + e);
            answer = "No se pudo realizar la Asignacion";
        } finally {
            this.cerrarConex();
        }
        return answer;
    }

    //mostrar datos en la tabla
    public ArrayList<AsignacionGSCP> listAsign() {
        ArrayList<AsignacionGSCP> list = new ArrayList();
        try {
            this.Conectar();
            sql = "select* from datos_profesores";
            run = this.getMiconexion().prepareStatement(sql);
            values = this.run.executeQuery();
            while (values.next()) {
                
               AsignacionGSCP asignacion = new AsignacionGSCP();
               asignacion.setCod_asignar(this.values.getInt("codigo_asig"));
               asignacion.setCdMaestro(this.values.getString("cod_prof"));
               asignacion.setNombre(this.values.getString("nombre"));
               asignacion.setApellido(this.values.getString("apellido"));
               asignacion.setCdGrado(this.values.getInt("cod_grado"));
               asignacion.setNomre_grado(this.values.getString("nombre_grado"));
               asignacion.setCdSecc(this.values.getInt("cod_sec"));
               asignacion.setNombre_seccion(this.values.getString("nombre_seccion"));
               asignacion.setCdCurso(this.values.getInt("cod_curso"));
               asignacion.setNombre_curso(this.values.getString("nombre_curso"));

                list.add(asignacion);
            }
        } catch (SQLException e) {
            System.out.println("Error en AsignGSCPDAO(lista): " + e);
        } finally {
            this.cerrarConex();
        }
        return list;
    }

    //mostrar datos en los combobox
    public ArrayList<AsignacionGSCP> DatosGSCP() {
        ArrayList<AsignacionGSCP> list = new ArrayList();
        try {
            this.Conectar();
            sql = "select * from cursgradsecprof";
            run = this.getMiconexion().prepareStatement(sql);
            values = this.run.executeQuery();
            while (values.next()) {
                AsignacionGSCP asignar = new AsignacionGSCP();
                asignar.setCdGrado(values.getInt("cod_grad"));
                asignar.setCdSecc(values.getInt("cod_sec"));
                asignar.setCdCurso(values.getInt("cod_curso"));
                asignar.setCdMaestro(values.getString("cod_prof"));
                list.add(asign);
            }

        } catch (SQLException e) {
            System.out.println("error en datos de asignacion GSCP" + e);
        } finally {
            this.cerrarConex();
        }
        return list;
    }

    public String actualizarDatos(AsignacionGSCP asignar) {
        answer = null;

        try {
            this.Conectar();
            sql = "update cursgradsecprof set cod_grad=?, cod_sec=?,cod_curso=?, cod_prof=? where cod_curs_grad_sec_prof=?";
            run = this.getMiconexion().prepareStatement(sql);
            run.setInt(1, asignar.getCdGrado());
            run.setInt(2, asignar.getCdSecc());
            run.setInt(3, asignar.getCdCurso());
            run.setString(4, asignar.getCdMaestro());
            run.setInt(5,asignar.getCodigo());
            run.executeUpdate();
            answer = "dato almacenado correctamente";
           
        } catch (SQLException e) {
            System.out.println("error en el dao de actualizar los datos" + e);
            answer = "error en dao" + e;
        } finally {
            this.cerrarConex();
        }
        return answer;
    }

}
