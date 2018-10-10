/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcentroeduc;

import com.centroeduc.controller.AdminControlador;
import vista.JFrmAdmin;

/**
 *
 * @author Usuario
 */
public class DCentroEduc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrmAdmin frmadmin = new JFrmAdmin();
        AdminControlador admincontrol = new AdminControlador(frmadmin);
        frmadmin.setVisible(true);
    }
    
}
