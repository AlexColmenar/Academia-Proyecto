package org.example.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {

    public JButton btnAlta;
    public JButton btnConsulta;
    public JButton btnSalir;

    // Ventana principal del sistema con accesos a las operaciones
    // (alta, consulta/edición/baja) y un área para un logo.

    public MainMenu() {
        setTitle("Sistema de Gestión Académica");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(new EmptyBorder(18, 18, 18, 18));
        root.setBackground(Color.WHITE);

        // Cabecera
        JLabel lblTitle = new JLabel("ACADEMIA", SwingConstants.LEFT);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblTitle.setForeground(new Color(30, 60, 120));
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.add(lblTitle, BorderLayout.WEST);
        root.add(header, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(1, 2, 20, 20));
        center.setBackground(Color.WHITE);

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBorder(new EmptyBorder(10, 10, 10, 10));
        left.setBackground(Color.WHITE);

        btnAlta = createStyledButton("Alta de Alumnos", new Color(76, 145, 255));
        btnConsulta = createStyledButton("Consulta, edición y baja", new Color(36, 163, 154));
        btnSalir = createStyledButton("Salir", new Color(220, 75, 60));

        left.add(btnAlta);
        left.add(Box.createVerticalStrut(12));
        left.add(btnConsulta);
        left.add(Box.createVerticalStrut(12));
        left.add(btnSalir);

        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(Color.WHITE);

        // Cargar la foto de la academia en panel con bordes redondeados
        ImagenVista panelImagen = new ImagenVista("/images/academia.png", 420, 340, 18);
        right.add(panelImagen, BorderLayout.CENTER);

        center.add(left);
        center.add(right);

        root.add(center, BorderLayout.CENTER);

        add(root);
    }

    private JButton createStyledButton(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        b.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(220, 44));
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
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
