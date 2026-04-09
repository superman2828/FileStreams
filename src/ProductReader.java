import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {
    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<String> lines = new ArrayList<>();

        final int FIELDS_LENGTH = 4;

        String id, name, description;
        double cost;

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                // Finally we can read the file LOL!
                int line = 0;  // if we want to keep track of the line numbers
                while (reader.ready()) {
                    rec = reader.readLine();
                    lines.add(rec);  // read all the lines into memory in an array list
                    line++;
                    // echo to screen
                    System.out.printf("\nLine %4d %-60s ", line, rec);
                }
                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nData file read!");

                // Now process the lines in the arrayList
                // Split the line into the fields by using split with a comma
                // use trim to remove leading and trailing spaces
                // Numbers need to be converted back to numberic values. Here only
                // the last field cost is a double the rest are strings.

                System.out.printf("\n%-8s%-25s%-25s%-25s", "ID", "Name", "Description", "Cost");
                System.out.print("\n=================================================================");

                for (String l : lines) {
                    String[] fields = l.split(",");

                    if (fields.length == FIELDS_LENGTH) {
                        id = fields[0].trim();
                        name = fields[1].trim();
                        description = fields[2].trim();
                        cost = Double.parseDouble(fields[3].trim());

                        System.out.printf("\n%-8s%-25s%-25s$%6.2f", id, name, description, cost);
                    } else {
                        System.out.println("\nInvalid record: " + l);
                    }

                }
            }
            else //User closed file chooser without choosing a file
            {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        }   //End of try block
        catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
