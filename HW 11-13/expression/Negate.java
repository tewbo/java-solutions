package expression;

import java.util.Objects;

public class Negate extends ExpressionElement {
    protected final ExpressionElement expressionElement;

    public Negate(ExpressionElement expressionElement) {
        this.expressionElement = expressionElement;
    }

    @Override
    public double evaluate(double x) {
        return -expressionElement.evaluate(x);
    }

    @Override
    public int evaluate(int x) {
        return -expressionElement.evaluate(x);
    }

    @Override
    public String toMiniStringWithPriority(int topPriority) {
        if (topPriority == UNARY_PRIORITY) {
            return " -" + expressionElement.toMiniStringWithPriority(UNARY_PRIORITY);
        }
        return "-" + expressionElement.toMiniStringWithPriority(UNARY_PRIORITY);
    }

    @Override
    public String toString() {
        return "-(" + expressionElement.toString() + ")";
    }

    @Override
    public boolean equals(Object object) {
        return Objects.nonNull(object)
                && getClass().equals(object.getClass())
                && expressionElement.equals(((Negate) object).expressionElement);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return - expressionElement.evaluate(x, y, z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expressionElement.hashCode(), getClass());
    }
}
