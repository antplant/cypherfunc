package org.cypherfunc.dsl;

import org.cypherfunc.dsl.writer.StringQueryWriter;


public class RelationshipExpression implements Expression {
    @Override
    public void write(StringQueryWriter writer) {
        writer.write("-->");
    }
}
