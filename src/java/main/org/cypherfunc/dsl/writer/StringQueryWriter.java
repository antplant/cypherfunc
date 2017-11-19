package org.cypherfunc.dsl.writer;

public class StringQueryWriter {
    private StringBuilder builder = new StringBuilder();

    public void write(String expression) {
        builder.append(expression);
    }

    public String toString() {
        return builder.toString();
    }
}
