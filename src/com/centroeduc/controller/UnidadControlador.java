package com.centroeduc.controller;

import com.centroeduc.dao.UnidadDAO;
import com.centroeduc.model.Unidad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JFrmUnidad;

/**
 *
 * @author Usuario
 */
public class UnidadControlador implements ActionListener, MouseListener {

    JFrmUnidad unidad = new JFrmUnidad();
    UnidadDAO dao = new UnidadDAO();
    Unidad uni = new Unidad();

    public UnidadControlador(JFrmUnidad un) {

        this.unidad = un;
        this.unidad.jBtnGuardar.addActionListener(this);
        this.unidad.jBtnEstado.addActionListener(this);
        this.unidad.jBtnActualizar.addActionListener(this);
        this.unidad.jTblUnidad.addMouseListener(this);
        listaUnidad();
    }

    @Override
    public void actionPerformed(ActionEvent eventoU) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Unidad");
        if (eventoU.getSource() == this.unidad.jBtnGuardar) {
            guardarUnidad();
        }

        if (eventoU.getSource() == this.unidad.jBtnEstado) {
            cambiarEstado();
        }

        if (eventoU.getSource() == this.unidad.jBtnActualizar) {
            editarUnidad();
        }
    }

    public void guardarUnidad() {
        String mensaje = null;

        uni.setNombre(this.unidad.jTxtNombre.getText());
        uni.setDescripcion(this.unidad.jTxtDescripcion.getText());
        uni.setFechaIni(this.unidad.jTxtFechaInicio.getText());
        uni.setFechaFin(this.unidad.jTxtFechaFin.getText());
        uni.setEstado(1);
        mensaje = dao.ingresoUnidad(uni);

        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaUnidad();
    }

    public void editarUnidad() {
        String mensaje = null;

        uni.setNombre(this.unidad.jTxtNombre.getText());
        uni.setDescripcion(this.unidad.jTxtDescripcion.getText());
        uni.setFechaIni(this.unidad.jTxtFechaInicio.getText());
        uni.setFechaFin(this.unidad.jTxtFechaFin.getText());
        uni.setCodigo(Integer.parseInt(this.unidad.jTxtCodigo.getText()));
        mensaje = dao.editarUnidad(uni);

        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaUnidad();
    }

    public void cambiarEstado() {
        String mensaje = null;

        uni.setCodigo(Integer.parseInt(this.unidad.jTxtCodigo.getText()));
        mensaje = dao.estadoUnidad(uni);

        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaUnidad();
    }

    public void listaUnidad() {
        ArrayList<Unidad> list = new ArrayList();
        list = dao.mostrarLista();
        DefaultTableModel tabla = new DefaultTableModel();
        this.unidad.jTblUnidad.setModel(tabla);

        tabla.addColumn("Codigo");
        tabla.addColumn("Nombre");
        tabla.addColumn("Descripcion");
        tabla.addColumn("Fecha de Inicio");
        tabla.addColumn("Fecha de Finalizacion");

        Object[] columna = new Object[5];

        for (int i = 0; i < list.size(); i++) {
            columna[0] = list.get(i).getCodigo();
            columna[1] = list.get(i).getNombre();
            columna[2] = list.get(i).getDescripcion();
            columna[3] = list.get(i).getFechaIni();
            columna[4] = list.get(i).getFechaFin();

            tabla.addRow(columna);
        }
    }

    public void limpiarControles() {
        unidad.jTxtCodigo.setText(null);
        unidad.jTxtNombre.setText(null);
        unidad.jTxtDescripcion.setText(null);
        unidad.jTxtFechaInicio.setText(null);
        unidad.jTxtFechaFin.setText(null);
    }

    @Override
    public void mouseClicked(MouseEvent me) {

        if (me.getSource() == unidad.jTblUnidad) {
            unidad.jTxtCodigo.setText(unidad.jTblUnidad.getValueAt(unidad.jTblUnidad.getSelectedRow(), 0).toString());
            unidad.jTxtNombre.setText(unidad.jTblUnidad.getValueAt(unidad.jTblUnidad.getSelectedRow(), 1).toString());
            unidad.jTxtDescripcion.setText(unidad.jTblUnidad.getValueAt(unidad.jTblUnidad.getSelectedRow(), 2).toString());
            unidad.jTxtFechaInicio.setText(unidad.jTblUnidad.getValueAt(unidad.jTblUnidad.getSelectedRow(), 3).toString());
            unidad.jTxtFechaFin.setText(unidad.jTblUnidad.getValueAt(unidad.jTblUnidad.getSelectedRow(), 4).toString());

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
