import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

//import com.ibm.j9ddr.vm29.pointer.generated.statPointer;

public class MainWindow extends JFrame{
	
    private static final long serialVersionUID = 1L;

    public MainWindow(){

        //JFrame parameters
        this.setTitle("MY JList Practice Window");
        this.setSize(300, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //LLables
        JLabel jlabel1 = new JLabel("This text will be replaced by list selection");
        jlabel1.setBounds(10, 10, 300, 15);
        this.add(jlabel1);

        //JText
        JTextField textField01 = new JTextField("Select one item from each list");
        textField01.setBounds(10, 50, 200, 15);
        this.add(textField01);
        
        //JLists
        String[] labelText = {"One", "Two", "Three", "Four", "Five"};
        JList<String> textlist = new JList<>(labelText);
        textlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        textlist.setBounds(10, 70 , 80, 100);
        this.add(textlist);

        String[] textColor = {"Red", "Green", "Blue"};
        JList<String> colorList = new JList<>(textColor);
        colorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        colorList.setBounds(10, 200 , 80, 60);
        this.add(colorList);

        Integer[] textSize = {8, 10, 12, 14, 16, 18, 20};
        JList<Integer> textSizeList = new JList<>(textSize);
        JScrollPane textSizeListPane = new JScrollPane(textSizeList);
        textSizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        textSizeListPane.setBounds(10, 300 , 80, 100);
        this.add(textSizeListPane);

        










        this.setLayout(null);
        this.setVisible(true);
    }

}
