package com.centroeduc.controller;

import com.centroeduc.dao.AdminDAO;
import com.centroeduc.model.Administrador;
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
import vista.JFrmAdmin;

public class AdminControlador implements ActionListener, MouseListener {

    //atributos
    JFrmAdmin logica = new JFrmAdmin();
    AdminDAO dao = new AdminDAO();
    Administrador datos = new Administrador();
    //metodos
    //metodo constructor
    
    public AdminControlador(JFrmAdmin logica) {
                
        this.logica = logica;
        
        this.logica.jBtnGuardar.setActionCommand("Guardar");
        this.logica.jBtnActualizar.setActionCommand("Actualizar");
        this.logica.jBtnEstado.setActionCommand("Eliminar");
        
        
        this.logica.jBtnGuardar.addActionListener(this);
        this.logica.jBtnActualizar.addActionListener(this);
        this.logica.jBtnEstado.addActionListener(this);                
        listaAdmin();
        
        this.logica.jTblAdmin.addMouseListener(this);
        
 
    }
    
    public void listaAdmin(){
         DefaultTableModel tabla = new DefaultTableModel();
         ArrayList<Administrador> list = new ArrayList();
        list = dao.listAdmin();
        
       
        this.logica.jTblAdmin.setModel(tabla);//asignando el modelo a la tabla
        
        //AGREGAR TITULOS A LA TABLA
        tabla.addColumn("Codigo");
        tabla.addColumn("Nombre");
        tabla.addColumn("Apellido");
        tabla.addColumn("Direccion");
        tabla.addColumn("Email");
        tabla.addColumn("Tel Casa");
        tabla.addColumn("Tel Móvil");
        tabla.addColumn("Fecha Nacimiento");
        tabla.addColumn("Cui");
        tabla.addColumn("");
        
        
        //ESTABLECER LA CANTIDAD DE COLUMNAS CON RELACION A LOS TITULOS
        Object[] columna= new Object[10];
        
        //ASIGNANDO A CADA COLUMNA UN VALOR
         for (int i = 0; i < list.size(); i++) { // SE RECORRE LA LISTA (ARRAYLIST) QUE NOS DA EL DAO
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
             
             tabla.addRow(columna);
         } 
         
         this.logica.jTblAdmin.getColumnModel().getColumn(9).setPreferredWidth(0);
         this.logica.jTblAdmin.getColumnModel().getColumn(9).setPreferredWidth(0);
         this.logica.jTblAdmin.getColumnModel().getColumn(9).setPreferredWidth(0);
     }
    
    public void guardarAdmin() {
        String mensaje=null;
                
        //obtener los valores del calendario
        String anio = Integer.toString(this.logica.jDCFechanac.getCalendar().get(java.util.Calendar.YEAR));
        String mes = Integer.toString(this.logica.jDCFechanac.getCalendar().get(java.util.Calendar.MONTH) + 1);
        String dia = Integer.toString(this.logica.jDCFechanac.getCalendar().get(java.util.Calendar.DATE));

        //convirtiendo al formato deseado
        String fechaseleccionada = anio + "/" + mes + "/" + dia;
        
        datos.setCodigo(this.logica.jTxtCodigo.getText());
        datos.setNombre(this.logica.jTxtNombre.getText());
        datos.setApellido(this.logica.jTxtApellido.getText());
        datos.setDireccion(this.logica.jTxtDireccion.getText());
        datos.setEmail(this.logica.jTxtEmail.getText());
        datos.setTelCasa(Integer.parseInt(this.logica.jTxtTelCasa.getText()));
        datos.setTelMovil(Integer.parseInt(this.logica.jTxtTelMovil.getText()));
        //datos.setFechanac(this.logica.jTxtFechaNac.getText());
        datos.setFechanac(fechaseleccionada);
        datos.setCui(Long.parseLong(this.logica.jTxtCui.getText()));
        datos.setPass(this.logica.jPsfContra.getText());
        datos.setEstado(1);
        
        mensaje = dao.registerAdmin(datos);
        
        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaAdmin();        
    }
    
