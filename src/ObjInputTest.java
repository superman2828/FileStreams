public class ObjInputTest {
    public static void main(String[] args) {

        SafeInputObject sio = new SafeInputObject();

        String words = sio.getNonZeroLenString("Enter some words");

        System.out.println("You entered: " + words);

        int number = sio.getRangedInt("Enter an int value", 1, 100);

        System.out.println("You entered a ranged int: " + number);

        int anyInt = sio.getInt("Enter any int value");

        System.out.println("You entered: " + anyInt);

        double rangedValue = sio.getRangedDouble("Enter a double value ", 10.0, 20.0);

        System.out.println("You entered: " + rangedValue);

        double anyDouble = sio.getDouble("Enter any double value");

        System.out.println("You entered: " + anyDouble);

        boolean bool = sio.getYNConfirm("Enter Y/N:");

        System.out.println("You entered: " + bool);

        String regEx = sio.getRegExString("Enter a string that matches the pattern d3-d3-d4","\\d{3}-\\d{3}-\\d{4}");

        System.out.println("You entered: " + regEx);

    }
}
