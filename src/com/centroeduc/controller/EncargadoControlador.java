package com.centroeduc.controller;

import com.centroeduc.dao.EncargadoDAO;
import com.centroeduc.dao.SecreDAO;
import com.centroeduc.model.Encargado;
import com.centroeduc.model.Secretaria;
import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultEditorKit;
import vista.JFrmEncargado;

public class EncargadoControlador implements ActionListener, MouseListener {

    JFrmEncargado logica = new JFrmEncargado();
    EncargadoDAO daoE = new EncargadoDAO();
    Encargado encargado = new Encargado();
    SecreDAO daoS = new SecreDAO();
    Secretaria secre = new Secretaria();

    String mensaje = null;

    public EncargadoControlador(JFrmEncargado logica) {

        this.logica = logica;

        this.logica.jBtnGuardarE.setActionCommand("Guardar");
        this.logica.jBtnActualizarE.setActionCommand("Actualizar");
        this.logica.jBtnEstadoE.setActionCommand("Eliminar");

        this.logica.jBtnGuardarE.addActionListener(this);
        this.logica.jBtnActualizarE.addActionListener(this);
        this.logica.jBtnEstadoE.addActionListener(this);
        listaEncargado();

        this.logica.jTblEncargado.addMouseListener(this);

    }

    public void listaEncargado() {

        DefaultTableModel tablaE = new DefaultTableModel();
        this.logica.jTblEncargado.setModel(tablaE);
        ArrayList<Encargado> lista = new ArrayList();
        lista = daoE.MostarEncargado();

        tablaE.addColumn("Codigo");
        tablaE.addColumn("Nombre");
        tablaE.addColumn("Apellido");
        tablaE.addColumn("Direccion");
        tablaE.addColumn("Email");
        tablaE.addColumn("Tel Casa");
        tablaE.addColumn("Tel Movil");
        tablaE.addColumn("Fecha Nacimiento");
        tablaE.addColumn("Cui");
        tablaE.addColumn("Secretaria");

        Object[] columna = new Object[10];

        for (int i = 0; i < lista.size(); i++) {
            columna[0] = lista.get(i).getCodigo();
            columna[1] = lista.get(i).getNombre();
            columna[2] = lista.get(i).getApellido();
            columna[3] = lista.get(i).getDireccion();
            columna[4] = lista.get(i).getEmail();
            columna[5] = lista.get(i).getTelCasa();
            columna[6] = lista.get(i).getTelMovil();
            columna[7] = lista.get(i).getFechanac();
            columna[8] = lista.get(i).getCui();
            columna[9] = lista.get(i).getCodS();

            tablaE.addRow(columna);
        }
        this.logica.jTblEncargado.getColumnModel().getColumn(9).setPreferredWidth(0);
        this.logica.jTblEncargado.getColumnModel().getColumn(9).setMaxWidth(0);
        this.logica.jTblEncargado.getColumnModel().getColumn(9).setMinWidth(0);

    }

    public void guardarEncargado() {

        String mensaje = null;
        //obtener los valores del calendario
        String anio = Integer.toString(this.logica.jDCFechanac.getCalendar().get(java.util.Calendar.YEAR));
        String mes = Integer.toString(this.logica.jDCFechanac.getCalendar().get(java.util.Calendar.MONTH) + 1);
        String dia = Integer.toString(this.logica.jDCFechanac.getCalendar().get(java.util.Calendar.DATE));

        //convirtiendo al formato deseado
        String fechaseleccionada = anio + "/" + mes + "/" + dia;

        encargado.setCodigo(this.logica.jTxtCodigoE.getText());
        encargado.setNombre(this.logica.jTxtNombreE.getText());
        encargado.setApellido(this.logica.jTxtApellidoE.getText());
        encargado.setDireccion(this.logica.jTxtDireccionE.getText());
        encargado.setEmail(this.logica.jTxtEmailE.getText());
        encargado.setTelCasa(Integer.parseInt(this.logica.jTxtTelCasaE.getText()));
        encargado.setTelMovil(Integer.parseInt(this.logica.jTxtTelMovilE.getText()));
        //encargado.setFechanac(this.logica.jTxtFechaNacE.getText());
        encargado.setFechanac(fechaseleccionada);
        encargado.setCui(Long.parseLong(this.logica.jTxtCuiE.getText()));
        secre.setCodigo(this.logica.jTxtCodS.getText());
        encargado.setEstado(1);

        //JOptionPane.showMessageDialog(null, encargado.getFechanac());
        mensaje = daoE.nuevoEncargado(encargado, secre);

        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaEncargado();
    }

