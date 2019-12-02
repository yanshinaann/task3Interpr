package task3.functions;

import java.util.HashMap;
import java.util.Map;

public class Function4 implements IFunction {
    private String type = "y = A* 1/(1+e^(-x)) + C";
    private Map<String, Double> params;

    public Function4() {
        params = new HashMap<>();
        params.put("A", 0.0);
        params.put("C", 0.0);

    }

//    @Override
//    public Map<String, Double> paramsFun(double[] a, Map<String, Double> paramsF) {
//
//        paramsF.put("A", a[1]);
//        paramsF.put("C", a[2]);
//        return paramsF;
//    }

    @Override
    public double computeY(double x) {
        return params.get("A") * 1 / (1 + Math.pow(Math.E, (-1 * x))) + params.get("C");
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getN() {
        return 2;
    }

    public void setType(String type) {
        this.type = type;
    }
}
