package expression;

public class Const extends ExpressionElement {
    final private double value;
    final private boolean isDouble;

    public Const(int value) {
        this.value = value;
        isDouble = false;
    }

    public Const(double value) {
        this.value = value;
        isDouble = true;
    }

    @Override
    public int evaluate(int x) {
        return (int) value;
    }

    @Override
    public double evaluate(double x) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int) value;
    }

    @Override
    public String toString() {
        return isDouble ? String.valueOf(value) : String.valueOf((int)value);
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public String toMiniStringWithPriority(int topPriority) {
        if (topPriority == UNARY_PRIORITY) {
            return " " + toMiniString();
        }
        return toMiniString();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Const) {
            return value == ((Const) object).value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return ((int) (value * 1000) + (int) value) % (Integer.MAX_VALUE / 4);
    }
}
