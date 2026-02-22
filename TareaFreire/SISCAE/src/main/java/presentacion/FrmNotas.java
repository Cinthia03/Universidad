package presentacion;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.util.HashSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.Nota;
import modelo.Estudiante;
import modelo.Materia;
import modelo.Tarea;
import negocio.EstudianteNegocio;
import negocio.MateriaNegocio;
import negocio.NotaNegocio;
import negocio.TareaNegocio;


public class FrmNotas extends javax.swing.JInternalFrame {
    
    private final NotaNegocio notaNegocio = new NotaNegocio();
    private final TareaNegocio tareaNegocio = new TareaNegocio();
    private int idNotaSeleccionada = -1;
    private int idTareaSeleccionada = -1;
    private TableRowSorter<DefaultTableModel> sorter;
    private String rolUsuario;


    public FrmNotas(String rol) {
        this.rolUsuario = rol;
        initComponents();
        cargarEstudiantes();
        cargarTabla();
        cargarEstudiantesPromedio();
        eventos();
        aplicarRol();
        pack();
    }

    // ================= EVENTOS =================
    private void eventos() {

        cmbEstudiante.addActionListener(e -> {
            cargarMaterias();
            cmbTipo.removeAllItems();
            cmbAsignacion.removeAllItems();
        });

        cmbMateria.addActionListener(e -> {
            cargarTipos();
            cmbAsignacion.removeAllItems();
        });

        cmbTipo.addActionListener(e -> cargarAsignaciones());
        btnAgregar.addActionListener(e -> guardarNota());
        btnEditar.addActionListener(e -> editarNota());
        btnNuevo.addActionListener(e -> nuevo());
        TablaNotas.getSelectionModel().addListSelectionListener(e -> seleccionarFila());
        cmbEstudiantesPromedio.addActionListener(e -> cargarPromedio());
        
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                filtrarTabla();
            }
        });
        
        btnImprimir.addActionListener(e -> imprimirTabla());
    }
    
    private void nuevo() {
        txtNota.setText("");
        cmbMateria.removeAllItems();
        cmbTipo.removeAllItems();
        cmbAsignacion.removeAllItems();
        TablaNotas.clearSelection();
        idNotaSeleccionada = -1;
        btnAgregar.setEnabled(true);
        btnEditar.setEnabled(true);
        JOptionPane.showMessageDialog(this, "Formulario listo para registrar una nueva nota");
    }
    
    //================= OCULTAR POR ROLES =================
    private void aplicarRol() {
        if ("ESTUDIANTE".equalsIgnoreCase(rolUsuario)) {
            // Ocultar formulario de registro
            cmbEstudiante.setVisible(false);
            cmbMateria.setVisible(false);
            cmbTipo.setVisible(false);
            cmbAsignacion.setVisible(false);
            txtNota.setVisible(false);

            btnAgregar.setVisible(false);
            btnEditar.setVisible(false);

            // Ocultar labels relacionados
            jLabel2.setVisible(false); // ESTUDIANTE
            jLabel4.setVisible(false); // MATERIA
            jLabel3.setVisible(false); // TIPO
            jLabel9.setVisible(false); // ASIGNACION
            jLabel5.setVisible(false); // NOTA
            jLabel6.setVisible(false); // /10

            // Ajuste visual
            TablaNotas.setEnabled(false);
        }
    }
    

    // ================= ESTUDIANTES =================
    private void cargarEstudiantes() {

        cmbEstudiante.removeAllItems();
        HashSet<String> estudiantes = new HashSet<>();

        for (Tarea t : tareaNegocio.listar()) {
            if ("ACTIVA".equalsIgnoreCase(t.getEstado())) {
                estudiantes.add(t.getNombreEstudiante());
            }
        }

        estudiantes.forEach(cmbEstudiante::addItem);
    }

    // ================= MATERIAS =================
    private void cargarMaterias() {

        cmbMateria.removeAllItems();
        String estudiante = (String) cmbEstudiante.getSelectedItem();
        if (estudiante == null) return;

        HashSet<String> materias = new HashSet<>();

        for (Tarea t : tareaNegocio.listar()) {
            if (t.getNombreEstudiante().equals(estudiante)) {
                materias.add(t.getNombreMateria());
            }
        }

        materias.forEach(cmbMateria::addItem);
    }

    // ================= TIPOS =================
    private void cargarTipos() {

        cmbTipo.removeAllItems();
        String estudiante = (String) cmbEstudiante.getSelectedItem();
        String materia = (String) cmbMateria.getSelectedItem();
        if (estudiante == null || materia == null) return;

        HashSet<String> tipos = new HashSet<>();

        for (Tarea t : tareaNegocio.listar()) {
            if (t.getNombreEstudiante().equals(estudiante)
                    && t.getNombreMateria().equals(materia)) {
                tipos.add(t.getTipo());
            }
        }

        tipos.forEach(cmbTipo::addItem);
    }

    // ================= ASIGNACIONES =================
    private void cargarAsignaciones() {
        cmbAsignacion.removeAllItems();
        idTareaSeleccionada = -1;

        String estudiante = (String) cmbEstudiante.getSelectedItem();
        String materia = (String) cmbMateria.getSelectedItem();
        String tipo = (String) cmbTipo.getSelectedItem();

        if (estudiante == null || materia == null || tipo == null) return;

        for (Tarea t : tareaNegocio.listar()) {
            if (t.getNombreEstudiante().equals(estudiante)
                    && t.getNombreMateria().equals(materia)
                    && t.getTipo().equals(tipo)
                    && "ACTIVA".equalsIgnoreCase(t.getEstado())) {

                boolean yaCalificada = notaNegocio.listar().stream().anyMatch(n ->
                        n.getTareaId() == t.getId()
                );

                if (!yaCalificada) {
                    cmbAsignacion.addItem(t.getTitulo());
                }
            }
        }

        // 🔴 EVENTO CLAVE
        cmbAsignacion.addActionListener(e -> asignarIdTarea());
    }
    
    private void asignarIdTarea() {
        String titulo = (String) cmbAsignacion.getSelectedItem();
        if (titulo == null) return;

        String estudiante = (String) cmbEstudiante.getSelectedItem();
        String materia = (String) cmbMateria.getSelectedItem();
        String tipo = (String) cmbTipo.getSelectedItem();

        for (Tarea t : tareaNegocio.listar()) {
            if (t.getTitulo().equals(titulo)
                    && t.getNombreEstudiante().equals(estudiante)
                    && t.getNombreMateria().equals(materia)
                    && t.getTipo().equals(tipo)) {

                idTareaSeleccionada = t.getId(); // ✅ AQUÍ ESTÁ LA CLAVE
                break;
            }
        }

        System.out.println("ID TAREA SELECCIONADA = " + idTareaSeleccionada);
    }

    // ================= GUARDAR =================
    private void guardarNota() {
       
        if (cmbEstudiante.getSelectedItem() == null ||
            cmbMateria.getSelectedItem() == null ||
            cmbTipo.getSelectedItem() == null ||
            cmbAsignacion.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(this, "Complete todos los campos");
            return;
        }

        double nota;
        try {
            nota = Double.parseDouble(txtNota.getText());
            if (nota < 0 || nota > 10) throw new Exception();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Nota inválida (0-10)");
            return;
        }
        
        int idTareaSeleccionada = this.idTareaSeleccionada; 

        Nota n = new Nota();
        n.setTareaId(idTareaSeleccionada);
        n.setEstudiante(cmbEstudiante.getSelectedItem().toString());
        n.setMateria(cmbMateria.getSelectedItem().toString());
        n.setTipo(cmbTipo.getSelectedItem().toString());
        n.setTitulo(cmbAsignacion.getSelectedItem().toString());
        n.setNota(nota);

        String res = notaNegocio.registrar(n);

        if (res.equals("OK")) {
            JOptionPane.showMessageDialog(this, "Nota registrada");
            limpiarFormulario();
            cargarTabla();
            cargarEstudiantesPromedio();
            cargarPromedio();
        } else {
            JOptionPane.showMessageDialog(this, res);
        }
    }

    // ================= EDITAR =================
    private void editarNota() {
        if (idNotaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una nota");
            return;
        }

        double nota;
        try {
            nota = Double.parseDouble(txtNota.getText());
            if (nota < 0 || nota > 10) throw new Exception();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Nota inválida");
            return;
        }

        Nota n = new Nota();
        n.setTareaId(idTareaSeleccionada); 
        n.setId(idNotaSeleccionada);
        n.setEstudiante(cmbEstudiante.getSelectedItem().toString());
        n.setMateria(cmbMateria.getSelectedItem().toString());
        n.setTipo(cmbTipo.getSelectedItem().toString());
        n.setTitulo(cmbAsignacion.getSelectedItem().toString());
        n.setNota(nota);

        notaNegocio.editar(n);

        JOptionPane.showMessageDialog(this, "Nota editada correctamente");

        limpiarFormulario();
        cargarTabla();
        cargarPromedio();
    }

    // ================= TABLA =================
    private void cargarTabla() {

        DefaultTableModel m = (DefaultTableModel) TablaNotas.getModel();
        m.setRowCount(0);

        for (Nota n : notaNegocio.listar()) {
            m.addRow(new Object[]{
                n.getId(),
                n.getEstudiante(),
                n.getMateria(),
                n.getTipo(),
                n.getTitulo(),
                n.getNota()
            });
        }     
        sorter = new TableRowSorter<>(m);
        TablaNotas.setRowSorter(sorter);
    }

    // ================= SELECCION =================
    private void seleccionarFila() {
        int filaVista = TablaNotas.getSelectedRow();
        if (filaVista == -1) return;

        int fila = TablaNotas.convertRowIndexToModel(filaVista);

        DefaultTableModel model = (DefaultTableModel) TablaNotas.getModel();

        idNotaSeleccionada = Integer.parseInt(model.getValueAt(fila, 0).toString());
        String estudiante = model.getValueAt(fila, 1).toString();
        String materia = model.getValueAt(fila, 2).toString();
        String tipo = model.getValueAt(fila, 3).toString();
        String asignacion = model.getValueAt(fila, 4).toString();
        String nota = model.getValueAt(fila, 5).toString();

        // Cargar combos en cascada
        cmbEstudiante.setSelectedItem(estudiante);

        cargarMaterias();
        cmbMateria.setSelectedItem(materia);

        cargarTipos();
        cmbTipo.setSelectedItem(tipo);

        cargarAsignaciones();
        cmbAsignacion.setSelectedItem(asignacion);

        txtNota.setText(nota);
    }

    // ================= PROMEDIO =================
    private void cargarPromedio() {

        String estudiante = (String) cmbEstudiantesPromedio.getSelectedItem();
        if (estudiante == null) return;

        txtPromedio.setText(
                String.format("%.2f", notaNegocio.calcularPromedio(estudiante))
        );
    }

    // ================= ESTUDIANTES PROMEDIO =================
    private void cargarEstudiantesPromedio() {

        cmbEstudiantesPromedio.removeAllItems();
        HashSet<String> estudiantes = new HashSet<>();

        for (Nota n : notaNegocio.listar()) {
            estudiantes.add(n.getEstudiante());
        }

        estudiantes.forEach(cmbEstudiantesPromedio::addItem);
    }
    
    // ================= FILTRAR =================
    private void filtrarTabla() {
        String texto = txtBuscar.getText().trim();

        if (texto.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(
                RowFilter.regexFilter("(?i)" + texto, 1) // columna ESTUDIANTE
            );
        }
    }
    
    // ================= IMPRIMIR =================  
    private String obtenerEstudianteBuscado() {
        String texto = txtBuscar.getText().trim();
        return texto.isEmpty() ? null : texto;
    }
    
    private void imprimirTabla() {
        JDialog preview = new JDialog(
                SwingUtilities.getWindowAncestor(this),
                "Vista previa de impresión",
                Dialog.ModalityType.APPLICATION_MODAL
        );
        preview.setSize(1000, 650);
        preview.setLocationRelativeTo(null);

        DefaultTableModel modelOriginal = (DefaultTableModel) TablaNotas.getModel();

        // Columnas
        String[] columnas = new String[TablaNotas.getColumnCount()];
        for (int i = 0; i < TablaNotas.getColumnCount(); i++) {
            columnas[i] = TablaNotas.getColumnName(i);
        }

        DefaultTableModel modelPreview = new DefaultTableModel(columnas, 0);

        // Filas visibles (filtradas)
        for (int i = 0; i < TablaNotas.getRowCount(); i++) {
            int modelIndex = TablaNotas.convertRowIndexToModel(i);
            Object[] fila = new Object[modelOriginal.getColumnCount()];
            for (int j = 0; j < fila.length; j++) {
                fila[j] = modelOriginal.getValueAt(modelIndex, j);
            }
            modelPreview.addRow(fila);
        }

        JTable tablaPreview = new JTable(modelPreview);
        tablaPreview.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaPreview.setFillsViewportHeight(true);

        JScrollPane scroll = new JScrollPane(tablaPreview);

        // 🔹 PROMEDIO PONDERADO DESDE NEGOCIO
        String estudiante = obtenerEstudianteBuscado();
        JLabel lblInfo = new JLabel();
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        lblInfo.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 16));

        if (estudiante != null) {
            double promedio = notaNegocio.calcularPromedio(estudiante);
            lblInfo.setText(
                    "Estudiante: " + estudiante +
                    "    |    Promedio Final: " +
                    String.format("%.2f", promedio)
            );
        } else {
            lblInfo.setText("REPORTE GENERAL DE NOTAS");
        }

        JButton btnPrint = new JButton("Imprimir");
        btnPrint.addActionListener(e -> {
            try {
                boolean ok = tablaPreview.print(
                        JTable.PrintMode.FIT_WIDTH,
                        new java.text.MessageFormat(lblInfo.getText()),
                        new java.text.MessageFormat("Página {0}")
                );

                JOptionPane.showMessageDialog(
                        preview,
                        ok ? "Impresión completada" : "Impresión cancelada"
                );

                if (ok) preview.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        preview,
                        "Error al imprimir: " + ex.getMessage()
                );
            }
        });

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnPrint);

        preview.setLayout(new BorderLayout());
        preview.add(lblInfo, BorderLayout.NORTH);
        preview.add(scroll, BorderLayout.CENTER);
        preview.add(panelBoton, BorderLayout.SOUTH);

        preview.setVisible(true);
    }

    // ================= LIMPIAR =================
    private void limpiarFormulario() {

        txtNota.setText("");
        cmbMateria.removeAllItems();
        cmbTipo.removeAllItems();
        cmbAsignacion.removeAllItems();
        idNotaSeleccionada = -1;
        TablaNotas.clearSelection();
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbEstudiante = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbMateria = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaNotas = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        cmbEstudiantesPromedio = new javax.swing.JComboBox<>();
        txtPromedio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbAsignacion = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnImprimir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SISCAE - GESTION DE CALIFICACIONES");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("ESTUDIANTE: ");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("MATERIA:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("TIPO:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("NOTA:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("/10");

        btnAgregar.setBackground(new java.awt.Color(204, 255, 204));
        btnAgregar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAgregar.setText("AGREGAR NOTA");

        btnEditar.setBackground(new java.awt.Color(255, 255, 204));
        btnEditar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnEditar.setText("EDITAR NOTA");

        TablaNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "ESTUDIANTE", "MATERIA", "TIPO", "ASIGNACION", "NOTA"
            }
        ));
        jScrollPane1.setViewportView(TablaNotas);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("ESTUDIANTE: ");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("PROMEDIO:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("ASIGNACION:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("BUSCAR");

        btnImprimir.setBackground(new java.awt.Color(204, 255, 255));
        btnImprimir.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnImprimir.setText("IMPRIMIR");

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnNuevo.setText("NUEVO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(cmbEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122)
                        .addComponent(cmbMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel2)
                        .addGap(243, 243, 243)
                        .addComponent(jLabel4)
                        .addGap(229, 229, 229)
                        .addComponent(jLabel3)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(210, 210, 210))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5)
                        .addGap(9, 9, 9)))
                .addGap(69, 69, 69))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(32, 32, 32)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1428, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(18, 18, 18)
                                            .addComponent(cmbEstudiantesPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addGap(28, 28, 28)
                                            .addComponent(txtPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(503, 503, 503)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(411, 411, 411)
                        .addComponent(btnAgregar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar)
                        .addGap(18, 18, 18)
                        .addComponent(btnImprimir)
                        .addGap(18, 18, 18)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEditar)
                    .addComponent(btnImprimir)
                    .addComponent(btnNuevo))
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbEstudiantesPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaNotas;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cmbAsignacion;
    private javax.swing.JComboBox<String> cmbEstudiante;
    private javax.swing.JComboBox<String> cmbEstudiantesPromedio;
    private javax.swing.JComboBox<String> cmbMateria;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtNota;
    private javax.swing.JTextField txtPromedio;
    // End of variables declaration//GEN-END:variables
}
