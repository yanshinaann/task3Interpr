package task3.draw;

import task3.lines.LineDrawer;
import task3.pixel.PixelDrawer;
import task3.points.RealPoint;
import task3.points.ScreenConverter;

import java.awt.*;

public class FunctionDrawer {

    private LineDrawer ld;
    private PixelDrawer pd;
    private ScreenConverter sc;
    private Interpritator in;



    public FunctionDrawer(Interpritator in, LineDrawer ld, PixelDrawer pd, ScreenConverter sc) {
        this.in = in;
        this.ld = ld;
        this.pd = pd;
        this.sc = sc;
    }

    public double compute(String func, Interpritator in, double x) {

        in.setVariable("x", x);
        try {
            return in.parse(func);
        } catch (Exception e) {
            System.out.println("неправильные данные");
        }
        return 0;
    }

    public void draw(String str) {

        double x = sc.getXr();
        double error = sc.getWr() / sc.getWs();
        double from = x;
        double to = sc.getWr()+(sc.getXr());
//        if(reverse) {
//            x = sc.getYr();
//            error = sc.getHr() / sc.getHs();
//            from = (-sc.getHr()+sc.getYr());
//            to =  sc.getYr();
//        }

        double y = compute(str, in, x);
        for (double i = from; i <= to; i+=error) {

            double newY = compute(str, in, x);
//            if(reverse) {
//                ld.drawLine(pd, sc.r2s(new RealPoint(y, x)), sc.r2s(new RealPoint(newY, i)), Color.RED);
//            }else {
                ld.drawLine(pd, sc.r2s(new RealPoint(x, y)), sc.r2s(new RealPoint(i, newY)), Color.RED);
           // }
            y = newY;
            x = i;
        }
    }
}
