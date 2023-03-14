package expression.exceptions;

import expression.ExpressionElement;
import expression.Gcd;

import java.net.Inet4Address;

public class CheckedGcd extends Gcd {
    public CheckedGcd(ExpressionElement operand1, ExpressionElement operand2) {
        super(operand1, operand2);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int val1 = operand1.evaluate(x, y, z);
        int val2 = operand2.evaluate(x, y, z);
        return findGcd(val1, val2);
    }
}
