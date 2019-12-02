package task3.lines;

import task3.pixel.PixelDrawer;
import task3.points.ScreenPoint;

import java.awt.Color;

import static java.lang.Math.abs;
import static java.lang.Math.ceil;
import static java.lang.Math.floor;


public class WuLineDrawer implements LineDrawer {

    public void drawLine(PixelDrawer pd, ScreenPoint p1, ScreenPoint p2, Color color) {

        int x1 = p1.getI();
        int x2 = p2.getI();
        int y1 = p1.getJ();
        int y2 = p2.getJ();
        // calculate dx & dy
        int dx = x2 - x1;
        int dy = y2 - y1;

        // calculate steps required for generating pixels
        int steps = abs(dx) > abs(dy) ? abs(dx) : abs(dy);//наклон рассчитывается по большей проекции

        // calculate increment in x & y for each steps
        float Xinc = dx / (float) steps;// на сколько за один шаг должен ув-ться параметр каждой
        float Yinc = dy / (float) steps;

        // Put pixel for each step
        float X = x1;
        float Y = y1;
        double k;
        for (int i = 0; i <= steps; i++) {
            if (Math.abs(dx) > Math.abs(dy))
                k = (Y - floor(Y)); //коэф яркости
            else
                k = (X - floor(X));

            Color firstPixel = new Color((int) (color.getRed() * k), (int) (color.getGreen() * k), (int) (color.getBlue() * k));
            Color secondPixel = new Color((int) (color.getRed() * (1 - k)), (int) (color.getGreen() * (1 - k)), (int) (color.getBlue() * (1 - k)));
            pd.drawPixel((int) floor(X), (int) floor(Y), secondPixel);  // put pixel at (X,Y) один унизу округляется
            pd.drawPixel((int) ceil(X), (int) ceil(Y), firstPixel);//другой открывается вверх
            X += Xinc;// increment in x at each step
            Y += Yinc;// increment in y at each step
        }

    }
}
//    @Override
//    public void drawLine(int x0, int y0, int x1, int y1, Color c) {
//
//        float dx = x1 - x0;
//        float dy = y1 - y0;
//        float gradient = 0;
//        float y = 0;
//        float x = 0;
//        if (abs(dx) >= abs(dy)) {
//            gradient = dy / abs(dx);
//            y = y0 + gradient;
//            x = x0 + signum(dx);
//            float incr = signum(dy);
//            if (dx > 0 && dy > 0 || dx < 0 && dy > 0 && abs(dx) > abs(dy)) {
//                incr = -incr;
//            }
//
//            for (int i = 0; i < abs(dx) - 1; i++) {
//
//                pd.drawPixel((int) x, (int) y,
//                        new Color((c.getRed() * (1 - (y - (int) floor(y)))),
//                                (int) (c.getGreen() * (1 - (y - (int) floor(y)))),
//                                (int) (c.getBlue() * (1 - (y - (int) floor(y))))));
//                pd.drawPixel((int) x, (int) (y + incr), new Color((c.getRed() * (y - (int) floor(y))),
//                        (int) (c.getGreen() * (y - (int) floor(y))),
//                        (int) (c.getBlue() * (y - (int) floor(y)))));
//// plot(pd, (int) x, (int)y, c, 0);
////plot(pd, (int) x, (int) (y + signum(dy)), Color.BLUE, 0);
//                x += signum(dx);
//                y += gradient;
//            }
//        } else {
//            gradient = dx / abs(dy);
//            y = y0 + signum(dy);
//            x = x0 + gradient;
//            float incr = signum(dx);
//            if (dx > 0 && dy > 0 || dx > 0 && dy < 0 && abs(dy) > abs(dx)) {
//                incr = -signum(dx);
//            }
//            for (int i = 0; i < abs(dy) - 1; i++) {
//                pd.drawPixel((int) x, (int) y, new Color(c.getRed() * (1 - (x - (int) x)),
//                        c.getGreen() * (1 - (x - (int) x)),
//                        c.getBlue() * (1 - (x - (int) x))));
//                pd.drawPixel((int) (x + incr), (int) y, new Color(c.getRed() * (x - (int) x),
//                        c.getGreen() * (x - (int) x),
//                        c.getBlue() * (x - (int) x)));
////plot(pd, (int) x, (int)y, c, 0);
////plot(pd, (int) (x + signum(dx)), (int) y, Color.BLUE, 0);
//                x += gradient;
//                y += signum(dy);
//            }
//
//        }
//    }
//    @Override
//    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
//        // calculate dx & dy
//        int dx = x2 - x1;
//        int dy = y2 - y1;
//
//        // calculate steps required for generating pixels
//        int steps = abs(dx) > abs(dy) ? abs(dx) : abs(dy);
//
//        // calculate increment in x & y for each steps
//        float Xinc = dx / (float) steps;
//        float Yinc = dy / (float) steps;
//
//        // Put pixel for each step
//        float X = x1;
//        float Y = y1;
//        double k = 0;
//        for (int i = 0; i <= steps; i++)
//        {
//            k=(Y-floor(Y));
//            Color firstPixel = new Color((int) (color.getRed()*k),(int)(color.getGreen()*k),(int)(color.getBlue()*k));
//            Color secondPixel = new Color((int) (color.getRed()*(1-k)),(int) (color.getGreen()*(1-k)),(int) (color.getBlue()*(1-k)));
//            pd.drawPixel((int) floor(X),(int) floor(Y),secondPixel);  // put pixel at (X,Y)
//            pd.drawPixel((int) ceil(X),(int) ceil(Y),firstPixel);
//            X += Xinc;           // increment in x at each step
//            Y += Yinc;           // increment in y at each step
//            // generation step by step
//        }
//    }

//    @Override
//    public void drawLine(int x1, int y1, int x2, int y2, Color c) {
//
//        pd.drawPixel(x1, y1, c);
//        pd.drawPixel(x2, y2, c);
//
//        if (Math.abs(y2 - y1) > Math.abs(x2 - x1)) {
//            double dx = x2 - x1;
//            double dy = y2 - y1;
//            double gradient = dx / dy;
//            double x = x1 + gradient;
//
//            for (int y = y1 + 1; y <= y2 - 1; y++) {
//                double t = 1 - (x - (int) x);
//                Color d = setColor(c, t);
//                pd.drawPixel((int) x, y, d);
//                t = x - (int) x;
//                d = setColor(c, t);
//                pd.drawPixel((int) x + 1, y, d);
//                x += gradient;
//            }
//        } else {
//            double dx = x2 - x1;
//            double dy = y2 - y1;
//
//            double gradient = dy / dx;
//            double y = y1 + gradient;
//            int x = x1 + 1;
//
//            for (x = x1 + 1; x <= x2 - 1; x++) {
//                double t = 1 - (y - (int) y);
//                Color d = setColor(c, t);
//                pd.drawPixel(x, (int) y, d);
//                t = y - (int) y;
//                d = setColor(c, t);
//                pd.drawPixel(x, (int) y + 1, d);
//                y += gradient;
//
//            }
//        }
//
//    }
//
//    private Color setColor(Color c, double t) {
//        int red = c.getRed();
//        int green = c.getGreen();
//        int blue = c.getBlue();
//        Color result = new Color(red, green, blue, (int) (255 * t));
//        return result;
//    }
//

