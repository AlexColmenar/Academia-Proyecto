package org.example;

// Importamos el controlador que maneja la lógica de los alumnos
import org.example.controller.AlumnoController;
// Importamos la ventana principal de la aplicación
import org.example.view.MainMenu;

import javax.swing.*; // Librería Swing para interfaces gráficas

// Clase principal que inicia la aplicación
public class Main {

    // Método principal: punto de entrada del programa
    public static void main(String[] args) {

        // SwingUtilities.invokeLater asegura que la interfaz gráfica se cree en el hilo
        // correcto
        SwingUtilities.invokeLater(() -> {

            // Creamos la ventana principal (vista)
            MainMenu menu = new MainMenu();

            // Creamos el controlador y le pasamos la vista
            // El controlador conecta la vista (UI) con la persistencia (DAO)
            new AlumnoController(menu);

            // Hacemos visible la ventana principal
            menu.setVisible(true);
        });
    }
}
