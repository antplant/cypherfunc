package org.cypherfunc.dsl;

import org.cypherfunc.dsl.writer.StringQueryWriter;
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

    @Test
    public void matchNodeWithProperties() {
        CypherQuery query = new CypherQuery().match(m -> m
                .node(n -> n
                        .withLabel("Test")
                        .withProperty("prop1", "foo")
                        .withProperty("prop2", "bar")));

        assertQueryEvaluates(query,
                "MATCH (:Test { prop1 : \"foo\" , prop2 : \"bar\" })");
    }

    private void assertQueryEvaluates(CypherQuery query, String expected) {
        StringQueryWriter writer = new StringQueryWriter();
        query.write(writer);

        assertEquals(expected, writer.toString());
    }
}