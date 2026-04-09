import java.util.Calendar;

public class Person
{

    Calendar c = Calendar.getInstance();

    private String IDNum;
    private String firstName;
    private String lastName;
    private String title;
    private int YOB;

    // Constructor
    public Person(String IDNum, String firstName, String lastName, String title, int YOB) {
        this.IDNum = IDNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.YOB = YOB;
    }

    // Getters and Setters
    public String getIDNum() {
        return IDNum;
    }

    public void setIDNum(String IDNum) {
        this.IDNum = IDNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYOB() {
        return YOB;
    }

    public void setYOB(int YOB) {
        this.YOB = YOB;
    }

    // toString
    // Overrides default toString method
    @Override
    public String toString() {
        return "Person{" +
                "IDNum='" + IDNum + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", YOB=" + YOB +
                '}';
    }


    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFormalName() {
        return title + " " + firstName + " " + lastName;
    }

    public int getAge(){ return c.get(Calendar.YEAR) - YOB; }

    public int getAge(int year){ return c.get(Calendar.YEAR) - year;}

    public String toCSVString() {
        return IDNum + "," + firstName + "," + lastName + "," + title + "," + YOB;
    }

    public String toXMLString() {
        return "<Person>" +
                "<IDNum>" + IDNum + "</IDNum>" +
                "<FirstName>" + firstName + "</FirstName>" +
                "<LastName>" + lastName + "</LastName>" +
                "<Title>" + title + "</Title>" +
                "<YOB>" + YOB + "</YOB>" +
                "</Person>";
    }

    public String toJSONString() {
        return "{" +
                "\"IDNum\":\"" + IDNum + "\"," +
                "\"firstName\":\"" + firstName + "\"," +
                "\"lastName\":\"" + lastName + "\"," +
                "\"title\":\"" + title + "\"," +
                "\"YOB\":" + YOB +
                "}";
    }

}
