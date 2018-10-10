package com.centroeduc.controller;

import com.centroeduc.dao.SeccionDAO;
import com.centroeduc.model.Seccion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JFrmSeccion;

/**
 *
 * @author Usuario
 */
public class SeccionControlador implements ActionListener, MouseListener {

    JFrmSeccion seccion = new JFrmSeccion();
    SeccionDAO dao = new SeccionDAO();
    Seccion ser = new Seccion();

    public SeccionControlador(JFrmSeccion se) {

        this.seccion = se;
        this.seccion.jBtnGuardar.addActionListener(this);
        this.seccion.jBtnActualizar.addActionListener(this);
        this.seccion.jBtnEliminar.addActionListener(this);
        this.seccion.jTblSeccion.addMouseListener(this);
        listaSeccion();
    }

    @Override
    public void actionPerformed(ActionEvent eventoS) {
        
        if (eventoS.getSource() == this.seccion.jBtnGuardar) {
            guardarSeccion();
        }

        if (eventoS.getSource() == this.seccion.jBtnActualizar) {
            actualizarSeccion();
        }

        if (eventoS.getSource() == this.seccion.jBtnEliminar) {
            eliminarSeccion();
        }
    }

    public void guardarSeccion() {
        String mensaje = null;

        ser.setDescripcion(this.seccion.jTxtDescripcion.getText());
        mensaje = dao.insertarSeccion(ser);

        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaSeccion();
    }

    public void actualizarSeccion() {
        String mensaje = null;

        ser.setDescripcion(this.seccion.jTxtDescripcion.getText());
        ser.setCodigo(Integer.parseInt(this.seccion.jTxtCodigo.getText()));
        mensaje = dao.editarSeccion(ser);

        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaSeccion();
    }

    public void eliminarSeccion() {
        String mensaje = null;

        ser.setDescripcion(this.seccion.jTxtDescripcion.getText());
        mensaje = dao.eliminarSeccion(ser);

        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaSeccion();
    }

    public void listaSeccion() {
        ArrayList<Seccion> list = new ArrayList();
        list = dao.MostrarSeccion();
        DefaultTableModel tabla = new DefaultTableModel();
        this.seccion.jTblSeccion.setModel(tabla);

        tabla.addColumn("Codigo");
        tabla.addColumn("Descripcion");

        Object[] columna = new Object[2];

        for (int i = 0; i < list.size(); i++) {
            columna[0] = list.get(i).getCodigo();
            columna[1] = list.get(i).getDescripcion();

            tabla.addRow(columna);
        }
    }

    public void limpiarControles() {
        seccion.jTxtCodigo.setText(null);
        seccion.jTxtDescripcion.setText(null);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (me.getSource() == seccion.jTblSeccion) {
            seccion.jTxtCodigo.setText(seccion.jTblSeccion.getValueAt(seccion.jTblSeccion.getSelectedRow(), 0).toString());
            seccion.jTxtDescripcion.setText(seccion.jTblSeccion.getValueAt(seccion.jTblSeccion.getSelectedRow(), 1).toString());

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
