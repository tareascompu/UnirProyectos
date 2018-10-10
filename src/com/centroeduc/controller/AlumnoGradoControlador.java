/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.centroeduc.controller;

import com.centroeduc.dao.AlumnoDAO;
import com.centroeduc.dao.AlumnoGradoDAO;
import com.centroeduc.model.Alumno;
import com.centroeduc.model.AlumnoGrado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import vista.JFrmAlumnoGrado;

/**
 *
 * @author Usuario
 */
public class AlumnoGradoControlador implements ActionListener {

    JFrmAlumnoGrado grado = new JFrmAlumnoGrado();
    AlumnoGradoDAO dao = new AlumnoGradoDAO();
    AlumnoGrado dato = new AlumnoGrado();

    public AlumnoGradoControlador(JFrmAlumnoGrado form) {
        this.grado = form;
        this.grado.jBtnGuardar.addActionListener(this);
        this.grado.jCmbxNombre.addActionListener(this);
        this.grado.jCbxCodCGSP.addActionListener(this);
        ocultarComboBox();
        listaAlumnoGrado();
        cargarDatos();

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("final");
        if (evento.getSource() == this.grado.jBtnGuardar) {
            JOptionPane.showMessageDialog(null,"hola");
            guardarAlumnGrad();
        }
        if (evento.getSource()==this.grado.jCmbxNombre) {
            this.grado.jCbxCodAlumn.setSelectedIndex(this.grado.jCmbxNombre.getSelectedIndex());
            
        }

    }

    public void guardarAlumnGrad() {
        String mensaje = null;
        
        mensaje = dao.asignarAG(Integer.parseInt(this.grado.jCbxCodAlumn.getSelectedItem().toString()), Integer.parseInt(this.grado.jCbxCodCGSP.getSelectedItem().toString()), Integer.parseInt(this.grado.jTxtCiclo.getText()));
        
                
        JOptionPane.showMessageDialog(null, mensaje);
        //actualizar grado
        listaAlumnoGrado();
        //actualizar datos de la taba
        cargarDatos();
       
    }
    public void ocultarComboBox(){
        //oculta el combobox de codigo alumno
        this.grado.jCbxCodAlumn.setVisible(false);
    }
    public void cargarDatos (){
       ArrayList<AlumnoGrado> listaAlumGrad = new ArrayList();
       ArrayList<Alumno> listaAlumno = new ArrayList();
       
       AlumnoGradoDAO alumnogradodao = new AlumnoGradoDAO();
       AlumnoDAO alumnodao = new AlumnoDAO();
       
       DefaultComboBoxModel alumgrad = new DefaultComboBoxModel();
       DefaultComboBoxModel codAlumno = new DefaultComboBoxModel();
       DefaultComboBoxModel alumno = new DefaultComboBoxModel();
       
       listaAlumno = alumnodao.mostrarAlumno();
        for (int i = 0; i <listaAlumno.size(); i++) {
            alumno.addElement(listaAlumno.get(i).getNombre()+" "+listaAlumno.get(i).getApellido());
            codAlumno.addElement(listaAlumno.get(i).getCodAlumno());
        }
        listaAlumGrad= alumnogradodao.MostrarDatosAlumnosGrado();
        for (int i = 0; i <listaAlumGrad.size(); i++) {
            alumgrad.addElement(listaAlumGrad.get(i).getCcursgradsecprof());
        }
        grado.jCbxCodAlumn.setModel(codAlumno);
        grado.jCmbxNombre.setModel(alumno);
        grado.jCbxCodCGSP.setModel(alumgrad);
    }
   
    public void listaAlumnoGrado(){
        ArrayList<AlumnoGrado> list = new ArrayList();
        list=dao.MostrarDatosAlumnosGrado();
        DefaultTableModel tabla = new DefaultTableModel();
        this.grado.jTblAlumnGrad.setModel(tabla);
        tabla.addColumn("Codigo de Alumno Grado");
        tabla.addColumn("Nombre");
        tabla.addColumn("Apellido");
        tabla.addColumn("Codigo de CGSP");
        tabla.addColumn("Ciclo");
        
        Object[] columna = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            columna[0] = list.get(i).getCodAlumnoGrado();
            columna[1] = list.get(i).getNombre_alumnograd();
            columna[2]=list.get(i).getApellido_alumnograd();
            columna[3] = list.get(i).getCcursgradsecprof();
            columna[4] = list.get(i).getYear();
            
            tabla.addRow(columna);
        }
        
        
    }

    
}
