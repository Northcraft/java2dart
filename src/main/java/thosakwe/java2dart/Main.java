package thosakwe.java2dart;

import com.github.javaparser.*;
import org.apache.commons.cli.*;
import org.apache.commons.cli.ParseException;
import thosakwe.java2dart.codegen.dart.DartLibrary;
import thosakwe.java2dart.transpiler.JavaToDartTranspiler;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Options opts = cliOptions();
            CommandLine commandLine = new DefaultParser().parse(opts, args);

            if (commandLine.hasOption("help")) {
                printUsage();
                return;
            }

            List<String> rest = commandLine.getArgList();

            if (rest.isEmpty()) {
                printUsage();
                System.exit(1);
            }

            String filename = rest.get(0);
            JavaToDartTranspiler transpiler = new JavaToDartTranspiler(commandLine, filename);
            DartLibrary library = transpiler.transpile(filename);
            library.saveToFile();
        } catch (ParseException e) {
            printUsage();
            System.exit(1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private static Options cliOptions() {
        return new Options()
                .addOption("h", "help", false, "Print this help information.")
                .addOption("o", "out", true, "The output filename.")
                .addOption(Option.builder()
                        .longOpt("verbose")
                        .desc("Print verbose output.")
                        .build());
    }

    private static void printUsage() {
        new HelpFormatter().printHelp("java2dart [options..] <filename>", cliOptions());
    }
}
