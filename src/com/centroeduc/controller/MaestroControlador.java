package com.centroeduc.controller;

import com.centroeduc.dao.MaestroDAO;
import com.centroeduc.model.Maestro;
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
import vista.JFrmMaestro;

/**
 *
 * @author Usuario
 */
public class MaestroControlador implements ActionListener, MouseListener {

    JFrmMaestro maestro = new JFrmMaestro();
    MaestroDAO dao = new MaestroDAO();
    Maestro mae = new Maestro();

    public MaestroControlador(JFrmMaestro ma) {

        this.maestro = ma;
        this.maestro.jBtnGuardar.addActionListener(this);
        this.maestro.jBtnActualizar.addActionListener(this);
        this.maestro.jBtnEstado.addActionListener(this);
        this.maestro.jTblMaestro.addMouseListener(this);
        listaMaestro();
    }

    @Override
    public void actionPerformed(ActionEvent eventoM) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        if (eventoM.getSource() == this.maestro.jBtnGuardar) {
            guardarMaestro();
        }
        if (eventoM.getSource() == this.maestro.jBtnActualizar) {
            actualizarMaestro();
        }
        if (eventoM.getSource() == this.maestro.jBtnEstado) {
            cambiarEstado();
        }
    }

    public void guardarMaestro() {
        String mensaje = null;
        
        String anio = Integer.toString(this.maestro.jDCFechanac.getCalendar().get(java.util.Calendar.YEAR));
        String mes = Integer.toString(this.maestro.jDCFechanac.getCalendar().get(java.util.Calendar.MONTH) + 1);
        String dia = Integer.toString(this.maestro.jDCFechanac.getCalendar().get(java.util.Calendar.DATE));
        
        String fechaseleccionada = anio + "/" + mes + "/" + dia;


        mae.setCodigo(this.maestro.jTxtCodigo.getText());
        mae.setNombre(this.maestro.jTxtNombre.getText());
        mae.setApellido(this.maestro.jTxtApellido.getText());
        mae.setDireccion(this.maestro.jTxtDireccion.getText());
        mae.setEmail(this.maestro.jTxtEmail.getText());
        mae.setTelCasa(Integer.parseInt(this.maestro.jTxtTelCasa.getText()));
        mae.setTelMovil(Integer.parseInt(this.maestro.jTxtTelMovil.getText()));
       // mae.setFechanac(this.maestro.jTxtFechaNac.getText());
        mae.setFechanac(fechaseleccionada);
        mae.setCui(Long.parseLong(this.maestro.jTxtCui.getText()));
        mae.setCodigoA(this.maestro.jTxtCodigoA.getText());
        mae.setPass(this.maestro.jPsfContra.getText());
        mae.setEstado(1);
        mensaje = dao.insertarMaestro(mae);

        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaMaestro();

    }

    public void actualizarMaestro() {
        String mensaje = null;
        
        String anio = Integer.toString(this.maestro.jDCFechanac.getCalendar().get(java.util.Calendar.YEAR));
        String mes = Integer.toString(this.maestro.jDCFechanac.getCalendar().get(java.util.Calendar.MONTH) + 1);
        String dia = Integer.toString(this.maestro.jDCFechanac.getCalendar().get(java.util.Calendar.DATE));
        
        String fechaseleccionada = anio + "/" + mes + "/" + dia;

        mae.setNombre(this.maestro.jTxtNombre.getText());
        mae.setApellido(this.maestro.jTxtApellido.getText());
        mae.setDireccion(this.maestro.jTxtDireccion.getText());
        mae.setEmail(this.maestro.jTxtEmail.getText());
        mae.setTelCasa(Integer.parseInt(this.maestro.jTxtTelCasa.getText()));
        mae.setTelMovil(Integer.parseInt(this.maestro.jTxtTelMovil.getText()));
        //mae.setFechanac(this.maestro.jTxtFechaNac.getText());
        mae.setFechanac(fechaseleccionada);
        mae.setCui(Long.parseLong(this.maestro.jTxtCui.getText()));
        mae.setCodigoA(this.maestro.jTxtCodigoA.getText());
        mae.setPass(this.maestro.jPsfContra.getText());
        mae.setCodigo(this.maestro.jTxtCodigo.getText());
        mensaje = dao.editarMaestro(mae);

        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaMaestro();

    }

    public void cambiarEstado() {
        String mensaje = null;

        mae.setCodigo(this.maestro.jTxtCodigo.getText());
        mensaje = dao.estadoMaestro(mae);
        
        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaMaestro();

    }

    public void listaMaestro() {
        ArrayList<Maestro> list = new ArrayList();
        list = dao.Mostrarprofesor();
        DefaultTableModel tabla = new DefaultTableModel();
        this.maestro.jTblMaestro.setModel(tabla);

        tabla.addColumn("Codigo");
        tabla.addColumn("Nombre");
        tabla.addColumn("Apellido");
        tabla.addColumn("Direccion");
        tabla.addColumn("Email");
        tabla.addColumn("Tel Casa");
        tabla.addColumn("Tel Movil");
        tabla.addColumn("Fecha de Nacimiento");
        tabla.addColumn("Cui");
        tabla.addColumn("Contraseña");
        tabla.addColumn("Codigo Admin");

        Object[] columna = new Object[11];

        for (int i = 0; i < list.size(); i++) {
            columna[0] = list.get(i).getCodigo();
            columna[1] = list.get(i).getNombre();
            columna[2] = list.get(i).getApellido();
            columna[3] = list.get(i).getDireccion();
            columna[4] = list.get(i).getEmail();
            columna[5] = list.get(i).getTelCasa();
            columna[6] = list.get(i).getTelMovil();
            columna[7] = list.get(i).getFechanac();
            columna[8] = list.get(i).getCui();
            columna[9] = list.get(i).getPass();
            columna[10] = list.get(i).getCodigoA();

            tabla.addRow(columna);
        }
        this.maestro.jTblMaestro.getColumnModel().getColumn(10).setPreferredWidth(0);
        this.maestro.jTblMaestro.getColumnModel().getColumn(10).setMaxWidth(0);
        this.maestro.jTblMaestro.getColumnModel().getColumn(10).setMinWidth(0);
        this.maestro.jTblMaestro.getColumnModel().getColumn(9).setPreferredWidth(0);
        this.maestro.jTblMaestro.getColumnModel().getColumn(9).setMaxWidth(0);
        this.maestro.jTblMaestro.getColumnModel().getColumn(9).setMinWidth(0);
        
    }

    public void limpiarControles() {
        
        Calendar clear = new GregorianCalendar();
        maestro.jDCFechanac.setCalendar(clear);
        
        maestro.jTxtCodigo.setText(null);
        maestro.jTxtNombre.setText(null);
        maestro.jTxtApellido.setText(null);
        maestro.jTxtDireccion.setText(null);
        maestro.jTxtEmail.setText(null);
        maestro.jTxtTelCasa.setText(null);
        maestro.jTxtTelMovil.setText(null);
      
        maestro.jTxtCui.setText(null);
        maestro.jPsfContra.setText(null);
        maestro.jTxtCodigoA.setText(null);

    }

    @Override
    public void mouseClicked(MouseEvent boton) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (boton.getSource() == maestro.jTblMaestro) {
            maestro.jTxtCodigo.setText(maestro.jTblMaestro.getValueAt(maestro.jTblMaestro.getSelectedRow(), 0).toString());
            maestro.jTxtNombre.setText(maestro.jTblMaestro.getValueAt(maestro.jTblMaestro.getSelectedRow(), 1).toString());
            maestro.jTxtApellido.setText(maestro.jTblMaestro.getValueAt(maestro.jTblMaestro.getSelectedRow(), 2).toString());
            maestro.jTxtDireccion.setText(maestro.jTblMaestro.getValueAt(maestro.jTblMaestro.getSelectedRow(), 3).toString());
            maestro.jTxtEmail.setText(maestro.jTblMaestro.getValueAt(maestro.jTblMaestro.getSelectedRow(), 4).toString());
            maestro.jTxtTelCasa.setText(maestro.jTblMaestro.getValueAt(maestro.jTblMaestro.getSelectedRow(), 5).toString());
            maestro.jTxtTelMovil.setText(maestro.jTblMaestro.getValueAt(maestro.jTblMaestro.getSelectedRow(), 6).toString());
           // maestro.jTxtFechaNac.setText(maestro.jTblMaestro.getValueAt(maestro.jTblMaestro.getSelectedRow(), 7).toString());
            maestro.jTxtCui.setText(maestro.jTblMaestro.getValueAt(maestro.jTblMaestro.getSelectedRow(), 8).toString());
            maestro.jPsfContra.setText(maestro.jTblMaestro.getValueAt(maestro.jTblMaestro.getSelectedRow(), 9).toString());
            maestro.jTxtCodigoA.setText(maestro.jTblMaestro.getValueAt(maestro.jTblMaestro.getSelectedRow(), 10).toString());
            
            
            Date fechaSelect = Date.valueOf(maestro.jTblMaestro.getValueAt(maestro.jTblMaestro.getSelectedRow(), 7).toString());
            maestro.jDCFechanac.setDate(fechaSelect);
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

}
