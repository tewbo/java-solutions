package expression;

import java.util.Objects;

public class Pow10 extends ExpressionElement {
    private final ExpressionElement expressionElement;

    public Pow10(ExpressionElement expressionElement) {
        this.expressionElement = expressionElement;
    }

    @Override
    public double evaluate(double x) {
        throw new IllegalArgumentException("Double values are incompatible with pow10.");
    }

    @Override
    public int evaluate(int x) {
        int val = expressionElement.evaluate(x);
        if (val < 0) {
            throw new IllegalArgumentException("Values less than one are incompatible with pow10");
        }
        int ans = 1;
        for (int i = 0; i < val; i++) {
            ans *= 10;
        }
        return ans;
    }

    @Override
    public String toMiniStringWithPriority(int topPriority) {
        if (topPriority == UNARY_PRIORITY) {
            return " pow10" + expressionElement.toMiniStringWithPriority(UNARY_PRIORITY);
        }
        return "pow10" + expressionElement.toMiniStringWithPriority(UNARY_PRIORITY);
    }

    @Override
    public boolean equals(Object object) {
        return Objects.nonNull(object)
                && getClass().equals(object.getClass())
                && expressionElement.equals(((Pow10) object).expressionElement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expressionElement.hashCode(), getClass());
    }

    @Override
    public String toString() {
        return "pow10(" + expressionElement.toString() + ")";
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int val = expressionElement.evaluate(x, y, z);
        if (val < 0) {
            throw new IllegalArgumentException("Values less than one are incompatible with pow10");
        }
        int ans = 1;
        for (int i = 0; i < val; i++) {
            if (ans > Integer.MAX_VALUE / 10) {
                throw new IllegalArgumentException("overflow");
            }
            ans *= 10;
        }
        return ans;
    }
}
