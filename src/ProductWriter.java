import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

//        000001, Pipeweed, Long Bottom Leaf, 600.0
//        000002, Lembas, Elven Wayfare Bread, 200.0
//        000003, Wine, Woodland Elf Wine, 400.0
//        000004, Mushrooms, Farmer Took’s Finest, 125.0
//        000005, Mithril, Enchanted Dwarven Armor, 3000.0


public class ProductWriter {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        ArrayList<Product> recs = new ArrayList<>();

        String id;
        String name;
        String description;
        double cost;

        Product prod;

        boolean done = false;

        do{
            id = SafeInput.getNonZeroLenString(in, "Enter the ID");
            name = SafeInput.getNonZeroLenString(in, "Enter the Name");
            description = SafeInput.getNonZeroLenString(in, "Enter the Description");
            cost = SafeInput.getDouble(in, "Enter the Cost");

            prod = new Product(id, name, description, cost);

            // System.out.println(rec);

            recs.add(prod);

            done = SafeInput.getYNConfirm(in, "Are you done?");

        }while(!done);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(Product r : recs)
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
