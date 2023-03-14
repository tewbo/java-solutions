package expression;

public class Multiply extends BinaryExpression {
    public static final int PRIORITY = 6;
    public Multiply(ExpressionElement operand1, ExpressionElement operand2) {
        super(operand1, operand2);
    }

    @Override
    public int evaluate(int x) {
        return operand1.evaluate(x) * operand2.evaluate(x);
    }

    @Override
    public double evaluate(double x) {
        return operand1.evaluate(x) * operand2.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operand1.evaluate(x, y, z) * operand2.evaluate(x, y, z);
    }

    @Override
    public String toString() {
        return "(" + operand1.toString() + " * " + operand2.toString() + ")";
    }

    @Override
    public String toMiniStringWithPriority(int topPriority) {
        return toMiniStringForBinary(topPriority, Divide.PRIORITY, Multiply.PRIORITY, Multiply.PRIORITY, "*");
    }
}
