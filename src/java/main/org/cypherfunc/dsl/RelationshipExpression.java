package org.cypherfunc.dsl;

import org.cypherfunc.dsl.writer.StringQueryWriter;


public class RelationshipExpression implements Expression {
    private String alias;

    @Override
    public void write(StringQueryWriter writer) {
        writer.write("-");

        if(alias != null) {
            writer.write("[r]");
        }

        writer.write("->");
    }

    public RelationshipExpression withAlias(String alias) {
        this.alias = alias;
        return this;
    }
}
