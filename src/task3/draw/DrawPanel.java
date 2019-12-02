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

    private LineDrawer lineDrawer;
    private ScreenConverter screenConverter;
    private Line line;
    private IFunction function;
    private double wr, hr, xr, yr;
    private int ws, hs;
    private Map<String, Double> params;
    private double[] array;

    private Scanner scanner = new Scanner(System.in);

    public DrawPanel() {
        super();
        //lineDrawer = new DDALineDrawer();
      // lineDrawer = new WuLineDrawer();
        lineDrawer = new BresLineDrawer();
        function = new Function0();

        wr = 100;
        hr = 100;
        xr = -wr / 2;
        yr = hr / 2;
        ws = 500;
        hs = 500;

        screenConverter = new ScreenConverter(xr, yr, wr, hr, ws, hs);
        line = new Line(new RealPoint(0, 0), new RealPoint(1, 1));

        params = new HashMap<>();
        params.put("A", 0.0);
        params.put("B", 0.0);
        params.put("C", 0.0);
        params.put("D", 0.0);
        params.put("W", 0.0);
        params.put("F", 0.0);
        params.put("A1", 0.0);
        params.put("W1", 0.0);
        params.put("A2", 0.0);
        params.put("W2", 0.0);
        params.put("F2", 0.0);
        params.put("C1", 0.0);
        params.put("C2", 0.0);
        params.put("T", 0.0);


        addMouseMotionListener(this);
        addMouseListener(this);
    }


    private void console() {
        array = new double[8];
        System.out.println(" 0. y = A*x^3 + B*x^2 + C*x + D ");
        System.out.println(" 1. y = A*sin(W*x + F) + C ");
        System.out.println(" 2. y = A1*sin(W1*x + F1)*(A2*cos(W2*x + F2)+C2) + C1 ");
        System.out.println(" 3. y = A*sin(W*x + F)*(e^(-x/T))+C ");
        System.out.println(" 4. y = A* 1/(1+e^(-x)) + C ");
        System.out.println(" 5. y = A / (B*x+D) + C");
        print("Выберете функцию от 0 до 5");
        Integer a = scanner.nextInt();
        switch (a) {
            case 0: {
                function = new Function0();
                break;
            }
            case 1: {
                function = new Function1();
                break;
            }
            case 2: {
                function = new Function2();
                break;
            }
            case 3: {
                function = new Function3();
                break;
            }
            case 4: {
                function = new Function4();
                break;
            }
            case 5: {
                function = new Function5();
                break;
            }
        }//mas fun

        print(function.getType());

        int n = function.getN();

        String str;
        if (n == 1) str = " коэффициент";
        else if ((n>1)&&(n<5)) str = " коэффициентa";
        else str = " коэффициентов";

            System.out.println("Введите " + n + str);
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextDouble();
        }
       // function.paramsFun(array, params);
//
        repaint();

    }

    private void print(String str) {
        System.out.println(str);
    }

    @Override
    public void paint(Graphics g) {
        DrawFunction drawFunction;
        Graphics2D gr = (Graphics2D) g;
        BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        PixelDrawer pd = new BufferedImagePixelDrawer(bufferedImage);

        if (lineDrawer != null) {
            lineDrawer.drawLine(pd, screenConverter.r2s(new RealPoint(-xr, 0)), screenConverter.r2s(new RealPoint(xr, 0)), Color.RED);
            lineDrawer.drawLine(pd, screenConverter.r2s(new RealPoint(0, -yr)), screenConverter.r2s(new RealPoint(0, yr)), Color.RED);
            console();
            drawFunction = new DrawFunction(function, lineDrawer, pd, screenConverter, Color.WHITE, ws, hs, params);//ws hs iz sc
            //создали один раз а сюда в кач-ве аргумента изменябщиеся значения
            drawFunction.drawF();

        }

        gr.drawImage(bufferedImage, 0, 0, null);
    }

    private ScreenPoint last = null;

    @Override
    public void mouseDragged(MouseEvent e) {
        if (last != null) {
            ScreenPoint cur = new ScreenPoint(e.getX(), e.getY());
            int di = cur.getI() - last.getI();
            int dj = cur.getJ() - last.getJ();
            RealPoint d = screenConverter.s2r(new ScreenPoint(di, dj));
            screenConverter.setXr(d.getX());
            screenConverter.setYr(d.getY());
            last = cur;

            repaint();

        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ScreenPoint m = new ScreenPoint(e.getX(), e.getY());
        line.setP2(screenConverter.s2r(m));
        repaint();
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
