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

    @Test
    public void matchRelationship() {
        CypherQuery query = new CypherQuery()
                .match(m -> m
                        .node(n -> n.withAlias("n"))
                        .relatedTo()
                        .node(n -> n.withAlias("m")));

        assertQueryEvaluates(query, "MATCH (n)--(m)");
    }

    @Test
    public void matchRelationshipWithAlias() {
        CypherQuery query = new CypherQuery()
                .match(m -> m
                        .node(n -> n.withAlias("n"))
                        .relatedTo(r -> r.withAlias("r"))
                        .node(n -> n.withAlias("m")));

        assertQueryEvaluates(query, "MATCH (n)-[r]-(m)");
    }

    @Test
    public void matchRightPointingRelationship() {
        CypherQuery query = new CypherQuery()
                .match(m -> m
                        .node(n -> n.withAlias("n"))
                        .relatedTo(r -> r.right())
                        .node(n -> n));

        assertQueryEvaluates(query, "MATCH (n)-->()");
    }

    @Test
    public void matchLeftPointingRelationship() {
        CypherQuery query = new CypherQuery()
                .match(m -> m
                        .node(n -> n.withAlias("n"))
                        .relatedTo(r -> r.left())
                        .node(n -> n.withAlias("m")));

        assertQueryEvaluates(query, "MATCH (n)<--(m)");
    }

    @Test
    public void matchBidirectionalRelationship() {
        CypherQuery query = new CypherQuery()
                .match(m -> m
                        .node(n -> n.withAlias("n"))
                        .relatedTo(r -> r.left().right())
                        .node(n -> n.withAlias("m")));

        assertQueryEvaluates(query, "MATCH (n)<-->(m)");
    }

    @Test
    public void matchRelationshipWithLabel() {
        CypherQuery query = new CypherQuery()
                .match(m -> m
                        .node(n -> n.withAlias("n"))
                        .relatedTo(r -> r.withLabel("KNOWS"))
                        .node(n -> n.withAlias("m")));

        assertQueryEvaluates(query, "MATCH (n)-[:KNOWS]-(m)");
    }

    @Test
    public void multipleMatches() {
        CypherQuery query = new CypherQuery()
                .match(m -> m.node(n -> n.withAlias("n").withLabel("Foo")))
                .match(m -> m.node(n -> n.withAlias("m").withLabel("Bar")));

        assertQueryEvaluates(query, "MATCH (n:Foo)\nMATCH (m:Bar)");
    }

    private void assertQueryEvaluates(CypherQuery query, String expected) {
        StringQueryWriter writer = new StringQueryWriter();
        query.write(writer);

        assertEquals(expected, writer.toString());
    }
}