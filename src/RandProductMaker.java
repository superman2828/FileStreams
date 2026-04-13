import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.RandomAccessFile;

public class RandProductMaker extends JFrame {

//            000001,Pipeweed,Long Bottom Leaf,600.0
//            000002,Lembas,Elven Wayfare Bread,200.0
//            000003,Wine,Woodland Elf Wine,400.0
//            000004,Mushrooms,Farmer Took's Finest,125.0
//            000005,Mithril,Enchanted Dwarven Armor,3000.0

    JTextField nameTF, productIDTF, descriptionTF, costTF, recordCountTF;
    JLabel name, productID, description, cost, recordCount;
    JButton add, quit;
    int recordCountNum;
    RandomAccessFile file;

    public RandProductMaker() {

        setTitle("RandProductMaker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        try
        {
            file = new RandomAccessFile("products.dat", "rw");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        JPanel inputPnl = new JPanel();
        inputPnl.setBorder(new EmptyBorder(10,10,10,10));
        inputPnl.setLayout(new GridLayout(5,2));
        inputPnl.add(productID =  new JLabel("Product ID: "));
        inputPnl.add(productIDTF =  new JTextField(35));
        inputPnl.add(name = new JLabel("Name: "));
        inputPnl.add(nameTF = new JTextField(35));
        inputPnl.add(description = new JLabel("Description: "));
        inputPnl.add(descriptionTF =  new JTextField(35));
        inputPnl.add(cost = new JLabel("Cost: "));
        inputPnl.add(costTF =   new JTextField(35));
        inputPnl.add(recordCount = new JLabel("Records Added: "));
        recordCountTF =   new JTextField(0 + "");
        recordCountTF.setEditable(false);
        inputPnl.add(recordCountTF);

        add(inputPnl, BorderLayout.CENTER);

        JPanel buttonPnl = new JPanel();
        buttonPnl.setLayout(new FlowLayout());
        buttonPnl.add(add = new JButton("Add"));
        buttonPnl.add(quit = new JButton("Quit"));
        add.addActionListener(e -> addRecord());
        quit.addActionListener(e -> System.exit(0));
        add(buttonPnl, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addRecord()
    {
        try
        {
            String id = productIDTF.getText().trim();
            String name = nameTF.getText().trim();
            String desc = descriptionTF.getText().trim();
            double cost = Double.parseDouble(costTF.getText().trim());

            if(id.isEmpty() || name.isEmpty() || desc.isEmpty())
            {
                JOptionPane.showMessageDialog(this,"All fields required");
                return;
            }

            Product p = new Product(id,name,desc,cost);

            file.seek(file.length());   // move to end of file
            p.writeToFile(file);

            recordCountNum++;
            recordCountTF.setText(recordCountNum + "");

            productIDTF.setText("");
            nameTF.setText("");
            descriptionTF.setText("");
            costTF.setText("");

            productIDTF.requestFocus();

        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this,"Invalid Input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {RandProductMaker frame = new RandProductMaker();});
    }

}
