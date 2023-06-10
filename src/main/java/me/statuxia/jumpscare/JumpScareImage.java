package me.statuxia.jumpscare;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class JumpScareImage extends JFrame {
    private final Image image;

    public JumpScareImage(InputStream stream, GraphicsDevice device) throws IOException {
        image = new ImageIcon(ImageIO.read(stream)).getImage();
        add(new DrawPanel(device.getDefaultConfiguration().getBounds()));
        setUndecorated(true);
        pack();
        setLocation(device.getDefaultConfiguration().getBounds().x, 0);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class DrawPanel extends JPanel {
        private final int width;
        private final int height;

        public DrawPanel(Rectangle rectangle) {
            this.width = rectangle.width;
            this.height = rectangle.height;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, width, height, null);
        }
    }
}
