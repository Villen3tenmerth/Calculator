package token;

public class Multiply extends Operation {
    public Multiply() {
        super(2);
    }

    @Override
    public int eval(int arg1, int arg2) {
        return arg1 * arg2;
    }

    @Override
    public String toString() {
        return "*";
    }
}
