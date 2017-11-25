package org.cypherfunc.dsl;


import org.cypherfunc.dsl.writer.StringQueryWriter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class CypherQuery {
    private List<MatchExpression> expressions = new ArrayList<>();

    public CypherQuery match(Function<MatchExpression, MatchExpression> descriptor) {
        expressions.add(descriptor.apply(new MatchExpression()));
        return this;
    }

    public void write(StringQueryWriter writer) {
        Iterator<MatchExpression> iterator = expressions.iterator();

        if (iterator.hasNext()) {
            iterator.next().write(writer);

            iterator.forEachRemaining(i -> {
                writer.write("\n");
                i.write(writer);
            });
        }
    }
}
