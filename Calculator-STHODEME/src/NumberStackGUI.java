
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Deque;

public class NumberStackGUI extends JFrame {

    private JTextField inputField;
    private JTextArea outputArea;
    private Deque<Double> numberStack = new ArrayDeque<>();

    public NumberStackGUI() {
        setTitle("Number Stack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        JButton addButton = new JButton("Add Number");
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();

                try {
                    double number = Double.parseDouble(input); // Direct parsing
                    numberStack.push(number);
                    updateOutputArea();
                    inputField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(NumberStackGUI.this, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        setVisible(true);
    }



    private void updateOutputArea() {
        outputArea.setText("");
        for (Double number : numberStack) {
            outputArea.append(number + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberStackGUI());
    }
}
