import org.w3c.dom.ls.LSOutput;

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

    // Zwischenspeicherung des Wertes im Display nach Druck auf eine der Operatorentasten.
    private Stack<String> inputStack = new Stack<>();

    double firstNumber;
    double secondNumber;

    String memoryPlus;

    private ActionListener actionListener;

    /**
     * Konstruktion der Engine mit einer Referenz auf das Hauptfenster. Über diese Referenz
     * der Inhalt des Eingabefeldes ausgelesen und beschrieben. Definiert die abstackten
     * Methoden der Listener-Objekte.
     *
     * @param calcWindow Referenz auf das Hauptfenster.
     */
    public CalcEngine(CalcWindow calcWindow) {
        // Weitere Ziffern werden im Display hinten angehängt.
        digitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel display = calcWindow.getDisplayField();
                String buttonInput = e.getActionCommand().toString();
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
                        display.setText(display.getText() + buttonInput);
                        break;
                    default:
                }
            }
        };

        // Operatoren behandeln.
        operatorListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel display = calcWindow.getDisplayField();
                String buttonInput = e.getActionCommand().toString();
                String operator = e.getActionCommand();

                // Operatortaste "C" - Display und inputStack löschen und Listener verlassen.
                if ("C".equals(buttonInput)) {
                    display.setText("");
                    inputStack.clear();
                    System.out.println("inputStack print loc-clear: " + inputStack);
                    calcWindow.stackDisplay.setText("");
                    calcWindow.msDisplay.setText("");
                    calcWindow.operatorDisplay.setText("");
                    return;
                }

                if ("Del".equals(buttonInput)){
                    String text = display.getText();
                    display.setText(text.substring(0, text.length() - 1));
                }
                if("+/-".equals(buttonInput)) {
                    double temp = Double.parseDouble(display.getText());
                    display.setText(String.valueOf(temp * (-1)));
                }

                // Was beim Druck auf die Operatorentasten geschieht hängt vom Zustand des inputBuffer ab.
                // Buffergröße 0, Display Inhalt auf den Stack legen. Außer bei "=" den Operator ebenfalls auf den Stack.
                System.out.println("Stack size: " + inputStack.size());
                //display.setText(inputStack.peek());
                double result = 0;
                System.out.println("operator: " + e.getActionCommand());
                if (!"=".equals(buttonInput)) {
                    //firstNumber = Double.parseDouble(display.getText().toString());
                    // firstNumber, if entered any, added to inputStack
                    if (!display.getText().isEmpty()) {
                        switch (buttonInput) {
                            case "1/X":
                                result = 1 / Double.parseDouble(display.getText());
                                display.setText(Double.toString(result));
                                break;
                            case "X²":
                                double value = Double.valueOf(display.getText());
                                result = value * value;
                                display.setText(Double.toString(result));
                                break;
                            case "√X":
                                result = Math.sqrt(Double.valueOf(display.getText()));
                                calcWindow.getDisplayField().setText(Double.toString(result));
                                break;
                            case "%":
                                result = Double.valueOf(display.getText()) / 100;
                                calcWindow.getDisplayField().setText(Double.toString(result));
                                break;
                            case "MS":
                                memoryPlus = "MS: " + display.getText();
                                calcWindow.msDisplay.setText(memoryPlus);
                                break;
                            case "MR":
                                if(!memoryPlus.isEmpty()) {
                                    display.setText(memoryPlus);
                                }
                                break;
                            case "M+":
                                double tempValue = Double.parseDouble(memoryPlus) + Double.parseDouble(display.getText());
                                display.setText(String.valueOf(tempValue));
                                break;
                            case "M-":
                                tempValue = Double.parseDouble(memoryPlus) - Double.parseDouble(display.getText());
                                display.setText(String.valueOf(tempValue));
                                break;
                            case "MC":
                                memoryPlus = "";
                                calcWindow.msDisplay.setText((memoryPlus));
                                break;

                            case "+":
                                calcWindow.operatorDisplay.setText(operator);
                                if (inputStack.size() == 0) {
                                    inputStack.push(display.getText());
                                    display.setText("0");
                                    calcWindow.stackDisplay.setText(inputStack.peek());
                                }
                                if (inputStack.size() == 1) {
                                    firstNumber = Double.parseDouble(inputStack.pop());
                                    secondNumber = Double.parseDouble(display.getText());
                                    result = firstNumber + secondNumber;
                                    display.setText("0");
                                    calcWindow.stackDisplay.setText(String.valueOf(result));
                                    inputStack.push(String.valueOf(result));
                                }
                                break;
                            case "-":
                                calcWindow.operatorDisplay.setText(operator);
                                if (inputStack.size() == 0) {
                                    inputStack.push(display.getText());
                                    display.setText("0");
                                    calcWindow.stackDisplay.setText(inputStack.peek());
                                }
                                if (inputStack.size() == 1) {
                                    firstNumber = Double.parseDouble(inputStack.pop());
                                    secondNumber = Double.parseDouble(display.getText());
                                    result = firstNumber - secondNumber;
                                    display.setText("0");
                                    calcWindow.stackDisplay.setText(String.valueOf(result));
                                    inputStack.push(String.valueOf(result));

                                }
                                break;
                            case "*":
                                calcWindow.operatorDisplay.setText(operator);
                                if (inputStack.size() == 0) {
                                    inputStack.push(display.getText());
                                    display.setText("0");
                                    calcWindow.stackDisplay.setText(inputStack.peek());
                                }
                                if (inputStack.size() == 1) {
                                    firstNumber = Double.parseDouble(inputStack.pop());
                                    secondNumber = Double.parseDouble(display.getText());
                                    result = firstNumber * secondNumber;
                                    display.setText("0");
                                    calcWindow.stackDisplay.setText(String.valueOf(result));
                                    inputStack.push(String.valueOf(result));
                                }
                                break;
                            case "/":
                                calcWindow.operatorDisplay.setText(operator);
                                if (inputStack.size() == 0) {
                                    inputStack.push(display.getText());
                                    display.setText("0");
                                    calcWindow.stackDisplay.setText(inputStack.peek());
                                }
                                if (inputStack.size() == 1) {
                                    firstNumber = Double.parseDouble(inputStack.pop());
                                    secondNumber = Double.parseDouble(display.getText());
                                    result = firstNumber / secondNumber;
                                    display.setText("0");
                                    calcWindow.stackDisplay.setText(String.valueOf(result));
                                    inputStack.push(String.valueOf(result));
                                }
                                break;
                            case "=":
                                calcWindow.operatorDisplay.setText(operator);
                                if (inputStack.size() == 0) {
                                    System.out.println();
                                }
                                if (inputStack.size() == 1) {
                                    firstNumber = Double.parseDouble(inputStack.pop());
                                    secondNumber = Double.parseDouble(display.getText());
                                    switch (operator){
                                        case "+":
                                            result = firstNumber + secondNumber;
                                            display.setText(String.valueOf(result));
                                            inputStack.empty();
                                        case "-":
                                            result = firstNumber - secondNumber;
                                            display.setText(String.valueOf(result));
                                            inputStack.empty();
                                        case "*":
                                            result = firstNumber * secondNumber;
                                            display.setText(String.valueOf(result));
                                            inputStack.empty();
                                        case "/":
                                            result = firstNumber / secondNumber;
                                            display.setText(String.valueOf(result));
                                            inputStack.empty();
                                    }
                                }
                            default:
                                //inputStack.push(calcWindow.getDisplayField().getText());
                                //inputStack.push(e.getActionCommand());

                        }
                    }
                    //display.setText(inputStack.peek());
                    //System.out.println("inputStack print loc-0: " + inputStack);
                    //return;
                }
                //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

                //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            }
        };
    }
        /**
         * Liefert den digitListener zurück.
         * @return ActionListener
         */

        public ActionListener getDigitListener(){
            return this.digitListener;
        }
        public ActionListener getOperatorListener(){
            return this.operatorListener;
        }



}