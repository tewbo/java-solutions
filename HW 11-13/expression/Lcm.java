package expression;

import static expression.Gcd.findGcd;

public class Lcm extends BinaryExpression {
    public static final int PRIORITY = 2;

    public Lcm(ExpressionElement operand1, ExpressionElement operand2) {
        super(operand1, operand2);
    }

    @Override
    public double evaluate(double x) {
        throw new IllegalArgumentException("lcm arguments must be int");
    }

    @Override
    public int evaluate(int x) {
        int val1 = operand1.evaluate(x);
        int val2 = operand2.evaluate(x);
        if (val1 == 0 || val2 == 0) {
            return 0;
        }
        return val1 / findGcd(val1, val2) * val2;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int val1 = operand1.evaluate(x, y, z);
        int val2 = operand2.evaluate(x, y, z);
        if (val1 == 0 && val2 == 0) {
            return 0;
        }
        return val1 / findGcd(val1, val2) * val2;
    }

    @Override
    public String toMiniStringWithPriority(int topPriority) {
        StringBuilder sb = new StringBuilder();
        if (topPriority > Lcm.PRIORITY || topPriority == Gcd.PRIORITY) {
            sb.append('(');
        }
        sb.append(operand1.toMiniStringWithPriority(ExpressionElement.LOWEST_PRIORITY));
        sb.append(" lcm ");
        sb.append(operand2.toMiniStringWithPriority(Lcm.PRIORITY));
        if (topPriority > Lcm.PRIORITY || topPriority == Gcd.PRIORITY) {
            sb.append(')');
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "(" + operand1.toString() + " lcm " + operand2.toString() + ")";
    }
}
