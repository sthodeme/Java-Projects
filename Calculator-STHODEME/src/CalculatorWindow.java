import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
@SuppressWarnings("serial")

public class CalculatorWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    JTextField inputTextField; //to access the content, even after the window run is over

	public CalculatorWindow() {
        this.setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 400);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        setLocationRelativeTo(null);
        setLayout(null);

        // set Resource 
        /*/
        CalcEngine calcEngine = new CalcEngine(this);
        Font displayFont = new Font("Ink Free", Font.BOLD, 30);
        Font digitsFont = new Font("Ink Free", Font.BOLD, 30);
        this.display = new JLabel();
        */
        
        //Jlabel to show first number while the second number is being entered
        JLabel firstNumberLabel = new JLabel("xxx");
        firstNumberLabel.setBounds(70, 5, 100, 20);
        firstNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(firstNumberLabel);

        //Jlabel to show the mathematical operator being used
        JLabel operatorLabel = new JLabel("+");
        operatorLabel.setBounds(180, 5, 30, 20);
        this.add(operatorLabel);

        //Jlabel to show the (first & ) second number being entered
        JLabel label01 = new JLabel("Input:");
        int xPos = 5, yPos = 5, xWidth = 50, yHeight = 50;

        label01.setBounds(5, 25, 50, 20);
        this.add(label01);

        //JTextField - to show the (first & ) second number being entered
        JTextField inputTextField = new JTextField();
        inputTextField.setBounds(60, 25, 150, 20);
        inputTextField.setBackground(Color.YELLOW);
        inputTextField.setHorizontalAlignment(JTextField.RIGHT);
        inputTextField.setEditable(false); 
        inputTextField.setOpaque(true);
        this.add(inputTextField);
        /*/
        ActionListener dl = calcEngine.getDigitListener();
        ActionListener ol = calcEngine.getOperatorListener();
        */
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
     
        //JButton "0-9", '.' and '+/-'
        JButton[] numberButtons = new JButton[12];

        //JButton  array initialization for numbers 0-9
        for (int i = 0; i < 10; i++){
            numberButtons[i] = new JButton(String.valueOf(i));
        }
        //JButton  array initialization for '.' and '+/-' buttons
        numberButtons[10] = new JButton(".");
        numberButtons[11] = new JButton("+/-");
        //JButton "0-9", "." & "+/-" positioning in JFrame
        // (this is required, when no layout manager is used)
        numberButtons[0].setBounds(xPos, 80, xWidth, yHeight);
        numberButtons[1].setBounds(60, 80, xWidth, yHeight);
        numberButtons[2].setBounds(115, 80, xWidth, yHeight);
        numberButtons[3].setBounds(170, 80, xWidth, yHeight);
        numberButtons[4].setBounds(xPos, 135, xWidth, yHeight);
        numberButtons[5].setBounds(60, 135, xWidth, yHeight);
        numberButtons[6].setBounds(115, 135, xWidth, yHeight);
        numberButtons[7].setBounds(170, 135, xWidth, yHeight);
        numberButtons[8].setBounds(xPos, 195, xWidth, yHeight);
        numberButtons[9].setBounds(60, 195, xWidth, yHeight);
        numberButtons[10].setBounds(115, 195, xWidth, yHeight);
        numberButtons[11].setBounds(170, 195, xWidth, yHeight);


        //Add all number buttons to the frame
        for (int i = 0; i < 12; i++){
            this.add(numberButtons[i]);
        }

        //JButton negative "(-)"
        JButton negButton = new JButton("+/-");
        negButton.setBounds(170, 195, xWidth, xWidth);
        this.add(negButton);

        // Function Buttons
         //JButton "."
         JButton pointButton = new JButton(".");
         pointButton.setBounds(115, 195, xWidth, yHeight);

        //JButton '+' operation
        JButton addButton = new JButton("+");
        addButton.setBounds(5, 255, xWidth, yHeight);

        //JButton '-' operation
        JButton subButton = new JButton("-");
        subButton.setBounds(60, 255, xWidth, yHeight);

        //JButton 'x' operation
        JButton mulButton = new JButton("x");
        mulButton.setBounds(115, 255, xWidth, yHeight);

        //JButton '%' operation
        JButton divButton = new JButton("/");
        divButton.setBounds(170, 255, xWidth, yHeight);

        //JButton to calculate the result
        JButton equalsToButton = new JButton("=");
        equalsToButton.setBounds(xPos, 325, xWidth, yHeight);
        
        //JButton to clear all text fields
        JButton deleteButton = new JButton("Del");
        deleteButton.setBounds(60, 325, xWidth, yHeight);

        //JButton to clear all text fields
        JButton clearButton = new JButton("Clr");
        clearButton.setBounds(115, 325, xWidth, yHeight);

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
                        //subButton.setEnabled(false);
                    }
                    if (e.getSource() == mulButton){
                        inputTextField.setText(inputTextField.getText() + "*");
                    }
                    if (e.getSource() == divButton){
                        inputTextField.setText(inputTextField.getText() + "/");
                    }

                    if (e.getSource() == equalsToButton){
                        double result = 0;
                        String input = inputTextField.getText();
                        String[] numbers = input.split("[+\\-*/]");
                        result = Double.parseDouble(numbers[0]);
                        //double num1 = Double.parseDouble(numbers[0]);
                        double num2 = Double.parseDouble(numbers[1]);
                        
                        if (input.contains("+")){
                            result = result + num2;
                        }
                        if (input.contains("-")){
                            result = result - num2;
                        }
                        if (input.contains("*")){
                            result = result * num2;
                        }
                        if (input.contains("/")){
                            result = result / num2;
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
        for (int i = 0; i < 11; i++){
            numberButtons[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    JButton button = (JButton) e.getSource();
                    inputTextField.setText(inputTextField.getText() + button.getText());
                }
            });
        }

        // Add action listener to the negative button
        negButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                double temp = Double.parseDouble(inputTextField.getText());
                inputTextField.setText(String.valueOf(temp * (-1)));
                
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
        /* 
        // Add action listener to the delete button
        deleteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String text = inputTextField.getText();
                inputTextField.setText(text.substring(0, text.length() - 1));
            }
        });
        */


        
        
        setVisible(true);
    }
}
