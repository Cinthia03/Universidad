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
import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modelo.Estudiante;
import modelo.Materia;
import modelo.Tarea;
import negocio.DocenteNegocio;
import negocio.EstudianteNegocio;
import negocio.MateriaNegocio;
import negocio.TareaNegocio;

public class FrmEstudiantes extends javax.swing.JInternalFrame {

    private MateriaNegocio materiaNegocio = new MateriaNegocio();
    private DocenteNegocio docenteNegocio = new DocenteNegocio();
    EstudianteNegocio negocio = new EstudianteNegocio();
    int idSeleccionado = -1;
    
    public FrmEstudiantes() {
        initComponents();
        ajustarColumnas(); 
        cargarTabla("");
        eventos();
        cargarMaterias();      
        pack();        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPanelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        txtApellidos = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnPrevisualizar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cmbMaterias = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cmbDocente = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cmbCurso1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaEstudiantes = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();

        JPanelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SISCAE - GESTION DE ESTUDIANTES");

        btnEliminar.setBackground(new java.awt.Color(255, 204, 204));
        btnEliminar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEliminar.setText("ELIMINAR");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("BUSCAR:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("CORREO:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("NOMBRE:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("APELLIDO:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("CEDULA:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("TELEFONO:");

        btnGuardar.setBackground(new java.awt.Color(204, 255, 204));
        btnGuardar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnGuardar.setText("GUARDAR");

        btnEditar.setBackground(new java.awt.Color(255, 255, 204));
        btnEditar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEditar.setText("EDITAR");

        btnImprimir.setBackground(new java.awt.Color(204, 255, 255));
        btnImprimir.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnImprimir.setText("Imprimir");

        btnPrevisualizar.setBackground(new java.awt.Color(153, 204, 255));
        btnPrevisualizar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPrevisualizar.setText("Previsualizar");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("MATERIA:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("DOCENTE:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("CURSO:");

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        TablaEstudiantes.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        TablaEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRES", "APELLIDOS", "CEDULA", "CORREO", "TELEFONO", "MATERIA", "DOCENTE", "CURSO", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TablaEstudiantes);

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnNuevo.setText("NUEVO");

        javax.swing.GroupLayout JPanelPrincipalLayout = new javax.swing.GroupLayout(JPanelPrincipal);
        JPanelPrincipal.setLayout(JPanelPrincipalLayout);
        JPanelPrincipalLayout.setHorizontalGroup(
            JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelPrincipalLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelPrincipalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1449, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrevisualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimir)
                        .addGap(37, 37, 37))))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addGap(32, 32, 32)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCorreo)
                    .addComponent(txtCedula)
                    .addComponent(txtNombres)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(56, 56, 56)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTelefono)
                    .addComponent(cmbMaterias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbDocente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbCurso1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(93, 93, 93))
        );
        JPanelPrincipalLayout.setVerticalGroup(
            JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(17, 17, 17)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbMaterias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(13, 13, 13)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(11, 11, 11)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbCurso1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addGap(68, 68, 68)
                .addComponent(jLabel8)
                .addGap(6, 6, 6)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrevisualizar)
                    .addComponent(btnImprimir))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


     // =========================
    // EVENTOS
    // =========================
    private void eventos() {

        btnGuardar.addActionListener(e -> registrar());
        btnEditar.addActionListener(e -> editar());
        btnEliminar.addActionListener(e -> eliminar());
        btnPrevisualizar.addActionListener(e -> previsualizar());
        btnImprimir.addActionListener(e -> imprimir());
        btnNuevo.addActionListener(e -> nuevo());

        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { buscar(); }
            public void removeUpdate(DocumentEvent e) { buscar(); }
            public void changedUpdate(DocumentEvent e) { buscar(); }
        });

        cmbMaterias.addActionListener(e -> cargarDocenteCurso());

        TablaEstudiantes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarDatosSeleccionados();
            }
        });
    }
    
    private void nuevo() {
        limpiarFormulario();
        TablaEstudiantes.clearSelection();
        txtBuscar.setText("");
        idSeleccionado = -1;
        txtCedula.setEnabled(true);
        cargarMaterias();
        txtNombres.requestFocus();
        JOptionPane.showMessageDialog(this, "Formulario listo para nuevo registro");
    }

    // =========================
    // CARGAR MATERIAS
    // =========================
    private void cargarMaterias() {
        cmbMaterias.removeAllItems();

        for (Materia m : materiaNegocio.listar()) {
            cmbMaterias.addItem(m.getNombreMateria());
        }

        cargarDocenteCurso();
    }

    // =========================
    // CARGAR DOCENTE Y CURSO
    // =========================
    private void cargarDocenteCurso() {

        cmbDocente.removeAllItems();
        cmbCurso1.removeAllItems();

        if (cmbMaterias.getSelectedItem() == null) return;

        String nombreMateria = cmbMaterias.getSelectedItem().toString();

        for (Materia m : materiaNegocio.listar()) {
            if (m.getNombreMateria().equals(nombreMateria)) {
                cmbDocente.addItem(m.getDocenteInfo());
                cmbCurso1.addItem(m.getCurso());
                break;
            }
        }
    }

    // =========================
    // REGISTRAR
    // =========================
    private void registrar() {

        if (txtNombres.getText().trim().isEmpty() ||
            txtApellidos.getText().trim().isEmpty() ||
            txtCedula.getText().trim().isEmpty() ||
            txtCorreo.getText().trim().isEmpty() ||
            txtTelefono.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return;
        }

        if (!txtCedula.getText().matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "La cédula debe tener 10 dígitos");
            return;
        }

        if (!txtTelefono.getText().matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "El teléfono debe tener 10 dígitos");
            return;
        }

        if (!txtCorreo.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(this, "Correo inválido");
            return;
        }

        Estudiante e = new Estudiante();
        e.setNombres(txtNombres.getText());
        e.setApellidos(txtApellidos.getText());
        e.setCedula(txtCedula.getText());
        e.setCorreo(txtCorreo.getText());
        e.setTelefono(txtTelefono.getText());
        e.setNombreMateria(cmbMaterias.getSelectedItem().toString());
        e.setNombreDocente(cmbDocente.getSelectedItem().toString());
        e.setCurso(cmbCurso1.getSelectedItem().toString());

        String r = negocio.registrar(e);
        JOptionPane.showMessageDialog(this, r.equals("OK") ? "Registrado correctamente" : r);

        limpiarFormulario();
        cargarTabla("");
    }

    // =========================
    // EDITAR
    // =========================
    private void editar() {

        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante");
            return;
        }

        Estudiante e = new Estudiante();
        e.setId(idSeleccionado);
        e.setNombres(txtNombres.getText());
        e.setApellidos(txtApellidos.getText());
        e.setCorreo(txtCorreo.getText());
        e.setTelefono(txtTelefono.getText());
        e.setNombreMateria(cmbMaterias.getSelectedItem().toString());
        e.setNombreDocente(cmbDocente.getSelectedItem().toString());
        e.setCurso(cmbCurso1.getSelectedItem().toString());

        String r = negocio.editar(e);
        JOptionPane.showMessageDialog(this, r.equals("OK") ? "Editado correctamente" : r);

        limpiarFormulario();
        cargarTabla("");
    }

    // =========================
    // ELIMINAR
    // =========================
    private void eliminar() {

        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante");
            return;
        }

        if (JOptionPane.showConfirmDialog(this, "¿Eliminar estudiante?", "Confirmar",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            negocio.eliminar(idSeleccionado);
            JOptionPane.showMessageDialog(this, "Estudiante eliminado");
            limpiarFormulario();
            cargarTabla("");
        }
    }

    // =========================
    // BUSCAR
    // =========================
    private void buscar() {

        String filtro = txtBuscar.getText().trim();
        ArrayList<Estudiante> lista = negocio.buscar(filtro);

        DefaultTableModel modelo = (DefaultTableModel) TablaEstudiantes.getModel();
        modelo.setRowCount(0);

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay coincidencias");
            cargarTabla("");
            return;
        }

        for (Estudiante e : lista) {
            modelo.addRow(new Object[]{
                e.getId(),
                e.getNombres(),
                e.getApellidos(),
                e.getCedula(),
                e.getCorreo(),
                e.getTelefono(),
                e.getNombreMateria(),
                e.getNombreDocente(),
                e.getCurso(),
                e.getEstado()
            });
        }
    }

    // =========================
    // TABLA
    // =========================
    private void cargarTabla(String filtro) {

        DefaultTableModel modelo = (DefaultTableModel) TablaEstudiantes.getModel();
        modelo.setRowCount(0);

        for (Estudiante e : negocio.buscar(filtro)) {
            modelo.addRow(new Object[]{
                e.getId(),
                e.getNombres(),
                e.getApellidos(),
                e.getCedula(),
                e.getCorreo(),
                e.getTelefono(),
                e.getNombreMateria(),
                e.getNombreDocente(),
                e.getCurso(),
                e.getEstado()
            });
        }
    }

    // =========================
    // SELECCIONAR FILA
    // =========================
    private void cargarDatosSeleccionados() {

        int fila = TablaEstudiantes.getSelectedRow();
        if (fila == -1) return;

        idSeleccionado = Integer.parseInt(TablaEstudiantes.getValueAt(fila, 0).toString());

        txtNombres.setText(TablaEstudiantes.getValueAt(fila, 1).toString());
        txtApellidos.setText(TablaEstudiantes.getValueAt(fila, 2).toString());
        txtCedula.setText(TablaEstudiantes.getValueAt(fila, 3).toString());
        txtCorreo.setText(TablaEstudiantes.getValueAt(fila, 4).toString());
        txtTelefono.setText(TablaEstudiantes.getValueAt(fila, 5).toString());
        cmbMaterias.setSelectedItem(TablaEstudiantes.getValueAt(fila, 6).toString());
        cmbDocente.setSelectedItem(TablaEstudiantes.getValueAt(fila, 7).toString());
        cmbCurso1.setSelectedItem(TablaEstudiantes.getValueAt(fila, 8).toString());

        txtCedula.setEnabled(false);
    }

    // =========================
    // LIMPIAR
    // =========================
    private void limpiarFormulario() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCedula.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        cmbMaterias.setSelectedIndex(0);
        txtCedula.setEnabled(true);
        idSeleccionado = -1;
    }

    // =========================
    // UTILIDADES
    // =========================
    private void ajustarColumnas() {
        TablaEstudiantes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        TablaEstudiantes.setAutoCreateRowSorter(true);
    }

    private void previsualizar() {

        JTable tablaPreview = new JTable(TablaEstudiantes.getModel());
        tablaPreview.setFont(new Font("Serif", Font.PLAIN, 14));
        tablaPreview.setRowHeight(22);

        JScrollPane scroll = new JScrollPane(tablaPreview);
        scroll.setPreferredSize(new Dimension(1000, 600));

        Frame parent = JOptionPane.getFrameForComponent(this);
        JDialog vista = new JDialog(parent, "Vista previa", true);
        vista.setLayout(new BorderLayout());
        vista.add(scroll, BorderLayout.CENTER);
        vista.pack();
        vista.setLocationRelativeTo(parent);
        vista.setVisible(true);
    }

    private void imprimir() {
        try {
            TablaEstudiantes.print(JTable.PrintMode.FIT_WIDTH);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al imprimir");
        }
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelPrincipal;
    private javax.swing.JTable TablaEstudiantes;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnPrevisualizar;
    private javax.swing.JComboBox<String> cmbCurso1;
    private javax.swing.JComboBox<String> cmbDocente;
    private javax.swing.JComboBox<String> cmbMaterias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
