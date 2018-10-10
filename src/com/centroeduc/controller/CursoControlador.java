/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.centroeduc.controller;

import com.centroeduc.dao.CursoDAO;
import com.centroeduc.model.Curso;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JFrmCurso;

/**
 *
 * @author javam2018
 */
public class CursoControlador  implements ActionListener,MouseListener{
    JFrmCurso curso = new JFrmCurso();
    CursoDAO dao = new CursoDAO();
    Curso dato = new Curso();
    
    public CursoControlador(JFrmCurso form){
        this.curso=form;
        this.curso.jBtnGuardar.addActionListener(this);
        this.curso.jBtnModificar.addActionListener(this);
        this.curso.jBtnEstado.addActionListener(this);
        this.curso.jTblCurso.addMouseListener(this);
        listaCurso();
        
        
    }
    @Override
    public void actionPerformed(ActionEvent evento) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("final");
        if (evento.getSource()==this.curso.jBtnGuardar) {
            guardarCurso();
        }
        if (evento.getSource()== this.curso.jBtnModificar) {
             modificarCurso();
        }
        if (evento.getSource()==this.curso.jBtnEstado) {
            
        }
    }
    public void guardarCurso(){
        String mensaje = null;
        dato.setNombre(this.curso.jTxtNombre.getText());
        dato.setHinicio(this.curso.jTxtInicio.getText());
        dato.setHfin(this.curso.jTxtFin.getText());
        dato.setJornada(this.curso.jTxtJornada.getText());
        dato.setCupo(Integer.parseInt(this.curso.jTxtCupo.getText()));
        dato.setEstado(1);
        mensaje =dao.newCurso(dato);
        
        JOptionPane.showMessageDialog(null,mensaje);
        listaCurso();
        limpiarControles();
       
    }
    public void listaCurso(){
        ArrayList<Curso> list = new ArrayList();
        list=dao.listCourse();
        DefaultTableModel tabla = new DefaultTableModel();
        this.curso.jTblCurso.setModel(tabla);
        tabla.addColumn("Codigo");
        tabla.addColumn("Nombre");
        tabla.addColumn("Horario de Inicio");
        tabla.addColumn("Horario de Fin");
        tabla.addColumn("Jornada");
        tabla.addColumn("Cupo");
        tabla.addColumn("Estado");
        
        Object[] columna = new Object[10];
        for (int i = 0; i <list.size(); i++) {
            columna[0]=list.get(i).getCod();
            columna[1]=list.get(i).getNombre();
            columna[2]=list.get(i).getHinicio();
            columna[3]=list.get(i).getHfin();
            columna[4]=list.get(i).getJornada();
            columna[5]=list.get(i).getCupo();
            columna[6]=list.get(i).getEstado();
            
            tabla.addRow(columna);
            
        }
        
    }
     public void modificarCurso(){
         String mensaje = null;
        
        dato.setNombre(this.curso.jTxtNombre.getText());
        dato.setHinicio(this.curso.jTxtInicio.getText());
        dato.setHfin(this.curso.jTxtFin.getText());
        dato.setJornada(this.curso.jTxtJornada.getText());
        dato.setCupo(Integer.parseInt(this.curso.jTxtCupo.getText()));
        
        mensaje= dao.updateCourse(dato);
        JOptionPane.showMessageDialog(null, mensaje);
        listaCurso();
        limpiarControles();
         
     }
       public void limpiarControles() {
        curso.jTxtNombre.setText(null);
        curso.jTxtInicio.setText(null);
        curso.jTxtFin.setText(null);
        curso.jTxtJornada.setText(null);
        curso.jTxtCupo.setText(null);

    }

    @Override
    public void mouseClicked(MouseEvent me) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (me.getSource()==curso.jTblCurso) {
            curso.jLblCodigo.setText(curso.jTblCurso.getValueAt(curso.jTblCurso.getSelectedRow(),0).toString());
            curso.jTxtNombre.setText(curso.jTblCurso.getValueAt(curso.jTblCurso.getSelectedRow(),1).toString());
            curso.jTxtInicio.setText(curso.jTblCurso.getValueAt(curso.jTblCurso.getSelectedRow(),2).toString());
            curso.jTxtFin.setText(curso.jTblCurso.getValueAt(curso.jTblCurso.getSelectedRow(),3).toString());
            curso.jTxtJornada.setText(curso.jTblCurso.getValueAt(curso.jTblCurso.getSelectedRow(),4).toString());
            curso.jTxtCupo.setText(curso.jTblCurso.getValueAt(curso.jTblCurso.getSelectedRow(),5).toString());
            
            
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
