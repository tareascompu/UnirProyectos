package com.centroeduc.controller;

import com.centroeduc.dao.GradoDAO;
import com.centroeduc.model.Grado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JFrmGrado;

/**
 *
 * @author Usuario
 */
public class GradoControlador implements ActionListener, MouseListener {

    JFrmGrado grado = new JFrmGrado();
    GradoDAO dao = new GradoDAO();
    Grado gra = new Grado();

    public GradoControlador(JFrmGrado ga) {

        this.grado = ga;
        this.grado.jBtnGuardar.addActionListener(this);
        this.grado.jBtnActualizar.addActionListener(this);
        this.grado.jBtnEstado.addActionListener(this);
        this.grado.jTblGrado.addMouseListener(this);
        listaGrado();
    }

    @Override
    public void actionPerformed(ActionEvent eventoG) {
        
        if (eventoG.getSource() == this.grado.jBtnGuardar) {
            guardarGrado();
        }
        if (eventoG.getSource() == this.grado.jBtnActualizar) {
            actualizarGrado();
        }
        if (eventoG.getSource()==this.grado.jBtnEstado) {
            cambiarEstado();
        }
    }

    public void guardarGrado() {
        String mensaje = null;

        gra.setDescripcion(this.grado.jTxtDescripcion.getText());
        gra.setEstado(1);
        mensaje = dao.ingresarGrado(gra);

        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaGrado();
    }

    public void actualizarGrado() {
        String mensaje = null;

        gra.setDescripcion(this.grado.jTxtDescripcion.getText());
        gra.setCodigo(Integer.parseInt(this.grado.jTxtCodigo.getText()));
        mensaje = dao.modificarGrado(gra);

        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaGrado();
    }

    public void cambiarEstado() {
        String mensaje = null;

        gra.setDescripcion(this.grado.jTxtDescripcion.getText());
        mensaje = dao.estadoGrado(gra);

        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaGrado();
    }

    public void listaGrado() {
        ArrayList<Grado> list = new ArrayList();
        list = dao.mostrarGrado();
        DefaultTableModel tabla = new DefaultTableModel();
        this.grado.jTblGrado.setModel(tabla);

        tabla.addColumn("Codigo");
        tabla.addColumn("Descipcion");

        Object[] columna = new Object[2];

        for (int i = 0; i < list.size(); i++) {
            columna[0] = list.get(i).getCodigo();
            columna[1] = list.get(i).getDescripcion();

            tabla.addRow(columna);
        }
    }

    public void limpiarControles() {
        grado.jTxtCodigo.setText(null);
        grado.jTxtDescripcion.setText(null);
    }

    @Override
    public void mouseClicked(MouseEvent me) {

        if (me.getSource() == grado.jTblGrado) {
            grado.jTxtCodigo.setText(grado.jTblGrado.getValueAt(grado.jTblGrado.getSelectedRow(), 0).toString());
            grado.jTxtDescripcion.setText(grado.jTblGrado.getValueAt(grado.jTblGrado.getSelectedRow(), 1).toString());
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
