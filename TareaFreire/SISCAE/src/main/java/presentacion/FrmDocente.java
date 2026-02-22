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
import negocio.DocenteNegocio;

/**
 *
 * @author Cinth
 */
public class FrmDocente extends javax.swing.JInternalFrame {
    
    DocenteNegocio negocio = new DocenteNegocio();
    int idSeleccionado = -1;
    
    public FrmDocente() {
        initComponents();
        ajustarColumnas(); 
        cargarTabla("");
        pack(); 
        
        // Eventos botones
        btnGuardar.addActionListener(e -> registrar());
        btnEditar.addActionListener(e -> editar());
        btnEliminar.addActionListener(e -> eliminar());
        btnPrevisualizar.addActionListener(e -> previsualizar());
        btnImprimir.addActionListener(e -> imprimir());
        btnNuevo.addActionListener(e -> nuevo());

        // Evento clic en tabla
        TablaDocentes.addMouseListener(new MouseAdapter() {
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
        limpiarFormulario();
        TablaDocentes.clearSelection();
        txtBuscar.setText("");
        idSeleccionado = -1;
        txtCedula.setEnabled(true);
        txtNombres.requestFocus();
        JOptionPane.showMessageDialog(this, "Formulario listo para nuevo registro");
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
        cmbEspecialidad = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnPrevisualizar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaDocentes = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        JPanelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SISCAE - GESTION DE DOCENTES");

        btnEliminar.setBackground(new java.awt.Color(255, 204, 204));
        btnEliminar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEliminar.setText("ELIMINAR");

        cmbEspecialidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administración de Empresas", "Agronomía", "Arquitectura", "Artes Plásticas", "Biología", "Bioquímica", "Ciencias de la Computación", "Ciencias del Deporte", "Comercio Exterior", "Comunicación Social", "Contabilidad y Auditoría", "Derecho", "Diseño Gráfico", "Economía", "Educación", "Educación Física", "Educación Inicial", "Educación Básica", "Educación Inclusiva", "Enfermería", "Estadística", "Filosofía", "Finanzas", "Física", "Gastronomía", "Geología", "Gestión Empresarial", "Historia", "Idiomas", "Ingeniería Agroindustrial", "Ingeniería Ambiental", "Ingeniería Civil", "Ingeniería de Sistemas", "Ingeniería Electrónica", "Ingeniería Industrial", "Ingeniería Mecánica", "Ingeniería de Software", "Lengua y Literatura", "Marketing", "Matemáticas", "Medicina", "Metodología de la Investigación", "Nutrición", "Odontología", "Psicología", "Psicopedagogía", "Química", "Relaciones Internacionales", "Sociología", "Trabajo Social", "Turismo" }));

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

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("ESPECIALIDAD");

        btnEditar.setBackground(new java.awt.Color(255, 255, 204));
        btnEditar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEditar.setText("EDITAR");

        btnImprimir.setBackground(new java.awt.Color(204, 255, 255));
        btnImprimir.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnImprimir.setText("Imprimir");

        btnPrevisualizar.setBackground(new java.awt.Color(153, 204, 255));
        btnPrevisualizar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPrevisualizar.setText("Previsualizar");

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        TablaDocentes.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        TablaDocentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRES", "APELLIDOS", "CEDULA", "CORREO", "TELEFONO", "ESPECIALIDAD", "ESTADO"
            }
        ));
        jScrollPane2.setViewportView(TablaDocentes);

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnNuevo.setText("NUEVO");

