package org.example;

import org.example.controller.AlumnoController;
import org.example.view.MainMenu;

import javax.swing.*;

public class Main {
    // Entrada de la aplicación.
    // Crea la interfaz principal (`MainMenu`) y registra el `AlumnoController`.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Intentar aplicar un aspecto moderno (Nimbus si está disponible)
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception ignored) {
            }

            MainMenu menu = new MainMenu();
            // Intentar cargar un icono simple desde resources (si existe)
            try {
                java.net.URL iconUrl = Main.class.getResource("/images/academia_icon.png");
                if (iconUrl != null) {
                    menu.setIconImage(new ImageIcon(iconUrl).getImage());
                }
            } catch (Exception ignored) {
            }
            new AlumnoController(menu);
            menu.setVisible(true);
        });
    }
}
