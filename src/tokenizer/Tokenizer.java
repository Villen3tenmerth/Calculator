package tokenizer;

import token.*;
import token.Number;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private final CharStream stream;
    private State state;

    public Tokenizer(InputStream inputStream) {
        stream = new CharStream(inputStream);
        state = State.START;
    }

    private Token nextToken() {
        switch (state) {
            case START:
                char c = stream.curChar();
                Token res;
                switch (c) {
                    case '(':
                        res = new OpenBrace();
                        break;
                    case ')':
                        res = new CloseBrace();
                        break;
                    case '+':
                        res = new Add();
                        break;
                    case '-':
                        res = new Subtract();
                        break;
                    case '*':
                        res = new Multiply();
                        break;
                    case '/':
                        res = new Divide();
                        break;
                    default:
                        if (Character.isDigit(c)) {
                            state = State.NUMBER;
                        } else if (c == (char)-1) {
                            state = State.END;
                        } else if (Character.isWhitespace(c)) {
                            stream.nextChar();
                        } else {
                            state = State.ERROR;
                        }
                        return nextToken();
                }
                stream.nextChar();
                return res;
            case NUMBER:
                int number = stream.curChar() - '0';
                while (Character.isDigit(stream.nextChar())) {
                    number = number * 10 + (stream.curChar() - '0');
                }
                state = State.START;
                return new Number(number);
            case ERROR:
                throw new RuntimeException("Error while parsing expression");
            case END:
                return null;
            default:
                throw new RuntimeException("Invalid tokenizer state");
        }
    }

    public List<Token> tokenize() {
        List<Token> result = new ArrayList<>();
        stream.nextChar();
        while (state != State.END) {
            Token token = nextToken();
            if (token != null) {
                result.add(token);
            }
        }
        return result;
    }
}
