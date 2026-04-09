import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

//        000001, Bilbo, Baggins, Esq., 1060
//        000002, Frodo, Baggins, Esq., 1120
//        000003, Samwise, Gamgee, Esq., 1125
//        000004, Peregrin, Took, Esq., 1126
//        000005, Meridoc, Brandybuck, Esq., 1126


public class PersonGenerator {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        Person perp;

        // ArrayList<String> recs = new ArrayList<>();
        ArrayList<Person> recs = new ArrayList<>();

        String id;
        String firstName;
        String lastName;
        String title;
        int yob;

        boolean done = false;

        do{
            id = SafeInput.getNonZeroLenString(in, "Enter the ID");
            firstName = SafeInput.getNonZeroLenString(in, "Enter the First Name");
            lastName = SafeInput.getNonZeroLenString(in, "Enter the Last Name");
            title = SafeInput.getNonZeroLenString(in, "Enter the Title");
            yob = SafeInput.getRangedInt(in, "Enter the Year of Birth", 1940, 2010);

            // rec = id + ", " + firstName + ", " + lastName + ", " + title + ", " + yob;
            perp = new Person(id, firstName, lastName, title, yob);

            // System.out.println(rec);

            recs.add(perp);

            done = SafeInput.getYNConfirm(in, "Are you done?");

        }while(!done);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(Person r : recs)
            {
                writer.write(r.toCSVString(), 0, r.toCSVString().length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}
