package task3.functions;

import java.util.HashMap;
import java.util.Map;

public class Function5 implements IFunction {
    private String type = "y = A / (B*x+D) + C";
    @Override
    public Map<String, Double> paramsFun(double [] a,  Map<String, Double> paramsF){

        paramsF.put("A", a[1]);
        paramsF.put("B", a[2]);
        paramsF.put("D", a[3]);
        paramsF.put("C", a[4]);
        return paramsF;
    }
    @Override
    public double computeY(double x, Map<String, Double> params) {
        return params.get("A") / (params.get("B") * x + params.get("D") + params.get("C"));
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
