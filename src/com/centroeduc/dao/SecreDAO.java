
package com.centroeduc.dao;


import com.centroeduc.model.Administrador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.centroeduc.model.Secretaria;




public class SecreDAO  extends Conexion{
    
    private String sql;
    private PreparedStatement run;
    private String ingreso = null;
    ResultSet resultadoSelect;
    
    //Agragar una nueva secrtaria
    Secretaria secre = new Secretaria();
    Administrador admin = new Administrador();
    
    public String nuevaSecretaria(Secretaria secretaria, Administrador adm) {
        System.out.println(secretaria.toString());
        try {
            this.Conectar();
            System.out.println("DAO SECRETARIA");
            sql = "INSERT INTO secretaria values(?,?,?,?,?,?,?,?,?,?,MD5(?),?)"; 
            System.out.println(sql);
            run = this.getMiconexion().prepareStatement(sql);
            
            
            run.setString(1, secretaria.getCodigo());
            run.setString(2, secretaria.getNombre());
            run.setString(3, secretaria.getApellido());
            run.setString(4, secretaria.getDireccion());
            run.setString(5, secretaria.getEmail());
            run.setInt(6, secretaria.getTelCasa());
            run.setInt(7, secretaria.getTelMovil());
            run.setString(8, secretaria.getFechanac());
            run.setLong(9, secretaria.getCui());            
            run.setString(10, adm.getCodigo());
            run.setString(11, secretaria.getPass());            
            run.setInt(12, 1);

            run.executeUpdate();
            ingreso = "Datos Almacenados Exitosamente!";

        } catch (SQLException e) {
            ingreso = "Error al almacenar datos" + e;
            System.out.println("Error en Almacenar los Datos de Secretaria");
        } finally {
            this.cerrarConex();
        }
        return ingreso;
    }
    
     public ArrayList<Secretaria> MostrarSecretaria() {
        ArrayList<Secretaria> array = null;
        ResultSet resultado;
        
        try {
            this.Conectar();
            sql = "select * from secretaria WHERE estado=1";
            run = this.getMiconexion().prepareStatement(sql);
            resultado = run.executeQuery();
            array = new ArrayList();
            
            while (resultado.next()) {
                Secretaria secre = new Secretaria();
                Administrador admin = new Administrador();
                secre.setCodigo(resultado.getString("cod_secre"));
                secre.setNombre(resultado.getString("nombre"));
                secre.setApellido(resultado.getString("apellido"));
                secre.setDireccion(resultado.getString("direccion"));
                secre.setEmail(resultado.getString("email"));
                secre.setTelCasa(resultado.getInt("tel_casa"));
                secre.setTelMovil(resultado.getInt("tel_movil"));
                secre.setFechanac(resultado.getString("fechanac"));
                secre.setCui(resultado.getLong("cui"));
                secre.setCodAdm(resultado.getString("cod_admin"));
                System.out.println(resultado.getString("cod_admin"));
                secre.setPass(resultado.getString("password"));
                secre.setEstado(resultado.getInt("estado"));
                array.add(secre);
            }
        } catch (Exception e) {
            System.out.println("Error en Mostrar Datos" + e);
        } finally {
            this.cerrarConex();
        }
        return array;
    }
     
     public String editarSecretaria(Secretaria secre, Administrador admin) {
        ingreso = null;

        try {
            this.Conectar();
            sql = "Update secretaria set nombre=?, apellido=?, direccion=?, email=?, tel_casa=?, tel_movil=?, fechanac=?, cui=?, password=?, cod_admin=? where cod_secre=?";
            run = this.getMiconexion().prepareStatement(sql);
            
            
            run.setString(1, secre.getNombre());
            run.setString(2, secre.getApellido());
            run.setString(3, secre.getDireccion());
            run.setString(4, secre.getEmail());
            run.setInt(5, secre.getTelCasa());
            run.setInt(6, secre.getTelMovil());
            run.setString(7, secre.getFechanac());
            run.setLong(8, secre.getCui());
            run.setString(9, secre.getPass());  
            run.setString(10, admin.getCodigo());
            run.setString(11, secre.getCodigo());
            
            run.executeUpdate();
            
            ingreso = "Datos Actualizados con exito!";
            
        } catch (SQLException e) {
            System.out.println("Error al actualizar los datos" + e);
        } finally {
            this.cerrarConex();
        }
        return ingreso;
    }
     
   
     public Secretaria buscarCodigo(String id) {
        Secretaria secre = new Secretaria();

        try{
            this.Conectar();
            sql = "SELECT * FROM secretaria WHERE cod_secre=? ";        
            run = this.getMiconexion().prepareStatement(sql);
            run.setString(1, id);
            resultadoSelect = run.executeQuery();
                                   
            if(this.resultadoSelect.next()){
                secre.setCodigo(this.resultadoSelect.getString(1));
                secre.setNombre(this.resultadoSelect.getString("nombre"));
                secre.setApellido(this.resultadoSelect.getString("apellido"));
                secre.setDireccion(this.resultadoSelect.getString("direccion"));
                secre.setEmail(this.resultadoSelect.getString("email"));
                secre.setTelCasa(this.resultadoSelect.getInt("tel_casa"));
                secre.setTelMovil(this.resultadoSelect.getInt("tel_movil"));
                secre.setFechanac(this.resultadoSelect.getString("fechanac"));
                secre.setCui(this.resultadoSelect.getLong("cui"));
                admin.setCodigo(this.resultadoSelect.getString("cod_admin"));
                secre.setPass(this.resultadoSelect.getString("password"));
                secre.setEstado(this.resultadoSelect.getInt("estado"));
            }else{
                System.out.println("No se encontraron registros");
            }             
            run.close();
            resultadoSelect.close();
            
        } catch (Exception e) {
            System.out.println("Error en SecreDao Busqueda" + e);
        } finally {
            this.cerrarConex();
        }
        return secre;
    }
     
      public String estado(Secretaria secre){
        ingreso=null;
        try{
            this.Conectar();
            sql="UPDATE secretaria SET estado=? WHERE cod_secre=?";
            run= this.getMiconexion().prepareStatement(sql);
            
            run.setInt(1, 2);
            run.setString(2, secre.getCodigo());
            run.executeUpdate();
            run.close();
            
        }catch(Exception e){
            System.out.println("Error en el Estado, DAO" + e);
        
        }finally{
            this.cerrarConex();           
        }
        return ingreso;
    }


    
    
    
    
    
    
}
