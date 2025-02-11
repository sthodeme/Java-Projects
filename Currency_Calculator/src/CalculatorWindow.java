import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CalculatorWindow extends JFrame {
    public CalculatorWindow() {
        this.setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        //Jlabel
        JLabel label01 = new JLabel("Betrag in Euro:");
        label01.setBounds(5, 5, 100, 20);
        this.add(label01);

        //JTextField - entered currency value
        JTextField inputTextField = new JTextField();
        inputTextField.setBounds(110, 5, 150, 20);
        inputTextField.setBackground(Color.YELLOW);
        inputTextField.setEditable(true);
        this.add(inputTextField);

        //Jlabel
        JLabel label02 = new JLabel("Betrag in       :");
        label02.setBounds(5, 35, 130, 20);
        this.add(label02);

        //JTextField - converted currency output
        JTextField outputTextField = new JTextField();
        outputTextField.setBounds(110, 35, 150, 20);
        outputTextField.setEditable(false);
        outputTextField.setBackground(Color.GREEN);
        this.add(outputTextField);

        //JButton 'to USD' conversion
        JButton convertToDollarButton = new JButton("to USD");
        convertToDollarButton.setBounds(5, 65, 100, 20);
        this.add(convertToDollarButton);

        //JButton 'to Baht' conversion
        JButton convertToBahtButton = new JButton("to Baht");
        convertToBahtButton.setBounds(120, 65, 100, 20);
        this.add(convertToBahtButton);
        
      //JButton 'Clear all' conversion
        JButton clearAllButton = new JButton("Clear all");
        clearAllButton.setBounds(60, 95, 100, 20);
        this.add(clearAllButton);

        ActionListener multipleActions = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (e.getSource() == convertToDollarButton){    
                    double euro = Double.parseDouble(inputTextField.getText());
                    double dollar = euro * 1.1;
                    label02.setText("Betrag in USD:");
                    outputTextField.setText(String.valueOf(dollar));
                }
                if (e.getSource() == convertToBahtButton){
                    double euro = Double.parseDouble(inputTextField.getText());
                    double baht = euro * 35;
                    label02.setText("Betrag in Baht:");
                    outputTextField.setText(String.valueOf(baht));
                }
                if (e.getSource() == clearAllButton) {
                	inputTextField.setText("");
                	outputTextField.setText("");
                }
                
            }
        };
        
        convertToDollarButton.addActionListener(multipleActions);
        convertToBahtButton.addActionListener(multipleActions);
        clearAllButton.addActionListener(multipleActions);

        setVisible(true);
    }
}
