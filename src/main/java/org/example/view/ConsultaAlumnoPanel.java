package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConsultaAlumnoPanel extends JFrame {

    public JTable tablaAlumnos;
    public JButton btnEditar, btnEliminar, btnVolver, btnCalificaciones;

    // Ventana de consulta/edición/baja de alumnos.
    // Muestra una tabla con los alumnos y botones para operar sobre ellos.

    public ConsultaAlumnoPanel() {
        setTitle("Consulta, edición y baja");
        setSize(820, 470);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout(12, 12));
        panelPrincipal.setBorder(new EmptyBorder(12, 12, 12, 12));
        panelPrincipal.setBackground(Color.WHITE);

        tablaAlumnos = new JTable();
        tablaAlumnos.setModel(new DefaultTableModel(
                new Object[] { "ID", "Nombre", "Email", "Carrera" }, 0));
        tablaAlumnos.setFillsViewportHeight(true);
        tablaAlumnos.setRowHeight(26);

        panelPrincipal.add(new JScrollPane(tablaAlumnos), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        panelBotones.setBackground(Color.WHITE);

        btnEditar = createStyledButton("Editar", new Color(255, 167, 38));
        btnEliminar = createStyledButton("Eliminar", new Color(220, 75, 60));
        btnCalificaciones = createStyledButton("Calificaciones", new Color(76, 145, 255));
        btnVolver = createStyledButton("Volver", new Color(158, 158, 158));

        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCalificaciones);
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