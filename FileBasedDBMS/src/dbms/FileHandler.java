package dbms;

import java.io.*;

public class FileHandler {

    private static final String DATA_DIR = "data/";

    static {
        new File(DATA_DIR).mkdir();
    }

    public static void saveSchema(Schema schema) throws IOException {
        File file = new File(DATA_DIR + schema.getTableName() + ".schema");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (Column col : schema.getColumns()) {
            writer.write(col.getName() + "," + col.getType());
            writer.newLine();
        }
        writer.close();
    }

    public static Schema loadSchema(String tableName) throws IOException {
        File file = new File(DATA_DIR + tableName + ".schema");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        Schema schema = new Schema(tableName);
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            schema.addColumn(new Column(parts[0], parts[1]));
        }
        reader.close();
        return schema;
    }
}
