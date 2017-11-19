package org.cypherfunc.dsl;
import org.cypherfunc.dsl.writer.StringQueryWriter;

import java.util.ArrayList;

public class NodeExpression {
    private String alias;
    private String label;
    private ArrayList<Property> properties = new ArrayList<>();

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

        writeProperties(writer);

        writer.write(")");
    }

    private void writeProperties(StringQueryWriter writer) {
        if (!properties.isEmpty()) {
            writer.write(" {");

            String delimiter = "";
            for(Property prop : properties) {
                writer.write(delimiter);
                prop.write(writer);
                delimiter = ",";
            }

            writer.write("}");
        }
    }

    public NodeExpression withLabel(String label) {
        this.label = label;
        return this;
    }

    public NodeExpression withProperty(String name, String value) {
        properties.add(new Property(name, value));
        return this;
    }
}
