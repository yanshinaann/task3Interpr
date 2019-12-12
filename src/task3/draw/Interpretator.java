package task3.draw;


import java.util.HashMap;


public class Interpretator {
    private class Result {

        public double cur;
        public String rest;

        public Result(double v, String r) {
            this.cur = v;
            this.rest = r;

        }
    }

    private HashMap<String, Double> variables;


    public Interpretator() {
        variables = new HashMap<String, Double>();
    }

    public void setVariable(String variableName, Double variableValue)
    {
        variables.put(variableName, variableValue);
    }

    public Double getVariable(String variableName) {
        if (!variables.containsKey(variableName)) {
            System.err.println( "Error: Try get non-exists variable '"+variableName+"'" );
            return 0.0;
        }
        return variables.get(variableName);
    }

    public double parse(String s) throws Exception {


        Result result = plusMinus(s);
        if (!result.rest.isEmpty()) {
            System.err.println("Error: can't full parse");
            System.err.println("rest: " + result.rest);
        }
        return result.cur;
    }

    private Result plusMinus(String s) throws Exception {
        Result current = mulDiv(s);
        double acc = current.cur;

        while (current.rest.length() > 0) {
            if (!(current.rest.charAt(0) == '+' || current.rest.charAt(0) == '-')) break;

            char sign = current.rest.charAt(0);
            String next = current.rest.substring(1);

            current = mulDiv(next);
            if (sign == '+') {
                acc += current.cur;
            } else {
                acc -= current.cur;
            }
        }
        return new Result(acc, current.rest);
    }

    private Result bracket(String s) throws Exception {
        char zeroChar = s.charAt(0);
        if (zeroChar == '(') {
            Result r = plusMinus(s.substring(1));
            if (!r.rest.isEmpty() && r.rest.charAt(0) == ')') {
                r.rest = r.rest.substring(1);
            } else {
                System.err.println("Error: no close bracket");
            }
            return r;
        }
        return functionVariable(s);
    }

    private Result functionVariable(String s) throws Exception {
        String f = "";
        int i = 0;
        if(s.charAt(0) == '-') {
            s = "0" + s;
            Result cur = plusMinus(s);
        }
        while (i < s.length() && (Character.isLetter(s.charAt(i)) || ( Character.isDigit(s.charAt(i)) && i > 0 ) )) {

            f += s.charAt(i);
            i++;
        }
        if (!f.isEmpty()) { // если что-нибудь нашли
            if(f.equals("exp")) return new Result(Math.E, s.substring(f.length()));
            if ( s.length() > i && s.charAt( i ) == '(') { // и следующий символ скобка значит - это функция
                Result r = bracket(s.substring(f.length()));
                return processFunction(f, r);
            } else { // иначе - это переменная
                return new Result(getVariable(f), s.substring(f.length()));
            }
        }
        return num(s);
    }

    private Result mulDiv(String s) throws Exception {
        Result current = bracket(s);

        double acc = current.cur;
        while (true) {
            if (current.rest.length() == 0) {
                return current;
            }
            char sign = current.rest.charAt(0);
            if ((sign != '*' && sign != '/' && sign != '^')) return current;

            String next = current.rest.substring(1);
            Result right = bracket(next);

            if (sign == '*') {
                acc *= right.cur;
            } else if(sign=='/') {

                acc /= right.cur;

            }else if(sign == '^') {
                acc = Math.pow(acc, right.cur);
            }

            current = new Result(acc, right.rest);
        }
    }

    private Result num(String s) throws Exception {
        int i = 0;
        int dot_cnt = 0;
        boolean negative = false;
        // число также может начинаться с минуса
        if( s.charAt(0) == '-' ){
            negative = true;
            s = s.substring( 1 );
        }

        while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {

            if (s.charAt(i) == '.' && ++dot_cnt > 1) {
                throw new Exception("not valid number '" + s.substring(0, i + 1) + "'");
            }
            i++;
        }
        if( i == 0 ){
            throw new Exception( "can't get valid number in '" + s + "'" );
        }

        double dPart = Double.parseDouble(s.substring(0, i));
        if( negative ) dPart = -dPart;
        String restPart = s.substring(i);

        return new Result(dPart, restPart);
    }


    private Result processFunction(String func, Result r) {
        if (func.equals("sin")) {
            return new Result(Math.sin(r.cur), r.rest);
        } else if (func.equals("cos")) {
            return new Result(Math.cos(r.cur), r.rest);
        } else if (func.equals("tan")) {
            return new Result(Math.tan(r.cur), r.rest);
        } else {
            System.err.println("function '" + func + "' is not defined");
        }
        return r;
    }

}