package org.example.view;

import javax.swing.*;
import java.awt.*;

public class AltaAlumnoPanel extends JFrame {

    public JTextField txtNombre, txtEmail, txtCarrera;
    public JButton btnGuardar, btnCancelar;

    // Ventana para ingresar datos de un nuevo alumno.
    // Contiene campos de texto para nombre/email/carrera y botones
    // guardar/cancelar.

    public AltaAlumnoPanel() {
        setTitle("Alta de Alumno");
        setSize(450, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtNombre = new JTextField();
        panel.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtEmail = new JTextField();
        panel.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Carrera:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtCarrera = new JTextField();
        panel.add(txtCarrera, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        buttons.add(btnGuardar);
        buttons.add(btnCancelar);
        panel.add(buttons, gbc);

        add(panel);
    }
}