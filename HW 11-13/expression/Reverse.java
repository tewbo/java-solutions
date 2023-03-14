package expression;

import java.util.Objects;

public class Reverse extends ExpressionElement {
    protected final ExpressionElement expressionElement;

    public Reverse(ExpressionElement expressionElement) {
        this.expressionElement = expressionElement;
    }

    @Override
    public double evaluate(double x) {
        throw new IllegalArgumentException("double can't be reversed");
    }

    @Override
    public int evaluate(int x) {
        int val = expressionElement.evaluate(x);
        int ans = 0;
        while (val != 0) {
            ans *= 10;
            ans += val % 10;
            val /= 10;
        }
        return ans;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int val = expressionElement.evaluate(x, y, z);
        int ans = 0;
        while (val != 0) {
            ans *= 10;
            ans += val % 10;
            val /= 10;
        }
        return ans;
    }

    @Override
    public String toMiniStringWithPriority(int topPriority) {
        if (topPriority == UNARY_PRIORITY) {
            return " reverse" + expressionElement.toMiniStringWithPriority(UNARY_PRIORITY);
        }
        return "reverse" + expressionElement.toMiniStringWithPriority(UNARY_PRIORITY);
    }

    @Override
    public String toString() {
        return "reverse(" + expressionElement.toString() + ")";
    }

    @Override
    public boolean equals(Object object) {
        return Objects.nonNull(object)
                && getClass().equals(object.getClass())
                && expressionElement.equals(((Reverse) object).expressionElement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expressionElement.hashCode(), getClass());
    }
}
