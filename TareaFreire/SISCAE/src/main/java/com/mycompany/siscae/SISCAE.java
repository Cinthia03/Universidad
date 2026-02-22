package com.mycompany.siscae;

import javax.swing.SwingUtilities;
import presentacion.FrmLogin;



public class SISCAE {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmLogin vistaLog = new FrmLogin();
            vistaLog.setLocationRelativeTo(null); // Centrar ventana
            vistaLog.setVisible(true);
        });
    }
}
