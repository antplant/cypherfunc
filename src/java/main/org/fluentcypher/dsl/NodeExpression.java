package org.fluentcypher.dsl;

import org.fluentcypher.dsl.writer.StringQueryWriter;

public class NodeExpression {
    private String alias;
    private String label;

    public NodeExpression withAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public void write(StringQueryWriter writer) {
        writer.write("(");

        if (alias != null) {
            writer.write(alias);
        }

        if (label != null) {
            writer.write(String.format(":%s", label));
        }

        writer.write(")");
    }

    public NodeExpression withLabel(String label) {
        this.label = label;
        return this;
    }
}
