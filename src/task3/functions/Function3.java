package task3.functions;

import java.util.HashMap;
import java.util.Map;

public class Function3 implements IFunction {
    private String type = " y = A*sin(W*x + F)*(e^(-x/T))+C";
    private Map<String, Double> paramsF;
    public Function3() {
        paramsF = new HashMap<>();
        paramsF.put("A", 0.0);
        paramsF.put("W", 0.0);
        paramsF.put("F", 0.0);
        paramsF.put("T", 0.0);
        paramsF.put("C", 0.0);
    }
//    @Override
//    public Map<String, Double> paramsFun(double [] a, Map<String, Double> paramsF){
//
//        paramsF.put("A", a[1]);
//        paramsF.put("W", a[2]);
//        paramsF.put("F", a[3]);
//        paramsF.put("T", a[4]);
//        paramsF.put("C", a[5]);
//        return paramsF;
//    }
    @Override
    public double computeY(double x) {
        return paramsF.get("A") * Math.sin(paramsF.get("W") * x + paramsF.get("F")) *
                (Math.pow(Math.E, (-1 * x / paramsF.get("T")))) + paramsF.get("C");
    }

    @Override
    public String getType() {
        return type;
    }
    @Override
    public int getN() {
        return  5;
    }
    public void setType(String type) {
        this.type = type;
    }
}
