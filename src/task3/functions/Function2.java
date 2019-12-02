package task3.functions;

import java.util.HashMap;
import java.util.Map;

public class Function2 implements IFunction {
    private String type = " y = A1*sin(W1*x + F1)*(A2*cos(W2*x + F2)+C2) + C1";
    private Map<String, Double> paramsF;
//    @Override
//    public Map<String, Double> paramsFun(double [] a, Map<String, Double> paramsF){
//        paramsF.put("A1", a[1]);
//        paramsF.put("W1", a[2]);
//        paramsF.put("F1", a[3]);
//        paramsF.put("A2", a[4]);
//        paramsF.put("W2", a[5]);
//        paramsF.put("F2", a[6]);
//        paramsF.put("C2", a[7]);
//        paramsF.put("C1", a[8]);
//        return paramsF;//массив имен параметров
//    }

    public Function2() {
        paramsF = new HashMap<>();
        paramsF.put("A1", 0.0);
        paramsF.put("W1", 0.0);
        paramsF.put("F1", 0.0);
        paramsF.put("A2", 0.0);
        paramsF.put("W2", 0.0);
        paramsF.put("F2", 0.0);
        paramsF.put("C2", 0.0);
        paramsF.put("C1", 0.0);
    }

    @Override
    public double computeY(double x) {
        return paramsF.get("A1") * Math.sin(paramsF.get("W1") * x
                + paramsF.get("F1")) * (paramsF.get("A2") *
                Math.cos(paramsF.get("W2") * x + paramsF.get("F2")) +
                paramsF.get("C2")) + paramsF.get("C1");

    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getN() {
        return 8;
    }

    public void setType(String type) {
        this.type = type;
    }
    //setparam....
}
