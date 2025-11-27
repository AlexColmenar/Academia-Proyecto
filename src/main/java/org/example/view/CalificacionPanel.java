package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CalificacionPanel extends JFrame {

    public JTable tablaCalificaciones;
    public JButton btnAgregar, btnEditar, btnEliminar, btnVolver;

    // Ventana que muestra las calificaciones de un alumno en una tabla
    // y botones para agregar/editar/eliminar.

    public CalificacionPanel() {
        setTitle("Calificaciones");
        setSize(820, 470);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout(12, 12));
        panelPrincipal.setBorder(new EmptyBorder(12, 12, 12, 12));
        panelPrincipal.setBackground(Color.WHITE);

        tablaCalificaciones = new JTable();
        tablaCalificaciones.setModel(new DefaultTableModel(
                new Object[] { "ID", "Materia", "Nota", "Fecha" }, 0));
        tablaCalificaciones.setFillsViewportHeight(true);
        tablaCalificaciones.setRowHeight(26);
        panelPrincipal.add(new JScrollPane(tablaCalificaciones), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        panelBotones.setBackground(Color.WHITE);

        btnAgregar = createStyledButton("Agregar", new Color(76, 175, 80));
        btnEditar = createStyledButton("Editar", new Color(255, 167, 38));
        btnEliminar = createStyledButton("Eliminar", new Color(220, 75, 60));
        btnVolver = createStyledButton("Volver", new Color(158, 158, 158));

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVolver);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        add(panelPrincipal);
    }

    private JButton createStyledButton(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        b.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                b.setBackground(bg.darker());
            }

            public void mouseExited(MouseEvent e) {
                b.setBackground(bg);
            }
        });
        return b;
    }
}
