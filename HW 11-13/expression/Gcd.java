package expression;

public class Gcd extends BinaryExpression {
    public static final int PRIORITY = 1;

    public Gcd(ExpressionElement operand1, ExpressionElement operand2) {
        super(operand1, operand2);
    }

    public static int findGcd(int value1, int value2) {
        if (value1 < 0) {
            value1 = -value1;
        }
        if (value2 < 0) {
            value2 = -value2;
        }
        return (value2 == 0) ? value1 : findGcd(value2, value1 % value2);
    }

    @Override
    public double evaluate(double x) {
        throw new IllegalArgumentException("gcd arguments must be int");
    }

    @Override
    public int evaluate(int x) {
        int val1 = operand1.evaluate(x);
        int val2 = operand2.evaluate(x);
        return findGcd(val1, val2);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int val1 = operand1.evaluate(x, y, z);
        int val2 = operand2.evaluate(x, y, z);
        return findGcd(val1, val2);
    }

    @Override
    public String toMiniStringWithPriority(int topPriority) {
        return toMiniStringForBinary(topPriority, LOWEST_PRIORITY, Gcd.PRIORITY, Gcd.PRIORITY, "gcd");
    }

    @Override
    public String toString() {
        return "(" + operand1.toString() + " gcd " + operand2.toString() + ")";
    }
}
