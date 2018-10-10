package com.centroeduc.controller;

import com.centroeduc.dao.AdminDAO;
import com.centroeduc.dao.SecreDAO;
import com.centroeduc.model.Administrador;
import com.centroeduc.model.Secretaria;

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
import vista.JFrmSecre;

public class SecreControlador implements ActionListener, MouseListener {
    

    JFrmSecre logica = new JFrmSecre();
    SecreDAO daoS = new SecreDAO();
    Secretaria datosS = new Secretaria();
    Administrador Adm = new Administrador();
    AdminDAO dao = new AdminDAO();

    public SecreControlador(JFrmSecre logica ) {           
            this.logica = logica;
        
        this.logica.jBtnGuardarS.setActionCommand("Guardar");
        this.logica.jBtnActualizarS.setActionCommand("Actualizar");
        this.logica.jBtnEliminarS.setActionCommand("Eliminar");
        
        this.logica.jBtnGuardarS.addActionListener(this);
        this.logica.jBtnActualizarS.addActionListener(this);
        this.logica.jBtnEliminarS.addActionListener(this);
        listaSecre();
        
        this.logica.jTblSecre.addMouseListener(this);
    }
    
     public void listaSecre() {
        DefaultTableModel tablaS = new DefaultTableModel();
        this.logica.jTblSecre.setModel(tablaS);       
        ArrayList<Secretaria> lista = new ArrayList();
        lista = daoS.MostrarSecretaria();
        

        tablaS.addColumn("Codigo");
        tablaS.addColumn("nombre");
        tablaS.addColumn("apellido");
        tablaS.addColumn("direccion");
        tablaS.addColumn("email");
        tablaS.addColumn("tel_casa");
        tablaS.addColumn("tel_movil");
        tablaS.addColumn("fechanac");
        tablaS.addColumn("cui");
        tablaS.addColumn("Administrador");
        tablaS.addColumn("");

        Object[] columna = new Object[11];

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
            columna[9] = lista.get(i).getCodAdm();
            columna[10] =lista.get(i).getPass();
            
            tablaS.addRow(columna);
        }
        this.logica.jTblSecre.getColumnModel().getColumn(10).setPreferredWidth(0);
        this.logica.jTblSecre.getColumnModel().getColumn(10).setMaxWidth(0);
        this.logica.jTblSecre.getColumnModel().getColumn(10).setMinWidth(0);
        //jTblReservaciones.getColumnModel().getColumn(7).setPreferredWidth(0);
    }
     
     public void guardarSecre() {
        String mensaje = null;
        
        String anio = Integer.toString(this.logica.jDCFechanac.getCalendar().get(java.util.Calendar.YEAR));
        String mes = Integer.toString(this.logica.jDCFechanac.getCalendar().get(java.util.Calendar.MONTH) + 1);
        String dia = Integer.toString(this.logica.jDCFechanac.getCalendar().get(java.util.Calendar.DATE));
        String fechaseleccionada = anio + "/" + mes + "/" + dia;

        datosS.setCodigo(this.logica.jTxtCodigoS.getText());
        datosS.setNombre(this.logica.jTxtNombreS.getText());
        datosS.setApellido(this.logica.jTxtApellidoS.getText());
        datosS.setDireccion(this.logica.jTxtDireccionS.getText());
        datosS.setEmail(this.logica.jTxtEmailS.getText());        
        datosS.setTelCasa(Integer.parseInt(this.logica.jTxtTelCasaS.getText()));
        datosS.setTelMovil(Integer.parseInt(this.logica.jTxtTelMovilS.getText()));
       // datosS.setFechanac(this.logica.jTxtFechaNacS.getText());
        datosS.setFechanac(fechaseleccionada);
        datosS.setCui(Long.parseLong(this.logica.jTxtCuiS.getText()));
        Adm.setCodigo(this.logica.jTxtCodigoAdmS.getText());
        datosS.setPass(this.logica.jPsfContraS.getText());
        datosS.setEstado(1);

        mensaje = daoS.nuevaSecretaria(datosS, Adm);
        //mensaje = daoS.nuevaSecretaria(datosS);

        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaSecre();

    }
      public void editarSecre(){
         String mensaje = null; 
         
         String anio = Integer.toString(this.logica.jDCFechanac.getCalendar().get(java.util.Calendar.YEAR));
        String mes = Integer.toString(this.logica.jDCFechanac.getCalendar().get(java.util.Calendar.MONTH) + 1);
        String dia = Integer.toString(this.logica.jDCFechanac.getCalendar().get(java.util.Calendar.DATE));
        String fechaseleccionada = anio + "/" + mes + "/" + dia;
         
        datosS.setNombre(this.logica.jTxtNombreS.getText());
        datosS.setApellido(this.logica.jTxtApellidoS.getText());
        datosS.setDireccion(this.logica.jTxtDireccionS.getText());
        datosS.setEmail(this.logica.jTxtEmailS.getText());        
        datosS.setTelCasa(Integer.parseInt(this.logica.jTxtTelCasaS.getText()));
        datosS.setTelMovil(Integer.parseInt(this.logica.jTxtTelMovilS.getText()));      
        datosS.setFechanac(fechaseleccionada);
        datosS.setCui(Long.parseLong(this.logica.jTxtCuiS.getText()));
        Adm.setCodigo(this.logica.jTxtCodigoAdmS.getText());
        datosS.setPass(this.logica.jPsfContraS.getText());
        datosS.setCodigo(this.logica.jTxtCodigoS.getText());
        datosS.setEstado(1);
         
          mensaje = daoS.editarSecretaria(datosS, Adm);
        //mensaje = daoS.nuevaSecretaria(datosS);

        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaSecre();
    }
      
      public void cambio(){
          String mensaje = null;
          
          datosS.setCodigo(this.logica.jTxtCodigoS.getText());
          mensaje = daoS.estado(datosS);

          JOptionPane.showMessageDialog(logica, mensaje);
          limpiarControles();
          listaSecre();
      }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("SI LLEGO");
        String evento = e.getActionCommand();
        
        if(evento.equals("Guardar")){
            guardarSecre();
        }        
        if (e.getSource() == this.logica.jBtnGuardarS) {
            guardarSecre();
        }
        if(evento.equals("Actualizar")){
            editarSecre();
        } 
        if(e.getSource() == this.logica.jBtnActualizarS){
            editarSecre();
        }
        if(evento.equals("Eliminar")){
            cambio();
        }
        if(e.getSource() == this.logica.jBtnEliminarS){
            cambio();
        }
        
    }


    @Override
    public void mouseClicked(MouseEvent e) {
       if(e.getSource() == logica.jTblSecre){
           logica.jTxtCodigoS.setText(logica.jTblSecre.getValueAt(logica.jTblSecre.getSelectedRow(),0).toString());       
           logica.jTxtNombreS.setText(logica.jTblSecre.getValueAt(logica.jTblSecre.getSelectedRow(),1).toString());
           logica.jTxtApellidoS.setText(logica.jTblSecre.getValueAt(logica.jTblSecre.getSelectedRow(),2).toString());
           logica.jTxtDireccionS.setText(logica.jTblSecre.getValueAt(logica.jTblSecre.getSelectedRow(),3).toString());
           logica.jTxtEmailS.setText(logica.jTblSecre.getValueAt(logica.jTblSecre.getSelectedRow(),4).toString());
           logica.jTxtTelCasaS.setText(logica.jTblSecre.getValueAt(logica.jTblSecre.getSelectedRow(),5).toString());
           logica.jTxtTelMovilS.setText(logica.jTblSecre.getValueAt(logica.jTblSecre.getSelectedRow(),6).toString());                     
           
           Date fechaSelect = Date.valueOf(logica.jTblSecre.getValueAt(logica.jTblSecre.getSelectedRow(), 7).toString());
           logica.jDCFechanac.setDate(fechaSelect);
            
           logica.jTxtCuiS.setText(logica.jTblSecre.getValueAt(logica.jTblSecre.getSelectedRow(),8).toString());
           logica.jTxtCodigoAdmS.setText(logica.jTblSecre.getValueAt(logica.jTblSecre.getSelectedRow(),9).toString());
           logica.jPsfContraS.setText(logica.jTblSecre.getValueAt(logica.jTblSecre.getSelectedRow(),10).toString());
       }
    }


    
    public void limpiarControles() {
        
        Calendar clear = new GregorianCalendar();
        logica.jDCFechanac.setCalendar(clear);
        
        logica.jTxtCodigoS.setText(null);
        logica.jTxtNombreS.setText(null);
        logica.jTxtApellidoS.setText(null);
        logica.jTxtDireccionS.setText(null);
        logica.jTxtEmailS.setText(null);
        logica.jTxtTelCasaS.setText(null);
        logica.jTxtTelMovilS.setText(null);        
        logica.jTxtCuiS.setText(null);
        logica.jPsfContraS.setText(null);
        logica.jTxtCodigoS.requestFocus();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}



