package org.example.controller;

import org.example.dao.CalificacionDAO;
import org.example.dao.MateriaDAO;
import org.example.model.Calificacion;
import org.example.model.Materia;
import org.example.view.MateriaPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MateriaController {

    private final MateriaDAO materiaDAO = new MateriaDAO();
    private final CalificacionDAO calDAO = new CalificacionDAO();

    public void open() {
        MateriaPanel mp = new MateriaPanel();

        Runnable cargarMaterias = () -> {
            DefaultListModel<String> lm = new DefaultListModel<>();
            List<Materia> lista = materiaDAO.listar();
            for (Materia m : lista) {
                lm.addElement(m.getId() + ": " + m.getNombre());
            }
            mp.listaMaterias.setModel(lm);
        };

        cargarMaterias.run();

        mp.listaMaterias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String sel = mp.listaMaterias.getSelectedValue();
                if (sel == null)
                    return;
                Long id = Long.parseLong(sel.split(":")[0]);
                cargarAlumnos(id, mp);
            }
        });

        mp.btnAgregarMateria.addActionListener(ev -> {
            String nombre = JOptionPane.showInputDialog(mp, "Nombre de la materia:");
            if (nombre != null && !nombre.trim().isEmpty()) {
                Materia m = materiaDAO.buscarPorNombre(nombre.trim());
                if (m == null) {
                    materiaDAO.insertar(new Materia(nombre.trim()));
                    cargarMaterias.run();
                } else {
                    JOptionPane.showMessageDialog(mp, "La materia ya existe.");
                }
            }
        });

        mp.btnEliminarMateria.addActionListener(ev -> {
            String sel = mp.listaMaterias.getSelectedValue();
            if (sel == null) {
                JOptionPane.showMessageDialog(mp, "Selecciona una materia");
                return;
            }
            Long id = Long.parseLong(sel.split(":")[0]);
            int r = JOptionPane.showConfirmDialog(mp, "Eliminar materia ID " + id + "?", "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                try {
                    materiaDAO.eliminar(id);
                    cargarMaterias.run();
                    ((DefaultTableModel) mp.tablaAlumnos.getModel()).setRowCount(0);
                } catch (IllegalStateException ise) {
                    JOptionPane.showMessageDialog(mp, ise.getMessage(), "No permitido", JOptionPane.WARNING_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mp, "Error al eliminar: " + ex.getMessage());
                }
            }
        });

        mp.btnVolver.addActionListener(ev -> mp.dispose());

        mp.setVisible(true);
    }

    private void cargarAlumnos(Long materiaId, MateriaPanel mp) {
        List<Calificacion> lista = calDAO.buscarPorMateria(materiaId);
        DefaultTableModel model = new DefaultTableModel(new Object[] { "ID", "Alumno", "Nota", "Fecha" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Calificacion c : lista) {
            model.addRow(new Object[] { c.getId(), c.getAlumno() != null ? c.getAlumno().getNombre() : "", c.getNota(),
                    c.getFecha() });
        }
        mp.tablaAlumnos.setModel(model);
    }
}
