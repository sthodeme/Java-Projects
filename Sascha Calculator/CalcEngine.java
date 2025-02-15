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
                String input = display.getText() + e.getActionCommand().toString();
                // Display und inputStack löschen wenn nur ein Wert auf dem Stack liegt.
                if (inputStack.size() == 1) {
                    display.setText(input);
                    inputStack.clear();

                } else {
                    display.setText(input);

                }
            }
        };

        // Operatoren behandeln.
        operatorListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel display = calcWindow.getDisplayField();
                String input = display.getText();
                String operator = e.getActionCommand();
                System.out.println("Stack vorher: " + inputStack);
                // Operatortaste "C" - Display und inputStack löschen und Listener verlassen.
                if ("C".equals(operator)) {
                    display.setText("0");
                    inputStack.clear();
                    calcWindow.stackDisplay.setText(display.getText());
                    System.out.println(inputStack);
                    return;
                }

                // Was beim Druck auf die Operatorentasten geschieht hängt vom Zustand des inputBuffer ab.
                // Buffergröße 0, Display Inhalt auf den Stack legen. Außer bei "=" den Operator ebenfalls auf den Stack.
                System.out.println("Stack size: " +inputStack.size());
                if (inputStack.size() == 0) {
                    inputStack.push(display.getText());
                    System.out.println(inputStack);
                    calcWindow.stackDisplay.setText(inputStack.peek());
                    double result = 0;
                    if (!"=".equals(operator)) {
                        //string temp =
                        switch (operator) {
                            case "1/X":
                                result = 1 / Double.valueOf(inputStack.peek());
                                inputStack.push(Double.toString(result));
                                calcWindow.stackDisplay.setText(String.valueOf(inputStack));
                                display.setText(Double.toString(result));
                                inputStack.pop();
                            case "X2":
                                double value = Double.valueOf(inputStack.peek());
                                result = value * value;
                                System.out.println("i am in X2");
                                inputStack.push(Double.toString(result));
                                calcWindow.stackDisplay.setText(String.valueOf(inputStack));
                                display.setText(Double.toString(result));
                                inputStack.pop();
                            case "XR":
                                result = Math.sqrt(Double.valueOf(inputStack.peek()));
                                inputStack.push(Double.toString(result));
                                calcWindow.stackDisplay.setText(String.valueOf(inputStack));
                                display.setText(inputStack.peek());
                                inputStack.pop();
                            default:
                                inputStack.push(operator);
                        }
                    }
                    //display.setText("0");
                    calcWindow.stackDisplay.setText(String.valueOf(result));
                    System.out.println(inputStack);
                    return;
                }

                // Was beim Druck auf die Operatorentasten geschieht hängt vom Zustand des inputBuffer ab.
                // Buffergröße 1, Außer bei "=" den Operator ebenfalls auf den Stack.

                if (inputStack.size() == 1) {
                    if (!"=".equals(operator)) {
                        inputStack.push(display.getText());
                        calcWindow.stackDisplay.setText(inputStack.peek());
                        if (!"=".equals(operator)) {
                            double result;
                            //string temp =
                            switch (operator) {
                                case "1/X":
                                    result = 1 / Double.valueOf(inputStack.pop());
                                    inputStack.push(Double.toString(result));
                                    calcWindow.stackDisplay.setText(String.valueOf(result));
                                    display.setText(inputStack.peek());
                                case "X2":
                                    result = Math.pow(Double.valueOf(inputStack.pop()), 2);
                                    inputStack.push(Double.toString(result));
                                    calcWindow.stackDisplay.setText(String.valueOf(result));
                                    display.setText(inputStack.peek());
                                case "XR":
                                    result = Math.sqrt(Double.valueOf(inputStack.pop()));
                                    inputStack.push(Double.toString(result));
                                    calcWindow.stackDisplay.setText(String.valueOf(result));
                                    display.setText(inputStack.peek());
                                default:
                                    inputStack.push(operator);
                            }

                            display.setText(inputStack.peek());

                        }
                        display.setText("0");
                        System.out.println(inputStack);
                        calcWindow.stackDisplay.setText(String.valueOf(inputStack));
                        return;
                    }

                    // Bei 2 Einträgen liegt ein Wert und ein Operator auf dem Stack.
                    // Wert und Operator aus dem Stack holen, Berechnung ausführen und Ergebnis auf den Stack und ins Display legen.

                    if (inputStack.size() == 2) {
                        String toOperate = inputStack.pop();
                        double valueA = Double.valueOf(inputStack.pop());
                        double valueB = Double.valueOf(display.getText());
                        String result = "0";
                        switch (toOperate) {
                            case "+":
                                result = Double.toString(valueA + valueB);
                                break;
                            case "-":
                                result = Double.toString(valueA - valueB);
                                break;
                            case "*":
                                result = Double.toString(valueA * valueB);
                                break;
                            case "/":
                                result = Double.toString(valueA / valueB);
                                break;
                            case "%":
                                result = Double.toString(valueA % valueB);

                        }
                        inputStack.push(result);
                        // Außer "=" die operator auch wieder auf den inputStack
                        if (!"=".equals(operator)) {
                            inputStack.push(operator);
                        }
                        display.setText(result);
                        System.out.println(inputStack);

                    }
                }
            }

        };
    }
        /**
         * Liefert den digitListener zurück.
         * @return ActionListener
         */

        public ActionListener getDigitListener () {
            return this.digitListener;
        }

        /**
         * Liefert den operatorListener zurück.
         * @return ActionListener
         */
        public ActionListener getOperatorListener () {
            return this.operatorListener;
        }
}