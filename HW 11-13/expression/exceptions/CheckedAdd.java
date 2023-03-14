package expression.exceptions;

import expression.*;

public class CheckedAdd extends Add {

    public CheckedAdd(ExpressionElement operand1, ExpressionElement operand2) {
        super(operand1, operand2);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int firstValue = operand1.evaluate(x, y, z);
        int secondValue = operand2.evaluate(x, y, z);
        if (firstValue > 0 && Integer.MAX_VALUE - firstValue < secondValue ||
                firstValue < 0 && Integer.MIN_VALUE - firstValue > secondValue) {
            throw new IllegalArgumentException("overflow");
        }
        return firstValue + secondValue;
    }
}
