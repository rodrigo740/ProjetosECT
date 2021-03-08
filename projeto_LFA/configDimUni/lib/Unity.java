package lib;
public class Unity {
    private String uni;
    private int pow;
    private String expression;

    public Unity(String uni, int pow) {
        this.uni = uni;
        this.pow = pow;
    }

    public Unity(String uni, int pow, String expression) {
        this.uni = uni;
        this.pow = pow;
        this.expression = expression;
    }

    public String getUnity() {
        return uni;
    }

    public int getPow() {
        return pow;
    }

    public String getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return uni;
    }

}
