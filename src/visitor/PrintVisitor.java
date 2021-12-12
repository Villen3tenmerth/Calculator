package visitor;

import token.Brace;
import token.Number;
import token.Operation;
import token.Token;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class PrintVisitor implements TokenVisitor {
    private final OutputStream outputStream;

    public PrintVisitor(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    private void write(Token token) {
        try {
            outputStream.write((token.toString() + " ").getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void visit(Number token) {
        write(token);
    }

    @Override
    public void visit(Operation token) {
        write(token);
    }

    @Override
    public void visit(Brace token) {
        write(token);
    }
}
