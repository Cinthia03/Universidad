package presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Materia;
import modelo.Docente;
import negocio.DocenteNegocio;
import negocio.MateriaNegocio;


public class FrmMateria extends javax.swing.JInternalFrame {
    
    MateriaNegocio negocio = new MateriaNegocio();
    int idSeleccionado = -1;
    
    public FrmMateria() {
        initComponents();
        ajustarColumnas(); 
        cargarTabla("");
        cargarDocentesEnCombo(); 
        pack(); 
        
        // Eventos botones
        btnGuardar.addActionListener(e -> registrar());
        btnEditar.addActionListener(e -> editar());
        btnEliminar.addActionListener(e -> eliminar());
        btnPrevisualizar.addActionListener(e -> previsualizar());
        btnImprimir.addActionListener(e -> imprimir());
        btnNuevo.addActionListener(e -> nuevo());

        // Evento clic en tabla
        TablaMaterias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarDatosSeleccionados();
            }
        });
        
        
        txtBuscar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { buscar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { buscar(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { buscar(); }
        });   
    }
    
    private void nuevo() {
        txtNombreMateria.setText("");
        txtDescripcion.setText("");
        cmbCurso.setSelectedIndex(0);
        cmbDocente.setSelectedIndex(0);
        cmbDocente.setEnabled(true);
        cmbCurso.setEnabled(true);
        TablaMaterias.clearSelection();
        txtBuscar.setText("");
        idSeleccionado = -1;
        txtNombreMateria.requestFocus();
        JOptionPane.showMessageDialog(this, "Formulario listo para registrar nueva materia");
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPanelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombreMateria = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        cmbDocente = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnPrevisualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        cmbCurso = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaMaterias = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        JPanelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SISCAE - GESTION DE MATERIA");

        btnEliminar.setBackground(new java.awt.Color(255, 204, 204));
        btnEliminar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEliminar.setText("ELIMINAR");

        cmbDocente.setToolTipText("");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("BUSCAR:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("NOMBRE:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("DESCRIPCION:");

        btnGuardar.setBackground(new java.awt.Color(204, 255, 204));
        btnGuardar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnGuardar.setText("GUARDAR");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("DOCENTE:");

        btnEditar.setBackground(new java.awt.Color(255, 255, 204));
        btnEditar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEditar.setText("EDITAR");

        btnImprimir.setBackground(new java.awt.Color(204, 255, 255));
        btnImprimir.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnImprimir.setText("Imprimir");

        btnPrevisualizar.setBackground(new java.awt.Color(153, 204, 255));
        btnPrevisualizar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPrevisualizar.setText("Previsualizar");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("CURSO:");

        cmbCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A-101", "A-102", "A-103", "A-104", "A-105", "B-201", "B-202", "B-203", "B-204", "B-205", "C-301", "C-302", "C-303", "C-304", "C-305", "D-401", "D-402", "D-403", "D-404", "D-405", "E-501", "E-502", "E-503", "E-504", "E-505" }));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        TablaMaterias.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        TablaMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "DESCRIPCION", "DOCENTE", "CURSO", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TablaMaterias);

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnNuevo.setText("NUEVO");

        javax.swing.GroupLayout JPanelPrincipalLayout = new javax.swing.GroupLayout(JPanelPrincipal);
        JPanelPrincipal.setLayout(JPanelPrincipalLayout);
        JPanelPrincipalLayout.setHorizontalGroup(
            JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel8)
                .addGap(41, 41, 41)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPrevisualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnImprimir)
                .addGap(34, 34, 34))
            .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addGap(426, 426, 426)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(31, 31, 31)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbCurso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbDocente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                            .addComponent(txtNombreMateria))
                        .addGap(45, 45, 45)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1411, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        JPanelPrincipalLayout.setVerticalGroup(
            JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelPrincipalLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel9))
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(btnImprimir)
                    .addComponent(btnPrevisualizar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     // ======================= FUNCIONALIDAD =======================

     private void registrar() {

        if (txtNombreMateria.getText().trim().isEmpty()
                || cmbDocente.getSelectedIndex() == 0
                || cmbCurso.getSelectedIndex() == -1) {

            JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios");
            return;
        }

        String docenteTexto = cmbDocente.getSelectedItem().toString();
        int docenteId = Integer.parseInt(docenteTexto.split(" ")[0]);
        //int docenteId = Integer.parseInt(
        //        cmbDocente.getSelectedItem().toString().split(" ")[0]);

        Materia m = new Materia();
        m.setNombreMateria(txtNombreMateria.getText());
        m.setDescripcion(txtDescripcion.getText());
        m.setDocenteId(docenteId);
        m.setCurso(cmbCurso.getSelectedItem().toString());

        String r = negocio.registrar(m);
        JOptionPane.showMessageDialog(this,
                r.equals("OK") ? "Materia registrada correctamente" : r);

        cargarTabla("");
        limpiarFormulario();
    }

    private void editar() {

        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una materia");
            return;
        }

        String docenteTexto = cmbDocente.getSelectedItem().toString();
        int docenteId = Integer.parseInt(docenteTexto.split(" ")[0]);
        //int docenteId = Integer.parseInt(
        //        cmbDocente.getSelectedItem().toString().split(" ")[0]);

        Materia m = new Materia();
        m.setId(idSeleccionado);
        m.setNombreMateria(txtNombreMateria.getText());
        m.setDescripcion(txtDescripcion.getText());
        m.setDocenteId(docenteId);
        m.setDocenteInfo(docenteTexto); 
        m.setCurso(cmbCurso.getSelectedItem().toString());

        String r = negocio.editar(m);
        JOptionPane.showMessageDialog(this,
                r.equals("OK") ? "Materia editada correctamente" : r);

        cargarTabla("");
        limpiarFormulario();
    }

    private void eliminar() {
        if (idSeleccionado == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione una materia");
        return;
        }

        int op = JOptionPane.showConfirmDialog(
            this,
            "⚠ ¿Está seguro de eliminar esta materia?\nEsta acción NO se puede deshacer.",
            "Confirmación",
            JOptionPane.YES_NO_OPTION
        );

        if (op == JOptionPane.YES_OPTION) {
            negocio.eliminarDefinitivo(idSeleccionado); // 👈 NUEVO MÉTODO
            JOptionPane.showMessageDialog(this, "Materia eliminada definitivamente");
            cargarTabla("");
            limpiarFormulario();
        }
    }

    private void buscar() {
        cargarTabla(txtBuscar.getText().trim());
    }

    private void cargarTabla(String filtro) {

        DefaultTableModel modelo = (DefaultTableModel) TablaMaterias.getModel();
        modelo.setRowCount(0);

        for (Materia m : negocio.buscar(filtro)) {
            modelo.addRow(new Object[]{
                m.getId(),
                m.getNombreMateria(),
                m.getDescripcion(),
                m.getDocenteInfo(), // 👈 TEXTO COMPLETO
                m.getCurso(),
                m.getEstado()
            });
        }
    }

    private void cargarDatosSeleccionados() {

        int fila = TablaMaterias.getSelectedRow();
        if (fila == -1) return;

        idSeleccionado = Integer.parseInt(TablaMaterias.getValueAt(fila, 0).toString());
        txtNombreMateria.setText(TablaMaterias.getValueAt(fila, 1).toString());
        txtDescripcion.setText(TablaMaterias.getValueAt(fila, 2).toString());
        cmbCurso.setSelectedItem(TablaMaterias.getValueAt(fila, 4).toString());

        String docenteTexto = TablaMaterias.getValueAt(fila, 3).toString();
        String docenteId = docenteTexto.split(" ")[0];

        for (int i = 1; i < cmbDocente.getItemCount(); i++) {
            if (cmbDocente.getItemAt(i).startsWith(docenteId + " ")) {
                cmbDocente.setSelectedIndex(i);
                break;
            }
        }

        // 🔒 BLOQUEAR CAMPOS
        cmbDocente.setEnabled(false);
        cmbCurso.setEnabled(false);
    }

    private void limpiarFormulario() {
        txtNombreMateria.setText("");
        txtDescripcion.setText("");
        cmbCurso.setSelectedIndex(0);
        cmbDocente.setSelectedIndex(0);
        idSeleccionado = -1;
        
        // ✅ VOLVER A HABILITAR
        cmbDocente.setEnabled(true);
        cmbCurso.setEnabled(true);

        idSeleccionado = -1;
    }

    private void ajustarColumnas() {
        TablaMaterias.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        TablaMaterias.setAutoCreateRowSorter(true);
    }

    // ======================= DOCENTES =======================

    private void cargarDocentesEnCombo() {

        DocenteNegocio negocioDoc = new DocenteNegocio();

        cmbDocente.removeAllItems();
        cmbDocente.addItem("Seleccione...");

        for (Docente d : negocioDoc.listar()) {
            if ("ACTIVO".equalsIgnoreCase(d.getEstado())) {
                cmbDocente.addItem(
                        d.getId() + " " +
                        d.getEspecialidad() + " - " +
                        d.getNombres() + " " +
                        d.getApellidos()
                );
            }
        }
    }

    // ======================= PREVISUALIZAR / IMPRIMIR =======================

    private void previsualizar() {

        JTable vistaTabla = new JTable(TablaMaterias.getModel()) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        vistaTabla.setFont(new Font("Serif", Font.PLAIN, 14));
        vistaTabla.setRowHeight(22);

        JScrollPane scroll = new JScrollPane(vistaTabla);
        scroll.setPreferredSize(new Dimension(1000, 600));

        Frame parent = JOptionPane.getFrameForComponent(this);
        JDialog dialog = new JDialog(parent, "Vista previa", true);

        JButton btnCerrar = new JButton("CERRAR");
        btnCerrar.addActionListener(e -> dialog.dispose());

        dialog.add(scroll, BorderLayout.CENTER);
        dialog.add(btnCerrar, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    private void imprimir() {
        try {
            TablaMaterias.print(JTable.PrintMode.FIT_WIDTH);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }


  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelPrincipal;
    private javax.swing.JTable TablaMaterias;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnPrevisualizar;
    private javax.swing.JComboBox<String> cmbCurso;
    private javax.swing.JComboBox<String> cmbDocente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombreMateria;
    // End of variables declaration//GEN-END:variables
}
