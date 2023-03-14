package expression;

public abstract class ExpressionElement implements Expression, DoubleExpression, TripleExpression {
    public final static int LOWEST_PRIORITY = 0;
    public final static int UNARY_PRIORITY = 10;

    public abstract String toMiniStringWithPriority(int topPriority);

    public abstract boolean equals(Object object);

    public abstract int hashCode();

    public abstract String toString();

    @Override
    public String toMiniString() {
        return toMiniStringWithPriority(LOWEST_PRIORITY);
    }
}
