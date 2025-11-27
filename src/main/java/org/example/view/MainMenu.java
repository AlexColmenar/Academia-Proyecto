package org.example.view;

import javax.swing.*;
import java.awt.*;

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

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("SISTEMA DE GESTIÓN ACADÉMICA", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        root.add(lblTitle, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(1, 2, 20, 20));

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnAlta = new JButton("Alta de Alumnos");
        btnAlta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        btnConsulta = new JButton("Consulta, edición y baja");
        btnConsulta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        btnSalir = new JButton("Salir");
        btnSalir.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        left.add(btnAlta);
        left.add(Box.createVerticalStrut(10));
        left.add(btnConsulta);
        left.add(Box.createVerticalStrut(10));
        left.add(btnSalir);

        JPanel right = new JPanel(new BorderLayout());
        JLabel logo = new JLabel();
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        java.net.URL imgURL = getClass().getResource("/images/logoAcademia.png");
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image scaledImage = icon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(scaledImage));
        } else {
            System.out.println("No se encontró la imagen en /images/logoAcademia.png");
        }

        right.add(logo, BorderLayout.CENTER);

        center.add(left);
        center.add(right);

        root.add(center, BorderLayout.CENTER);

        add(root);
    }
}
