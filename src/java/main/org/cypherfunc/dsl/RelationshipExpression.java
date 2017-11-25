package org.cypherfunc.dsl;

import org.cypherfunc.dsl.writer.StringQueryWriter;


public class RelationshipExpression implements Expression {
    private String alias;
    private boolean right;
    private boolean left;
    private String label;

    @Override
    public void write(StringQueryWriter writer) {
        if (left) {
            writer.write("<");
        }

        writer.write("-");

        writeBody(writer);

        writer.write("-");

        if (right) {
            writer.write(">");
        }
    }

    private void writeBody(StringQueryWriter writer) {
        boolean hasBody = alias != null || label != null;

        if (!hasBody) {
            return;
        }

        writer.write("[");

        if (alias != null) {
            writer.write("r");
        }

        if (label != null) {
            writer.write(String.format(":%s", label));
        }

        writer.write("]");
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

    public RelationshipExpression withLabel(String label) {
        this.label = label;
        return this;
    }
}
