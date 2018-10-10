/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.centroeduc.controller;

import com.centroeduc.dao.AsignGSCPDAO;
import com.centroeduc.dao.CursoDAO;
import com.centroeduc.dao.GradoDAO;
import com.centroeduc.dao.MaestroDAO;
import com.centroeduc.dao.SeccionDAO;
import com.centroeduc.model.AsignacionGSCP;
import com.centroeduc.model.Curso;
import com.centroeduc.model.Grado;
import com.centroeduc.model.Maestro;
import com.centroeduc.model.Seccion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JFrmAsigGSCP;

/**
 *
 * @author Usuario
 */
public class AsignGSCPControlador implements ActionListener, MouseListener {

    JFrmAsigGSCP asign = new JFrmAsigGSCP();
    AsignGSCPDAO dao = new AsignGSCPDAO();
    AsignacionGSCP dato = new AsignacionGSCP();

    public AsignGSCPControlador(JFrmAsigGSCP form) {
        this.asign = form;
        this.asign.jBtnGuardar.addActionListener(this);
        this.asign.jBtnActualizar.addActionListener(this);
        this.asign.jCbxGrado.addActionListener(this);
        this.asign.jCbxSeccion.addActionListener(this);
        this.asign.jCbxCurso.addActionListener(this);
        this.asign.jCbxProfesor.addActionListener(this);
        this.asign.jTblAsignar.addMouseListener(this);
        ocultarComboBox();
        listaGSCP();
        mostrarGSCP();
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (evento.getSource() == this.asign.jBtnGuardar) {
            guardarGSCP();
        }
        if (evento.getSource() == this.asign.jBtnActualizar) {
            actualizarGSCP();
        }
        if (evento.getSource() == this.asign.jCbxGrado) {
            this.asign.jCbxGradoCodigo.setSelectedIndex(this.asign.jCbxGrado.getSelectedIndex());
        }
        if (evento.getSource() == this.asign.jCbxSeccion) {
            this.asign.jCbxSeccionCodigo.setSelectedIndex(this.asign.jCbxSeccion.getSelectedIndex());
        }
        if (evento.getSource() == this.asign.jCbxCurso) {
            this.asign.jCbxCursoCodigo.setSelectedIndex(this.asign.jCbxCurso.getSelectedIndex());
        }
        if (evento.getSource() == this.asign.jCbxProfesor) {
            this.asign.jCbxProfesorCodigo.setSelectedIndex(this.asign.jCbxProfesor.getSelectedIndex());
        }

    }

    public void guardarGSCP() {

        String mensaje = null;
        mensaje = dao.newAsing(Integer.parseInt(this.asign.jCbxGradoCodigo.getSelectedItem().toString()), Integer.parseInt(this.asign.jCbxSeccionCodigo.getSelectedItem().toString()), Integer.parseInt(this.asign.jCbxCursoCodigo.getSelectedItem().toString()), this.asign.jCbxProfesorCodigo.getSelectedItem().toString());

        JOptionPane.showMessageDialog(null, mensaje);
        listaGSCP();
        mostrarGSCP();
    }

   
    
    public void actualizarGSCP() {        
        String mensaje = null;
        dato.setCodigo(Integer.parseInt(this.asign.jLblCodigoGeneral.getText()));
        dato.setCdGrado(Integer.parseInt(this.asign.jCbxGradoCodigo.getSelectedItem().toString()));
        dato.setCdSecc(Integer.parseInt(this.asign.jCbxSeccionCodigo.getSelectedItem().toString()));
        dato.setCdCurso(Integer.parseInt(this.asign.jCbxCursoCodigo.getSelectedItem().toString()));
        dato.setCdMaestro(this.asign.jCbxProfesorCodigo.getSelectedItem().toString());
        mensaje = dao.actualizarDatos(dato);
        
        JOptionPane.showMessageDialog(null, mensaje);
        listaGSCP();
        mostrarGSCP();

    }

    public void ocultarComboBox() {
        //para ocultar combobox de los codigos
        this.asign.jCbxGradoCodigo.setVisible(false);
        this.asign.jCbxSeccionCodigo.setVisible(false);
        this.asign.jCbxCursoCodigo.setVisible(false);
        this.asign.jCbxProfesorCodigo.setVisible(false);
    }
   

