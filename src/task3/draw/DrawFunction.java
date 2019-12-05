package task3.draw;

import task3.pixel.PixelDrawer;
import task3.points.ScreenConverter;
import task3.functions.IFunction;
import task3.lines.LineDrawer;
import task3.points.RealPoint;
import task3.points.ScreenPoint;

import java.awt.*;
import java.util.Map;

public class DrawFunction {

    private IFunction f;
    private LineDrawer lineDrawer;
   // private PixelDrawer pd;
    private ScreenConverter sc;
    private Color c;
    private int weightWindow, heightWindow;
    private Map<String, Double> params;

    public DrawFunction(IFunction f, LineDrawer lineDrawer, ScreenConverter sc, Color c, int weightWindow, int heightWindow, Map<String, Double> params) {
        this.f = f;
        this.lineDrawer = lineDrawer;
        //this.pd = pd;
        this.sc = sc;
        this.c = c;
        this.weightWindow = weightWindow;
        this.heightWindow = heightWindow;
        this.params = params;
    }


    public void drawF(PixelDrawer pd, Map<String, Double> params) { //d в кач-ве аргумента pd if param
        double x, y;
        RealPoint tempRP = new RealPoint(-1 * weightWindow / 2, heightWindow / 2), rp;
        ScreenPoint tempSP, sp;

        for (int i = -1 * weightWindow / 2; i < weightWindow / 2; i++) {

            tempSP = sc.r2s(tempRP);
            x = i;
            y = f.computeY(x, params);

            rp = new RealPoint(x, y);
            sp = sc.r2s(rp);

            lineDrawer.drawLine(pd, tempSP, sp, c);

            tempRP = rp;

        }
    }

    //
}
