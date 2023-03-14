package expression.exceptions;

import expression.ExpressionElement;
import expression.Reverse;

public class CheckedReverse extends Reverse {
    public CheckedReverse(ExpressionElement expressionElement) {
        super(expressionElement);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int val = expressionElement.evaluate(x, y, z);
        int ans = 0;
        while (val != 0) {
            if (ans > Integer.MAX_VALUE / 10 || ans < Integer.MIN_VALUE / 10) {
                throw new IllegalArgumentException("overflow");
            }
            ans *= 10;
            if (ans > 0 && Integer.MAX_VALUE - ans < val % 10 || ans < 0 && Integer.MIN_VALUE - ans > val % 10) {
                throw new IllegalArgumentException("overflow");
            }
            ans += val % 10;
            val /= 10;
        }
        return ans;
    }
}
