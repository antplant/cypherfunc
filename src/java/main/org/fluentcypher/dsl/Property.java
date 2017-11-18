package org.fluentcypher.dsl;

import org.fluentcypher.dsl.writer.StringQueryWriter;

public class Property {
    private final String name;
    private final String value;

    public Property(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public void write(StringQueryWriter writer) {
        writer.write(String.format(" %s : \"%s\" ", name, value));
    }
}
