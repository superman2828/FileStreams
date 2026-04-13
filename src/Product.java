public class Product {
    private String ID;
    private String name;
    private String description;
    private double cost;

    public static final int ID_SIZE = 6;
    public static final int NAME_SIZE = 35;
    public static final int DESC_SIZE = 75;

    public static final int RECORD_SIZE =
            (ID_SIZE + NAME_SIZE + DESC_SIZE) * 2 + 8;

    // Constructor
    public Product(String ID, String name, String description, double cost) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    // Getters and Setters

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }

    public String toCSVString() {
        return ID + "," + name + "," + description + "," + cost;
    }

    public String toXMLString() {
        return "<Product>" +
                "<ID>" + ID + "</ID>" +
                "<Name>" + name + "</Name>" +
                "<Description>" + description + "</Description>" +
                "<Cost>" + cost + "</Cost>" +
                "</Product>";
    }

    public String toJSONString() {
        return "{" +
                "\"ID\":\"" + ID + "\"," +
                "\"name\":\"" + name + "\"," +
                "\"description\":\"" + description + "\"," +
                "\"cost\":" + cost +
                "}";
    }

    public static String padString(String s, int length)
    {
        StringBuilder sb = new StringBuilder(s);

        while(sb.length() < length)
            sb.append(" ");

        if(sb.length() > length)
            sb.setLength(length);

        return sb.toString();
    }

    public void writeToFile(java.io.RandomAccessFile file) throws Exception
    {
        file.writeChars(padString(ID, ID_SIZE));
        file.writeChars(padString(name, NAME_SIZE));
        file.writeChars(padString(description, DESC_SIZE));
        file.writeDouble(cost);
    }

}
