package expression.exceptions;

import expression.ExpressionElement;
import expression.Lcm;

import static expression.Gcd.findGcd;
import static expression.exceptions.CheckedMultiply.checkMultiplyOverflow;

public class CheckedLcm extends Lcm {
    public CheckedLcm(ExpressionElement operand1, ExpressionElement operand2) {
        super(operand1, operand2);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int firstValue = operand1.evaluate(x, y, z);
        int secondValue = operand2.evaluate(x, y, z);
        if (firstValue == 0 || secondValue == 0) {
            return 0;
        }
        int gcd = findGcd(firstValue, secondValue);
        firstValue /= gcd;
        checkMultiplyOverflow(firstValue, secondValue);
        return firstValue * secondValue;
    }
}
