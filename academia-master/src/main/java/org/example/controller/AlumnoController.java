package org.example.controller;

// Importamos las clases necesarias de nuestro proyecto
import org.example.dao.AlumnoDAO; // DAO para manejar operaciones con la base de datos
import org.example.model.Alumno; // Clase modelo de Alumno
import org.example.view.AltaAlumnoPanel; // Panel de alta de alumnos
import org.example.view.ConsultaAlumnoPanel;// Panel de consulta de alumnos
import org.example.view.MainMenu; // Menú principal de la app

// Librerías Swing y utilidades
import javax.swing.*; // Para ventanas, botones, cuadros de diálogo, etc.
import javax.swing.table.DefaultTableModel; // Para controlar los datos de la tabla
import java.awt.event.WindowAdapter; // Para eventos de ventanas
import java.awt.event.WindowEvent;
import java.util.List; // Para manejar listas de objetos Alumno

// Clase controlador que gestiona la interacción entre la vista y el modelo
public class AlumnoController {

    // --- Atributos ---
    private final AlumnoDAO dao; // Objeto DAO para acceder a la base de datos
    private final MainMenu menu; // Ventana principal
    private AltaAlumnoPanel altaPanel; // Ventana de alta
    private ConsultaAlumnoPanel consultaPanel; // Ventana de consulta

    // --- Constructor ---
    public AlumnoController(MainMenu menu) {
        this.menu = menu; // Guardamos la referencia del menú principal
        this.dao = new AlumnoDAO(); // Creamos el DAO para manejar los alumnos
        initController(); // Inicializamos los eventos de los botones
    }

    // --- Inicializar los listeners de los botones del menú principal ---
    private void initController() {
        menu.btnAlta.addActionListener(e -> openAlta()); // Abrir ventana de alta
        menu.btnConsulta.addActionListener(e -> openConsulta()); // Abrir ventana de consulta
        menu.btnSalir.addActionListener(e -> System.exit(0)); // Salir de la aplicación
    }

    // --- Abrir ventana de alta de alumno ---
    private void openAlta() {
        altaPanel = new AltaAlumnoPanel(); // Creamos la ventana de alta
        altaPanel.setVisible(true); // La mostramos

        // --- Guardar un nuevo alumno ---
        altaPanel.btnGuardar.addActionListener(ev -> {
            try {
                // Leemos los datos ingresados por el usuario
                String nombre = altaPanel.txtNombre.getText().trim();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(altaPanel, "El nombre es obligatorio");
                    return;
                }
                String email = altaPanel.txtEmail.getText().trim();
                String carrera = altaPanel.txtCarrera.getText().trim();

                // Creamos un objeto Alumno y lo guardamos en la base de datos
                Alumno a = new Alumno(nombre, email, carrera);
                dao.insertar(a);

                // Mensaje de éxito y cerramos la ventana
                JOptionPane.showMessageDialog(altaPanel, "Alumno guardado");
                altaPanel.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(altaPanel, "Error: " + ex.getMessage());
                ex.printStackTrace(); // Para depuración
            }
        });

        // --- Cancelar alta ---
        altaPanel.btnCancelar.addActionListener(ev -> altaPanel.dispose());

        // Listener para eventos de ventana (no hace nada en este caso)
        altaPanel.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                /* nada */ }
        });
    }

    // --- Abrir ventana de consulta, edición y baja ---
    private void openConsulta() {
        consultaPanel = new ConsultaAlumnoPanel(); // Creamos la ventana de consulta
        cargarTabla(); // Cargamos los datos de la base de datos
        consultaPanel.setVisible(true); // Mostramos la ventana

        // --- Botón Volver ---
        consultaPanel.btnVolver.addActionListener(e -> consultaPanel.dispose());

        // --- Botón Eliminar ---
        consultaPanel.btnEliminar.addActionListener(e -> {
            int row = consultaPanel.tablaAlumnos.getSelectedRow(); // Fila seleccionada
            if (row == -1) {
                JOptionPane.showMessageDialog(consultaPanel, "Selecciona un registro");
                return;
            }

            Long id = (Long) consultaPanel.tablaAlumnos.getValueAt(row, 0); // Obtener id
            int r = JOptionPane.showConfirmDialog(consultaPanel, "¿Eliminar alumno ID " + id + "?", "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                dao.eliminar(id); // Eliminar del DAO
                cargarTabla(); // Recargar la tabla
            }
        });

        // --- Botón Editar ---
        consultaPanel.btnEditar.addActionListener(e -> {
            int row = consultaPanel.tablaAlumnos.getSelectedRow(); // Fila seleccionada
            if (row == -1) {
                JOptionPane.showMessageDialog(consultaPanel, "Selecciona un registro");
                return;
            }

            Long id = (Long) consultaPanel.tablaAlumnos.getValueAt(row, 0); // Obtener id
            Alumno a = dao.buscarPorId(id); // Buscar el alumno en la base de datos
            if (a == null) {
                JOptionPane.showMessageDialog(consultaPanel, "Registro no encontrado");
                return;
            }

            // --- Crear panel para edición de campos ---
            JTextField tfNombre = new JTextField(a.getNombre());
            JTextField tfEmail = new JTextField(a.getEmail());
            JTextField tfCarrera = new JTextField(a.getCarrera());

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Nombre:"));
            panel.add(tfNombre);
            panel.add(new JLabel("Email:"));
            panel.add(tfEmail);
            panel.add(new JLabel("Carrera:"));
            panel.add(tfCarrera);

            // Mostrar cuadro de diálogo de edición
            int res = JOptionPane.showConfirmDialog(consultaPanel, panel, "Editar Alumno ID " + id,
                    JOptionPane.OK_CANCEL_OPTION);
            if (res == JOptionPane.OK_OPTION) {
                try {
                    // Guardar cambios en el objeto Alumno
                    a.setNombre(tfNombre.getText().trim());
                    a.setEmail(tfEmail.getText().trim());
                    a.setCarrera(tfCarrera.getText().trim());
                    dao.actualizar(a); // Actualizar en la base de datos
                    cargarTabla(); // Recargar la tabla
                    JOptionPane.showMessageDialog(consultaPanel, "Actualizado");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(consultaPanel, "Error: " + ex.getMessage());
                }
            }
        });
    }

    // --- Método para cargar los datos de los alumnos en la tabla ---
    private void cargarTabla() {
        List<Alumno> lista = dao.listar(); // Obtener lista del DAO

        // Creamos un modelo de tabla donde las celdas no se pueden editar
        DefaultTableModel model = new DefaultTableModel(new Object[] { "ID", "Nombre", "Email", "Carrera" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Agregar cada alumno a la tabla
        for (Alumno a : lista) {
            model.addRow(new Object[] { a.getId(), a.getNombre(), a.getEmail(), a.getCarrera() });
        }

        consultaPanel.tablaAlumnos.setModel(model); // Aplicar el modelo a la tabla
    }
}
