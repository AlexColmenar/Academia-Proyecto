package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CalificacionPanel extends JFrame {

    public JTable tablaCalificaciones;
    public JButton btnAgregar, btnEditar, btnEliminar, btnVolver;

    // Ventana que muestra las calificaciones de un alumno en una tabla
    // y botones para agregar/editar/eliminar.

    public CalificacionPanel() {
        setTitle("Calificaciones");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tablaCalificaciones = new JTable();
        tablaCalificaciones.setModel(new DefaultTableModel(
                new Object[] { "ID", "Materia", "Nota", "Fecha" }, 0));
        panelPrincipal.add(new JScrollPane(tablaCalificaciones), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10);

        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnVolver = new JButton("Volver");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelBotones.add(btnAgregar, gbc);

        gbc.gridx = 1;
        panelBotones.add(btnEditar, gbc);

        gbc.gridx = 2;
        panelBotones.add(btnEliminar, gbc);

        gbc.gridx = 3;
        panelBotones.add(btnVolver, gbc);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        add(panelPrincipal);
    }
}
