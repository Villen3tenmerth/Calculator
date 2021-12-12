package token;

public class Add extends Operation {
    public Add() {
        super(1);
    }

    @Override
    public int eval(int arg1, int arg2) {
        return arg1 + arg2;
    }

    @Override
    public String toString() {
        return "+";
    }
}
