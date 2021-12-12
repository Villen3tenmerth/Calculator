package token;

public class Subtract extends Operation {
    public Subtract() {
        super(1);
    }

    @Override
    public int eval(int arg1, int arg2) {
        return arg1 - arg2;
    }

    @Override
    public String toString() {
        return "-";
    }
}
