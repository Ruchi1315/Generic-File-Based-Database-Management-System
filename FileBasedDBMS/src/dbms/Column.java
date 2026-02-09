package dbms;

public class Column {
    private String name;
    private String type; // INT, STRING, DOUBLE

    public Column(String name, String type) {
        this.name = name;
        this.type = type.toUpperCase();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
