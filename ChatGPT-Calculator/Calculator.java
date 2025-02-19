
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {

    private JTextField display;
    private JButton[] buttons;
    private String[] buttonLabels = {
            "7", "8", "9", "/", "MS",
            "4", "5", "6", "*", "MR",
            "1", "2", "3", "-", "", // Empty button for spacing
            "0", ".", "=", "+"
    };
    private double operand1 = 0;
    private String operator = "";
    private boolean clearDisplay = false;
    private double memory = 0; // Variable to store memory value

    public Calculator() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new BorderLayout());
        this.setResizable(false);

        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 5)); // 4x5 grid
        buttons = new JButton[20]; // 20 buttons

        for (int i = 0; i < 19; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();

        switch (buttonPressed) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case ".":
                if (clearDisplay) {
                    display.setText("");
                    clearDisplay = false;
                }
                display.setText(display.getText() + buttonPressed);
                break;

            case "+":
            case "-":
            case "*":
            case "/":
                operand1 = Double.parseDouble(display.getText());
                operator = buttonPressed;
                clearDisplay = true;
                break;

            case "=":
                double operand2 = Double.parseDouble(display.getText());
                double result = calculate(operand1, operand2, operator);
                display.setText(String.valueOf(result));
                operand1 = 0;
                operator = "";
                clearDisplay = true;
                break;

            case "MS":  // Memory Store
                memory = Double.parseDouble(display.getText());
                break;

            case "MR":  // Memory Recall
                display.setText(String.valueOf(memory));
                clearDisplay = true; // Clear after recalling
                break;

            default:
                // Handle invalid input (optional)
                break;
        }

    
    }

    private double calculate(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    JOptionPane.showMessageDialog(this, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                    return 0; // Or throw an exception
                }
                return operand1 / operand2;
            default:
                return 0; // Should not happen
        }
    }
}
