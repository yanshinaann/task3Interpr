package task3.functions;

import java.util.HashMap;
import java.util.Map;

public class Function5 implements IFunction {
    private String type = "y = A / (B*x+D) + C";
    Map<String, Double> paramsF;
//    @Override
//    public Map<String, Double> paramsFun(double [] a,  Map<String, Double> paramsF){
//
//        paramsF.put("A", a[1]);
//        paramsF.put("B", a[2]);
//        paramsF.put("D", a[3]);
//        paramsF.put("C", a[4]);
//        return paramsF;
//    }

    public Function5() {
        paramsF = new HashMap<>();
        paramsF.put("A", 0.0);
        paramsF.put("B", 0.0);
        paramsF.put("D", 0.0);
        paramsF.put("C", 0.0);
    }

    @Override
    public double computeY(double x) {
        return paramsF.get("A") / (paramsF.get("B") * x + paramsF.get("D") + paramsF.get("C"));
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
