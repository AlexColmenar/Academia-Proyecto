package org.example.controller;

import org.example.dao.AlumnoDAO;
import org.example.model.Alumno;
import org.example.view.AltaAlumnoPanel;
import org.example.view.ConsultaAlumnoPanel;
import org.example.view.MainMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class AlumnoController {

    // Controlador responsable de la lógica de alumnos
    // abre los paneles de alta y consulta
    // delega operaciones CRUD a `AlumnoDAO`
    // coordina con `CalificacionController` para las calificaciones

    private final AlumnoDAO dao;
    private final MainMenu menu;
    private final CalificacionController calController;
    private AltaAlumnoPanel altaPanel;
    private ConsultaAlumnoPanel consultaPanel;

    public AlumnoController(MainMenu menu) {
        this.menu = menu;
        this.dao = new AlumnoDAO();
        this.calController = new CalificacionController();
        initController();
    }

    // Inicializa listeners del menú principal

    private void initController() {
        menu.btnAlta.addActionListener(e -> openAlta());
        menu.btnConsulta.addActionListener(e -> openConsulta());
        menu.btnSalir.addActionListener(e -> System.exit(0));
    }

    private void openAlta() {
        // Muestra un formulario para crear un nuevo Alumno y lo guarda vía DAO
        altaPanel = new AltaAlumnoPanel();
        altaPanel.setVisible(true);

        altaPanel.btnGuardar.addActionListener(ev -> {
            try {
                String nombre = altaPanel.txtNombre.getText().trim();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(altaPanel, "El nombre es obligatorio");
                    return;
                }
                String email = altaPanel.txtEmail.getText().trim();
                String carrera = altaPanel.txtCarrera.getText().trim();

                Alumno a = new Alumno(nombre, email, carrera);
                dao.insertar(a);

                JOptionPane.showMessageDialog(altaPanel, "Alumno guardado");
                altaPanel.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(altaPanel, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        altaPanel.btnCancelar.addActionListener(ev -> altaPanel.dispose());

        altaPanel.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
            }
        });
    }

    private void openConsulta() {
        // Muestra listado de alumnos y ofrece editar/eliminar/ver calificaciones
        consultaPanel = new ConsultaAlumnoPanel();
        cargarTabla();
        consultaPanel.setVisible(true);

        consultaPanel.btnVolver.addActionListener(e -> consultaPanel.dispose());

        consultaPanel.btnEliminar.addActionListener(e -> {
            int row = consultaPanel.tablaAlumnos.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(consultaPanel, "Selecciona un registro");
                return;
            }

            Long id = (Long) consultaPanel.tablaAlumnos.getValueAt(row, 0);
            int r = JOptionPane.showConfirmDialog(consultaPanel, "¿Eliminar alumno ID " + id + "?", "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                dao.eliminar(id);
                cargarTabla();
            }
        });

        consultaPanel.btnEditar.addActionListener(e -> {
            int row = consultaPanel.tablaAlumnos.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(consultaPanel, "Selecciona un registro");
                return;
            }

            Long id = (Long) consultaPanel.tablaAlumnos.getValueAt(row, 0);
            Alumno a = dao.buscarPorId(id);
            if (a == null) {
                JOptionPane.showMessageDialog(consultaPanel, "Registro no encontrado");
                return;
            }

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

            int res = JOptionPane.showConfirmDialog(consultaPanel, panel, "Editar Alumno ID " + id,
                    JOptionPane.OK_CANCEL_OPTION);
            if (res == JOptionPane.OK_OPTION) {
                try {
                    a.setNombre(tfNombre.getText().trim());
                    a.setEmail(tfEmail.getText().trim());
                    a.setCarrera(tfCarrera.getText().trim());
                    dao.actualizar(a);
                    cargarTabla();
                    JOptionPane.showMessageDialog(consultaPanel, "Actualizado");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(consultaPanel, "Error: " + ex.getMessage());
                }
            }
        });

        consultaPanel.btnCalificaciones.addActionListener(e -> {
            int row = consultaPanel.tablaAlumnos.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(consultaPanel, "Selecciona un registro");
                return;
            }

            Long id = (Long) consultaPanel.tablaAlumnos.getValueAt(row, 0);
            Alumno a = dao.buscarPorId(id);
            if (a == null) {
                JOptionPane.showMessageDialog(consultaPanel, "Registro no encontrado");
                return;
            }

            calController.openFor(a);
        });
    }

    private void cargarTabla() {
        // Recupera la lista desde DAO y monta el modelo de la tabla (solo lectura)
        List<Alumno> lista = dao.listar();

        DefaultTableModel model = new DefaultTableModel(new Object[] { "ID", "Nombre", "Email", "Carrera" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Alumno a : lista) {
            model.addRow(new Object[] { a.getId(), a.getNombre(), a.getEmail(), a.getCarrera() });
        }

        consultaPanel.tablaAlumnos.setModel(model);
    }

}
