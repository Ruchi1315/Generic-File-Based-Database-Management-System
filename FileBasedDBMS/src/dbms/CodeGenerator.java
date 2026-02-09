package dbms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CodeGenerator {

    private static final String GEN_DIR = "generated/";

    static {
        new File(GEN_DIR).mkdir();
    }

    public static void generateJavaClass(Schema schema) throws IOException {
        String className = schema.getTableName();
        File file = new File(GEN_DIR + className + ".java");

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write("public class " + className + " {\n\n");

        // Fields
        for (Column col : schema.getColumns()) {
            writer.write("    public " +
                    mapType(col.getType()) + " " +
                    col.getName() + ";\n");
        }

        writer.write("\n    public " + className + "() {}\n");

        writer.write("\n}\n");

        writer.close();

        System.out.println("Generated class: " + className + ".java");
    }

    private static String mapType(String type) {
        switch (type) {
            case "INT":
                return "int";
            case "DOUBLE":
                return "double";
            case "STRING":
                return "String";
            default:
                return "String";
        }
    }
}
