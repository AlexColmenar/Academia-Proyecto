package org.example.controller;

import org.example.dao.CalificacionDAO;
import org.example.dao.MateriaDAO;
import org.example.model.Alumno;
import org.example.model.Calificacion;
import org.example.model.Materia;
import org.example.view.CalificacionPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CalificacionController {

    private final CalificacionDAO dao;
    // Controlador de calificaciones. Se encarga de mostrar el panel de
    // calificaciones para un `Alumno`, y realizar las operaciones CRUD
    // usando `CalificacionDAO`.

    public CalificacionController() {
        this.dao = new CalificacionDAO();
    }

    public void openFor(Alumno a) {
        CalificacionPanel cp = new CalificacionPanel();
        MateriaDAO materiaDAO = new MateriaDAO();

        Runnable cargar = () -> {
            java.util.List<Calificacion> lista = dao.buscarPorAlumno(a.getId());
            DefaultTableModel model = new DefaultTableModel(new Object[] { "ID", "Materia", "Nota", "Fecha" }, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            for (Calificacion c : lista) {
                String nombreMat = c.getMateria() != null ? c.getMateria().getNombre() : "";
                model.addRow(new Object[] { c.getId(), nombreMat, c.getNota(), c.getFecha() });
            }

            cp.tablaCalificaciones.setModel(model);
        };

        cargar.run();
        cp.setVisible(true);

        cp.btnVolver.addActionListener(ev -> cp.dispose());

        cp.btnAgregar.addActionListener(ev -> {
            // Panel con JComboBox de materias y botón para crear nueva materia
            java.util.List<Materia> materias = materiaDAO.listar();
            JComboBox<String> combo = new JComboBox<>();
            for (Materia m : materias)
                combo.addItem(m.getNombre());
            JButton btnNueva = new JButton("Nueva...");

            JTextField tfNota = new JTextField();
            JTextField tfFecha = new JTextField(LocalDate.now().toString());

            JPanel top = new JPanel();
            top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
            top.add(combo);
            top.add(Box.createHorizontalStrut(6));
            top.add(btnNueva);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Materia:"));
            panel.add(top);
            panel.add(new JLabel("Nota:"));
            panel.add(tfNota);
            panel.add(new JLabel("Fecha (YYYY-MM-DD):"));
            panel.add(tfFecha);

            btnNueva.addActionListener(ev2 -> {
                String nombre = JOptionPane.showInputDialog(cp, "Nombre de la nueva materia:");
                if (nombre != null && !nombre.trim().isEmpty()) {
                    Materia exist = materiaDAO.buscarPorNombre(nombre.trim());
                    if (exist == null) {
                        materiaDAO.insertar(new Materia(nombre.trim()));
                        combo.addItem(nombre.trim());
                        combo.setSelectedItem(nombre.trim());
                    } else {
                        JOptionPane.showMessageDialog(cp, "La materia ya existe.");
                    }
                }
            });

            int res = JOptionPane.showConfirmDialog(cp, panel, "Agregar calificación", JOptionPane.OK_CANCEL_OPTION);
            if (res == JOptionPane.OK_OPTION) {
                try {
                    String mat = combo.getSelectedItem() != null ? combo.getSelectedItem().toString() : "";
                    Double nota = Double.parseDouble(tfNota.getText().trim());
                    LocalDate fecha = LocalDate.parse(tfFecha.getText().trim());
                    Materia m = materiaDAO.buscarPorNombre(mat);
                    if (m == null) {
                        m = new Materia(mat);
                        materiaDAO.insertar(m);
                    }
                    Calificacion cal = new Calificacion(m, nota, fecha, a);
                    dao.insertar(cal);
                    cargar.run();
                } catch (DateTimeParseException dtp) {
                    JOptionPane.showMessageDialog(cp, "Fecha inválida: use YYYY-MM-DD");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(cp, "Error: " + ex.getMessage());
                }
            }
        });

        cp.btnEliminar.addActionListener(ev -> {
            int row = cp.tablaCalificaciones.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(cp, "Selecciona un registro");
                return;
            }

            Long id = (Long) cp.tablaCalificaciones.getValueAt(row, 0);
            int r = JOptionPane.showConfirmDialog(cp, "Eliminar calificación ID " + id + "?", "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                dao.eliminar(id);
                cargar.run();
            }
        });

        cp.btnEditar.addActionListener(ev -> {
            int row = cp.tablaCalificaciones.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(cp, "Selecciona un registro");
                return;
            }

            Long id = (Long) cp.tablaCalificaciones.getValueAt(row, 0);
            Calificacion cal = dao.buscarPorId(id);
            if (cal == null) {
                JOptionPane.showMessageDialog(cp, "Registro no encontrado");
                return;
            }

            java.util.List<Materia> materias = materiaDAO.listar();
            JComboBox<String> combo = new JComboBox<>();
            for (Materia m : materias)
                combo.addItem(m.getNombre());
            if (cal.getMateria() != null)
                combo.setSelectedItem(cal.getMateria().getNombre());

            JTextField tfNota = new JTextField(cal.getNota() != null ? cal.getNota().toString() : "");
            JTextField tfFecha = new JTextField(cal.getFecha() != null ? cal.getFecha().toString() : "");

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Materia:"));
            panel.add(combo);
            panel.add(new JLabel("Nota:"));
            panel.add(tfNota);
            panel.add(new JLabel("Fecha (YYYY-MM-DD):"));
            panel.add(tfFecha);

            int res = JOptionPane.showConfirmDialog(cp, panel, "Editar calificación ID " + id,
                    JOptionPane.OK_CANCEL_OPTION);
            if (res == JOptionPane.OK_OPTION) {
                try {
                    String newMat = combo.getSelectedItem() != null ? combo.getSelectedItem().toString() : "";
                    Materia m2 = materiaDAO.buscarPorNombre(newMat);
                    if (m2 == null) {
                        m2 = new Materia(newMat);
                        materiaDAO.insertar(m2);
                    }
                    cal.setMateria(m2);
                    cal.setNota(Double.parseDouble(tfNota.getText().trim()));
                    cal.setFecha(LocalDate.parse(tfFecha.getText().trim()));
                    dao.actualizar(cal);
                    cargar.run();
                    JOptionPane.showMessageDialog(cp, "Actualizado");
                } catch (DateTimeParseException dtp) {
                    JOptionPane.showMessageDialog(cp, "Fecha inválida: use YYYY-MM-DD");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(cp, "Error: " + ex.getMessage());
                }
            }
        });
    }
}
