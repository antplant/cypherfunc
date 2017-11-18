package org.fluentcypher.dsl;

import org.fluentcypher.dsl.writer.StringQueryWriter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CypherQueryTest {
    @Test
    public void matchNode() {
        CypherQuery query = new CypherQuery().match(m -> m
                .node(n -> n
                        .withAlias("n")));

        assertQueryEvaluates(query, "MATCH (n)");
    }

    @Test
    public void matchNodeWithLabel() {
        CypherQuery query = new CypherQuery().match(m -> m
                .node(n -> n
                        .withLabel("Foo")));

        assertQueryEvaluates(query, "MATCH (:Foo)");
    }

    @Test
    public void matchNodeWithLabelAndAlias() {
        CypherQuery query = new CypherQuery().match(m -> m
                .node(n -> n
                        .withAlias("n")
                        .withLabel("Foo")));

        assertQueryEvaluates(query, "MATCH (n:Foo)");
    }

    private void assertQueryEvaluates(CypherQuery query, String expected) {
        StringQueryWriter writer = new StringQueryWriter();
        query.write(writer);

        assertEquals(expected, writer.toString());
    }
}