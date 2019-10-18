package task3.pixel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BufferedImagePixelDrawer implements PixelDrawer {

    private BufferedImage image;

    public BufferedImagePixelDrawer(BufferedImage image) {
        this.image = image;
    }

    @Override
    public void drawPixel(int x, int y, Color c) {
        if (x >= 0 && y >= 0 &&
                x < image.getWidth() &&
                y < image.getHeight()) {
            image.setRGB(x, y, c.getRGB());
        }
    }

    @Override
    public Color getColorPixel(int x, int y) {
        return new Color(image.getRGB(x,y));
    }

    @Override
    public void pixel(int x, int y, Color c, float d, PixelDrawer p) {
        if (x >= 0 && y >= 0 &&
                x < image.getWidth() &&
                y < image.getHeight()) {
            Color c1 = new Color(
                    (int) (d * c.getRed() + (1-d) * p.getColorPixel(x, y).getRed()),
                    (int) (d * c.getGreen() + (1-d) * p.getColorPixel(x, y).getGreen()),
                    (int) (d * c.getBlue() + (1-d) * p.getColorPixel(x, y).getBlue()));
            image.setRGB(x, y, c1.getRGB());
        }
    }
}
