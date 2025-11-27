package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ConsultaAlumnoPanel extends JFrame {

    public JTable tablaAlumnos;
    public JButton btnEditar, btnEliminar, btnVolver, btnCalificaciones;

    // Ventana de consulta/edición/baja de alumnos.
    // Muestra una tabla con los alumnos y botones para operar sobre ellos.

    public ConsultaAlumnoPanel() {
        setTitle("Consulta, edición y baja");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tablaAlumnos = new JTable();
        tablaAlumnos.setModel(new DefaultTableModel(
                new Object[] { "ID", "Nombre", "Email", "Carrera" }, 0));

        panelPrincipal.add(new JScrollPane(tablaAlumnos), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10);

        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnCalificaciones = new JButton("Calificaciones");
        btnVolver = new JButton("Volver");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelBotones.add(btnEditar, gbc);

        gbc.gridx = 1;
        panelBotones.add(btnEliminar, gbc);

        gbc.gridx = 2;
        panelBotones.add(btnCalificaciones, gbc);

        gbc.gridx = 3;
        panelBotones.add(btnVolver, gbc);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
    }
}