    public void editarAdm(){
        
        String mensaje = null;        

        String anio = Integer.toString(this.logica.jDCFechanac.getCalendar().get(java.util.Calendar.YEAR));
        String mes = Integer.toString(this.logica.jDCFechanac.getCalendar().get(java.util.Calendar.MONTH) + 1);
        String dia = Integer.toString(this.logica.jDCFechanac.getCalendar().get(java.util.Calendar.DATE));
        String fechaseleccionada = anio + "/" + mes + "/" + dia;
        
        datos.setNombre(this.logica.jTxtNombre.getText());
        datos.setApellido(this.logica.jTxtApellido.getText());
        datos.setDireccion(this.logica.jTxtDireccion.getText());
        datos.setEmail(this.logica.jTxtEmail.getText());
        datos.setTelCasa(Integer.parseInt(this.logica.jTxtTelCasa.getText()));
        datos.setTelMovil(Integer.parseInt(this.logica.jTxtTelMovil.getText()));
       // datos.setFechanac(this.logica.jTxtFechaNac.getText());
        datos.setFechanac(fechaseleccionada);
        datos.setCui(Long.parseLong(this.logica.jTxtCui.getText()));
        datos.setPass(this.logica.jPsfContra.getText());
        datos.setCodigo(this.logica.jTxtCodigo.getText());
        datos.setEstado(1);
        
            mensaje = dao.updateAdmin(datos);
            
            JOptionPane.showMessageDialog(null, mensaje);
            limpiarControles();
            listaAdmin();
    }
    
    public void cambioEstado(){
        String mensaje = null;
        datos.setCodigo(this.logica.jTxtCodigo.getText());
        mensaje=dao.changeState(datos);
        
        JOptionPane.showMessageDialog(logica, mensaje);
        limpiarControles();
        listaAdmin();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("llegue");        
        String evento = e.getActionCommand();
        
        if(evento.equals("Guardar")){
            guardarAdmin();        
        }
        
        if (e.getSource() == this.logica.jBtnGuardar) {
            guardarAdmin();
        }
        
        if (evento.equals("Actualizar")) {
            editarAdm();
        }
        
         if (e.getSource() == this.logica.jBtnActualizar) {
            editarAdm();
        }
         
         if (evento.equals("Estado")) {
            cambioEstado();
        }
         
          if (e.getSource() == this.logica.jBtnEstado) {
            cambioEstado();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
         if(e.getSource() == logica.jTblAdmin){
             logica.jTxtCodigo.setText(logica.jTblAdmin.getValueAt(logica.jTblAdmin.getSelectedRow(),0).toString());
             logica.jTxtNombre.setText(logica.jTblAdmin.getValueAt(logica.jTblAdmin.getSelectedRow(),1).toString());
             logica.jTxtApellido.setText(logica.jTblAdmin.getValueAt(logica.jTblAdmin.getSelectedRow(),2).toString());
             logica.jTxtDireccion.setText(logica.jTblAdmin.getValueAt(logica.jTblAdmin.getSelectedRow(), 3).toString());
             logica.jTxtEmail.setText(logica.jTblAdmin.getValueAt(logica.jTblAdmin.getSelectedRow(),4).toString());
             logica.jTxtTelCasa.setText(logica.jTblAdmin.getValueAt(logica.jTblAdmin.getSelectedRow(), 5).toString());
             logica.jTxtTelMovil.setText(logica.jTblAdmin.getValueAt(logica.jTblAdmin.getSelectedRow(), 6).toString());
             //logica.jTxtFechaNac.setText(logica.jTblAdmin.getValueAt(logica.jTblAdmin.getSelectedRow(), 7).toString());
             
             Date fechaSelect = Date.valueOf(logica.jTblAdmin.getValueAt(logica.jTblAdmin.getSelectedRow(), 7).toString());
            logica.jDCFechanac.setDate(fechaSelect);
             
             logica.jTxtCui.setText(logica.jTblAdmin.getValueAt(logica.jTblAdmin.getSelectedRow(), 8).toString());
             logica.jPsfContra.setText(logica.jTblAdmin.getValueAt(logica.jTblAdmin.getSelectedRow(), 9).toString());

         }
    }

   
    
     public void limpiarControles(){
         
   
        
        logica.jTxtCodigo.setText(null);
        logica.jTxtNombre.setText(null);
        logica.jTxtApellido.setText(null);
        logica.jTxtDireccion.setText(null);
        logica.jTxtEmail.setText(null);
        logica.jTxtTelCasa.setText(null);
        logica.jTxtTelMovil.setText(null);
        //logica.jTxtFechaNac.setText(null);
        
        Calendar clear = new GregorianCalendar();
        logica.jDCFechanac.setCalendar(clear);
        
        logica.jTxtCui.setText(null);
        logica.jPsfContra.setText(null);
        logica.jTxtCodigo.requestFocus();
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


