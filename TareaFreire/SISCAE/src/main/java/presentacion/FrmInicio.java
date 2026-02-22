package presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import modelo.Usuario;

public class FrmInicio extends JFrame {

    private final Usuario usuarioActual;

    // Contenedor central
    private JDesktopPane desktop;

    // Barra de menú
    private JMenuBar menuBar;

    // Menús
    private JMenu menuEstudiantes;
    private JMenu menuDocentes;
    private JMenu menuMaterias;
    private JMenu menuTareas;
    private JMenu menuNotas;

    // Items de menú
    private JMenuItem itemEstudiantes;
    private JMenuItem itemDocentes;
    private JMenuItem itemMaterias;
    private JMenuItem itemTareas;
    private JMenuItem itemNotas;

    public FrmInicio(Usuario u) {
        this.usuarioActual = u;
        initComponents();
        setLocationRelativeTo(null);
        aplicarRol();
        mostrarPantallaInicio();
    }

    // ================================
    // INICIALIZAR COMPONENTES
    // ================================
    private void initComponents() {

        // Desktop
        desktop = new JDesktopPane();

        // Barra de menú
        menuBar = new JMenuBar();

        // Menús
        menuEstudiantes = new JMenu("ESTUDIANTES");
        menuDocentes    = new JMenu("DOCENTES");
        menuMaterias    = new JMenu("MATERIAS");
        menuTareas      = new JMenu("TAREAS");
        menuNotas       = new JMenu("NOTAS");

        // Items
        itemEstudiantes = new JMenuItem("Gestionar Estudiantes");
        itemDocentes    = new JMenuItem("Gestionar Docentes");
        itemMaterias    = new JMenuItem("Gestionar Materias");
        itemTareas      = new JMenuItem("Gestionar Tareas");
        itemNotas       = new JMenuItem("Gestionar Notas");

        // Eventos
        itemEstudiantes.addActionListener(e -> abrirFormulario(new FrmEstudiantes()));
        itemDocentes.addActionListener(e -> abrirFormulario(new FrmDocente()));
        itemMaterias.addActionListener(e -> abrirFormulario(new FrmMateria()));
        itemTareas.addActionListener(e -> abrirFormulario(new FrmTareas()));
        itemNotas.addActionListener(e -> abrirFormulario(new FrmNotas(usuarioActual.getRol())));

        // Agregar items a menús
        menuEstudiantes.add(itemEstudiantes);
        menuDocentes.add(itemDocentes);
        menuMaterias.add(itemMaterias);
        menuTareas.add(itemTareas);
        menuNotas.add(itemNotas);

        // Agregar menús a la barra
        menuBar.add(menuEstudiantes);
        menuBar.add(menuDocentes);
        menuBar.add(menuMaterias);
        menuBar.add(menuTareas);
        menuBar.add(menuNotas);

        setJMenuBar(menuBar);

        // JFrame
        setLayout(new BorderLayout());
        add(desktop, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1500, 680));
        pack();
    }

    // ================================
    // CONTROL DE ROLES
    // ================================
    private void aplicarRol() {
        String rol = usuarioActual.getRol();

        switch (rol) {
            case "ADMIN" -> {
                menuEstudiantes.setVisible(true);
                menuDocentes.setVisible(true);
                menuMaterias.setVisible(true);
                menuTareas.setVisible(true);
                menuNotas.setVisible(true);
            }
            case "DOCENTE" -> {
                menuEstudiantes.setVisible(false);
                menuDocentes.setVisible(false);
                menuMaterias.setVisible(false);
                menuTareas.setVisible(true);
                menuNotas.setVisible(true);
            }
            case "ESTUDIANTE" -> {
                menuEstudiantes.setVisible(false);
                menuDocentes.setVisible(false);
                menuMaterias.setVisible(false);
                menuTareas.setVisible(false);
                menuNotas.setVisible(true);
            }
            default ->
                JOptionPane.showMessageDialog(this, "Rol no reconocido");
        }
    }

    // =======================================
    // ABRIR FORMULARIOS INTERNOS
    // =======================================
    private void abrirFormulario(JInternalFrame frame) {

        desktop.removeAll();
        desktop.repaint();

        frame.setClosable(true);
        frame.setIconifiable(true);
        frame.setMaximizable(true);
        frame.setResizable(true);

        desktop.add(frame);
        frame.setVisible(true);

        frame.setSize(desktop.getWidth(), desktop.getHeight());
        frame.setLocation(0, 0);
    }

    // ================================
    // PANTALLA DE BIENVENIDA
    // ================================
    private void mostrarPantallaInicio() {

        JInternalFrame inicio = new JInternalFrame(
                "Inicio", false, false, false, false
        );
        inicio.setSize(500, 200);

        JLabel lbl = new JLabel(
                "Bienvenido/a " + usuarioActual.getUsuario()
                        + " | Rol: " + usuarioActual.getRol(),
                SwingConstants.CENTER
        );
        lbl.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 18));

        inicio.add(lbl, BorderLayout.CENTER);
        inicio.setVisible(true);

        desktop.add(inicio);
        inicio.setLocation(
                (desktop.getWidth() - inicio.getWidth()) / 2,
                (desktop.getHeight() - inicio.getHeight()) / 2
        );
    }
}
