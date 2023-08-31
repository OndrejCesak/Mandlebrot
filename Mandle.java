import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.*;

public class Mandle extends JFrame implements MouseListener {

    public static void main(String[] args) {
        new Mandle();
    }

    int width = 800, height = 800, iterations = 400;
    float colorOffset = 0f;
    double posx = 0, posy = 0, scale = 1, mult = 4;

    Mandle() {
        setSize(width, height);
        setUndecorated(false);
        addMouseListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.black);
        g2.fillRect(0, 0, width, height);

        mandlebrot(g2);
    }

    void mandlebrot(Graphics2D g2) {

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                double za = 0, zb = 0;

                int totalIterations = (int) (iterations);

                for (int i = 0; i < totalIterations; i++) {

                    if (za * za + zb * zb >= 4.0) {
                        g2.setColor(Color.getHSBColor((float) i / totalIterations + colorOffset, 1.0f, 1.0f));
                        g2.fillRect(x, y, 1, 1);
                        break;
                    }

                    double ca = (((double) x / width) * (4.0 / height * width) - (2.0 / height * width)) / scale + posx;
                    double cb = (((double) y / height) * 4.0 - 2.0) / scale + posy;

                    double newa = za * za - zb * zb + ca;
                    double newb = 2 * za * zb + cb;

                    za = newa;
                    zb = newb;
                }
            }
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        posx += (((double) e.getX() / width) * (4.0 / height * width) - (2.0 / height * width)) / scale;
        posy += (((double) e.getY() / height) * 4.0 - 2.0) / scale;

        scale *= mult;
        iterations += 50;

        repaint();
    }
}
