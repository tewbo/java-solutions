package expression.exceptions;

import expression.ExpressionElement;
import expression.Multiply;
import expression.TripleExpression;
import expression.Variable;

public class CheckedMultiply extends Multiply{
    static void checkMultiplyOverflow(int val1, int val2) {
        if ((val1 > 0 && val2 > 0 && Integer.MAX_VALUE / val1 < val2) ||
                (val1 < 0 && val2 < 0 && Integer.MAX_VALUE / val1 > val2) ||
                (val1 > 0 && val2 < 0 && Integer.MIN_VALUE / val1 > val2) ||
                (val1 < 0 && val2 > 0 && Integer.MIN_VALUE / val2 > val1)) {
            throw new IllegalArgumentException("overflow");
        }
    }

    public CheckedMultiply(ExpressionElement operand1, ExpressionElement operand2) {
        super(operand1, operand2);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int firstValue = operand1.evaluate(x, y, z);
        int secondValue = operand2.evaluate(x, y, z);
        checkMultiplyOverflow(firstValue, secondValue);
        return firstValue * secondValue;
    }
}
