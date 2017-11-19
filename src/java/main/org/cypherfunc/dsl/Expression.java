package org.cypherfunc.dsl;


import org.cypherfunc.dsl.writer.StringQueryWriter;

public interface Expression {
    void write(StringQueryWriter writer);
}
