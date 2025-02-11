import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class EncodeDecodeWindow extends JFrame {
    public EncodeDecodeWindow() {
        this.setTitle("Caeser Cifer Encoder/Decoder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 370);
        setLocationRelativeTo(null);
        setLayout(null);

         //Jlabel for 'instruction'
         JLabel instructionLabel = new JLabel("Choose one of the following action:");
         instructionLabel.setBounds(5, 5, 200, 20);
         this.add(instructionLabel);

         //JList for Encoding/Decoding
        String[] options = {"Encode", "Decode"};
        JList<String> EncodeDecodeList = new JList<>(options);
        EncodeDecodeList.setBounds(5, 30, 100, 40);
        EncodeDecodeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.add(EncodeDecodeList);

        //Text preparation for 'inputLabel'
        String forInputLabel = "Enter text to       :";
        String forOutputLabel = " Encoded/Decoded Text:";
        ListSelectionListener selectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (EncodeDecodeList.getSelectedValue().equals("Encode")) {
                    forInputLabel = "Enter text to Encode:";
                    forInputLabel = "Encode Text:";
                } else {
                    forInputLabel = "Enter text to Decode:";
                    forInputLabel = "Decode Text:";
                }
            }
        };
        EncodeDecodeList.addListSelectionListener(selectionListener);

        //Jlabel for 'Key'
        JLabel keyLabel = new JLabel("Enter 'Key' value:");
        keyLabel.setBounds(5, 755, 100, 20);
        this.add(keyLabel);

        //JTextField - entered currency value
        JTextField keyField = new JTextField();
        keyField.setBounds(110, 75, 150, 20);
        keyField.setBackground(Color.WHITE);
        keyField.setEditable(true);
        this.add(keyField);

        //Jlabel
        JLabel inputLabel = new JLabel(forInputLabel);
        inputLabel.setBounds(5, 105, 200, 20);
        this.add(inputLabel);

        //JTextField - entered text for encoding/decoding
        JTextField inputTextField = new JTextField();
        inputTextField.setBounds(5, 125, 250, 50);
        inputTextField.setBackground(Color.WHITE);
        inputTextField.setEditable(true);
        this.add(inputTextField);

        //JButton to 'Perform Encoding/Decoding'
        JButton convertToDollarButton = new JButton("Perform Encoding/Decoding");
        convertToDollarButton.setBounds(5, 195, 200, 20);
        this.add(convertToDollarButton);

        //Jlabel
        JLabel outputLabel = new JLabel(forOutputLabel);
        outputLabel.setBounds(5, 230, 200, 20);
        this.add(outputLabel);

        

        //JTextField - Encode/Decode text
        JTextField outputTextField = new JTextField();
        outputTextField.setBounds(5, 250, 250, 50);
        outputTextField.setEditable(false);
        outputTextField.setBackground(Color.WHITE);
        this.add(outputTextField);

        
/* 
        //JButton 'to Baht' conversion
        JButton convertToBahtButton = new JButton("to Baht");
        convertToBahtButton.setBounds(120, 65, 100, 20);
        this.add(convertToBahtButton);
*/
      //JButton 'Clear all' conversion
        JButton clearAllButton = new JButton("Clear all");
        clearAllButton.setBounds(5, 310, 100, 20);
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

                if (e.getSource() == clearAllButton) {
                	inputTextField.setText("");
                	outputTextField.setText("");
                }
                
            }
        };
        
        convertToDollarButton.addActionListener(multipleActions);
        clearAllButton.addActionListener(multipleActions);

        setVisible(true);
    }
}
