package dbms;

import java.io.*;
import java.util.*;

public class RecordManager {

    private static final String DATA_DIR = "data/";

    public static void insert(String tableName, List<String> values) throws Exception {
        Schema schema = FileHandler.loadSchema(tableName);

        if (values.size() != schema.getColumns().size()) {
            throw new Exception("Column count mismatch");
        }

        // Type validation
        for (int i = 0; i < values.size(); i++) {
            validateType(values.get(i), schema.getColumns().get(i).getType());
        }

        BufferedWriter writer = new BufferedWriter(
                new FileWriter(DATA_DIR + tableName + ".csv", true)
        );

        writer.write(String.join(",", values));
        writer.newLine();
        writer.close();
    }

    public static void readAll(String tableName) throws IOException {
        BufferedReader reader = new BufferedReader(
                new FileReader(DATA_DIR + tableName + ".csv")
        );

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

    public static void deleteById(String tableName, int id) throws IOException {
        File input = new File(DATA_DIR + tableName + ".csv");
        File temp = new File(DATA_DIR + "temp.csv");

        BufferedReader reader = new BufferedReader(new FileReader(input));
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp));

        String line;
        boolean firstLine = true;

        while ((line = reader.readLine()) != null) {
            if (firstLine) {
                writer.write(line);
                writer.newLine();
                firstLine = false;
                continue;
            }

            String[] parts = line.split(",");
            if (Integer.parseInt(parts[0]) != id) {
                writer.write(line);
                writer.newLine();
            }
        }

        reader.close();
        writer.close();

        input.delete();
        temp.renameTo(input);
    }

    private static void validateType(String value, String type) throws Exception {
        try {
            switch (type) {
                case "INT":
                    Integer.parseInt(value);
                    break;
                case "DOUBLE":
                    Double.parseDouble(value);
                    break;
                case "STRING":
                    break;
                default:
                    throw new Exception("Unknown type");
            }
        } catch (Exception e) {
            throw new Exception("Type mismatch: expected " + type);
        }
    }
}
