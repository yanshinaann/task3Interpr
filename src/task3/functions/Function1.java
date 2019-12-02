package task3.functions;

import java.util.HashMap;
import java.util.Map;

public class Function1 implements IFunction {
    private String type = " y = A*sin(W*x + F) + C";
    private Map<String, Double> paramsF;

    public Function1 (){
        paramsF = new HashMap<>();
        paramsF.put("A", 0.0);
        paramsF.put("W", 0.0);
        paramsF.put("F", 0.0);
        paramsF.put("C", 0.0);
    }
    @Override
    public double computeY(double x) {
        return paramsF.get("A") * Math.sin(paramsF.get("W") * x + paramsF.get("F"))
                + paramsF.get("C");
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
