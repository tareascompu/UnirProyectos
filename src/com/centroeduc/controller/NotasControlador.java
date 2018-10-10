package com.centroeduc.controller;

import com.centroeduc.dao.NotasDAO;
import com.centroeduc.model.Notas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JFrmNotas;

/**
 *
 * @author Usuario
 */
public class NotasControlador implements ActionListener, MouseListener {

    JFrmNotas notas = new JFrmNotas();
    NotasDAO dao = new NotasDAO();
    Notas not = new Notas();

    public NotasControlador(JFrmNotas no) {

        this.notas = no;
        this.notas.jBtnGuardar.addActionListener(this);
        this.notas.jBtnActualizar.addActionListener(this);
        this.notas.jTblNotas.addMouseListener(this);
        listaNotas();
    }

    @Override
    public void actionPerformed(ActionEvent eventoN) {

        if (eventoN.getSource() == this.notas.jBtnGuardar) {
            guardarNotas();
        }
        
        if (eventoN.getSource()==this.notas.jBtnActualizar) {
            actualizarNotas();
        }
    }

    public void guardarNotas() {
        String mensaje = null;

        not.setCodAlumGrado(Integer.parseInt(this.notas.jTxtCodigoAlumnoGrado.getText()));
        not.setNota(Integer.parseInt(this.notas.jTxtCodigoAlumnoGrado.getText()));
        not.setCodUnidad(Integer.parseInt(this.notas.jTxtCodigoUnidad.getText()));
        not.setCusGradSecProf(Integer.parseInt(this.notas.jTxtCodigoAGSCP.getText()));
        mensaje = dao.agregarNotas(not);

        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaNotas();
    }

    public void actualizarNotas() {
        String mensaje = null;

        not.setCodAlumGrado(Integer.parseInt(this.notas.jTxtCodigoAlumnoGrado.getText()));
        not.setNota(Integer.parseInt(this.notas.jTxtNota.getText()));
        mensaje = dao.modificar(not);

        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaNotas();
    }

    public void listaNotas() {
        ArrayList<Notas> list = new ArrayList();
        list = dao.MostrarNotas();
        DefaultTableModel tabla = new DefaultTableModel();
        this.notas.jTblNotas.setModel(tabla);

        tabla.addColumn("Codigo-Alumno-Grado");
        tabla.addColumn("Nota");
        tabla.addColumn("Codigo Unidad");
        tabla.addColumn("Codigo Asignacion GSCP");

        Object[] columna = new Object[4];

        for (int i = 0; i < list.size(); i++) {

            columna[0] = list.get(i).getCodAlumGrado();
            columna[1] = list.get(i).getNota();
            columna[2] = list.get(i).getCodUnidad();
            columna[3] = list.get(i).getCusGradSecProf();

            tabla.addRow(columna);
        }
    }

    public void limpiarControles() {
        notas.jTxtCodigoAGSCP.setText(null);
        notas.jTxtCodigoAlumnoGrado.setText(null);
        notas.jTxtCodigoAlumnoGrado.setText(null);
        notas.jTxtCodigoUnidad.setText(null);
    }

    @Override
    public void mouseClicked(MouseEvent me) {

        if (me.getSource() == notas.jTblNotas) {
            notas.jTxtCodigoAlumnoGrado.setText(notas.jTblNotas.getValueAt(notas.jTblNotas.getSelectedRow(), 0).toString());
            notas.jTxtNota.setText(notas.jTblNotas.getValueAt(notas.jTblNotas.getSelectedRow(), 1).toString());
            notas.jTxtCodigoUnidad.setText(notas.jTblNotas.getValueAt(notas.jTblNotas.getSelectedRow(), 2).toString());
            notas.jTxtCodigoAGSCP.setText(notas.jTblNotas.getValueAt(notas.jTblNotas.getSelectedRow(), 3).toString());
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
