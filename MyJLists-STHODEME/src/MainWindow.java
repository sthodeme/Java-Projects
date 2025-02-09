import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.synth.ColorType;

import java.lang.String.*;
import java.util.stream.Collector;
import java.awt.Color;
import java.awt.Font;

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
        //labelText and its selection listener
        String[] labelText = {"One", "Two", "Three", "Four", "Five"};
        JList<String> textList = new JList<>(labelText);
        textList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        textList.setBounds(10, 70 , 80, 100);
        this.add(textList);

        textList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){
                    jlabel1.setText(textList.getSelectedValue());
                }
                
            }
        });
        

        //labelColor and its selection listener
        String[] textColor = {"Red", "Green", "Blue"};
        JList<String> colorList = new JList<>(textColor);
        colorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        colorList.setBounds(10, 200 , 80, 60);
        this.add(colorList);

        colorList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){
                    String selectedColor = colorList.getSelectedValue();
                    
                    Color color;
                    switch (selectedColor) {
                        case "Red":
                            color = Color.RED;
                            break;
                        case "Green":
                            color = Color.GREEN;
                            break;
                        case "Blue":
                            color = Color.BLUE;
                            break;
                        default:
                            color = Color.BLACK;
                            break;
                    }
                    jlabel1.setForeground(color);
                }
            }
        });

        //labelSize and its selection listener
        Integer[] textSize = {8, 10, 12, 14, 16, 18, 20};
        JList<Integer> textSizeList = new JList<>(textSize);
        JScrollPane textSizeListPane = new JScrollPane(textSizeList);
        textSizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        textSizeListPane.setBounds(10, 300 , 80, 100);
        this.add(textSizeListPane);

        //labelSizeList selection listener
        textSizeList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){
                    float fontSize = textSizeList.getSelectedValue();
                    Font jLebelTextFont = jlabel1.getFont();
                    Font jLebelTextNewFont = jLebelTextFont.deriveFont(fontSize);
                    jlabel1.setFont(jLebelTextNewFont);
                }
            }
        });

        










        this.setLayout(null);
        this.setVisible(true);
    }

}
