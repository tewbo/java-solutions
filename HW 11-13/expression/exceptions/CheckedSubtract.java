package expression.exceptions;

import expression.ExpressionElement;
import expression.Subtract;
import expression.TripleExpression;
import expression.Variable;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(ExpressionElement operand1, ExpressionElement operand2) {
        super(operand1, operand2);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int firstValue = operand1.evaluate(x, y, z);
        int secondValue = operand2.evaluate(x, y, z);
        if (secondValue < 0 && Integer.MAX_VALUE + secondValue < firstValue ||
                secondValue > 0 && Integer.MIN_VALUE + secondValue > firstValue) {
            throw new IllegalArgumentException("overflow");
        }
        return firstValue - secondValue;
    }
}
