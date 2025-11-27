package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

// Componente simple que muestra una imagen con bordes redondeados.
public class ImagenVista extends JComponent {
    private Image imagen;
    private int arco;

    public ImagenVista(String rutaRecurso, int ancho, int alto, int arco) {
        this.imagen = null;
        this.arco = arco;
        ImageIcon ic = null;
        try {
            java.net.URL url = getClass().getResource(rutaRecurso);
            if (url != null) {
                ic = new ImageIcon(url);
                Image img = ic.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                this.imagen = img;
            }
        } catch (Exception ignored) {
        }

        setPreferredSize(new Dimension(ancho, alto));
        setMinimumSize(new Dimension(ancho, alto));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            Shape clip = new RoundRectangle2D.Float(0, 0, w, h, arco, arco);
            g2.setClip(clip);

            if (imagen != null) {
                g2.drawImage(imagen, 0, 0, w, h, this);
            } else {
                g2.setColor(new Color(230, 230, 230));
                g2.fillRect(0, 0, w, h);
            }

            g2.setClip(null);
            g2.setColor(new Color(200, 200, 200, 200));
            g2.setStroke(new BasicStroke(1f));
            g2.draw(clip);
        } finally {
            g2.dispose();
        }
    }
}
