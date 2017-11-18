package org.fluentcypher.dsl;


import org.fluentcypher.dsl.writer.StringQueryWriter;

import java.util.function.Function;

public class MatchExpression {
    private NodeExpression expression;

    public MatchExpression node(Function<NodeExpression, NodeExpression> descriptor) {
        expression = descriptor.apply(new NodeExpression());
        return this;
    }

    public void write(StringQueryWriter writer) {
        writer.write("MATCH ");
        expression.write(writer);
    }
}
