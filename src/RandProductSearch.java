import javax.swing.*;
import java.awt.*;
import java.io.RandomAccessFile;

public class RandProductSearch extends JFrame {

    //            000001,Pipeweed,Long Bottom Leaf,600.0
    //            000002,Lembas,Elven Wayfare Bread,200.0
    //            000003,Wine,Woodland Elf Wine,400.0
    //            000004,Mushrooms,Farmer Took's Finest,125.0
    //            000005,Mithril,Enchanted Dwarven Armor,3000.0

    JTextField searchTF;
    JTextArea resultTA;
    JButton searchBtn, quitBtn;

    RandomAccessFile file;

    public RandProductSearch() {

        setTitle("RandProductSearch");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel searchPanel = new JPanel(new FlowLayout());

        searchPanel.add(new JLabel("Enter partial product name:"));

        searchTF = new JTextField(20);
        searchPanel.add(searchTF);

        searchBtn = new JButton("Search");
        searchPanel.add(searchBtn);

        add(searchPanel, BorderLayout.NORTH);

        resultTA = new JTextArea();
        resultTA.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTA);

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        quitBtn = new JButton("Quit");

        buttonPanel.add(quitBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        searchBtn.addActionListener(e -> searchProducts());
        quitBtn.addActionListener(e -> System.exit(0));

        try
        {
            file = new RandomAccessFile("products.dat","r");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"Error opening file");
        }

        setVisible(true);
    }

    private void searchProducts()
    {
        try
        {
            resultTA.setText("");

            String search = searchTF.getText().trim().toLowerCase();

            if(search.isEmpty())
            {
                JOptionPane.showMessageDialog(this,"Enter a search term");
                return;
            }

            long numRecords = file.length() / Product.RECORD_SIZE;

            for(int i=0;i<numRecords;i++)
            {
                file.seek(i * Product.RECORD_SIZE);

                String id = readFixedString(Product.ID_SIZE).trim();
                String name = readFixedString(Product.NAME_SIZE).trim();
                String desc = readFixedString(Product.DESC_SIZE).trim();
                double cost = file.readDouble();

                if(name.toLowerCase().contains(search))
                {
                    resultTA.append(
                            "ID: " + id +
                                    " | Name: " + name +
                                    " | Description: " + desc +
                                    " | Cost: " + cost +
                                    "\n"
                    );
                }
            }

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"Search Error");
        }
    }

    private String readFixedString(int size) throws Exception
    {
        char[] chars = new char[size];

        for(int i=0;i<size;i++)
        {
            chars[i] = file.readChar();
        }

        return new String(chars);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> { RandProductSearch search = new RandProductSearch();});
    }
}