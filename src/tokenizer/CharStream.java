package tokenizer;

import java.io.IOException;
import java.io.InputStream;

public class CharStream {
    private final InputStream inputStream;
    private char current;

    public CharStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public char nextChar() {
        try {
            current = (char) inputStream.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return current;
    }

    public char curChar() {
        return current;
    }
}
