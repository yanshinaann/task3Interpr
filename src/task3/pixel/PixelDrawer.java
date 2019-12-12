package task3.pixel;

import java.awt.*;

public interface PixelDrawer {
    void drawPixel(int x, int y, Color c);

    Color getColorPixel(int x, int y);

    void pixel(int x, int y, Color c, float d, PixelDrawer p);
}
