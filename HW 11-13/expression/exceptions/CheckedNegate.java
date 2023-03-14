package expression.exceptions;

import expression.ExpressionElement;
import expression.Negate;
import expression.TripleExpression;
import expression.Variable;

public class CheckedNegate extends Negate {
    public CheckedNegate(ExpressionElement expressionElement) {
        super(expressionElement);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int value = expressionElement.evaluate(x, y, z);
        if (value == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("overflow");
        }
        return -value;
    }


}

