/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.centroeduc.controller;

import com.centroeduc.dao.AlumnoDAO;
import com.centroeduc.model.Alumno;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JFrmAlumnoSave;
import vista.MdiAdmin;

/**
 *
 * @author javam2018
 */
public class AlumnoControlador implements ActionListener, MouseListener {

    JFrmAlumnoSave alumno = new JFrmAlumnoSave();
    AlumnoDAO dao = new AlumnoDAO();
    Alumno dato = new Alumno();

    public AlumnoControlador(JFrmAlumnoSave form) {
        this.alumno = form;
        this.alumno.jBtnGuardar.addActionListener(this);
        this.alumno.jBtnModificars.addActionListener(this);
        this.alumno.jTblAlumno.addMouseListener(this);
        this.alumno.jBtnEstado.addActionListener(this);
        listaAlumno();
    }
    @Override
    public void actionPerformed(ActionEvent evento) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("final");
        if (evento.getSource() == this.alumno.jBtnGuardar) {
            guardarAlumno();

        }
        if (evento.getSource()==this.alumno.jBtnModificars) {
            modificarAlumno();
        }
        if (evento.getSource()==this.alumno.jBtnEstado) {
            estado();
            
        }
        
       
    }
    public void guardarAlumno() {
        
        
        String mensaje = null;
        
        String anio = Integer.toString(this.alumno.jDCFechanac.getCalendar().get(java.util.Calendar.YEAR));
        String mes = Integer.toString(this.alumno.jDCFechanac.getCalendar().get(java.util.Calendar.MONTH) + 1);
        String dia = Integer.toString(this.alumno.jDCFechanac.getCalendar().get(java.util.Calendar.DATE));

        //convirtiendo al formato deseado
        String fechaseleccionada = anio + "/" + mes + "/" + dia;
        
        dato.setNombre(this.alumno.jTxtNombre.getText());
        dato.setApellido(this.alumno.jTxtApellido.getText());
        dato.setDireccion(this.alumno.jTxtDireccion.getText());
        dato.setEmail(this.alumno.jTxtEmail.getText());
        dato.setTelEmergencia(Integer.parseInt(this.alumno.jTxtTelEmergencia.getText()));
        dato.setCodEncargado(this.alumno.jTxtCodEncargado.getText());
        dato.setCodSecretaria(this.alumno.jTxtCodSecretaria.getText());
        //dato.setFechanac(this.alumno.jTxtFechaNac.getText());
        dato.setFechanac(fechaseleccionada);
        dato.setPadecimiento(this.alumno.jTxtAPadecimiento.getText());
        dato.setEstado(1);
        mensaje = dao.ingresarAlum(dato);
        
        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaAlumno();

    }
    
    public void listaAlumno(){
        ArrayList<Alumno> list = new ArrayList();
        list = dao.mostrarAlumno();
        DefaultTableModel tabla = new DefaultTableModel();
        this.alumno.jTblAlumno.setModel(tabla);
        tabla.addColumn("Codigo");
        tabla.addColumn("Nombre");
        tabla.addColumn("Apellido");
        tabla.addColumn("Direccion");
        tabla.addColumn("Email");
        tabla.addColumn("Tel Emergencia");
        tabla.addColumn("Cod Encargado");
        tabla.addColumn("Cod Secretaria");
        tabla.addColumn("Fecha Nacimiento");
        tabla.addColumn("Padecimientos");
        
        Object[] columna = new Object[10];
        for (int i = 0; i < list.size(); i++) {
            columna[0] = list.get(i).getCodAlumno();
            columna[1] = list.get(i).getNombre();
            columna[2] = list.get(i).getApellido();
            columna[3] = list.get(i).getDireccion();
            columna[4] = list.get(i).getEmail();
            columna[5] = list.get(i).getTelEmergencia();
            columna[6] = list.get(i).getCodEncargado();
            columna[7] = list.get(i).getCodSecretaria();
            columna[8] = list.get(i).getFechanac();
            columna[9] = list.get(i).getPadecimiento();
            
            tabla.addRow(columna);
            
        }
    }
    public void modificarAlumno(){
        String mensaje = null;
        
        
        String anio = Integer.toString(this.alumno.jDCFechanac.getCalendar().get(java.util.Calendar.YEAR));
        String mes = Integer.toString(this.alumno.jDCFechanac.getCalendar().get(java.util.Calendar.MONTH) + 1);
        String dia = Integer.toString(this.alumno.jDCFechanac.getCalendar().get(java.util.Calendar.DATE));

        
        String fechaseleccionada = anio + "/" + mes + "/" + dia;
        
        dato.setCodAlumno(Integer.parseInt(this.alumno.jTxtCodAl.getText()));
        dato.setNombre(this.alumno.jTxtNombre.getText());
        dato.setApellido(this.alumno.jTxtApellido.getText());
        dato.setDireccion(this.alumno.jTxtDireccion.getText());
        dato.setEmail(this.alumno.jTxtEmail.getText());
        dato.setTelEmergencia(Integer.parseInt(this.alumno.jTxtTelEmergencia.getText()));
        dato.setCodEncargado(this.alumno.jTxtCodEncargado.getText());
        dato.setCodSecretaria(this.alumno.jTxtCodSecretaria.getText());
        //dato.setFechanac(this.alumno.jTxtFechaNac.getText());
        dato.setFechanac(fechaseleccionada);        
        dato.setPadecimiento(this.alumno.jTxtAPadecimiento.getText());
        
        mensaje = dao.modificarAlumno(dato);
        
        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaAlumno();  
        
        
    }
    public void estado(){
        String mensaje = null;
        dato.setCodAlumno(Integer.parseInt(this.alumno.jTxtCodAl.getText()));
        mensaje=dao.estadoAlumno(dato);
        
        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaAlumno();
    }

    public void limpiarControles() {
        Calendar clear = new GregorianCalendar();
        alumno.jDCFechanac.setCalendar(clear);
        
        alumno.jTxtNombre.setText(null);
        alumno.jTxtApellido.setText(null);
        alumno.jTxtDireccion.setText(null);
        alumno.jTxtEmail.setText(null);
        alumno.jTxtTelEmergencia.setText(null);
        alumno.jTxtCodEncargado.setText(null);
        alumno.jTxtCodSecretaria.setText(null);
        //alumno.jTxtFechaNac.setText(null);
        alumno.jTxtAPadecimiento.setText(null);

    }
    @Override
    public void mouseClicked(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (me.getSource()==alumno.jTblAlumno) {
            alumno.jTxtCodAl.setText(alumno.jTblAlumno.getValueAt(alumno.jTblAlumno.getSelectedRow(), 0).toString());
            alumno.jTxtNombre.setText(alumno.jTblAlumno.getValueAt(alumno.jTblAlumno.getSelectedRow(), 1).toString());
            alumno.jTxtApellido.setText(alumno.jTblAlumno.getValueAt(alumno.jTblAlumno.getSelectedRow(), 2).toString());
            alumno.jTxtDireccion.setText(alumno.jTblAlumno.getValueAt(alumno.jTblAlumno.getSelectedRow(), 3).toString());
            alumno.jTxtEmail.setText(alumno.jTblAlumno.getValueAt(alumno.jTblAlumno.getSelectedRow(), 4).toString());
            alumno.jTxtTelEmergencia.setText(alumno.jTblAlumno.getValueAt(alumno.jTblAlumno.getSelectedRow(), 5).toString());
            alumno.jTxtCodEncargado.setText(alumno.jTblAlumno.getValueAt(alumno.jTblAlumno.getSelectedRow(), 6).toString());
            alumno.jTxtCodSecretaria.setText(alumno.jTblAlumno.getValueAt(alumno.jTblAlumno.getSelectedRow(), 7).toString());
           // alumno.jTxtFechaNac.setText(alumno.jTblAlumno.getValueAt(alumno.jTblAlumno.getSelectedRow(), 8).toString());
           
            Date fechaSelect = Date.valueOf(alumno.jTblAlumno.getValueAt(alumno.jTblAlumno.getSelectedRow(), 8).toString());
            alumno.jDCFechanac.setDate(fechaSelect);
            
            alumno.jTxtAPadecimiento.setText(alumno.jTblAlumno.getValueAt(alumno.jTblAlumno.getSelectedRow(), 9).toString());
 
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

