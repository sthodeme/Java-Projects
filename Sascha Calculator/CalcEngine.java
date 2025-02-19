import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.JLabel;

/**
 * Definiert die verschiedenen ActionListener für die Tasten.
 * Implementiert die Logik für den Taschenrechner.
 */
public class CalcEngine {
    // ActionListener für die Zifferntasten '0-9', '.' ( & 'Del' )
    private ActionListener digitListener;
    
    // ActionListener für die Operatoren "+", "-", "*", "/", "C" und "="
    private ActionListener operatorListener;

    private ActionListener allListener;

    // Zwischenspeicherung des Wertes im Display nach Druck auf eine der Operatorentasten.
    private Stack<String> inputNumberStack = new Stack<>();
    private Stack<String> operatorStack = new Stack<>();
    boolean clearDisplay = false;

    double firstNumber;
    double secondNumber;

    String memoryPlus;


    /**
     * Konstruktion der Engine mit einer Referenz auf das Hauptfenster. Über diese Referenz
     * der Inhalt des Eingabefeldes ausgelesen und beschrieben. Definiert die abstackten
     * Methoden der Listener-Objekte.
     *
     * @param calcWindow Referenz auf das Hauptfenster.
     */
    public CalcEngine(CalcWindow calcWindow) {
        // Weitere Ziffern werden im Display hinten angehängt.
        allListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel display = calcWindow.getDisplayField();
                String buttonInput = e.getActionCommand().toString();
                String operator = e.getActionCommand();
                double result = 0;
                switch (buttonInput) {
                    //capturing digits
                    case ".":
                        if (!display.getText().contains(".")) {
                            display.setText(display.getText() + buttonInput);
                        }
                        break;
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
                        if (clearDisplay) {
                            display.setText("");
                            clearDisplay = false;
                        }
                        display.setText(display.getText() + buttonInput);
                        break;
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                        operatorStack.push(e.getActionCommand());
                        firstNumber = Double.parseDouble(display.getText());
                        calcWindow.stackDisplay.setText(display.getText());
                        calcWindow.operatorDisplay.setText(operatorStack.peek());
                        inputNumberStack.push(display.getText());
                        operator = buttonInput;
                        clearDisplay = true;
                        /* 
                        calcWindow.stackDisplay.setText(display.getText());
                        calcWindow.operatorDisplay.setText(operatorStack.peek());
                        inputNumberStack.push(display.getText());
                        display.setText("0");
                        //System.out.println("I am in '+' with inputNumberStack size: " + inputNumberStack.size());
                        */
                        break;
                    case "AC":
                        display.setText("0");
                        inputNumberStack.clear();
                        operatorStack.clear();
                        System.out.println("inputNumberStack print loc-clear: " + inputNumberStack);
                        calcWindow.stackDisplay.setText("");
                        calcWindow.msTextDisplay.setText("");
                        calcWindow.msValueDisplay.setText("");
                        calcWindow.operatorDisplay.setText("");
                        break;
                    case "Del":
                        String text = display.getText();
                        if (text.length() == 1) {
                            display.setText("0");
                        }else {
                            display.setText(text.substring(0, text.length() - 1));
                        }
                        break;
                    case "+/-":
                        double temp = Double.parseDouble(display.getText());
                        display.setText(String.valueOf(temp * (-1)));
                        //operatorStack.pop();
                        break;
                        case "1/X":
                        result = 1 / Double.parseDouble(display.getText());
                        display.setText(Double.toString(result));
                        break;
                    case "X²":
                        double value = Double.valueOf(display.getText());
                        result = value * value;
                        System.out.println("result: " + result);
                        display.setText(Double.toString(result));
                        break;
                    case "√X":
                        result = Math.sqrt(Double.valueOf(display.getText()));
                        display.setText(Double.toString(result));
                        break;
                    case "%":
                        result = Double.valueOf(display.getText()) / 100;
                        display.setText(Double.toString(result));
                        break;
                    case "MS":
                        calcWindow.msTextDisplay.setText("MS: ");
                        memoryPlus = display.getText();
                        calcWindow.msValueDisplay.setText(memoryPlus);
                        //operatorStack.pop();
                        //memory = Double.parseDouble(display.getText());
                        break;
                    case "MR":
                        if (!memoryPlus.isEmpty()) {
                            display.setText(memoryPlus);
                            operatorStack.pop();
                            clearDisplay = true; 
                        }
                        //display.setText(String.valueOf(memory));
                        // Clear after recalling
                        break;
                    case "M+":
                        double tempValue = Double.parseDouble(memoryPlus) + Double.parseDouble(display.getText());
                        display.setText(String.valueOf(tempValue));
                        //operatorStack.pop();
                        break;
                    case "M-":
                        tempValue = Double.parseDouble(memoryPlus) - Double.parseDouble(display.getText());
                        display.setText(String.valueOf(tempValue));
                        //operatorStack.pop();
                        break;
                    case "MC":
                        //memoryPlus = "";
                        calcWindow.msTextDisplay.setText("");
                        calcWindow.msValueDisplay.setText("");
                        //operatorStack.pop();
                        break;
        
                    case "=":
                        //firstNumber = Double.parseDouble(inputNumberStack.pop());
                        //secondNumber = Double.parseDouble(display.getText());
                        secondNumber = Double.parseDouble(display.getText());
                        result = calculate(firstNumber, secondNumber, operator);
                        display.setText(String.valueOf(result));
                        calcWindow.stackDisplay.setText(display.getText());
                        inputNumberStack.empty();
                        firstNumber = 0;
                        operator = "";
                        clearDisplay = true;
                        break;
                    
                    default:
                }
            }
        };
    }
    /**
     * return action listener.
     * @return ActionListener
     */
    
    public ActionListener getAllListener(){
        return this.allListener;
    }

    private double calculate(double firstNumber, double secondNumber, String operator) {

        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                if (secondNumber == 0) {
                    System.out.println("Cannot divide by zero Error");
                    return 0; // Or throw an exception
                }
                return firstNumber / secondNumber;
            default:
                return 0; // Should not happen
        }
    }

}

