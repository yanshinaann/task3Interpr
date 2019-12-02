package task3.lines;

import task3.pixel.PixelDrawer;
import task3.points.ScreenPoint;

import java.awt.*;

public class BresLineDrawer implements LineDrawer {


    public BresLineDrawer() {

    }


    private int sign(int x) {
        return Integer.compare(x, 0);
        //возвращает 0, если аргумент (x) равен нулю; -1, если x < 0 и 1, если x > 0.
    }

    public void drawLine(PixelDrawer pd, ScreenPoint p1, ScreenPoint p2, Color c) {
        int x, y, dx, dy, incx, incy, pdx, pdy, es, el, err;

        dx = p2.getI() - p1.getI();
        dy = p2.getJ() - p1.getJ();

        incx = sign(dx);
        incy = sign(dy);

        dx = Math.abs(dx);
        dy = Math.abs(dy);
        if (dx > dy) //определяем наклон отрезка:
        {
            pdx = incx;
            pdy = 0;
            es = dy;
            el = dx;
        } else//случай, когда прямая скорее "высокая", чем длинная, т.е. вытянута по оси y
        {
            pdx = 0;
            pdy = incy;
            es = dx;
            el = dy;//тогда в цикле будем двигаться по y
        }

        x = p1.getI();
        y = p1.getJ();
        err = el / 2;
        pd.drawPixel(x, y, c);//ставим первую точку

        for (int t = 0; t < el; t++)//идём по всем точкам
        {
            err -= es;
            if (err < 0) {
                err += el;
                x += incx;//сместить вверх или вниз, если по х
                y += incy;//сместить влево-вправо, если по y
            } else {
                x += pdx;
                y += pdy;
            }

            pd.drawPixel(x, y, c);
        }
    }

//    @Override
//    public void drawLine(PixelDrawer pd, ScreenPoint p1, ScreenPoint p2, Color c) {
//        drawLine(p1.getI(), p2.getJ(), p2.getI(), p2.getJ(), c, pd);
//    }
}