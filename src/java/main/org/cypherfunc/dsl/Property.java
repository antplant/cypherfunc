package org.cypherfunc.dsl;

import org.cypherfunc.dsl.writer.StringQueryWriter;

public class Property {
    private final String propertyName;
    private final String parameterName;

    public Property(String propertyName, String parameterName) {
        this.propertyName = propertyName;
        this.parameterName = parameterName;
    }

    public void write(StringQueryWriter writer) {
        writer.write(String.format(" %s : $%s ", propertyName, parameterName));
    }
}
