package task3.functions;

import java.util.HashMap;
import java.util.Map;

public class Function1 implements IFunction {
    private String type = " y = A*sin(W*x + F) + C";
    private Map<String, Double> paramsF;
    @Override
    public Map<String, Double> paramsFun(double [] a, Map<String, Double> paramsF){

        paramsF.put("A", a[1]);
        paramsF.put("W", a[2]);
        paramsF.put("F", a[3]);
        paramsF.put("C", a[4]);
        return paramsF;
    }
    @Override
    public double computeY(double x, Map<String, Double> params) {
        return params.get("A") * Math.sin(params.get("W") * x + params.get("F")) + params.get("C");
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getN() {
        return 4;
    }

    public void setType(String type) {
        this.type = type;
    }
}
