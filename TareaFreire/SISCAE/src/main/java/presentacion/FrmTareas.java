package presentacion;

import datos.MateriaDatos;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import modelo.Estudiante;
import modelo.Tarea;
import negocio.EstudianteNegocio;
import negocio.NotaNegocio;
import negocio.TareaNegocio;


public class FrmTareas extends javax.swing.JInternalFrame {

    private EstudianteNegocio estudianteNegocio = new EstudianteNegocio();
    private TareaNegocio tareaNegocio = new TareaNegocio();
    private NotaNegocio notaNegocio = new NotaNegocio();
    
    private int idSeleccionado = -1;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public FrmTareas() {
        initComponents();
        cargarMaterias();
        cargarTabla();
        eventos();
        pack();
    }
    
    // =========================
    // EVENTOS
    // =========================
    private void eventos() {
        btnGuardar.addActionListener(e -> guardar());
        btnEditar.addActionListener(e -> editar());
        btnEliminar.addActionListener(e -> eliminar());
        btnNuevo.addActionListener(e -> nuevo());
        btnImprimir.addActionListener(e -> imprimir());
        btnPrevisualizar.addActionListener(e -> previsualizar());

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                buscar();
            }
        });

        cmbMaterias.addActionListener(e -> cargarDatos());

        TablaTareas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && TablaTareas.getSelectedRow() != -1) {
                seleccionarFila();
            }
        });
    }

    // =========================
    // NUEVO
    // =========================
    private void nuevo() {
        idSeleccionado = -1;
        txtTitulo.setText("");
        txtDescripcion.setText("");
        txtFechaEntrega.setText("");
        cmbTipo.setSelectedIndex(0);
        cmbEstudiante.setSelectedIndex(-1);
        cmbDocente.setSelectedIndex(-1);
        txtCurso.setText("");
        TablaTareas.clearSelection();
        txtTitulo.requestFocus();
    }

    // =========================
    // CARGAR MATERIAS
    // =========================
    private void cargarMaterias() {
        cmbMaterias.removeAllItems();
        ArrayList<String> materias = new ArrayList<>();

        for (Estudiante e : estudianteNegocio.listar()) {
            if (!materias.contains(e.getNombreMateria())) {
                materias.add(e.getNombreMateria());
                cmbMaterias.addItem(e.getNombreMateria());
            }
        }
        cargarDatos();
    }

    // =========================
    // CARGAR DATOS
    // =========================
    private void cargarDatos() {
        cmbDocente.removeAllItems();
        cmbEstudiante.removeAllItems();
        txtCurso.setText("");

        if (cmbMaterias.getSelectedItem() == null) return;

        String materia = cmbMaterias.getSelectedItem().toString();
        boolean cursoAsignado = false;

        for (Estudiante e : estudianteNegocio.listar()) {
            if (e.getNombreMateria().equals(materia)) {
                if (cmbDocente.getItemCount() == 0) {
                    cmbDocente.addItem(e.getNombreDocente());
                }
                cmbEstudiante.addItem(e.getNombres() + " " + e.getApellidos());

                if (!cursoAsignado) {
                    txtCurso.setText(e.getCurso());
                    cursoAsignado = true;
                }
            }
        }
    }

    // =========================
    // GUARDAR
    // =========================
    private void guardar() {
        if (!validarFecha()) return;

        Tarea t = new Tarea();
        t.setNombreMateria(cmbMaterias.getSelectedItem().toString());
        t.setNombreDocente(cmbDocente.getSelectedItem().toString());
        t.setNombreEstudiante(cmbEstudiante.getSelectedItem().toString());
        t.setCurso(txtCurso.getText());
        t.setTitulo(txtTitulo.getText());
        t.setDescripcion(txtDescripcion.getText());
        t.setTipo(cmbTipo.getSelectedItem().toString());

        try {
            t.setFechaEntrega(sdf.parse(txtFechaEntrega.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Fecha inválida");
            return;
        }

        String res = tareaNegocio.registrar(t);

        if (res.equals("OK")) {
            JOptionPane.showMessageDialog(this, "Tarea registrada");
            limpiar();
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(this, res);
        }
    }

    // =========================
    // EDITAR
    // =========================
    private void editar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una tarea");
            return;
        }

        if (!validarFecha()) return;

        Tarea t = new Tarea();
        t.setId(idSeleccionado);
        t.setNombreMateria(cmbMaterias.getSelectedItem().toString());
        t.setNombreDocente(cmbDocente.getSelectedItem().toString());
        t.setNombreEstudiante(cmbEstudiante.getSelectedItem().toString());
        t.setCurso(txtCurso.getText());
        t.setTitulo(txtTitulo.getText());
        t.setDescripcion(txtDescripcion.getText());
        t.setTipo(cmbTipo.getSelectedItem().toString());

        try {
            t.setFechaEntrega(sdf.parse(txtFechaEntrega.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Fecha inválida");
            return;
        }

        tareaNegocio.editar(t);
        JOptionPane.showMessageDialog(this, "Tarea editada");
        limpiar();
        cargarTabla();
    }

    // =========================
    // ELIMINAR
    // =========================
    private void eliminar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una tarea");
            return;
        }

        if (notaNegocio.existeNotaPorTarea(idSeleccionado)) {
            JOptionPane.showMessageDialog(
                this,
                "No se puede eliminar una tarea calificada",
                "Acción no permitida",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int op = JOptionPane.showConfirmDialog(
            this,
            "¿Eliminar tarea?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION
        );

        if (op == JOptionPane.YES_OPTION) {
            tareaNegocio.eliminar(idSeleccionado);
            JOptionPane.showMessageDialog(this, "Tarea eliminada");
            limpiar();
            cargarTabla();
        }
    }

    // =========================
    // BUSCAR
    // =========================
    private void buscar() {
        DefaultTableModel modelo = (DefaultTableModel) TablaTareas.getModel();
        modelo.setRowCount(0);

        for (Tarea t : tareaNegocio.buscar(txtBuscar.getText())) {
            boolean tieneNota = notaNegocio.existeNotaPorTarea(t.getId());
            modelo.addRow(new Object[]{
                t.getId(),
                t.getNombreMateria(),
                t.getNombreDocente(),
                t.getNombreEstudiante(),
                t.getCurso(),
                t.getTitulo(),
                t.getDescripcion(),
                t.getTipo(),
                sdf.format(t.getFechaEntrega()),
                tieneNota ? "CALIFICADA" : "SIN NOTA"
            });
        }
    }

    // =========================
    // TABLA
    // =========================
    private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) TablaTareas.getModel();
        modelo.setRowCount(0);

        for (Tarea t : tareaNegocio.listar()) {
            boolean tieneNota = notaNegocio.existeNotaPorTarea(t.getId());
            modelo.addRow(new Object[]{
                t.getId(),
                t.getNombreMateria(),
                t.getNombreDocente(),
                t.getNombreEstudiante(),
                t.getCurso(),
                t.getTitulo(),
                t.getDescripcion(),
                t.getTipo(),
                sdf.format(t.getFechaEntrega()),
                tieneNota ? "CALIFICADA" : "SIN NOTA"
            });
        }
    }

    // =========================
    // VALIDAR FECHA
    // =========================
    private boolean validarFecha() {
        try {
            Date fecha = sdf.parse(txtFechaEntrega.getText());
            Date hoy = sdf.parse(sdf.format(new Date()));
            if (fecha.before(hoy)) {
                JOptionPane.showMessageDialog(this, "La fecha no puede ser menor a hoy");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Formato inválido dd-MM-yyyy");
            return false;
        }
        return true;
    }

    private void limpiar() {
        idSeleccionado = -1;
        txtTitulo.setText("");
        txtDescripcion.setText("");
        txtFechaEntrega.setText("");
        TablaTareas.clearSelection();
    }

    // =========================
    // IMPRIMIR
    // =========================
    private void imprimir() {
        try {
            TablaTareas.print(JTable.PrintMode.FIT_WIDTH);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al imprimir");
        }
    }

    // =========================
    // PREVISUALIZAR
    // =========================
    private void previsualizar() {

        JTable tablaPreview = new JTable(TablaTareas.getModel());
        tablaPreview.setFont(new Font("Serif", Font.PLAIN, 14));
        tablaPreview.setRowHeight(22);

        JScrollPane scroll = new JScrollPane(tablaPreview);
        scroll.setPreferredSize(new Dimension(900, 500));

        Frame parent = JOptionPane.getFrameForComponent(this);
        JDialog vista = new JDialog(parent, "Vista previa", true);
        vista.setLayout(new BorderLayout());
        vista.add(scroll, BorderLayout.CENTER);
        vista.pack();
        vista.setLocationRelativeTo(parent);
        vista.setVisible(true);
    }
    
    //
    private void seleccionarFila() {
        int fila = TablaTareas.getSelectedRow();
        if (fila == -1) return;

        DefaultTableModel modelo = (DefaultTableModel) TablaTareas.getModel();

        idSeleccionado = Integer.parseInt(modelo.getValueAt(fila, 0).toString());

        cmbMaterias.setSelectedItem(modelo.getValueAt(fila, 1).toString());
        cmbDocente.setSelectedItem(modelo.getValueAt(fila, 2).toString());
        cmbEstudiante.setSelectedItem(modelo.getValueAt(fila, 3).toString());
        txtCurso.setText(modelo.getValueAt(fila, 4).toString());
        txtTitulo.setText(modelo.getValueAt(fila, 5).toString());
        txtDescripcion.setText(modelo.getValueAt(fila, 6).toString());
        cmbTipo.setSelectedItem(modelo.getValueAt(fila, 7).toString());
        txtFechaEntrega.setText(modelo.getValueAt(fila, 8).toString());
    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPanelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        txtTitulo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnPrevisualizar = new javax.swing.JButton();
        cmbMaterias = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cmbDocente = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtFechaEntrega = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cmbEstudiante = new javax.swing.JComboBox<>();
        txtCurso = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaTareas = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();

        JPanelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SISCAE - GESTION DE TAREAS");

        btnEliminar.setBackground(new java.awt.Color(255, 204, 204));
        btnEliminar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEliminar.setText("ELIMINAR");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("BUSCAR:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("TITULO:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("MATERIA:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("DESCRIPCION:");

        btnGuardar.setBackground(new java.awt.Color(204, 255, 204));
        btnGuardar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnGuardar.setText("GUARDAR");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("ESTUDIANTE:");

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
        jLabel9.setText("DOCENTE:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("FECHA ENTREGA: ");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("CURSO:");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("TIPO:");

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TAREA", "LECCION", "FORO", "PROYECTO", "TALLER", "EXAMEN" }));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        TablaTareas.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        TablaTareas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "MATERIA", "DOCENTE", "ESTUDIANTE:", "CURSO", "TITULO", "DESCRIPCION", "TIPO", "FECHA ENTREGA"
            }
        ));
        jScrollPane2.setViewportView(TablaTareas);

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnNuevo.setText("NUEVO");

        javax.swing.GroupLayout JPanelPrincipalLayout = new javax.swing.GroupLayout(JPanelPrincipal);
        JPanelPrincipal.setLayout(JPanelPrincipalLayout);
        JPanelPrincipalLayout.setHorizontalGroup(
            JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel8)
                        .addGap(41, 41, 41)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel3))
                                .addGap(67, 67, 67)
                                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbMaterias, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbEstudiante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCurso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbTipo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtFechaEntrega, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(74, 74, 74)
                                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE))
                                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPrevisualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnImprimir))))
                    .addComponent(jScrollPane2))
                .addGap(28, 28, 28))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        JPanelPrincipalLayout.setVerticalGroup(
            JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelPrincipalLayout.createSequentialGroup()
                                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2))
                                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(69, 69, 69))
                                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelPrincipalLayout.createSequentialGroup()
                                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)))))
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmbMaterias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(12, 12, 12)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(80, 80, 80)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(btnImprimir)
                    .addComponent(btnPrevisualizar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelPrincipal;
    private javax.swing.JTable TablaTareas;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnPrevisualizar;
    private javax.swing.JComboBox<String> cmbDocente;
    private javax.swing.JComboBox<String> cmbEstudiante;
    private javax.swing.JComboBox<String> cmbMaterias;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCurso;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtFechaEntrega;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