        javax.swing.GroupLayout JPanelPrincipalLayout = new javax.swing.GroupLayout(JPanelPrincipal);
        JPanelPrincipal.setLayout(JPanelPrincipalLayout);
        JPanelPrincipalLayout.setHorizontalGroup(
            JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(54, 54, 54)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGap(75, 75, 75)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addGap(44, 44, 44)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cmbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122)
                        .addComponent(btnPrevisualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnImprimir))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1417, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 38, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        JPanelPrincipalLayout.setVerticalGroup(
            JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cmbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir)
                    .addComponent(btnPrevisualizar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
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

    // ---------------------------- FUNCIONALIDAD ----------------------------

    private void registrar() {
        // Validar campos vacíos
        if (txtNombres.getText().trim().isEmpty() ||
            txtApellidos.getText().trim().isEmpty() ||
            txtCedula.getText().trim().isEmpty() ||
            txtCorreo.getText().trim().isEmpty() ||
            txtTelefono.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return;
        }

        // Validar cedula
        if (!txtCedula.getText().matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "La cédula debe tener exactamente 10 números");
            return;
        }

        // Validar teléfono
        if (!txtTelefono.getText().matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "El teléfono debe tener 10 números");
            return;
        }

        // Validar correo
        if (!txtCorreo.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(this, "Correo inválido: Ejemplo@instituto.edu.ec");
            return;
        }

        // Si todo está bien, registrar
        modelo.Docente e = new modelo.Docente();
        e.setNombres(txtNombres.getText());
        e.setApellidos(txtApellidos.getText());
        e.setCedula(txtCedula.getText());
        e.setCorreo(txtCorreo.getText());
        e.setTelefono(txtTelefono.getText());
        e.setEspecialidad(cmbEspecialidad.getSelectedItem().toString());

        String r = negocio.registrar(e);
        JOptionPane.showMessageDialog(this, r.equals("OK") ? "Registrado correctamente" : r);

        cargarTabla("");
        limpiarFormulario();
    }


    private void editar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un docente en la tabla");
            return;
        }

        modelo.Docente e = new modelo.Docente();
        e.setId(idSeleccionado);
        e.setNombres(txtNombres.getText());
        e.setApellidos(txtApellidos.getText());
        e.setCorreo(txtCorreo.getText());
        e.setTelefono(txtTelefono.getText());
        e.setEspecialidad(cmbEspecialidad.getSelectedItem().toString());

        String r = negocio.editar(e);
        JOptionPane.showMessageDialog(this, r.equals("OK") ? "Editado correctamente" : r);

        cargarTabla("");
        limpiarFormulario();
    }

    private void eliminar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un docente");
            return;
        }

        if (JOptionPane.showConfirmDialog(this, "¿Eliminar docente?", "Confirmar",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            negocio.eliminar(idSeleccionado);
            JOptionPane.showMessageDialog(this, "Docente marcado como inactivo");
            cargarTabla("");
            limpiarFormulario();
        }
    }
    
    private void buscar() {
    String filtro = txtBuscar.getText().trim();
    ArrayList<modelo.Docente> resultados = negocio.buscar(filtro);
    DefaultTableModel modelo = (DefaultTableModel) TablaDocentes.getModel();
    modelo.setRowCount(0);

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No existen registros que coincidan con el criterio ingresado.");
            return;
        }

        for (modelo.Docente e : resultados) {
            modelo.addRow(new Object[]{
                e.getId(),
                e.getNombres(),
                e.getApellidos(),
                e.getCedula(),
                e.getCorreo(),
                e.getTelefono(),
                e.getEspecialidad(),
                e.getEstado()
            });
        }
    }

    private void cargarTabla(String filtro) {
        DefaultTableModel modelo = (DefaultTableModel) TablaDocentes.getModel();
        modelo.setRowCount(0);

        for (modelo.Docente e : negocio.buscar(filtro)) {
            modelo.addRow(new Object[]{
                e.getId(),
                e.getNombres(),
                e.getApellidos(),
                e.getCedula(),
                e.getCorreo(),
                e.getTelefono(),
                e.getEspecialidad(),
                e.getEstado()
            });
        }
    }

    private void seleccionarFila() {
        int fila = TablaDocentes.getSelectedRow();
        idSeleccionado = Integer.parseInt(TablaDocentes.getValueAt(fila, 0).toString());

        txtNombres.setText(TablaDocentes.getValueAt(fila, 1).toString());
        txtApellidos.setText(TablaDocentes.getValueAt(fila, 2).toString());
        txtCedula.setText(TablaDocentes.getValueAt(fila, 3).toString());
        txtCorreo.setText(TablaDocentes.getValueAt(fila, 4).toString());
        txtTelefono.setText(TablaDocentes.getValueAt(fila, 5).toString());
        cmbEspecialidad.setSelectedItem(TablaDocentes.getValueAt(fila, 6).toString());
    }
    
    private void limpiarFormulario() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCedula.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        cmbEspecialidad.setSelectedIndex(0);
        idSeleccionado = -1;
        
        // 👉 Habilitar cédula cuando NO estás editando
        txtCedula.setEnabled(true);
    }
    
    private void ajustarColumnas() {
        TablaDocentes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TablaDocentes.setAutoCreateRowSorter(true); // permite ordenar columnas
    }
    
    private void cargarDatosSeleccionados() {
        int fila = TablaDocentes.getSelectedRow();
        if (fila == -1) return;

        idSeleccionado = Integer.parseInt(TablaDocentes.getValueAt(fila, 0).toString());
        txtNombres.setText(TablaDocentes.getValueAt(fila, 1).toString());
        txtApellidos.setText(TablaDocentes.getValueAt(fila, 2).toString());
        txtCedula.setText(TablaDocentes.getValueAt(fila, 3).toString());
        txtCorreo.setText(TablaDocentes.getValueAt(fila, 4).toString());
        txtTelefono.setText(TablaDocentes.getValueAt(fila, 5).toString());
        cmbEspecialidad.setSelectedItem(TablaDocentes.getValueAt(fila, 6).toString());

        // 👉 DESHABILITAR CÉDULA SOLO EN EDICIÓN
        txtCedula.setEnabled(false);
    }
    
    private void previsualizar() {
        JTable tablaPreview = new JTable(TablaDocentes.getModel()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaPreview.setFont(new Font("Serif", Font.PLAIN, 14));
        tablaPreview.setRowHeight(22);

        JScrollPane scroll = new JScrollPane(tablaPreview);
        scroll.setPreferredSize(new Dimension(1000, 600));

        // 🔥 Corrección importante: obtener un Frame válido
        Frame parent = JOptionPane.getFrameForComponent(this);

        JDialog vista = new JDialog(parent, "Vista previa", true);
        vista.setLayout(new BorderLayout());

        JButton btnCerrar = new JButton("CERRAR");
        btnCerrar.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnCerrar.addActionListener(e -> vista.dispose());

        vista.add(scroll, BorderLayout.CENTER);
        vista.add(btnCerrar, BorderLayout.SOUTH);
        vista.pack();
        vista.setLocationRelativeTo(parent);
        vista.setVisible(true);
    }
    
    private void imprimir() {
        try {
            boolean ok = TablaDocentes.print(JTable.PrintMode.FIT_WIDTH);

            if (ok) {
                JOptionPane.showMessageDialog(this, "Impreso correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "La impresión fue cancelada");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al imprimir: " + ex.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelPrincipal;
    private javax.swing.JTable TablaDocentes;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnPrevisualizar;
    private javax.swing.JComboBox<String> cmbEspecialidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
