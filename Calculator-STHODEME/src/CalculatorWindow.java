import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CalculatorWindow extends JFrame {
    private static final long serialVersionUID = 1L;

	public CalculatorWindow() {
        this.setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(230, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        /* 
        //Jlabel to show first number while the second number is being entered
        JLabel firstNumberLabel = new JLabel("xxx");
        firstNumberLabel.setBounds(70, 5, 100, 20);
        firstNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(firstNumberLabel);

        //Jlabel to show the mathematical operator being used
        JLabel operatorLabel = new JLabel("+");
        operatorLabel.setBounds(180, 5, 30, 20);
        this.add(operatorLabel);
        */

        //Jlabel to show the (first & ) second number being entered
        JLabel label01 = new JLabel("Input:");
        label01.setBounds(5, 25, 50, 20);
        this.add(label01);

        //JTextField - to show the (first & ) second number being entered
        JTextField inputTextField = new JTextField();
        inputTextField.setBounds(60, 25, 150, 20);
        inputTextField.setBackground(Color.YELLOW);
        inputTextField.setEditable(false); 
        this.add(inputTextField);

        //Jlabel
        JLabel label02 = new JLabel("Output:");
        label02.setBounds(5, 45, 50, 20);
        this.add(label02);

        //JTextField - calculation result output
        JTextField outputTextField = new JTextField();
        outputTextField.setBounds(60, 45, 150, 20);
        outputTextField.setEditable(false);
        outputTextField.setBackground(Color.GREEN);
        this.add(outputTextField);
     
        //JButton  array for numbers 0-9
        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++){
            numberButtons[i] = new JButton(String.valueOf(i));
        }
        //JButton "0-9" positioning in JFrame
        // (this is required, when no layout manager is used)
        numberButtons[0].setBounds(5, 80, 50, 50);
        numberButtons[1].setBounds(60, 80, 50, 50);
        numberButtons[2].setBounds(115, 80, 50, 50);
        numberButtons[3].setBounds(170, 80, 50, 50);
        numberButtons[4].setBounds(5, 135, 50, 50);
        numberButtons[5].setBounds(60, 135, 50, 50);
        numberButtons[6].setBounds(115, 135, 50, 50);
        numberButtons[7].setBounds(170, 135, 50, 50);
        numberButtons[8].setBounds(5, 195, 50, 50);
        numberButtons[9].setBounds(60, 195, 50, 50);


        //Add all number buttons to the frame
        for (int i = 0; i < 10; i++){
            this.add(numberButtons[i]);
        }

        //JButton negative "(-)"
        JButton minusButton = new JButton("(-)");
        minusButton.setBounds(170, 195, 50, 50);
        this.add(minusButton);

        // Function Buttons
         //JButton "."
         JButton pointButton = new JButton(".");
         pointButton.setBounds(115, 195, 50, 50);

        //JButton '+' operation
        JButton addButton = new JButton("+");
        addButton.setBounds(5, 255, 50, 25);

        //JButton '-' operation
        JButton subButton = new JButton("-");
        subButton.setBounds(60, 255, 50, 25);

        //JButton 'x' operation
        JButton mulButton = new JButton("x");
        mulButton.setBounds(115, 255, 50, 25);

        //JButton '%' operation
        JButton divButton = new JButton("/");
        divButton.setBounds(170, 255, 50, 25);

        //JButton to calculate the result
        JButton equalsToButton = new JButton("=");
        equalsToButton.setBounds(85, 290, 50, 25);
        
        //JButton to clear all text fields
        JButton deleteButton = new JButton("Del");
        deleteButton.setBounds(40, 335, 60, 20);

        //JButton to clear all text fields
        JButton clearButton = new JButton("Clear all");
        clearButton.setBounds(120, 335, 80, 20);

        // An Array of Function Buttons
        JButton[] functionButtons = new JButton[8];
        functionButtons[0] = pointButton;
        functionButtons[1] = addButton;
        functionButtons[2] = subButton;
        functionButtons[3] = mulButton;
        functionButtons[4] = divButton;
        functionButtons[5] = equalsToButton;
        functionButtons[6] = deleteButton;
        functionButtons[7] = clearButton;
        // Add all function buttons to the frame
        for (int i = 0; i < 8; i++){
            this.add(functionButtons[i]);
        }


        for (int i = 0; i < 8; i++){
            functionButtons[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    if (e.getSource() == pointButton){
                        inputTextField.setText(inputTextField.getText() + ".");
                    }
                    if (e.getSource() == addButton){
                        inputTextField.setText(inputTextField.getText() + "+");
                    }
                    if (e.getSource() == subButton){
                        inputTextField.setText(inputTextField.getText() + "-");
                    }
                    if (e.getSource() == mulButton){
                        inputTextField.setText(inputTextField.getText() + "*");
                    }
                    if (e.getSource() == divButton){
                        inputTextField.setText(inputTextField.getText() + "/");
                    }
                    if (e.getSource() == equalsToButton){
                        String input = inputTextField.getText();
                        String[] numbers = input.split("[+\\-*/]");
                        double num1 = Double.parseDouble(numbers[0]);
                        double num2 = Double.parseDouble(numbers[1]);
                        double result = 0;
                        if (input.contains("+")){
                            result = num1 + num2;
                        }
                        if (input.contains("-")){
                            result = num1 - num2;
                        }
                        if (input.contains("*")){
                            result = num1 * num2;
                        }
                        if (input.contains("/")){
                            result = num1 / num2;
                        }
                        outputTextField.setText(String.valueOf(result));
                    }
                    if (e.getSource() == deleteButton){
                        String text = inputTextField.getText();
                        inputTextField.setText(text.substring(0, text.length() - 1));
                    }
                    if (e.getSource() == clearButton){
                        inputTextField.setText("");
                        outputTextField.setText("");
                    }
                }
            });
        }

        // Add action listener to all number buttons
        for (int i = 0; i < 10; i++){
            numberButtons[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    JButton button = (JButton) e.getSource();
                    inputTextField.setText(inputTextField.getText() + button.getText());
                }
            });
        }

        // Add action listener to the minus button
        minusButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                inputTextField.setText(inputTextField.getText() + "-");
            }
        });

        // Add action listener to the clear button
        clearButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                inputTextField.setText("");
                outputTextField.setText("");
            }
        });

        // Add action listener to the delete button
        deleteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String text = inputTextField.getText();
                inputTextField.setText(text.substring(0, text.length() - 1));
            }
        });


        
        
        setVisible(true);
    }
}
