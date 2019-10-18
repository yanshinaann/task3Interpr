package task3.lines;

import task3.pixel.PixelDrawer;
import task3.points.ScreenPoint;

import java.awt.*;

public interface LineDrawer {
    void drawLine(PixelDrawer pd,
                  ScreenPoint p1, ScreenPoint p2, Color c);
}
