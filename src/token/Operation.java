package token;

import visitor.TokenVisitor;

public abstract class Operation implements Token {
    private final int priority;

    Operation(int priority) {
        this.priority = priority;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    public int getPriority() {
        return priority;
    }

    public abstract int eval(int arg1, int arg2);
}
