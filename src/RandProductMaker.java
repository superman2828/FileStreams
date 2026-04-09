import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RandProductMaker extends JFrame {

    JTextField nameTF, productIDTF, descriptionTF, costTF, recordCountTF;
    JLabel name, productID, description, cost, recordCount;
    JButton add, quit;
    int recordCountNum;

    public RandProductMaker() {

        setTitle("RandProductMaker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

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
        add(buttonPnl, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {RandProductMaker frame = new RandProductMaker();});
    }

}
