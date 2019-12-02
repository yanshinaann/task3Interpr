package task3.functions;

import java.util.HashMap;
import java.util.Map;

public class Function0 implements IFunction {
    private String type = "  y = A*x^3 + B*x^2 + C*x + D";
    private Map<String, Double> paramsF;

    public Function0(){
        paramsF = new HashMap<>();
        paramsF.replace("A", 0.0);
        paramsF.replace("B", 0.0);
        paramsF.replace("C", 0.0);
        paramsF.replace("D", 0.0);

    }
    @Override
    public double computeY(double x) {
        return paramsF.get("A") * Math.pow(x, 3) + paramsF.get("B")
                * Math.pow(x, 2) + paramsF.get("C") * x + paramsF.get("D");
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