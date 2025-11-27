package org.example.controller;

import org.example.dao.CalificacionDAO;
import org.example.model.Alumno;
import org.example.model.Calificacion;
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

        Runnable cargar = () -> {
            java.util.List<Calificacion> lista = dao.buscarPorAlumno(a.getId());
            DefaultTableModel model = new DefaultTableModel(new Object[] { "ID", "Materia", "Nota", "Fecha" }, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            for (Calificacion c : lista) {
                model.addRow(new Object[] { c.getId(), c.getMateria(), c.getNota(), c.getFecha() });
            }

            cp.tablaCalificaciones.setModel(model);
        };

        cargar.run();
        cp.setVisible(true);

        cp.btnVolver.addActionListener(ev -> cp.dispose());

        cp.btnAgregar.addActionListener(ev -> {
            JTextField tfMateria = new JTextField();
            JTextField tfNota = new JTextField();
            JTextField tfFecha = new JTextField(LocalDate.now().toString());

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Materia:"));
            panel.add(tfMateria);
            panel.add(new JLabel("Nota:"));
            panel.add(tfNota);
            panel.add(new JLabel("Fecha (YYYY-MM-DD):"));
            panel.add(tfFecha);

            int res = JOptionPane.showConfirmDialog(cp, panel, "Agregar calificación", JOptionPane.OK_CANCEL_OPTION);
            if (res == JOptionPane.OK_OPTION) {
                try {
                    String mat = tfMateria.getText().trim();
                    Double nota = Double.parseDouble(tfNota.getText().trim());
                    LocalDate fecha = LocalDate.parse(tfFecha.getText().trim());
                    Calificacion cal = new Calificacion(mat, nota, fecha, a);
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

            JTextField tfMateria = new JTextField(cal.getMateria());
            JTextField tfNota = new JTextField(cal.getNota() != null ? cal.getNota().toString() : "");
            JTextField tfFecha = new JTextField(cal.getFecha() != null ? cal.getFecha().toString() : "");

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Materia:"));
            panel.add(tfMateria);
            panel.add(new JLabel("Nota:"));
            panel.add(tfNota);
            panel.add(new JLabel("Fecha (YYYY-MM-DD):"));
            panel.add(tfFecha);

            int res = JOptionPane.showConfirmDialog(cp, panel, "Editar calificación ID " + id,
                    JOptionPane.OK_CANCEL_OPTION);
            if (res == JOptionPane.OK_OPTION) {
                try {
                    cal.setMateria(tfMateria.getText().trim());
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
