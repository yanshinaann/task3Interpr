package task3.draw;

import task3.lines.*;
import task3.pixel.BufferedImagePixelDrawer;
import task3.pixel.PixelDrawer;
import task3.points.ScreenConverter;
import task3.points.RealPoint;
import task3.points.ScreenPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class DrawPanel extends JPanel implements MouseMotionListener, MouseListener, KeyListener {

    private LineDrawer ld;
    private ScreenConverter sc;
    private Interpretator interpretator;
    private Line l;
    private String s;

    private Scanner scanner = new Scanner(System.in);

    public DrawPanel() {
        super();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();

        s = getStr();
        ld = new BresLineDrawer();
        sc = new ScreenConverter(-10, 10, 20, 20, 500, 500);
        l = new Line(new RealPoint(-1, -1), new RealPoint(1, 1));
        interpretator = new Interpretator();

    }

    private String getStr() {
        print("Введите функцию");

        return scanner.nextLine();
    }


    private void print(String str) {
        System.out.println(str);
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = (Graphics2D) bi.getGraphics();
        PixelDrawer pd = new BufferedImagePixelDrawer(bi);
        FunctionDrawer fd = new FunctionDrawer(interpretator, ld, pd, sc);

        ld.drawLine(pd, sc.r2s(new RealPoint(sc.getXr(), 0)), sc.r2s(new RealPoint(sc.getWr() + sc.getXr(), 0)), Color.RED);
        ld.drawLine(pd, sc.r2s(new RealPoint(0, sc.getYr())), sc.r2s(new RealPoint(0, -sc.getHr() + sc.getYr())), Color.RED);

        fd.draw(s);
        //("6*x^3+6*x^2+x+9")
        // A*x^3 + B*x^2 + C*x + D


        double deltaLabel = countDeltaLabels(sc.s2rX(150) - sc.s2rX(0));

        for (double i = 0; i > sc.getXr(); i = i - deltaLabel) {
            ld.drawLine(pd, sc.r2s(new RealPoint(i, deltaLabel / 10)), sc.r2s(new RealPoint(i, -deltaLabel / 10)), Color.RED);
            gr.drawString(i + "", sc.r2sX(i), sc.r2sY(0) + 5);
        }
        for (double i = 0; i < sc.s2rX(getWidth()); i = i + deltaLabel) {
            ld.drawLine(pd, sc.r2s(new RealPoint(i, deltaLabel / 10)), sc.r2s(new RealPoint(i, -deltaLabel / 10)), Color.RED);
            gr.drawString(i + "", sc.r2sX(i), sc.r2sY(0) + 5);
        }
        for (double i = 0; i > sc.s2rY(getHeight()); i = i - deltaLabel) {
            ld.drawLine(pd, sc.r2s(new RealPoint(deltaLabel / 10, i)), sc.r2s(new RealPoint(-deltaLabel / 10, i)), Color.RED);
            gr.drawString(i + "", sc.r2sX(0), sc.r2sY(i) + 5);
        }
        for (double i = 0; i < sc.getYr(); i = i + deltaLabel) {
            ld.drawLine(pd, sc.r2s(new RealPoint(deltaLabel / 10, i)), sc.r2s(new RealPoint(-deltaLabel / 10, i)), Color.RED);
            gr.drawString(i + "", sc.r2sX(0), sc.r2sY(i) + 5);
        }
        g.drawImage(bi, 0, 0, null);

    }

    public static double countDeltaLabels(double d) {
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

}
