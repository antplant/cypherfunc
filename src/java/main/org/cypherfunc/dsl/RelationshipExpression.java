package org.cypherfunc.dsl;

import org.cypherfunc.dsl.writer.StringQueryWriter;


public class RelationshipExpression implements Expression {
    private String alias;
    private boolean right;
    private boolean left;

    @Override
    public void write(StringQueryWriter writer) {
        if (left) {
            writer.write("<");
        }

        writer.write("-");

        if(alias != null) {
            writer.write("[r]");
        }

        writer.write("-");

        if(right) {
            writer.write(">");
        }
    }

    public RelationshipExpression withAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public RelationshipExpression right() {
        this.right = true;
        return this;
    }

    public RelationshipExpression left() {
        this.left = true;
        return this;
    }
}
