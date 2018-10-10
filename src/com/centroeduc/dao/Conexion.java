package com.centroeduc.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

  private Connection miconexion;
  
/*  private static final String URL = "jdbc:mysql://10.12.48.30:3306/centro_educativo";
  private static final String USER = "Markwell";
  private static final String PASSWORD = "Henry118"; */
    private static final String URL = "jdbc:mysql://localhost:3306/centro_educativo";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Connection getMiconexion() {
        return miconexion;
    }
    public void setMiconexion(Connection miconexion) {
        this.miconexion = miconexion;
    }
    public void Conectar() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            miconexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error en conexion: " + ex);
        }
    }
    public void cerrarConex() {
        try {
            if (miconexion != null) {
                if (miconexion.isClosed() == false) {
                    miconexion.close();
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar: " + ex);
        }
    }
}
