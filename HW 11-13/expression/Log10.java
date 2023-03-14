package expression;

import java.util.Objects;

public class Log10 extends ExpressionElement {

    private final ExpressionElement expressionElement;

    public Log10(ExpressionElement expressionElement) {
        this.expressionElement = expressionElement;
    }

    @Override
    public double evaluate(double x) {
        throw new IllegalArgumentException("Double values are incompatible with log10.");
    }

    @Override
    public int evaluate(int x) {
        int val = expressionElement.evaluate(x);
        if (val < 1) {
            throw new IllegalArgumentException("Values less than zero are incompatible with log10.");
        }
        int ans = 0;
        while (val >= 10) {
            ans++;
            val /= 10;
        }
        return ans;
    }

    @Override
    public String toMiniStringWithPriority(int topPriority) {
        if (topPriority == UNARY_PRIORITY) {
            return " log10" + expressionElement.toMiniStringWithPriority(UNARY_PRIORITY);
        }
        return "log10" + expressionElement.toMiniStringWithPriority(UNARY_PRIORITY);
    }

    @Override
    public boolean equals(Object object) {
        return Objects.nonNull(object)
                && getClass().equals(object.getClass())
                && expressionElement.equals(((Log10) object).expressionElement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expressionElement.hashCode(), getClass());
    }

    @Override
    public String toString() {
        return "log10(" + expressionElement.toString() + ")";
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int val = expressionElement.evaluate(x, y, z);
        if (val < 1) {
            throw new IllegalArgumentException("Values less than zero are incompatible with log10.");
        }
        int ans = 0;
        while (val >= 10) {
            ans++;
            val /= 10;
        }
        return ans;
    }
}
