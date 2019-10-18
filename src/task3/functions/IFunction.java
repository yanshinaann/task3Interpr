package task3.functions;

import java.util.Map;

public interface IFunction {

    double computeY(double x, Map<String, Double> params);
    Map<String, Double> paramsFun(double [] a, Map<String, Double> paramsF);
    String getType();

    int getN();
}
