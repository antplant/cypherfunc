package org.cypherfunc.dsl;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.cypherfunc.dsl.writer.StringQueryWriter;

import java.util.ArrayList;
import java.util.function.Function;

public class MatchExpression {
    private final ArrayList<Expression> expressions = new ArrayList<>();

    public MatchExpression node(Function<NodeExpression, NodeExpression> descriptor) {
        expressions.add(descriptor.apply(new NodeExpression()));
        return this;
    }

    public void write(StringQueryWriter writer) {
        writer.write("MATCH ");
        expressions.forEach(e -> e.write(writer));
    }

    public MatchExpression relatedTo() {
        expressions.add(new RelationshipExpression());
        return this;
    }

    public MatchExpression relatedTo(Function<RelationshipExpression, RelationshipExpression> descriptor) {
        expressions.add(descriptor.apply(new RelationshipExpression()));
        return this;
    }
}
