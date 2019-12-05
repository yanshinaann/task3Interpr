package task3.functions;

import java.util.HashMap;
import java.util.Map;

public class Function0 implements IFunction {
    private String type = "  y = A*x^3 + B*x^2 + C*x + D";
    private Map<String, Double> paramsF;
    @Override
    public Map<String, Double> paramsFun(double [] a,Map<String, Double> paramsF ){
        paramsF.replace("A", a[1]);
        paramsF.replace("B", a[2]);
        paramsF.replace("C", a[3]);
        paramsF.replace("D", a[4]);
        return paramsF;
    }
    @Override
    public double computeY(double x, Map<String, Double> params) {
        return params.get("A") * Math.pow(x, 3) + params.get("B") * Math.pow(x, 2) + params.get("C") * x + params.get("D");
    }
    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getN() {
       return  4;
    }

    public void setType(String type) {
        this.type = type;
    }
}