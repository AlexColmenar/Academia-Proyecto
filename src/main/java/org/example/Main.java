package org.example;

import org.example.controller.AlumnoController;
import org.example.view.MainMenu;

import javax.swing.*;

public class Main {
    // Entrada de la aplicación.
    // Crea la interfaz principal (`MainMenu`) y registra el `AlumnoController`.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu menu = new MainMenu();
            new AlumnoController(menu);
            menu.setVisible(true);
        });
    }
}
