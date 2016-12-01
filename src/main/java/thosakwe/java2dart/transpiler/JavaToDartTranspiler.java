package thosakwe.java2dart.transpiler;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import org.apache.commons.cli.CommandLine;
import thosakwe.java2dart.codegen.dart.DartLibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class JavaToDartTranspiler {
    private final CommandLine commandLine;
    private final String filename;

    public JavaToDartTranspiler(CommandLine commandLine, String filename) {
        this.commandLine = commandLine;
        this.filename = filename;
    }

    private void printDebug(String msg) {
        if (commandLine.hasOption("verbose")) {
            System.out.println(msg);
        }
    }

    private void printDebug(String format, Object... args) {
        printDebug(String.format(format, args));
    }

    public DartLibrary transpile(String filename) throws IOException, ParseException {
        InputStream in = new FileInputStream(filename);
        CompilationUnit ast = JavaParser.parse(in);
        DartLibrary lib = new DartLibrary();
        in.close();

        return lib;
    }
}
