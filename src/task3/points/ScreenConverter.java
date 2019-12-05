package task3.points;

import task3.points.RealPoint;
import task3.points.ScreenPoint;

public class ScreenConverter {
    private double xr, yr, wr, hr;
    private int ws, hs;

    public ScreenConverter(double xr, double yr, double wr, double hr, int ws, int hs) {
        this.xr = xr;
        this.yr = yr;
        this.wr = wr;
        this.hr = hr;
        this.ws = ws;
        this.hs = hs;
    }


    public ScreenPoint r2s(RealPoint p) {

            int i = (int) Math.round((p.getX() - xr) * ws / wr);
            int j = (int) Math.round((yr - p.getY()) * hs / hr);
            return new ScreenPoint(i, j);




    }

    public RealPoint s2r(ScreenPoint p) {
        double x = xr + p.getI() * wr / ws;
        double y = yr - p.getJ() * hr / hs;

        return new RealPoint(x, y);
    }
    public int r2sX(double x) {
        return (int) Math.round((x - xr) * ws / wr);
    }
    public int r2sY(double y) {
        return (int) Math.round((yr - y) * hs / hr);
    }
    public double s2rX(int i) {
        return xr + i * wr / ws;
    }

    public double s2rY(int j) {
        return yr - j * hr / hs;
    }

    public double getXr() {
        return xr;
    }

    public void setXr(double xr) {
        this.xr = xr;
    }

    public double getYr() {
        return yr;
    }

    public void setYr(double yr) {
        this.yr = yr;
    }

    public double getWr() {
        return wr;
    }

    public void setWr(double wr) {
        this.wr = wr;
    }

    public double getHr() {
        return hr;
    }

    public void setHr(double hr) {
        this.hr = hr;
    }

    public int getWs() {
        return ws;
    }

    public void setWs(int ws) {
        this.ws = ws;
    }

    public int getHs() {
        return hs;
    }

    public void setHs(int hs) {
        this.hs = hs;
    }
}
