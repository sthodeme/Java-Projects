import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.JLabel;

/**
 * captures all number inputs and operations through one ActionListener of the Buttons.
 * IImplements the logic of the Calculator.
 */
public class CalcEngine {

    // ActionListener for digits '0-9', '.'  & 'Del' & 'AC' & '+/-' & ....
    // ... '1/X' & 'X²' & '√X' & '%' & 'MS' & 'MR' & 'M+' & 'M-' & 'MC' & '=')
    private ActionListener allListener;

    // Intermidiate storage for numbers and operators
    private Stack<String> numberOperatorStack = new Stack<>();

    // decision if the display should be cleared or not
    boolean clearDisplay = true;

    // Variables/numbers storage for calculation
    double firstNumber;
    double secondNumber;

    // Temperary storage for memory (MS, MR, M+, M-) operations
    String memoryPlus;


    /**
     * Engine construction with reference to main window. Über diese Referenz
     * through this, the inputs and results are processed and returned.
     * Defines abstract menthods of the listener objects.
     *
     * @param calcWindow Reference to main window.
     */
    public CalcEngine(CalcWindow calcWindow) {
        
        allListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JLabel display = calcWindow.getDisplayField();
                String buttonInput = e.getActionCommand().toString();
                String operator = e.getActionCommand();
                double result = 0;
                System.out.println(numberOperatorStack);
                switch (buttonInput) {
                    //capturing digits
                    case ".":
                        // check point to avoid multiple decimal points
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
                        //Here we are checking if the stack is empty or not
                        if (numberOperatorStack.size() < 2) {
                            //if stack is empty, we push the number and operator
                            numberOperatorStack.push(display.getText());
                            numberOperatorStack.push(e.getActionCommand());
                            calcWindow.stackDisplay.setText(display.getText());
                            calcWindow.operatorDisplay.setText(numberOperatorStack.peek());
                            firstNumber = Double.parseDouble(display.getText());
                            //display.setText("0");
                            clearDisplay = true;
                        } else {
                            //if stack is not empty, we pop the number (as first number/operand) and operator
                            operator = numberOperatorStack.pop();
                            
                            //initialize the second number with the current display value
                            secondNumber = Double.parseDouble(display.getText());
                            //calculate the result by calling the calculate method
                            System.out.println("firstNumber: " + firstNumber);
                            System.out.println("secondNumber: " + secondNumber);
                            System.out.println("operator: " + operator);
                            result = calculate(firstNumber, secondNumber, operator);
                            display.setText(String.valueOf(result));
                            firstNumber = Double.parseDouble(Double.toString(result));
                            //push the result to the stackDisplay
                            //These are the intermediate results displayed on the stackDisplay, over the main display
                            calcWindow.stackDisplay.setText(Double.toString(result));
                            calcWindow.operatorDisplay.setText(operator);
                            //push the result to the stack
                            numberOperatorStack.push(Double.toString(result));
                            numberOperatorStack.push(operator);
                            //clear the display flag to allow new input
                            clearDisplay = true;
                        }
                        System.out.println(numberOperatorStack);
                        System.out.println("firstNumber: " + firstNumber);
                        System.out.println("secondNumber: " + secondNumber);
                        System.out.println("operator: " + operator);
                        break;
                    case "AC":
                        //reset the all display fields and clear the stack
                        display.setText("0");
                        numberOperatorStack.clear();
                        calcWindow.stackDisplay.setText("");
                        calcWindow.msTextDisplay.setText("");
                        calcWindow.msValueDisplay.setText("");
                        calcWindow.operatorDisplay.setText("");
                        break;
                    case "Del":
                        //remove one right most digit from the display
                        String text = display.getText();
                        if (text.length() == 1) {
                            display.setText("0");
                        }else {
                            display.setText(text.substring(0, text.length() - 1));
                        }
                        break;
                    case "+/-":
                        //change the sign of the number
                        double temp = Double.parseDouble(display.getText());
                        display.setText(String.valueOf(temp * (-1)));
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
                        //calculate the percentage of the number
                        result = Double.valueOf(display.getText()) / 100;
                        display.setText(Double.toString(result));
                        break;
                    case "MS":
                        //store the current display value in the memory 'memoryPlus'
                        calcWindow.msTextDisplay.setText("MS: ");
                        memoryPlus = display.getText();
                        calcWindow.msValueDisplay.setText(memoryPlus);
                        break;
                    case "MR":
                        //recall the value from the memory 'memoryPlus' and display it
                        if (!memoryPlus.isEmpty()) {
                            display.setText(memoryPlus);
                            clearDisplay = true; 
                        }
                        break;
                    case "M+":
                        //add the current display value to the memory 'memoryPlus'
                        double tempValue = Double.parseDouble(memoryPlus) + Double.parseDouble(display.getText());
                        display.setText(String.valueOf(tempValue));
                        break;
                    case "M-":
                        //subtract the current display value from the memory 'memoryPlus'
                        tempValue = Double.parseDouble(memoryPlus) - Double.parseDouble(display.getText());
                        display.setText(String.valueOf(tempValue));
                        break;
                    case "MC":
                        //reset the memory 'memoryPlus'
                        calcWindow.msTextDisplay.setText("");
                        calcWindow.msValueDisplay.setText("");
                        break;
        
                    case "=":
                        //calculation based on two numbers and an operator
                        //return the result to the display
                        secondNumber = Double.parseDouble(display.getText());
                        result = calculate(firstNumber, secondNumber, operator);
                        display.setText(String.valueOf(result));
                        System.out.println(("firstNumber: " + firstNumber));
                        System.out.println(("operator: " + operator));
                        System.out.println(("secondNumber: " + secondNumber));
                        System.out.println("result from =: " + result);
                        calcWindow.stackDisplay.setText(display.getText());
                        firstNumber = 0;
                        //flag to clear the display (before next input)
                        //operator = "";
                        clearDisplay = true;
                        break;
                    
                    default:
                }
            }
        };
    }
    /**
     * Returns the ActionListener object.
     * @return ActionListener
     */
    public ActionListener getAllListener(){
        return this.allListener;
    }

    /**
     * r* Method to preform +, -, * & / operation.
     */
    private double calculate(double firstNumber, double secondNumber, String operator) {

        switch (operator) {
            case "+":
                System.out.println(("i am in calculate function, with case: +"));
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                if (secondNumber == 0) {
                    System.out.println("Cannot divide by zero Error");
                    return 0; 
                }
                return firstNumber / secondNumber;
            default:
                return 0; 
        }
    }

}


