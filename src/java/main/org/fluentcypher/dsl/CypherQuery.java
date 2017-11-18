package org.fluentcypher.dsl;


import org.fluentcypher.dsl.writer.StringQueryWriter;

import java.util.function.Function;

public class CypherQuery {
    private MatchExpression expression;

    public CypherQuery match(Function<MatchExpression, MatchExpression> descriptor) {
        expression = descriptor.apply(new MatchExpression());
        return this;
    }

    public void write(StringQueryWriter writer) {
        expression.write(writer);
    }
}
