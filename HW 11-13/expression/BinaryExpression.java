package expression;

import java.util.Objects;

public abstract class BinaryExpression extends ExpressionElement {
    final protected ExpressionElement operand1;
    final protected ExpressionElement operand2;

    public BinaryExpression(ExpressionElement operand1, ExpressionElement operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public String toMiniStringForBinary(final int topPriority, final int leftPriority,
                                        final int rightPriority, final int selfPriority, final String operation) {
        StringBuilder sb = new StringBuilder();
        if (topPriority > selfPriority) {
            sb.append('(');
        }
        sb.append(operand1.toMiniStringWithPriority(leftPriority));
        sb.append(" ");
        sb.append(operation);
        sb.append(" ");
        sb.append(operand2.toMiniStringWithPriority(rightPriority));
        if (topPriority > selfPriority) {
            sb.append(')');
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        return Objects.nonNull(object)
                && getClass().equals(object.getClass())
                && operand1.equals(((BinaryExpression) object).operand1)
                && operand2.equals(((BinaryExpression) object).operand2);
    }



    @Override
    public int hashCode() {
        final int M = 1_000_019;
        final int base = 997;
        return (base * operand1.hashCode() * 1337 + operand2.hashCode() * 997 + getClass().hashCode()) % M;
    }
}
