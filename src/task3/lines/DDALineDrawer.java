package task3.lines;

import task3.pixel.PixelDrawer;
import task3.points.ScreenPoint;

import java.awt.*;

public class DDALineDrawer implements LineDrawer {
    @Override
    public void drawLine(PixelDrawer pd, ScreenPoint p1, ScreenPoint p2, Color c) {
        int dx = p2.getI() - p1.getI();
        int dy = p2.getJ() - p1.getJ();
        int D = Math.max(Math.abs(dx), Math.abs(dy));
        double stepY = (double)dy / D;
        double stepX = (double)dx / D;
        for (int i = 0; i <= D; i++) {
            pd.drawPixel(
                    p1.getI() + (int) (stepX * i),
                    p1.getJ() + (int) (stepY * i), c);
        }
    }
}
