package dbms;

import java.io.*;

public class TableManager {

    private static final String DATA_DIR = "data/";

    public static void createTable(Schema schema) throws IOException {

        // 1. Save schema
        FileHandler.saveSchema(schema);

        // 2. Create CSV file with header
        File csvFile = new File(DATA_DIR + schema.getTableName() + ".csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));

        boolean first = true;
        for (Column col : schema.getColumns()) {
            if (!first) writer.write(",");
            writer.write(col.getName());
            first = false;
        }
        writer.newLine();
        writer.close();

        // 3. 🔥 Generate Java class on demand
        CodeGenerator.generateJavaClass(schema);

        // 4. 🔥 Compile generated class
        CompilerUtil.compile(schema.getTableName());

        // 5. 🔥 Load class dynamically
        try {
            Object obj = DynamicLoader.createInstance(schema.getTableName());
            System.out.println("Loaded class at runtime: " + obj.getClass().getName());
        } catch (Exception e) {
            System.out.println("Dynamic loading failed");
            e.printStackTrace(); // IMPORTANT for debugging
        }

        System.out.println("Table created: " + schema.getTableName());
    }
}