    public void mostrarGSCP() {
        ArrayList<AsignacionGSCP> list = new ArrayList();
        ArrayList<Grado> listaGrado = new ArrayList();
        ArrayList<Seccion> listaSeccion = new ArrayList();
        ArrayList<Curso> listaCurso = new ArrayList();
        ArrayList<Maestro> listaMaestro = new ArrayList();

        GradoDAO gradodao = new GradoDAO();
        SeccionDAO secciondao = new SeccionDAO();
        CursoDAO cursodao = new CursoDAO();
        MaestroDAO maestrodao = new MaestroDAO();

        DefaultComboBoxModel grado = new DefaultComboBoxModel();
        DefaultComboBoxModel seccion = new DefaultComboBoxModel();
        DefaultComboBoxModel curso = new DefaultComboBoxModel();
        DefaultComboBoxModel maestro = new DefaultComboBoxModel();

        DefaultComboBoxModel codgrado = new DefaultComboBoxModel();
        DefaultComboBoxModel codseccion = new DefaultComboBoxModel();
        DefaultComboBoxModel codcurso = new DefaultComboBoxModel();
        DefaultComboBoxModel codmaestro = new DefaultComboBoxModel();

        listaGrado = gradodao.mostrarGrado();
        for (int i = 0; i < listaGrado.size(); i++) {
            grado.addElement(listaGrado.get(i).getDescripcion());
            codgrado.addElement(listaGrado.get(i).getCodigo());
        }

        listaSeccion = secciondao.MostrarSeccion();
        for (int i = 0; i < listaSeccion.size(); i++) {
            seccion.addElement(listaSeccion.get(i).getDescripcion());
            codseccion.addElement(listaSeccion.get(i).getCodigo());

        }

        listaMaestro = maestrodao.Mostrarprofesor();
        for (int i = 0; i < listaMaestro.size(); i++) {
            maestro.addElement(listaMaestro.get(i).getNombre() + " " + listaMaestro.get(i).getApellido());
            codmaestro.addElement(listaMaestro.get(i).getCodigo());
        }

        listaCurso = cursodao.listCourse();
        for (int i = 0; i < listaCurso.size(); i++) {
            curso.addElement(listaCurso.get(i).getNombre());
            codcurso.addElement(listaCurso.get(i).getCod());

        }

        asign.jCbxGrado.setModel(grado);
        asign.jCbxSeccion.setModel(seccion);
        asign.jCbxProfesor.setModel(maestro);
        asign.jCbxCurso.setModel(curso);
        asign.jCbxGradoCodigo.setModel(codgrado);
        asign.jCbxSeccionCodigo.setModel(codseccion);
        asign.jCbxProfesorCodigo.setModel(codmaestro);
        asign.jCbxCursoCodigo.setModel(codcurso);

    }

    public void listaGSCP() {
        ArrayList<AsignacionGSCP> list = new ArrayList();
        list = dao.listAsign();
        DefaultTableModel tabla = new DefaultTableModel();
        this.asign.jTblAsignar.setModel(tabla);
        //tabla.addColumn("Cod_Curs_Grad_Sec_Prof");
        tabla.addColumn("cod_Cursgradsecprof");
        tabla.addColumn("cod_prof");        
        tabla.addColumn("nombre");
        tabla.addColumn("apellido");
       // tabla.addColumn("Cod_grado");
        tabla.addColumn("nombre_grado");
       // tabla.addColumn("cod_sec");
        tabla.addColumn("nombre_seccion");
        //tabla.addColumn("Cod_curso");
        tabla.addColumn("nombre_curso");
        

        Object[] columna = new Object[9];
        for (int i = 0; i < list.size(); i++) {
            columna[0]= list.get(i).getCod_asignar();
            columna[1] = list.get(i).getCdMaestro();
            columna[2] = list.get(i).getNombre();
            columna[3] = list.get(i).getApellido();
           // columna[3] = list.get(i).getCdGrado();
            columna[4] = list.get(i).getNomre_grado();
            //columna[5] = list.get(i).getCdSecc();
            columna[5] = list.get(i).getNombre_seccion();
            //columna[7] = list.get(i).getCdCurso();
            columna[6] = list.get(i).getNombre_curso();
            tabla.addRow(columna);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (me.getSource()== this.asign.jTblAsignar) {
            asign.jLblCodigoGeneral.setText(asign.jTblAsignar.getValueAt(asign.jTblAsignar.getSelectedRow(), 0).toString());
            asign.jCbxGrado.setSelectedItem(asign.jTblAsignar.getValueAt(asign.jTblAsignar.getSelectedRow(), 1).toString());
            asign.jCbxSeccion.setSelectedItem(asign.jTblAsignar.getValueAt(asign.jTblAsignar.getSelectedRow(), 2).toString());
            asign.jCbxCurso.setSelectedItem(asign.jTblAsignar.getValueAt(asign.jTblAsignar.getSelectedRow(), 3).toString());
            asign.jCbxProfesor.setSelectedItem(asign.jTblAsignar.getValueAt(asign.jTblAsignar.getSelectedRow(), 4).toString());
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
