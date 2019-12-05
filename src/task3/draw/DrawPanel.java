package task3.draw;

import task3.lines.*;
import task3.pixel.BufferedImagePixelDrawer;
import task3.pixel.PixelDrawer;
import task3.points.ScreenConverter;
import task3.functions.*;
import task3.points.RealPoint;
import task3.points.ScreenPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DrawPanel extends JPanel implements MouseMotionListener, MouseListener, KeyListener {

    private LineDrawer ld;
    //String f;
    private ScreenConverter sc;
    private Line line;
    private IFunction function;
    private double wr, hr, xr, yr;
    private int ws, hs;
    private Map<String, Double> params;
    private double[] array;
    Interpritator interpritator;
    private Line l;
    String s;

    private Scanner scanner = new Scanner(System.in);

    public DrawPanel() {
        super();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
        //  this.addMouseWheelListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();

        s = getStr();
        ld = new DDALineDrawer();
        sc = new ScreenConverter(-10, 10, 20, 20, 500, 500);
        l = new Line(new RealPoint(-1, -1), new RealPoint(1, 1));
        interpritator = new Interpritator();

//        super();
//        //lineDrawer = new DDALineDrawer();
//        // lineDrawer = new WuLineDrawer();
//        // lineDrawer = new BresLineDrawer();
//        function = new Function0();
//
//        wr = 100;
//        hr = 100;
//        xr = -wr / 2;
//        yr = hr / 2;
//        ws = 500;
//        hs = 500;
//
//        sc = new ScreenConverter(xr, yr, wr, hr, ws, hs);
//        line = new Line(new RealPoint(0, 0), new RealPoint(1, 1));
//
//        params = new HashMap<>();
//        params.put("A", 0.0);
//        params.put("B", 0.0);
//        params.put("C", 0.0);
//        params.put("D", 0.0);
//        params.put("W", 0.0);
//        params.put("F", 0.0);
//        params.put("A1", 0.0);
//        params.put("W1", 0.0);
//        params.put("A2", 0.0);
//        params.put("W2", 0.0);
//        params.put("F2", 0.0);
//        params.put("C1", 0.0);
//        params.put("C2", 0.0);
//        params.put("T", 0.0);
//
//
//        addMouseMotionListener(this);
//        addMouseListener(this);
    }

    private String getStr() {
        print("Введите функцию");
        String s = scanner.nextLine();
        return s;
    }


    private void print(String str) {
        System.out.println(str);
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = (Graphics2D) bi.getGraphics();
        PixelDrawer pd = new BufferedImagePixelDrawer(bi);
        //String s = getStr();
        FunctionDrawer fd = new FunctionDrawer(interpritator, ld, pd, sc);

        ld.drawLine(pd, sc.r2s(new RealPoint(sc.getXr(), 0)), sc.r2s(new RealPoint(sc.getWr() + sc.getXr(), 0)), Color.RED);
        ld.drawLine(pd, sc.r2s(new RealPoint(0, sc.getYr())), sc.r2s(new RealPoint(0, -sc.getHr() + sc.getYr())), Color.RED);

        fd.draw(s);
        //("6*x^3+6*x^2+x+9")
        // A*x^3 + B*x^2 + C*x + D


        double deltaNotches = countDeltaNotches(sc.s2rX(150) - sc.s2rX(0));

        for (double i = 0; i > sc.getXr(); i = i - deltaNotches) {
            ld.drawLine(pd, sc.r2s(new RealPoint(i, deltaNotches / 10)), sc.r2s(new RealPoint(i, -deltaNotches / 10)), Color.RED);
            gr.drawString(i + "", sc.r2sX(i), sc.r2sY(0) + 10);
        }
        for (double i = 0; i < sc.s2rX(getWidth()); i = i + deltaNotches) {
            ld.drawLine(pd, sc.r2s(new RealPoint(i, deltaNotches / 10)), sc.r2s(new RealPoint(i, -deltaNotches / 10)), Color.RED);
            gr.drawString(i + "", sc.r2sX(i), sc.r2sY(0) + 10);
        }
        for (double i = 0; i > sc.s2rY(getHeight()); i = i - deltaNotches) {
            ld.drawLine(pd, sc.r2s(new RealPoint(deltaNotches / 10, i)), sc.r2s(new RealPoint(-deltaNotches / 10, i)), Color.RED);
            gr.drawString(i + "", sc.r2sX(0), sc.r2sY(i) + 10);
        }
        for (double i = 0; i < sc.getYr(); i = i + deltaNotches) {
            ld.drawLine(pd, sc.r2s(new RealPoint(deltaNotches / 10, i)), sc.r2s(new RealPoint(-deltaNotches / 10, i)), Color.RED);
            gr.drawString(i + "", sc.r2sX(0), sc.r2sY(i) + 10);
        }
        g.drawImage(bi, 0, 0, null);
        /*for (double i = (int)sc.getXr(); i <= sc.getWr()+(sc.getXr()) ; i+= deltaNotches) {
            RealPoint r = new RealPoint(i, 0);
            gr.setColor(Color.WHITE);
            gr.drawString(String.valueOf(i), sc.r2s(r).getI(), sc.r2s(r).getJ());

        }
        double stepY = sc.getHr() / 10;
        for (double i = (int)(-sc.getHr()+sc.getYr()); i <= sc.getYr(); i+= deltaNotches) {
            RealPoint r = new RealPoint(0, i);
            gr.setColor(Color.WHITE);
            gr.drawString(String.valueOf(i), sc.r2s(r).getI(), sc.r2s(r).getJ());

        }*/


//        DrawFunction drawFunction;
//        Graphics2D gr = (Graphics2D) g;
//        BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
//        PixelDrawer pd = new BufferedImagePixelDrawer(bufferedImage);
//       // drawFunction = new DrawFunction(function, lineDrawer,  screenConverter, Color.WHITE, ws, hs, params);
//        if (lineDrawer != null) {
//            lineDrawer.drawLine(pd, screenConverter.r2s(new RealPoint(-xr, 0)), screenConverter.r2s(new RealPoint(xr, 0)), Color.RED);
//            lineDrawer.drawLine(pd, screenConverter.r2s(new RealPoint(0, -yr)), screenConverter.r2s(new RealPoint(0, yr)), Color.RED);
//            console();
//           drawFunction = new DrawFunction(function, lineDrawer,  screenConverter, Color.WHITE, ws, hs, params);//ws hs iz sc
//            //создали один раз а сюда в кач-ве аргумента изменябщиеся значения
//            drawFunction.drawF(pd, params);
//
//        }
//
//        gr.drawImage(bufferedImage, 0, 0, null);
    }

    public static double countDeltaNotches(double d) {
        double deltaNotches = 0;
        double y = d;
        double r = 0;
        while (y < 10) {
            r--;
            y *= 10;
        }

        while (y > 10) {
            r++;
            y /= 10;
        }

        double min = y - 1;
        deltaNotches = 1 * Math.pow(10, r);
        if (min > Math.abs(y - 2.5)) {
            min = Math.abs(y - 2.5);
            deltaNotches = 2.5 * Math.pow(10, r);
        }
        if (min > Math.abs(y - 5)) {
            deltaNotches = 5 * Math.pow(10, r);
        }


        return deltaNotches;
    }

    private ScreenPoint last = null;

    @Override
    public void mouseDragged(MouseEvent e) {
        if (last != null) {
            ScreenPoint cur = new ScreenPoint(e.getX(), e.getY());
            int di = cur.getI() - last.getI();
            int dj = cur.getJ() - last.getJ();
            RealPoint d = sc.s2r(new ScreenPoint(di, dj));
            sc.setXr(d.getX());
            sc.setYr(d.getY());
            last = cur;

            this.repaint(0, 0, 500, 500);

        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        last = new ScreenPoint(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        last = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }

//
//    private void console() {
//        array = new double[8];
//        System.out.println(" 0. y = A*x^3 + B*x^2 + C*x + D ");
//        System.out.println(" 1. y = A*sin(W*x + F) + C ");
//        System.out.println(" 2. y = A1*sin(W1*x + F1)*(A2*cos(W2*x + F2)+C2) + C1 ");
//        System.out.println(" 3. y = A*sin(W*x + F)*(e^(-x/T))+C ");
//        System.out.println(" 4. y = A* 1/(1+e^(-x)) + C ");
//        System.out.println(" 5. y = A / (B*x+D) + C");
//        print("Выберете функцию от 0 до 5");
//        Integer a = scanner.nextInt();
//        switch (a) {
//            case 0: {
//                function = new Function0();
//                break;
//            }
//            case 1: {
//                function = new Function1();
//                break;
//            }
//            case 2: {
//                function = new Function2();
//                break;
//            }
//            case 3: {
//                function = new Function3();
//                break;
//            }
//            case 4: {
//                function = new Function4();
//                break;
//            }
//            case 5: {
//                function = new Function5();
//                break;
//            }
//        }//mas fun
//
//        print(function.getType());
//
//        int n = function.getN();
//
//        String str;
//        if (n == 1) str = " коэффициент";
//        else if ((n > 1) && (n < 5)) str = " коэффициентa";
//        else str = " коэффициентов";
//
//        System.out.println("Введите " + n + str);
//        for (int i = 0; i < n; i++) {
//            array[i] = scanner.nextDouble();
//        }
//        // function.paramsFun(array, params);
////
//        repaint();
//
//    }
}