    public void editarEncargado() {
        String mensaje = null;

        
        String anio = Integer.toString(this.logica.jDCFechanac.getCalendar().get(java.util.Calendar.YEAR));
        String mes = Integer.toString(this.logica.jDCFechanac.getCalendar().get(java.util.Calendar.MONTH) + 1);
        String dia = Integer.toString(this.logica.jDCFechanac.getCalendar().get(java.util.Calendar.DATE));

        String fechaseleccionada = anio + "/" + mes + "/" + dia;

        encargado.setNombre(this.logica.jTxtNombreE.getText());
        encargado.setApellido(this.logica.jTxtApellidoE.getText());
        encargado.setDireccion(this.logica.jTxtDireccionE.getText());
        encargado.setEmail(this.logica.jTxtEmailE.getText());
        encargado.setTelCasa(Integer.parseInt(this.logica.jTxtTelCasaE.getText()));
        encargado.setTelMovil(Integer.parseInt(this.logica.jTxtTelMovilE.getText()));
        //encargado.setFechanac(this.logica.jTxtFechaNacE.getText());
        encargado.setFechanac(fechaseleccionada);
        encargado.setCui(Long.parseLong(this.logica.jTxtCuiE.getText()));
        secre.setCodigo(this.logica.jTxtCodS.getText());
        encargado.setCodigo(this.logica.jTxtCodigoE.getText());
        encargado.setEstado(1);

        mensaje = daoE.UpEnc(encargado, secre);

        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaEncargado();
    }

    public void cambioE() {
        String mensaje = null;

        encargado.setCodigo(this.logica.jTxtCodigoE.getText());
        mensaje = daoE.cambioEstado(encargado);

        JOptionPane.showMessageDialog(logica, mensaje);
        limpiarControles();
        listaEncargado();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("LLEGUE");
        String evento = e.getActionCommand();

        if (evento.equals("Guardar")) {
            guardarEncargado();
        }
        if (e.getSource() == this.logica.jBtnGuardarE) {
            guardarEncargado();
        }

        if (evento.equals("Actualizar")) {
            editarEncargado();
        }
        if (e.getSource() == this.logica.jBtnActualizarE) {
            editarEncargado();
        }
        if (evento.equals("Eliminar")) {
            cambioE();
        }
        if (e.getSource() == this.logica.jBtnEstadoE) {
            cambioE();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        String mensaje = null;

        if (e.getSource() == logica.jTblEncargado) {
            logica.jTxtCodigoE.setText(logica.jTblEncargado.getValueAt(logica.jTblEncargado.getSelectedRow(), 0).toString());
            logica.jTxtNombreE.setText(logica.jTblEncargado.getValueAt(logica.jTblEncargado.getSelectedRow(), 1).toString());
            logica.jTxtApellidoE.setText(logica.jTblEncargado.getValueAt(logica.jTblEncargado.getSelectedRow(), 2).toString());
            logica.jTxtDireccionE.setText(logica.jTblEncargado.getValueAt(logica.jTblEncargado.getSelectedRow(), 3).toString());
            logica.jTxtEmailE.setText(logica.jTblEncargado.getValueAt(logica.jTblEncargado.getSelectedRow(), 4).toString());
            logica.jTxtTelCasaE.setText(logica.jTblEncargado.getValueAt(logica.jTblEncargado.getSelectedRow(), 5).toString());
            logica.jTxtTelMovilE.setText(logica.jTblEncargado.getValueAt(logica.jTblEncargado.getSelectedRow(), 6).toString());
            //logica.jDCFechanac.setDate(new Date((long) logica.jTblEncargado.getValueAt(logica.jTblEncargado.getSelectedRow(),7)));
            logica.jTxtCuiE.setText(logica.jTblEncargado.getValueAt(logica.jTblEncargado.getSelectedRow(), 8).toString());
            logica.jTxtCodS.setText(logica.jTblEncargado.getValueAt(logica.jTblEncargado.getSelectedRow(), 9).toString());

            
            Date fechaSelect = Date.valueOf(logica.jTblEncargado.getValueAt(logica.jTblEncargado.getSelectedRow(), 7).toString());
            logica.jDCFechanac.setDate(fechaSelect);
            

        }
    }

    public void limpiarControles() {

        /*JDateChooser dateChooser = new JDateChooser();
        dateChooser.setCalendar(null);*/
        Calendar c2 = new GregorianCalendar();
        logica.jDCFechanac.setCalendar(c2);
        
        
        logica.jTxtCodigoE.setText(null);
        logica.jTxtNombreE.setText(null);
        logica.jTxtApellidoE.setText(null);
        logica.jTxtDireccionE.setText(null);
        logica.jTxtEmailE.setText(null);
        logica.jTxtTelCasaE.setText(null);
        logica.jTxtTelMovilE.setText(null);
        //logica.jTxtFechaNacE.setText(null);
        logica.jTxtCuiE.setText(null);
        logica.jTxtCodS.setText(null);
        logica.jTxtCodigoE.requestFocus();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
