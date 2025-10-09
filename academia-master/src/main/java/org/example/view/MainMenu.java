package org.example.view;

// Importación de librerías necesarias
import javax.swing.*; // Componentes gráficos de Swing (JFrame, JPanel, JButton, etc.)
import java.awt.*; // Clases de diseño (Layout) y fuentes

// Clase que representa la ventana principal de la aplicación
public class MainMenu extends JFrame {

    // Declaración de botones públicos (para poder usarlos en controladores)
    public JButton btnAlta;
    public JButton btnConsulta;
    public JButton btnSalir;

    // Constructor: define cómo se construye la ventana
    public MainMenu() {

        // --- Configuración básica de la ventana ---
        setTitle("Sistema de Gestión Académica"); // Título de la ventana
        setSize(800, 450); // Tamaño de la ventana (ancho x alto)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Acción al cerrar: salir de la app
        setLocationRelativeTo(null); // Centrar ventana en la pantalla

        // --- Panel raíz con BorderLayout ---
        JPanel root = new JPanel(new BorderLayout(10, 10));
        // 10,10 → márgenes horizontales y verticales entre los elementos
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Borde vacío de 20px alrededor de todo el contenido (margen interior)

        // --- Etiqueta superior (título) ---
        JLabel lblTitle = new JLabel("SISTEMA DE GESTIÓN ACADÉMICA", SwingConstants.CENTER);
        // SwingConstants.CENTER → centra el texto horizontalmente
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28)); // Fuente Arial, negrita, tamaño 28
        root.add(lblTitle, BorderLayout.NORTH); // Se coloca en la parte superior

        // --- Panel central con dos columnas ---
        JPanel center = new JPanel(new GridLayout(1, 2, 20, 20));
        // GridLayout con 1 fila, 2 columnas y separación de 20px

        // --- Panel izquierdo (botones) ---
        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        // BoxLayout.Y_AXIS → apila los componentes verticalmente
        left.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botón 1: Alta de Alumnos
        btnAlta = new JButton("Alta de Alumnos");
        btnAlta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        // Se asegura de que el botón se expanda a lo ancho, pero mantenga altura de
        // 40px

        // Botón 2: Consulta, edición y baja
        btnConsulta = new JButton("Consulta, edición y baja");
        btnConsulta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        // Botón 3: Salir
        btnSalir = new JButton("Salir");
        btnSalir.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        // Se añaden los botones al panel izquierdo con espacios entre ellos
        left.add(btnAlta);
        left.add(Box.createVerticalStrut(10)); // Espacio vertical de 10px
        left.add(btnConsulta);
        left.add(Box.createVerticalStrut(10));
        left.add(btnSalir);

        // --- Panel derecho (espacio para logo) ---
        JPanel right = new JPanel(new BorderLayout());
        JLabel logo = new JLabel();
        logo.setHorizontalAlignment(SwingConstants.CENTER); // Centrado
        java.net.URL imgURL = getClass().getResource("/images/logoAcademia.png");
        if (imgURL != null) {
            // Cargar la imagen
            ImageIcon icon = new ImageIcon(imgURL);

            // Escalar la imagen al tamaño deseado (por ejemplo 200x200)
            Image scaledImage = icon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);

            // Poner la imagen escalada en el JLabel
            logo.setIcon(new ImageIcon(scaledImage));
        } else {
            System.out.println("No se encontró la imagen en /images/logoAcademia.png");
        }

        right.add(logo, BorderLayout.CENTER);

        // --- Se añaden los dos paneles al centro ---
        center.add(left); // Panel de botones
        center.add(right); // Panel de logo

        // --- Se añade el panel central al panel raíz ---
        root.add(center, BorderLayout.CENTER);

        // --- Se agrega el panel raíz a la ventana ---
        add(root);
    }
}
