package dbms;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Schema student = new Schema("Student");
        student.addColumn(new Column("id", "INT"));
        student.addColumn(new Column("name", "STRING"));
        student.addColumn(new Column("cgpa", "DOUBLE"));

        TableManager.createTable(student);

        RecordManager.insert("Student", Arrays.asList("1", "Ruchi", "8.9"));
        RecordManager.insert("Student", Arrays.asList("2", "Ankit", "8.2"));

        System.out.println("\n--- Records ---");
        RecordManager.readAll("Student");

        RecordManager.deleteById("Student", 1);

        System.out.println("\n--- After Deletion ---");
        RecordManager.readAll("Student");
    }
}
