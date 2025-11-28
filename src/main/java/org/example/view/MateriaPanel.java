package org.example.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MateriaPanel extends JFrame {

    public JList<String> listaMaterias;
    public JTable tablaAlumnos;
    public JButton btnAgregarMateria;
    public JButton btnEliminarMateria;
    public JButton btnVolver;

    public MateriaPanel() {
        setTitle("Gestión de Materias");
        setSize(800, 450);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel left = new JPanel(new BorderLayout(6, 6));
        left.setPreferredSize(new Dimension(220, 0));
        left.add(new JLabel("Materias"), BorderLayout.NORTH);
        listaMaterias = new JList<>(new DefaultListModel<>());
        JScrollPane sp = new JScrollPane(listaMaterias);
        left.add(sp, BorderLayout.CENTER);

        JPanel leftButtons = new JPanel();
        leftButtons.setLayout(new BoxLayout(leftButtons, BoxLayout.X_AXIS));
        btnAgregarMateria = new JButton("Agregar");
        btnEliminarMateria = new JButton("Eliminar");
        leftButtons.add(btnAgregarMateria);
        leftButtons.add(Box.createHorizontalStrut(8));
        leftButtons.add(btnEliminarMateria);
        left.add(leftButtons, BorderLayout.SOUTH);

        // Tabla alumnos con nota
        JPanel right = new JPanel(new BorderLayout(6, 6));
        right.add(new JLabel("Alumnos y notas"), BorderLayout.NORTH);
        tablaAlumnos = new JTable(new DefaultTableModel(new Object[] { "ID", "Alumno", "Nota", "Fecha" }, 0));
        right.add(new JScrollPane(tablaAlumnos), BorderLayout.CENTER);

        btnVolver = new JButton("Cerrar");
        right.add(btnVolver, BorderLayout.SOUTH);

        root.add(left, BorderLayout.WEST);
        root.add(right, BorderLayout.CENTER);

        add(root);
    }
}
