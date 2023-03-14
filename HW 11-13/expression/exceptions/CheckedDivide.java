package expression.exceptions;

import expression.Divide;
import expression.ExpressionElement;

public class CheckedDivide extends Divide {

    public CheckedDivide(ExpressionElement operand1, ExpressionElement operand2) {
        super(operand1, operand2);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int firstValue = operand1.evaluate(x, y, z);
        int secondValue = operand2.evaluate(x, y, z);
        if (secondValue == 0) {
            throw new ArithmeticException("division by zero");
        }
        if (firstValue == Integer.MIN_VALUE && secondValue == -1) {
            throw new IllegalArgumentException("overflow");
        }
        return firstValue / secondValue;
    }
}
