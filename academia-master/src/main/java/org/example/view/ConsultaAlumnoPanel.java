package org.example.view;

// Importamos librerías para crear interfaces gráficas y tablas
import javax.swing.*; // JFrame, JPanel, JButton, JLabel, JTable, JScrollPane, etc.
import javax.swing.table.DefaultTableModel; // Para manejar los datos de la tabla
import java.awt.*; // Para layouts, alineaciones y tamaños

// Clase que representa la ventana de consulta, edición y baja de alumnos
public class ConsultaAlumnoPanel extends JFrame {

    // Tabla pública para mostrar los alumnos
    public JTable tablaAlumnos;

    // Botones públicos para acciones de edición, eliminación y volver
    public JButton btnEditar, btnEliminar, btnVolver;

    // Constructor de la ventana
    public ConsultaAlumnoPanel() {
        setTitle("Consulta, edición y baja"); // Título de la ventana
        setSize(800, 450); // Tamaño de la ventana (ancho x alto)
        setLocationRelativeTo(null); // Centrar ventana en la pantalla
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar solo esta ventana

        // --- Panel principal con BorderLayout ---
        // BorderLayout divide el panel en: NORTH, SOUTH, EAST, WEST, CENTER
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Margen interior de 10px alrededor de todo el panel

        // --- Tabla de alumnos ---
        tablaAlumnos = new JTable(); // Creamos la tabla
        // Definimos las columnas y que inicialmente esté vacía (0 filas)
        tablaAlumnos.setModel(new DefaultTableModel(
                new Object[] { "ID", "Nombre", "Email", "Carrera" }, 0));

        // Añadimos la tabla al panel con scroll (para poder desplazarse si hay muchas
        // filas)
        panelPrincipal.add(new JScrollPane(tablaAlumnos), BorderLayout.CENTER);

        // --- Panel inferior para los botones ---
        JPanel panelBotones = new JPanel(new GridBagLayout()); // Layout flexible para alinear los botones
        GridBagConstraints gbc = new GridBagConstraints(); // Objeto para controlar la posición
        gbc.insets = new Insets(0, 10, 0, 10); // Espacio entre botones (izquierda y derecha)

        // Creamos los botones
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnVolver = new JButton("Volver");

        // --- Colocamos los botones en fila ---
        gbc.gridx = 0;
        gbc.gridy = 0; // Primer botón, columna 0, fila 0
        panelBotones.add(btnEditar, gbc); // Añadimos el botón "Editar"

        gbc.gridx = 1; // Segundo botón, columna 1
        panelBotones.add(btnEliminar, gbc); // Añadimos el botón "Eliminar"

        gbc.gridx = 2; // Tercer botón, columna 2
        panelBotones.add(btnVolver, gbc); // Añadimos el botón "Volver"

        // Añadimos el panel de botones al panel principal, en la parte inferior (SOUTH)
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Finalmente añadimos el panel principal a la ventana
        add(panelPrincipal);
    }
}