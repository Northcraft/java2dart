package thosakwe.java2dart.codegen.dart;

import thosakwe.java2dart.codegen.CodeBuilder;
import thosakwe.java2dart.codegen.Compilable;

import java.util.ArrayList;
import java.util.List;

public class DartLibrary extends Compilable {
    private String name = null;
    private final List<Compilable> topLevelDefinitions = new ArrayList<>();

    public List<Compilable> getTopLevelDefinitions() {
        return topLevelDefinitions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void compile(CodeBuilder builder) {
        if (name != null)
            builder.printf("library %s%n", name);

        for (Compilable def : topLevelDefinitions) {
            builder.println();
            def.compile(builder);
        }
    }

    public void saveToFile() {

    }
}
